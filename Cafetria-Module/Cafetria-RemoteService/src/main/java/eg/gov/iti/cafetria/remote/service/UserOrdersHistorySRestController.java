/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package eg.gov.iti.cafetria.remote.service;

import eg.gov.iti.cafetria.converter.ItemConverter;
import eg.gov.iti.cafetria.converter.OrderConverter;
import eg.gov.iti.cafetria.dto.OrderDTO;
import eg.gov.iti.cafetria.dto.OrderRequestDTO;
import eg.gov.iti.cafetria.model.dal.domain.Customer;
import eg.gov.iti.cafetria.model.dal.domain.Item;
import eg.gov.iti.cafetria.model.dal.domain.Order;
import eg.gov.iti.cafetria.model.dal.domain.User;
import eg.gov.iti.cafetria.service.CustomerService;
import eg.gov.iti.cafetria.service.ItemService;
import eg.gov.iti.cafetria.service.OrderService;
import eg.gov.iti.cafetria.service.UserService;
import java.sql.Array;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author OsamaPC
 */
@RestController
public class UserOrdersHistorySRestController {

    @Autowired
    UserService userService;

    @RequestMapping(value = "/getOrders", params = "id", method = RequestMethod.GET)
    @ResponseBody
    public List<OrderDTO> getUserOrders(@RequestParam("id") int userId) {
        User user = userService.findOne(userId);
        Customer customer = user.getCustomer();
        List<Order> orders = (List<Order>) customer.getCorderCollection();
        List<OrderDTO> ordersDto = convert(orders);
        return ordersDto;

    }

    private List<OrderDTO> convert(List<Order> orders) {
        List<OrderDTO> ordersDTO = new ArrayList<>();
        OrderConverter orderConverter = new OrderConverter();
        for (int i = 0; i < orders.size(); i++) {
            ordersDTO.add(orderConverter.convertOrder(orders.get(i)));
        }
        return ordersDTO;
    }

}
