/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package eg.gov.iti.cafetria.view;

import eg.gov.iti.cafetria.model.dal.domain.Offer;
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
public class AllOffersController {

	@Autowired
	private OfferService offerService;

	@RequestMapping(value = "/offers")
	public String getAllOffers(Model model) {
		
		ArrayList<Offer> offerList = (ArrayList<Offer>) offerService.findAll();
		if (offerList == null) {
			offerList = new ArrayList<>();
		}
		model.addAttribute("offerList", offerList);

		return "home/offers";
	}
}
