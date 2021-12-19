/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package eg.gov.iti.cafetria.service.implementation;

import eg.gov.iti.cafetria.dto.ItemDTO;
import eg.gov.iti.cafetria.dto.OrderWebSocket;
import eg.gov.iti.cafetria.enums.PaymentType;
import eg.gov.iti.cafetria.model.dal.dao.CustomerRepository;
import eg.gov.iti.cafetria.model.dal.dao.ItemRepository;
import eg.gov.iti.cafetria.model.dal.dao.OfferRepository;
import eg.gov.iti.cafetria.model.dal.dao.OrderContainingItemsRepository;
import eg.gov.iti.cafetria.model.dal.dao.OrderRepository;
import eg.gov.iti.cafetria.model.dal.domain.Customer;
import eg.gov.iti.cafetria.model.dal.domain.Item;
import eg.gov.iti.cafetria.model.dal.domain.Offer;
import eg.gov.iti.cafetria.model.dal.domain.Order;
import eg.gov.iti.cafetria.model.dal.domain.OrderContainingItems;
import eg.gov.iti.cafetria.model.dal.domain.User;
import eg.gov.iti.cafetria.service.OrderService;
import java.time.Instant;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author OsamaPC
 */
@Service
public class OrderImpl implements OrderService {

    @Autowired
    OrderRepository orderRepository;
    @Autowired
    ItemRepository itemRepository;
    @Autowired
    OfferRepository offerRepository;
    @Autowired
    CustomerRepository customerRepository;
    @Autowired
    OrderContainingItemsRepository containingItemsRepository;

    @Autowired
    private SimpMessagingTemplate template;

    @Override
    public Order findOne(Integer id) {
        return orderRepository.getOne(id);
    }

    @Override
    @Transactional(readOnly = true)
    public Iterable<Order> findAll() {
        return orderRepository.findAll();
    }

    @Override
    @Transactional
    public void delete(Integer id) {
        orderRepository.delete(id);
    }

    @Override
    @Transactional
    public void insert(Order order) {
        order.setOrderTime(Date.from(Instant.now()));
        int total = calculateTotal(order);
        order.setTotalCost(total);
        if (order.getOrderContainingItemsCollection() != null) {
            for (OrderContainingItems containingItems : order.getOrderContainingItemsCollection()) {
                Item item = containingItems.getItem();
                int newQuantity = item.getQuantity() - containingItems.getQuantity();
                item.setQuantity(newQuantity);
                Item newItem = itemRepository.save(item);
                containingItems.setItem(newItem);
            }
        }
        List<OrderContainingItems> containingitems = null;
        if (order.getOrderContainingItemsCollection() != null) {
            containingitems = order.getOrderContainingItemsCollection();
        }
        Collection<Offer> offers = null;
        if (order.getOfferCollection() != null) {
            offers = order.getOfferCollection();
        }
        order.setOrderContainingItemsCollection(null);
        order.setOfferCollection(null);
        Order newOrder = orderRepository.save(order);
        newOrder.setOrderContainingItemsCollection(containingitems);
        newOrder.setOfferCollection(offers);
        if (containingitems != null) {
            for (OrderContainingItems citem : containingitems) {
                citem.setOrder1(newOrder);
                containingItemsRepository.save(citem);
            }
        }
        if (offers != null) {
            for (Offer offer : offers) {
                if (offer.getCorderCollection() == null) {
                    offer.setCorderCollection(new ArrayList<Order>());
                }
                offer.getCorderCollection().add(newOrder);
                offerRepository.save(offer);
            }
        }
        newOrder = orderRepository.save(newOrder);
        Customer customer = newOrder.getCustomer();
        if (customer.getCorderCollection() == null) {
            customer.setCorderCollection(new ArrayList<Order>());
        }
        customer.getCorderCollection().add(newOrder);
        if (newOrder.getPaymentType().equalsIgnoreCase(PaymentType.BALANCE.toString())) {
            int oldBalance = customer.getBalance();
            int newBalance = (int) (oldBalance - order.getTotalCost());
            customer.setBalance(newBalance);
        }
        customerRepository.save(customer);

//         convert to dto 
        OrderWebSocket orderDTO = covertOrderToDTO(newOrder);
        // send to activemq topic
        template.convertAndSend("/queue/orders", orderDTO);
    }

    @Override
    @Transactional
    public void update(Order order) {
        orderRepository.save(order);
    }

    @Override
    public boolean exists(Integer id) {
        return orderRepository.exists(id);
    }

    @Transactional
    @Override
    public void addAll(Collection<Order> orders) {
        for (Order order : orders) {
            orderRepository.save(order);
        }
    }

//    @Override
//    public List<Order> findCustomerOrders(Integer id) {
//        return orderRepository.findByCustomerId(id);
//    }
    @Override
    public void sendToServer(Order order) {
//         convert to dto 
        OrderWebSocket orderDTO = covertOrderToDTO(order);
//         send to activemq topic
        template.convertAndSend("/queue/orders", orderDTO);
    }

    private  ItemDTO toItemDTO(Item item,int q) {
        ItemDTO idto = new ItemDTO();
        idto.setId(item.getId());
        idto.setName(item.getName());
        idto.setPrice(item.getPrice());
        idto.setQuantity(q);
        idto.setRating(item.getRating());
        idto.setCategory(item.getCategory().getName());
        return idto;
    }
    private OrderWebSocket covertOrderToDTO(Order order) {
        OrderWebSocket dTO=new OrderWebSocket();
        dTO.setId(order.getId());
        dTO.setStatus(order.getStatus());
        dTO.setUserName(order.getCustomer().getUser().getName());
        dTO.setTotalCost(order.getTotalCost());
        List<Item> items=new ArrayList<>();
        for (int i = 0; i <order.getOrderContainingItemsCollection().size(); i++) {
           items.add(order.getOrderContainingItemsCollection().get(i).getItem());
        }
        List<ItemDTO> itemsDTO=new ArrayList();
        for (int i=0;i<items.size();i++) {
           itemsDTO.add(toItemDTO(items.get(i),order.getOrderContainingItemsCollection().get(i).getQuantity()));
        }
        dTO.setItemCollection(itemsDTO);
        return dTO;
    }
    private Integer calculateTotal(Order order) {
        int total = 0;
        if (order.getOrderContainingItemsCollection() != null) {
            for (OrderContainingItems orderContainingItems : order.getOrderContainingItemsCollection()) {
                total += orderContainingItems.getItem().getPrice() * orderContainingItems.getQuantity();
            }
        }
        if (order.getOfferCollection() != null) {
            for (Offer offer : order.getOfferCollection()) {
                int cost = 0;
                for (Item item : offer.getItemCollection()) {
                    cost += item.getPrice();
                }
                total += cost - (cost * (offer.getDiscountPercentage() / 100));
            }
        }
        return total;
    }

    @Override
    public Iterable<Order> findByStatusNot() {
        return orderRepository.findByStatusNot("FINISHED");
    }

    public void sendToUser(User u,int id) {
        System.out.println("============================================================="
                + "Order has already finished");
        template.convertAndSend("/queue/cuser" + u.getId(),id);
//        template.convertAndSend("/queue/user" +u.getId(), u.getName()+", Your Order has finished");
    }
}
