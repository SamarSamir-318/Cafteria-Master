/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package eg.gov.iti.cafetria.converter;

import eg.gov.iti.cafetria.dto.OfferDTO;
import eg.gov.iti.cafetria.dto.OfferItem;
import eg.gov.iti.cafetria.model.dal.domain.Item;
import eg.gov.iti.cafetria.model.dal.domain.Offer;
import java.util.ArrayList;

/**
 *
 * @author Administrator
 */
public class OfferConverter {

    public static OfferDTO convertToDto(Offer offer) {
        OfferDTO dTO = new OfferDTO();
        dTO.setId(offer.getId());
        dTO.setName(offer.getDescription());
        dTO.setPercentage(offer.getDiscountPercentage());
        dTO.setEndTime(offer.getEndTime().getTime());
        dTO.setItems(new ArrayList<OfferItem>());
        for (Item item : offer.getItemCollection()) {
            dTO.getItems().add(new OfferItem(item.getName(), item.getPrice()));
        }
        dTO.setTotal(calculateTotal(offer));
        dTO.setPercentage(offer.getDiscountPercentage());
        return dTO;
    }

    private static int calculateTotal(Offer offer) {
        int initialTotal = 0;
        for (Item item : offer.getItemCollection()) {
            initialTotal += item.getPrice();
        }
        return initialTotal;
    }
}
