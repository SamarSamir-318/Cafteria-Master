/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package eg.gov.iti.cafetria.service.implementation;

import eg.gov.iti.cafetria.model.dal.dao.ItemRepository;
import eg.gov.iti.cafetria.model.dal.dao.OfferRepository;
import eg.gov.iti.cafetria.model.dal.domain.Item;
import eg.gov.iti.cafetria.model.dal.domain.Offer;
import eg.gov.iti.cafetria.service.OfferService;
import java.util.ArrayList;
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
public class OfferServiceImp implements OfferService {

    @Autowired
    OfferRepository offerRepository;
    @Autowired
    ItemRepository itemRepository;

    @Override
    @Transactional
    public Offer findOne(Integer id) {
        return offerRepository.findOne(id);
    }

    @Override
    @Transactional(readOnly = true)
    public Iterable<Offer> findAll() {
        return offerRepository.findAll();
    }

    @Override
    @Transactional
    public void delete(Integer id) {
        offerRepository.delete(id);
    }

    @Override
    @Transactional
    public void insert(Offer offer) {
        Offer newOffer = offerRepository.save(offer);
        for (Item item : newOffer.getItemCollection()) {
            if (item.getOfferCollection() == null) {
                item.setOfferCollection(new ArrayList<Offer>());
            }
            item.getOfferCollection().add(newOffer);
            itemRepository.save(item);
        }
    }

    @Override
    @Transactional
    public void update(Offer offer) {
        List<Item> allItems = itemRepository.findAll();
        for (Item item : allItems) {
            if (item.getOfferCollection()!=null&&item.getOfferCollection().contains(offer)) {
                item.getOfferCollection().remove(offer);
                itemRepository.save(item);
            }
        }
        Offer newOffer = offerRepository.save(offer);
        for (Item item : newOffer.getItemCollection()) {
            if (item.getOfferCollection() == null) {
                item.setOfferCollection(new ArrayList<Offer>());
            }
            item.getOfferCollection().add(newOffer);
            itemRepository.save(item);
        }
    }

    @Override
    @Transactional
    public boolean exists(Integer id) {
        return offerRepository.exists(id);
    }

    @Override
    @Transactional
    public void addAll(Collection<Offer> offers) {
        for (Offer of : offers) {
            offerRepository.save(of);
        }
    }

//    @Override
//    public ArrayList<Item> findByOffer(int offerID) {
//        return offerRepository.findByOffer(offerID);
//    }
}
