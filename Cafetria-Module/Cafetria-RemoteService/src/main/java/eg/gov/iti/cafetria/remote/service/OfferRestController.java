/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package eg.gov.iti.cafetria.remote.service;

import eg.gov.iti.cafetria.converter.OfferConverter;
import eg.gov.iti.cafetria.dto.OfferDTO;
import eg.gov.iti.cafetria.model.dal.domain.Offer;
import eg.gov.iti.cafetria.service.OfferService;
import java.time.Instant;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author Administrator
 */
@RestController
public class OfferRestController {

    @Autowired
    OfferService offerService;

    @RequestMapping(value = "/getOffers", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)

    public @ResponseBody
    List<OfferDTO> getOffers() {
        List<Offer> offers = (List<Offer>) offerService.findAll();
        return convertList(offers);
    }

    private List<OfferDTO> convertList(List<Offer> offers) {
        List<OfferDTO> result = new ArrayList<>();
        Date currentDate = Date.from(Instant.now());
        for (Offer offer : offers) {
            if (!offer.getStartTime().after(currentDate) && !offer.getEndTime().before(currentDate)) {
                result.add(OfferConverter.convertToDto(offer));
            }
        }
        return result;
    }

}
