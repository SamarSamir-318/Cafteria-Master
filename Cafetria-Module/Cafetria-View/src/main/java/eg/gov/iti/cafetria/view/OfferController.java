/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package eg.gov.iti.cafetria.view;

import eg.gov.iti.cafetria.model.dal.domain.Item;
import eg.gov.iti.cafetria.model.dal.domain.Offer;
import eg.gov.iti.cafetria.service.ItemService;
import eg.gov.iti.cafetria.service.OfferService;
import eg.gov.iti.cafetria.service.UserService;
import eg.gov.iti.cafetria.view.dto.OfferVO;
import java.util.ArrayList;
import java.util.List;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

/**
 *
 * @author Nesma
 */
@Controller
public class OfferController {

    @Autowired
    OfferService offerService;
    @Autowired
    ItemService itemService;
    
    @Autowired
    UserService userService;

    @RequestMapping(value = "/addOffer", method = RequestMethod.GET)
    public String showAddform(Model model) {
        model.addAttribute("offer", new OfferVO());
        return "offers/addOffer";
    }

    @ModelAttribute(value = "itemsList")
    public List<Item> getItems() {
        List<Item> items = (List<Item>) itemService.findAll();
        if (items == null) {
            items = new ArrayList<>();
        }
        return items;
    }

    @RequestMapping(value = "/addOffer", method = RequestMethod.POST)
    public String addProduct(@ModelAttribute("offer") @Valid OfferVO offervo, BindingResult result) {
        try {

            if (result.hasErrors()) {
                return "offers/addOffer";
            }
            Offer offer = fromVO(offervo);
            offerService.insert(offer);

        } catch (Exception ex) {
            ex.printStackTrace();
        }

        return ("redirect:/showAdminOffer.htm");

    }

    @RequestMapping(value = "/showAdminOffer", method = RequestMethod.GET)
    public String showAllCafetries(Model model) {
        ArrayList<Offer> offers = (ArrayList<Offer>) offerService.findAll();
        if (offers == null) {
            offers = new ArrayList<>();
        }
        model.addAttribute("offerList", offers);
        return "offers/showAdminOffer";

    }
    
    @RequestMapping(value = "/showCustomerOffer", method = RequestMethod.GET)
    public String showAllCafetries2(Model model) {
        ArrayList<Offer> offers = (ArrayList<Offer>) offerService.findAll();
        if (offers == null) {
            offers = new ArrayList<>();
        }
        model.addAttribute("offerList", offers);
        return "home/offers";

    }

    @RequestMapping(value = "/deleteOffer", method = RequestMethod.GET)
    public String delete(@RequestParam("id") Integer id) {
        offerService.delete(id);
        // return ("offers/showOffer");
        return ("redirect:/showAdminOffer.htm");
    }

    @RequestMapping(value = "/updateOffer", method = RequestMethod.GET)
    public String showEditform(@RequestParam("id") Integer id, Model model) {
        Offer offer = offerService.findOne(id);
        OfferVO offervo=toVO(offer);
        model.addAttribute("offer", offervo);
        return "offers/updateOffer";
    }

    @RequestMapping(value = "/updateOffer", method = RequestMethod.POST)
    public String editOffer(@ModelAttribute("offer") @Valid OfferVO offervo, BindingResult bindingResult) {

        try {
            if (bindingResult.hasErrors()) {

                return "offers/updateOffer";
            }
            Offer offer=fromVO(offervo);
            offerService.update(offer);

        } catch (Exception ex) {
            ex.printStackTrace();
            return "offers/updateOffer";
        }

        return ("redirect:/showAdminOffer.htm");

    }

    @RequestMapping(value = "/homeOffer", method = RequestMethod.GET)
    public String homeItem(@ModelAttribute("offer") Offer offer) {
        return "offers/homeOffer";

    }

    private Offer fromVO(OfferVO offervo) {
        Offer offer = new Offer();
        offer.setDescription(offervo.getDescription());
        offer.setDiscountPercentage(offervo.getDiscountPercentage());
        offer.setStartTime(offervo.getStartTime());
        offer.setEndTime(offervo.getEndTime());
        offer.setItemCollection(new ArrayList<Item>());
        for (Integer id : offervo.getItemsID()) {
            Item item = itemService.findOne(id);
            offer.getItemCollection().add(item);
        }
        return offer;
    }

    private OfferVO toVO(Offer offer) {
        OfferVO offervo=new OfferVO();
        offervo.setDescription(offer.getDescription());
        offervo.setDiscountPercentage(offer.getDiscountPercentage());
        offervo.setStartTime(offer.getStartTime());
        offervo.setEndTime(offer.getEndTime());
        offervo.setId(offer.getId());
        offervo.setItemsID(new ArrayList<Integer>());
        for (Item item : offer.getItemCollection()) {
            offervo.getItemsID().add(item.getId());
        }
        return offervo;
    }
}
