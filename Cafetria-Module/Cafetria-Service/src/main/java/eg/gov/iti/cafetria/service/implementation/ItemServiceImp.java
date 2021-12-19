/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package eg.gov.iti.cafetria.service.implementation;

import eg.gov.iti.cafetria.model.dal.dao.ItemRepository;
import eg.gov.iti.cafetria.model.dal.domain.Item;
import eg.gov.iti.cafetria.service.ItemService;
import java.util.Collection;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author Nesma
 */

@Service
public class ItemServiceImp  implements ItemService{

    @Autowired
    ItemRepository itemRepository;
    
    @Override
    @Transactional
    public Item findOne(Integer id) {
        return itemRepository.findOne(id);
    }

    @Override
    @Transactional(readOnly = true)
    public Iterable<Item> findAll() {
        return itemRepository.findAll();
    }

    @Override
    @Transactional
    public void delete(Integer id) {
        itemRepository.delete(id);
    }

    @Override
    @Transactional
    public Item insert(Item item) {
       return itemRepository.save(item);
    }

    @Override
    @Transactional
    public void update(Item item) {
        itemRepository.save(item);
    }

    @Override
    @Transactional
    public boolean exists(Integer id) {
        return itemRepository.exists(id);
    }

    @Override
    @Transactional
    public void addAll(Collection<Item> items) {
          for (Item it : items) {
            itemRepository.save(it);
        }
    }

    @Override
    public List<Item> findByQuantityGreaterThan() {
       return itemRepository.findByQuantityGreaterThan(0);
    }

}
