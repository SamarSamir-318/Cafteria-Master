/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package eg.gov.iti.cafetria.service;

import eg.gov.iti.cafetria.model.dal.domain.Category;
import eg.gov.iti.cafetria.model.dal.domain.Item;
import java.util.Collection;
import java.util.List;

/**
 *
 * @author Nesma
 */
public interface ItemService {

    public Item findOne(Integer id);

    public Iterable<Item> findAll();

    public void delete(Integer id);

    public Item insert(Item item);

    public void update(Item item);

    public boolean exists(Integer id);

    public void addAll(Collection<Item> items);
    
    public List<Item> findByQuantityGreaterThan();


}
