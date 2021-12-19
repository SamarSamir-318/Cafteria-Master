/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package eg.gov.iti.cafetria.service;

import eg.gov.iti.cafetria.model.dal.domain.OrderContainingItems;
import java.util.Collection;

/**
 *
 * @author OsamaPC
 */
public interface OrderConainingItemsService {
    
    public OrderContainingItems findOne(Integer id);
    
    public Iterable<OrderContainingItems> findAll();

    public void delete(Integer id);

    public void insert(OrderContainingItems containingItems);

    public void update(OrderContainingItems containingItems);

    public boolean exists(Integer id);

    public void addAll(Collection<OrderContainingItems> cafetries);
}
