/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package eg.gov.iti.cafetria.remote.service;

import eg.gov.iti.cafetria.dto.ItemDTO;
import eg.gov.iti.cafetria.dto.JResponse;
import eg.gov.iti.cafetria.model.dal.domain.Customer;
import eg.gov.iti.cafetria.model.dal.domain.Item;
import eg.gov.iti.cafetria.service.CustomerService;
import eg.gov.iti.cafetria.service.ItemService;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author Nesma
 */
@RestController

public class ItemFavouriteController {

    @Autowired
    private CustomerService customerService;
    @Autowired
    private ItemService itemService;

    @RequestMapping(value = "/setFavouriteItem/{customerId}/{itemId}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public @ResponseBody
    JResponse setFavouriteItem(@PathVariable("customerId") int customerId, @PathVariable("itemId") int itemId) {

       
        Customer customer = customerService.getCustomer(customerId);
        if (customer == null) {

           return new JResponse(0, "false");
            // return false;

        }

        Item item = itemService.findOne(itemId);
        if (item == null) {
            // return false;
            return new JResponse(0, "false");

        }

        if (customer.getItemCollection().contains(item)) {
            // return  false;
             return new JResponse(0, "false");
        }
        customer.getItemCollection().add(item);
        customerService.updateCustomer(customer);
        //return  true;
          return new JResponse(1,"true");
    }

    @RequestMapping(value = "/getFavouriteItems/{customerId}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public @ResponseBody
    ArrayList<ItemDTO> getFavouriteItems(@PathVariable("customerId") int customerId) {

        Customer customer = customerService.getCustomer(customerId);
        if (customer == null) {
            return null;
        }
        Collection<Item> favIt = customer.getItemCollection();
        ArrayList<ItemDTO> favouriteItems = new ArrayList<>();
        Iterator<Item> it = favIt.iterator();
        while (it.hasNext()) {
            Item item = it.next();
            ItemDTO itemDTO = new ItemDTO();
            itemDTO.setId(item.getId());
            itemDTO.setName(item.getName());
            itemDTO.setCategory(item.getCategory().getName());
            itemDTO.setImage(item.getImagePath());
            itemDTO.setPrice(item.getPrice());
            itemDTO.setQuantity(item.getQuantity());
            itemDTO.setRating(item.getRating());
            favouriteItems.add(itemDTO);
        }
        return favouriteItems;
    }

    @RequestMapping(value = "/deleteFavouriteItem/{customerId}/{itemId}", method = RequestMethod.GET)
    public @ResponseBody
    JResponse deleteFavouriteItem(@PathVariable("customerId") int customerId, @PathVariable("itemId") int itemId) {

        Customer customer = customerService.getCustomer(customerId);
        if (customer == null) {
            //  return false;
            return new JResponse(0, "false");
        }
        Item item = itemService.findOne(itemId);
        if (item == null) {
            // return false;
            return new JResponse(0, "false");
        }
        if (!customer.getItemCollection().contains(item)) {
            //  return false;
            return new JResponse(0, "false");
        }
        customer.getItemCollection().remove(item);
        customerService.updateCustomer(customer);

        // return true;
        return new JResponse(1, "true");

    }

}
