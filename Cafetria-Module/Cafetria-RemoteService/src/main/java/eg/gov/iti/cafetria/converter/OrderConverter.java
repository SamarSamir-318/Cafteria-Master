/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package eg.gov.iti.cafetria.converter;

import eg.gov.iti.cafetria.dto.ItemDTO;
import eg.gov.iti.cafetria.dto.OfferDTO;
import eg.gov.iti.cafetria.dto.OrderDTO;
import eg.gov.iti.cafetria.model.dal.domain.Item;
import eg.gov.iti.cafetria.model.dal.domain.Offer;
import eg.gov.iti.cafetria.model.dal.domain.Order;
import eg.gov.iti.cafetria.model.dal.domain.OrderContainingItems;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author OsamaPC
 */
public class OrderConverter {

    public OrderDTO convertOrder(Order order) {
        OrderDTO dto = new OrderDTO();
        dto.setId(order.getId());
        dto.setOrderDate(order.getOrderTime());
        dto.setDeliveryTime(order.getDeliveryTime());
        dto.setStatus(order.getStatus());
        List<OrderContainingItems> containingItems=(List<OrderContainingItems>) order.getOrderContainingItemsCollection();
        List<Offer> offers = (List<Offer>) order.getOfferCollection();
        List<ItemDTO> itemsDTO = new ArrayList<>();
        List<OfferDTO> offersDTO = new ArrayList<>();
        if (containingItems != null) {
            for (OrderContainingItems i : containingItems) {
                ItemDTO idto= new ItemDTO();
                idto.setId(i.getItem().getId());
                idto.setName(i.getItem().getName());
                idto.setQuantity(i.getQuantity());
                itemsDTO.add(idto);
            }
        }
        if (offers != null) {
            for (Offer offer : offers) {
                offersDTO.add(OfferConverter.convertToDto(offer));
            }
        }
        dto.setItems(itemsDTO);
        dto.setOffers(offersDTO);
        dto.setTotalCost(order.getTotalCost());
        return dto;

    }

}
