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
import java.util.ArrayList;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 *
 * @author atom
 */
@Controller
public class AllItemsAndOffersController {
	
	@Autowired
	private ItemService itemService;
	
	@Autowired
	private OfferService offerService;
	
	@RequestMapping(value="/allItemsAndOffers")
	public String getAllItems(Model model) {
		ArrayList<Item> itemList = (ArrayList<Item>)itemService.findByQuantityGreaterThan();
		if(itemList == null)
			itemList = new ArrayList<>();
		model.addAttribute("allItems", itemList);
		
		return "home/home";
	}
}
