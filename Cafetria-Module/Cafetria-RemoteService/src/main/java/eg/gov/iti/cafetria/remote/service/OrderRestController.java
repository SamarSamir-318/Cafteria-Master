/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package eg.gov.iti.cafetria.remote.service;

import eg.gov.iti.cafetria.dto.JResponse;
import eg.gov.iti.cafetria.dto.OrderItem;
import eg.gov.iti.cafetria.dto.OrderRequestDTO;
import eg.gov.iti.cafetria.enums.OrderStatus;
import eg.gov.iti.cafetria.enums.PaymentType;
import eg.gov.iti.cafetria.model.dal.domain.Customer;
import eg.gov.iti.cafetria.model.dal.domain.Item;
import eg.gov.iti.cafetria.model.dal.domain.Offer;
import eg.gov.iti.cafetria.model.dal.domain.Order;
import eg.gov.iti.cafetria.model.dal.domain.OrderContainingItems;
import eg.gov.iti.cafetria.service.CustomerService;
import eg.gov.iti.cafetria.service.ItemService;
import eg.gov.iti.cafetria.service.OfferService;
import eg.gov.iti.cafetria.service.OrderService;
import java.time.Instant;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author Nour
 */
@RestController
public class OrderRestController {

    @Autowired
    ItemService itemService;
    @Autowired
    CustomerService customerService;
    @Autowired
    OrderService orderService;
    @Autowired
    OfferService offerService;

    @RequestMapping(value = "/makeorder", method = RequestMethod.POST)
    public @ResponseBody
    JResponse makeOrder(@RequestBody OrderRequestDTO orderRequest) {
        try {
            Order order = new Order();
            order.setOrderTime(Date.from(Instant.now()));
            List<OrderContainingItems> containingItems=convertItems(orderRequest.getItems());
            order.setOrderContainingItemsCollection(containingItems);
            List<Offer> offers = convertOffers(orderRequest.getOffers());
            order.setOfferCollection(offers);
            Customer customer = customerService.getCustomer(orderRequest.getUserId());
            order.setCustomer(customer);
            order.setPaymentType(orderRequest.getPaymentType());
            order.setDeliveryTime(Date.from(Instant.ofEpochMilli(orderRequest.getDeliveryTime())));
            order.setStatus(OrderStatus.NOTSTARTED.toString());
            orderService.insert(order);
            return new JResponse(1, "order created successfully");
        } catch (Exception ex) {
            ex.printStackTrace();
            return new JResponse(0, "problem occured while making the order");
        }
    }
    
    @RequestMapping(value = "/getPaymentTypes", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public @ResponseBody
    List<String> getPayments() {
        String[] result= Arrays.toString(PaymentType.values()).replaceAll("^.|.$", "").split(", ");
        return Arrays.asList(result);
    }

    private OrderContainingItems convertFromDto(OrderItem orderItem) {
        OrderContainingItems result=new OrderContainingItems();
        Item item = itemService.findOne(orderItem.getId());
        result.setItem(item);
        result.setQuantity(orderItem.getQuantity());
        return result;
    }

    private List<OrderContainingItems> convertItems(List<OrderItem> orderItems) {
        List<OrderContainingItems> result = new ArrayList<>();
        if (orderItems != null) {
            for (OrderItem orderItem : orderItems) {
                result.add(convertFromDto(orderItem));
            }
        }
        return result;
    }

    private List<Offer> convertOffers(List<Integer> offers) {
        List<Offer> result = new ArrayList<>();
        if (offers != null) {
            for (Integer offerId : offers) {
                Offer offer = offerService.findOne(offerId);
                if (offer != null) {
                    result.add(offer);
                }
            }
        }
        return result;
    }

}
