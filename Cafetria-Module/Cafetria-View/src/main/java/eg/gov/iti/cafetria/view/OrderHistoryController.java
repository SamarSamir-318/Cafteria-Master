/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package eg.gov.iti.cafetria.view;

import eg.gov.iti.cafetria.converter.OrderConverter;
import eg.gov.iti.cafetria.dto.OrderDTO;
import eg.gov.iti.cafetria.model.dal.domain.Customer;
import eg.gov.iti.cafetria.model.dal.domain.Order;
import eg.gov.iti.cafetria.model.dal.domain.User;
import eg.gov.iti.cafetria.service.UserService;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import javax.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

/**
 *
 * @author Masoud
 */
@Controller
public class OrderHistoryController {
    
    @Autowired
    UserService userService;
    
    @RequestMapping(value = "/showCustomerOrders", method = RequestMethod.GET)
    public String showAllCustomerOrders(Model model, HttpSession session) {
//        addIdToSession(session);
        System.out.println("In Controller");
        User sessionUser = (User) session.getAttribute("user"); //userService.findOne((Integer) session.getAttribute("loggedIn"));
        User user = userService.findOne(sessionUser.getId());
		List<OrderDTO> orders = null;
		if(user != null) {
			Customer customer = user.getCustomer();
			List<Order> orderlist = (List<Order>) customer.getCorderCollection();
			if(orderlist == null)
				orderlist = new ArrayList<>();
			orders = convert(orderlist);
		} else {
			user = new User();
			orders = new ArrayList<>();
		}
        model.addAttribute("orders", orders);
        model.addAttribute("user", user);
        return "customerOrders/showCustomerOrders";
    }
    
    @RequestMapping(value = "/showCustomerOrders", method = RequestMethod.POST)
    public String refreshAllCustomerOrders(@RequestParam Map<String, String> params
                                            ,@ModelAttribute("orders") List<OrderDTO> orders
                                            , @ModelAttribute("user") User user
                                            , HttpSession session) {
        
        Order order = new Order();
//        order.setCustomer(orders);
        return "customerOrders/showCustomerOrders";
    }
    
//    private void addIdToSession(HttpSession session) {
//        session.setAttribute("loggedIn", new Integer(1));
//    }
    
    private List<OrderDTO> convert(List<Order> orders) {
        List<OrderDTO> ordersDTO = new ArrayList<>();
        OrderConverter orderConverter = new OrderConverter();
        for (int i = orders.size() - 1; i >= 0; i--) {
            ordersDTO.add(orderConverter.convertOrder(orders.get(i)));
        }
        return ordersDTO;
    }
}
