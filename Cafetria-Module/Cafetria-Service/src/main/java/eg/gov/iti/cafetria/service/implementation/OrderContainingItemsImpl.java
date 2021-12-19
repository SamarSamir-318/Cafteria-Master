/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package eg.gov.iti.cafetria.service.implementation;

import eg.gov.iti.cafetria.model.dal.dao.OrderContainingItemsRepository;
import eg.gov.iti.cafetria.model.dal.domain.OrderContainingItems;
import eg.gov.iti.cafetria.service.OrderConainingItemsService;
import java.util.Collection;
import org.springframework.beans.factory.annotation.Autowired;

/**
 *
 * @author OsamaPC
 */
public class OrderContainingItemsImpl implements OrderConainingItemsService{

    @Autowired
    OrderContainingItemsRepository containingItemsRepository;
    @Override
    public OrderContainingItems findOne(Integer id) {
        return containingItemsRepository.findOne(id);
    }

    @Override
    public Iterable<OrderContainingItems> findAll() {
        return containingItemsRepository.findAll();
    }

    @Override
    public void delete(Integer id) {
        
        containingItemsRepository.delete(id);
                
    }

    @Override
    public void insert(OrderContainingItems containingItems) {
        
        containingItemsRepository.save(containingItems);
    }

    @Override
    public void update(OrderContainingItems containingItems) {
        containingItemsRepository.save(containingItems);
    }

    @Override
    public boolean exists(Integer id) {
        return containingItemsRepository.exists(id);
    }

    @Override
    public void addAll(Collection<OrderContainingItems> orderContainingItemses) {
        
        containingItemsRepository.save(orderContainingItemses);
    }
    
}
