/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package eg.gov.iti.cafetria.view;

import eg.gov.iti.cafetria.model.dal.domain.Cafetria;
import eg.gov.iti.cafetria.model.dal.domain.Stock;
import eg.gov.iti.cafetria.model.dal.domain.Worker;
import eg.gov.iti.cafetria.service.CafetriaService;
import java.util.ArrayList;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

/**
 *
 * @author OsamaPC
 */
@Controller
public class CafetriaController {

    @Autowired
    CafetriaService cafetriaService;

    @RequestMapping(value = "/addCafetria", method = RequestMethod.GET)
    public String showAddform(Model model) {
        model.addAttribute("cafe", new Cafetria());
        model.addAttribute("errorDiv", "none");
        return "cafeterias/addCafetria";
    }

    @RequestMapping(value = "/addCafetria", method = RequestMethod.POST)
    public String addProduct(@ModelAttribute("cafe") @Valid Cafetria cafetria, BindingResult result, Model model) {
        try {
            if (result.hasErrors()) {
                model.addAttribute("errorDiv", "block");
                return "cafeterias/addCafetria";
            }
            cafetriaService.insert(cafetria);
        } catch (Exception ex) {
            ex.printStackTrace();
        }

        return ("redirect:/showCafetrias.htm");
    }

    @RequestMapping(value = "/showCafetrias", method = RequestMethod.GET)
    public String showAllCafetries(Model model) {
        ArrayList<Cafetria> cafetries = (ArrayList<Cafetria>) cafetriaService.findAll();
		if(cafetries == null)
			cafetries = new ArrayList<>();
        model.addAttribute("allcafetrias", cafetries);
		
        return "cafeterias/showCafetrias";
    }

    @RequestMapping(value = "/deleteCafetria", method = RequestMethod.GET)
    public ModelAndView delete(@RequestParam("id") Integer id) {
        cafetriaService.delete(id);
        return new ModelAndView("redirect:/showCafetrias.htm");
    }

    @RequestMapping(value = "/updateCafetria", method = RequestMethod.GET)
    public String showEditform(@RequestParam("id") Integer id, Model model) {

        Cafetria cafetria = cafetriaService.findOne(id);
		if(cafetria == null) {
			cafetria = new Cafetria();
			cafetria.setStockCollection(new ArrayList<Stock>());
			cafetria.setWorkerCollection(new ArrayList<Worker>());
		}
        model.addAttribute("cafe", cafetria);
        model.addAttribute("errorDiv", "none");
		
        return "cafeterias/updateCafetria";
    }

    @RequestMapping(value = "/updateCafetria", method = RequestMethod.POST)
    public ModelAndView editCategory(@ModelAttribute("cafe") @Valid Cafetria cafetria, BindingResult result) {
        try {
            if (result.hasErrors()) {
                return new ModelAndView("cafeterias/updateCafetria");
            }
            System.out.println(cafetria.getId()+"<<<<<<<<<<<<<<<<<<<<<<<<<<<");
            cafetriaService.update(cafetria);
        } catch (Exception ex) {
            ex.printStackTrace();
        }

        return new ModelAndView("redirect:/showCafetrias.htm");
    }

}
