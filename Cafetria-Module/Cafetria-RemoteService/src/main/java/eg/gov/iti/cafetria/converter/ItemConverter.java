/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package eg.gov.iti.cafetria.converter;

import eg.gov.iti.cafetria.dto.ItemDTO;
import eg.gov.iti.cafetria.model.dal.domain.Item;

/**
 *
 * @author OsamaPC
 */
public class ItemConverter {

    public ItemDTO toDTO(String context, Item item) {
        ItemDTO idto = new ItemDTO();
        idto.setId(item.getId());
        idto.setName(item.getName());
        idto.setPrice(item.getPrice());
        idto.setQuantity(item.getQuantity());
        idto.setRating(item.getRating());
        idto.setCategory(item.getCategory().getName());
        idto.setImage(context + "/photo/" + item.getImagePath());
        return idto;
    }
}
