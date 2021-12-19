/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package eg.gov.iti.cafetria.model.dal.dao;

/**
 *
 * @author ibrahiem
 */
import eg.gov.iti.cafetria.model.dal.domain.Item;
import java.util.List;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

/**
 * This is the Data Access layer. Simple huh? PLease note that no need for any
 * dao implementation. This is an interface!
 */
@Repository
public interface ItemRepository extends ITICRUDRepository<Item, Integer> {
     
    @Query("select i from Item i where i.name = ?")
    public Item findByName(String name);
    public List<Item> findByQuantityGreaterThan(int quantity);
}
