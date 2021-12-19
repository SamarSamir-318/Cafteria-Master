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
import org.springframework.data.jpa.repository.Query;
import eg.gov.iti.cafetria.model.dal.domain.Item;
import eg.gov.iti.cafetria.model.dal.domain.Offer;
import java.util.ArrayList;
import java.util.List;


import org.springframework.stereotype.Repository;

/**
 * This is the Data Access layer. Simple huh? PLease note that no need for any
 * dao implementation. This is an interface!
 */
@Repository
public interface OfferRepository extends ITICRUDRepository<Offer, Integer> {

//       public ArrayList<Item> findByOffer(int offerID);
    @Query("select f from Offer f where f.description = ?")
    public Offer findByDesc(String desc);
}
