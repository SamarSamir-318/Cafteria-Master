/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package eg.gov.iti.cafetria.service;

import eg.gov.iti.cafetria.model.dal.domain.Cafetria;
import eg.gov.iti.cafetria.model.dal.domain.Order;
import eg.gov.iti.cafetria.model.dal.domain.User;
import java.util.Collection;
import java.util.List;

/**
 *
 * @author OsamaPC
 */
public interface OrderService {

    public Order findOne(Integer id);

    public Iterable<Order> findAll();

    public void delete(Integer id);

    public void insert(Order order);

    public void update(Order order);

    public boolean exists(Integer id);

    public void addAll(Collection<Order> orders);
    
//    public List<Order> findAllCustomerOrders(Integer id);
    public void sendToServer(Order o);
    public void sendToUser(User u,int id);
    
    public Iterable<Order> findByStatusNot();
}
