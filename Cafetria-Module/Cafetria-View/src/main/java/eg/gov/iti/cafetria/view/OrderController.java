/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package eg.gov.iti.cafetria.view;

import eg.gov.iti.cafetria.enums.OrderStatus;
import eg.gov.iti.cafetria.enums.PaymentType;
import eg.gov.iti.cafetria.model.dal.domain.Customer;
import eg.gov.iti.cafetria.model.dal.domain.Item;
import eg.gov.iti.cafetria.model.dal.domain.Offer;
import eg.gov.iti.cafetria.model.dal.domain.Order;
import eg.gov.iti.cafetria.model.dal.domain.OrderContainingItems;
import eg.gov.iti.cafetria.model.dal.domain.Role;
import eg.gov.iti.cafetria.model.dal.domain.User;
import eg.gov.iti.cafetria.service.CustomerService;
import eg.gov.iti.cafetria.service.ItemService;
import eg.gov.iti.cafetria.service.OfferService;
import eg.gov.iti.cafetria.service.OrderService;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Date;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
//import eg.gov.iti.cafetria.dto.OrderDTO;

/**
 *
 * @author OsamaPC
 */
@Controller
public class OrderController {

    @Autowired
    OrderService orderService;
    @Autowired
    ItemService itemService;
//    @Autowired
//    CustomerService customerService;
    @Autowired
    OfferService offerService;

    @RequestMapping(value = "/addOrder", method = RequestMethod.GET)
    public String showAddform(Model model, HttpServletRequest request) {
        User user = (User) request.getSession().getAttribute("user");
        model.addAttribute("errorDiv", "none");
        Order sessionOrder = (Order) request.getSession().getAttribute("myOrder");
        List<OrderContainingItems> itemCollection = new ArrayList<>();
        if (sessionOrder.getOrderContainingItemsCollection() != null) {
            itemCollection = (List<OrderContainingItems>) sessionOrder.getOrderContainingItemsCollection();
            for (int i = 0; i < itemCollection.size(); i++) {
                itemCollection.get(i).setQuantity(1);
            }
        }
        sessionOrder.setOrderContainingItemsCollection(itemCollection);
        model.addAttribute("order", sessionOrder);
        if(isAdmin(user)){
        return "admin/orders/addOrder";
        }
        else{
            return "orders/addOrder";
        }
        
    }

    @ModelAttribute(value = "statusList")
    public String[] getStatuses() {
        return Arrays.toString(OrderStatus.values()).replaceAll("^.|.$", "").split(", ");
    }

    @ModelAttribute(value = "paymentList")
    public String[] getPayments() {
        return Arrays.toString(PaymentType.values()).replaceAll("^.|.$", "").split(", ");
    }

    @ModelAttribute(value = "itemList")
    public List<Item> getCafetrias() {
        List<Item> items = (List<Item>) itemService.findAll();
        if (items == null) {
            items = new ArrayList<>();
        }
        return items;
    }

    @RequestMapping(value = "/addOrder", method = RequestMethod.POST)
    public String addOrder(@ModelAttribute("order") @Valid Order order, BindingResult result, Model model, HttpServletRequest request) {
        try {
            if (result.hasErrors()) {
                System.out.println("error ");
                model.addAttribute("errorDiv", "block");
                return "orders/addOrder";
            }
//            Order sessionOrder = (Order) request.getSession().getAttribute("myOrder");
//            order.setOfferCollection(sessionOrder.getOfferCollection());
            User user= (User) request.getSession().getAttribute("user");
            Customer c = user.getCustomer();    //customerService.getCustomer(6);
            order.setCustomer(c);
            order.setStatus(OrderStatus.NOTSTARTED.toString());
//			order.setOrderTime(new Date());
            if (order.getOrderContainingItemsCollection() != null) {
                for (OrderContainingItems orderContainingItems : order.getOrderContainingItemsCollection()) {
                    Item i = itemService.findOne(orderContainingItems.getItem().getId());
                    orderContainingItems.setItem(i);
                }
            }

            orderService.insert(order);
            // if admin return ("redirect:/showAllOrders");
//            Collection<Role> roleCollection = c.getUser().getRoleCollection();
        } catch (Exception ex) {
            ex.printStackTrace();
        }

        return ("orders/success");
    }

    @RequestMapping(value = "/showAllOrders", method = RequestMethod.GET)
    public String showAllOrders2(Model model) {
        List<Order> orders = (List<Order>) orderService.findByStatusNot();
        if (orders == null) {
            orders = new ArrayList<>();
        }
        model.addAttribute("allOrders", orders);
        return "admin/orders/showAllOrders";
    }

    @RequestMapping(value = "/deleteOrder", method = RequestMethod.GET)
    public String delete(@RequestParam("id") Integer id) {
        orderService.delete(id);
        return "orders/showOrders";
    }

    @RequestMapping(value = "/updateOrder", method = RequestMethod.GET)
    public String showEditform(@RequestParam("id") Integer id, Model model) {
        Order order = orderService.findOne(id);
        model.addAttribute("errorDiv", "none");
        model.addAttribute("order", order);
        return "admin/orders/updateOrder";
    }

    @RequestMapping(value = "/updateOrder", method = RequestMethod.POST)
    public ModelAndView updateOrder(@ModelAttribute("order") @Valid Order order) {
        orderService.update(order);
        return new ModelAndView("redirect:/showOrders.htm");
    }

    @RequestMapping(value = "/showOrderItems", method = RequestMethod.GET)
    public String showOrderItems(@RequestParam("id") @Valid Integer id, Model model) {
        Order order = orderService.findOne(id);
        List<OrderContainingItems> ocis = (List<OrderContainingItems>) order.getOrderContainingItemsCollection();
        List<Item> allItems = new ArrayList<>();
        for (OrderContainingItems oci : ocis) {
            allItems.add(oci.getItem());
        }
        model.addAttribute("allItems", allItems);
        return ("orders/showOrderItems");
    }

    @RequestMapping(value = "/selectItems", method = RequestMethod.GET)
    public String selectItems(Model model,HttpServletRequest request) {
        User user = (User) request.getSession().getAttribute("user");
        
        List<Item> item = (List<Item>) itemService.findAll();
        if (item == null) {
            item = new ArrayList<>();
        }
        model.addAttribute("allItems", item);
        if(isAdmin(user)){
        return "admin/orders/selectItems";
        }
        else{
            return "orders/selectItems";
        }
    }

    @RequestMapping(value = "/makeOrder", method = RequestMethod.GET)
    public String selectOffers(Model model,HttpServletRequest request) {
        User user = (User) request.getSession().getAttribute("user");
        List<Offer> offers = (List<Offer>) offerService.findAll();
        if (offers == null) {
            offers = new ArrayList<>();
        }
        model.addAttribute("allOffers", offers);
        if(isAdmin(user)){
        return "admin/orders/selectOffers";
        }
        else{
            return "orders/selectOffers";
        }
       
    }

    @RequestMapping(value = "/addItemToOrder", method = RequestMethod.GET)
    public String getItem(@RequestParam("id") List<Integer> itemsID, Model model, HttpServletRequest request) {
        List<Item> items = new ArrayList<>();
        for (int i = 0; i < itemsID.size(); i++) {
            System.out.println("hi mr  " + itemsID.get(i));
            items.add(itemService.findOne(itemsID.get(i)));
        }
        List<OrderContainingItems> ocis = new ArrayList<>();
        for (Item item : items) {
            OrderContainingItems oci = new OrderContainingItems();
            oci.setItem(item);
            oci.setQuantity(1);
            ocis.add(oci);
        }

//        Order sessionOrder = (Order) request.getSession().getAttribute("myOrder");
        Order sessionOrder = new Order();
        sessionOrder.setOrderContainingItemsCollection(ocis);
        model.addAttribute("order", sessionOrder);
        request.getSession().setAttribute("myOrder", sessionOrder);
        return "orders/addOrder";
    }

    @RequestMapping(value = "/addOfferToOrder", method = RequestMethod.GET)
    public String getOffer(@RequestParam("id") List<Integer> offersID, Model model, HttpServletRequest request) {
        List<Offer> offers = new ArrayList<>();
        Order order1 = new Order();
        List<OrderContainingItems> orderItems = new ArrayList<>();
        for (int i = 0; i < offersID.size(); i++) {
            Offer offer = offerService.findOne(offersID.get(i));
            List<Item> itemCollection = (List<Item>) offer.getItemCollection();

            for (int j = 0; j < itemCollection.size(); j++) {
                OrderContainingItems containingItems = new OrderContainingItems();
                System.out.println("<><><><>>>>>  " + itemCollection.get(i).getName());
                containingItems.setItem(itemCollection.get(i));
                containingItems.setQuantity(1);
                containingItems.setOrder1(order1);
                orderItems.add(containingItems);
            }
            offers.add(offer);
        }
//        Order sessionOrder = new Order();
        order1.setOfferCollection(offers);
        order1.setOrderContainingItemsCollection(orderItems);
        model.addAttribute("order", order1);
        request.getSession().setAttribute("myOrder", order1);
        return "orders/addOrder";

    }

    @RequestMapping(value = "/changeStatusToFinished", method = RequestMethod.GET)
    public String changeStatusToFinshed(@RequestParam("id") int id) {
        Order finshedOrder = orderService.findOne(id);
        finshedOrder.setStatus(OrderStatus.FINISHED.toString());
        orderService.update(finshedOrder);
        orderService.sendToUser(finshedOrder.getCustomer().getUser(), finshedOrder.getId());
        return "admin/orders/showAllOrders";
    }
    
    @RequestMapping(value = "/success", method = RequestMethod.GET)
    public String success(){
        return "orders/success";
    }

    @RequestMapping(value = "/changeStatusToINPROGRESS", method = RequestMethod.GET)
    public String changeStatusToINPROGRESS(@RequestParam("id") int id) {
        Order finshedOrder = orderService.findOne(id);
        finshedOrder.setStatus(OrderStatus.INPROGRESS.toString());
        orderService.update(finshedOrder);
//        orderService.sendToUser(finshedOrder.getCustomer().getUser());
        return "admin/orders/showAllOrders";
    }

    @RequestMapping(value = "/offerOrder", method = RequestMethod.GET)
    public String makeOderWithOffer(@RequestParam("id") List<Integer> offersID, Model model, HttpServletRequest request) {

        
        for (int i = 0; i < offersID.size(); i++) {
            Order sessionOrder = new Order();
            ArrayList<Item> items = new ArrayList();
            List<Offer> offers = new ArrayList<>();
//            offers.add(offerService.findOne(offersID.get(i)));
            Offer offer = offerService.findOne(offersID.get(i));
            items.addAll(offer.getItemCollection());
            offers.add(offer);
            sessionOrder.setOfferCollection(offers);
            ArrayList<OrderContainingItems> orderItems = new ArrayList<>();
            for (int j = 0; j < items.size(); j++) {
                OrderContainingItems orderItem = new OrderContainingItems();
                orderItem.setItem(items.get(j));
                orderItem.setOrder1(sessionOrder);
                orderItem.setQuantity(1);  // no quantity attribute in the offer 
                orderItems.add(orderItem);
            }
            User user= (User) request.getSession().getAttribute("user");
            Customer c = user.getCustomer();  //customerService.getCustomer(1);   // from session isa
            sessionOrder.setCustomer(c);
            sessionOrder.setOrderContainingItemsCollection(orderItems);
//            request.getSession().setAttribute("myOrder", sessionOrder);
            sessionOrder.setDeliveryTime(new Date());
            sessionOrder.setPaymentType("cash");
            sessionOrder.setStatus("NOTSTARTED");
            
            orderService.insert(sessionOrder);
            sessionOrder=null;
        }

//        model.addAttribute("order", sessionOrder);
//        request.getSession().setAttribute("myOrder",sessionOrder);
        return "orders/selectItems";
    }
    
    private boolean isAdmin(User user){
        String admin=new String("ADMIN");
        List<Role> roleCollection = (List<Role>) user.getRoleCollection();
        for (int i = 0; i <roleCollection.size(); i++) {
            if (admin.equalsIgnoreCase(roleCollection.get(i).getName())){
                return true;
            }
        }
        return false;
    }
}
