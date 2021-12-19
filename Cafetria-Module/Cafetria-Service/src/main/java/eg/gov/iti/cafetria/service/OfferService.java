/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package eg.gov.iti.cafetria.service;

import eg.gov.iti.cafetria.model.dal.domain.Item;
import eg.gov.iti.cafetria.model.dal.domain.Offer;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 *
 * @author Nesma
 */
public interface OfferService {

    public Offer findOne(Integer id);

    public Iterable<Offer> findAll();

    public void delete(Integer id);

    public void insert(Offer offer);

    public void update(Offer offer);

    public boolean exists(Integer id);

    public void addAll(Collection<Offer> offers);

//     public ArrayList<Item> findByOffer(int offerID);
}
