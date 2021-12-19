/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package eg.gov.iti.cafetria.view;

import eg.gov.iti.cafetria.model.dal.domain.Cafetria;
import eg.gov.iti.cafetria.model.dal.domain.Stock;
import eg.gov.iti.cafetria.service.CafetriaService;
import eg.gov.iti.cafetria.service.StockService;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
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
 * @author Masoud
 */
@Controller
public class StockController {

    @Autowired
    private StockService stockService;

    @Autowired
    private CafetriaService cafetriaService;

    @ModelAttribute("cafetrias")
    public void getCafetrias(Model model) {
        ArrayList<Cafetria> cafetrias = (ArrayList<Cafetria>) cafetriaService.findAll();
        if (cafetrias == null) {
            cafetrias = new ArrayList<>();
        }
        model.addAttribute("cafetrias", cafetrias);
    }

    @RequestMapping(value = "/addStock", method = RequestMethod.GET)
    public ModelAndView showAddform(Model model) {

        model.addAttribute("stock", new Stock());
        return new ModelAndView("stocks/addStock");
    }

    @RequestMapping(value = "/addStock", method = RequestMethod.POST)
    public ModelAndView addStock(@ModelAttribute("stock") @Valid Stock stock, BindingResult result, Model model) {
        try {
            if (result.hasErrors()) {
                return new ModelAndView("stocks/addStock");
            }
            stockService.insert(stock);
        } catch (Exception ex) {
        }
        return new ModelAndView("redirect:/showStocks.htm");
    }

    @RequestMapping(value = "/showStocks", method = RequestMethod.GET)
    public String showAllStocks(Model model) {
        List<Stock> stockslist = (List<Stock>) stockService.findAll();
        if (stockslist == null) {
            stockslist = new ArrayList<>();
        }
        model.addAttribute("allStocks", stockslist);
        return "stocks/showStocks";
    }

    @RequestMapping(value = "/deleteStock", method = RequestMethod.GET)
    public ModelAndView delete(@RequestParam("id") Integer id) {
        stockService.delete(id);
        return new ModelAndView("redirect:/showStocks.htm");
    }

    @RequestMapping(value = "/updateStock", method = RequestMethod.GET)
    public String showEditform(@RequestParam("id") Integer id, Model model) {

        ArrayList<Cafetria> cafetrias = (ArrayList<Cafetria>) cafetriaService.findAll();
        if (cafetrias == null) {
            cafetrias = new ArrayList<>();
        }
        Stock stock = stockService.findOne(id);

        model.addAttribute("cafetrias", cafetrias);
        model.addAttribute("stock", stock);
        return "stocks/updateStock";
    }

    @RequestMapping(value = "/updateStock", method = RequestMethod.POST)
    public ModelAndView updateStock(@ModelAttribute("stock") @Valid Stock stock, BindingResult result) {

        if (result.hasErrors()) {
            return new ModelAndView("stocks/updateStock");
        }

        stockService.update(stock);
        return new ModelAndView("redirect:/showStocks.htm");

    }

}
