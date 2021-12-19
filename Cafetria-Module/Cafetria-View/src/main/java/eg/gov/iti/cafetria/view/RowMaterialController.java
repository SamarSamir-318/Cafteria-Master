/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package eg.gov.iti.cafetria.view;

import eg.gov.iti.cafetria.dto.RowMaterialDTO;
import eg.gov.iti.cafetria.dto.StockDTO;
import eg.gov.iti.cafetria.model.dal.domain.RowMaterial;
import eg.gov.iti.cafetria.model.dal.domain.Stock;
import eg.gov.iti.cafetria.service.RowMaterialService;
import eg.gov.iti.cafetria.service.StockService;
import java.util.ArrayList;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
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
public class RowMaterialController {

    @Autowired
    RowMaterialService rowMaterialService;

    @Autowired
    StockService stockService;

    @ModelAttribute("stocks")
    public void getStocks(Model model) {
        ArrayList<Stock> stockList = (ArrayList<Stock>) stockService.findAll();
        List<StockDTO> stocks = new ArrayList<>();
        for (Stock s : stockList) {
            StockDTO sd = new StockDTO();
            sd.setId(s.getId());
            sd.setCafeteria(s.getCafetria());
            sd.setLocation(s.getLocation());
            stocks.add(sd);
        }
        model.addAttribute("stocks", stocks);
    }

    @RequestMapping(value = "/addRowMaterial", method = RequestMethod.GET)
    public String initform(Model model) {
        model.addAttribute("rowMaterial", new RowMaterialDTO());
        return "rowMaterials/addRowMaterial";
    }

    @RequestMapping(value = "/addRowMaterial", method = RequestMethod.POST)
    public ModelAndView addRowMaterial(@ModelAttribute("rowMaterial") @Valid RowMaterialDTO rowMaterial,
            BindingResult result, Model model, HttpServletRequest request) {

        try {
            if (result.hasErrors()) {
                return new ModelAndView("rowMaterials/addRowMaterial");
            }
        } catch (Exception ex) {
        }

        RowMaterialDTO rowMaterialOnSesseon = rowMaterial;
        List<StockDTO> stockList = new ArrayList<>();
        for (Integer id : rowMaterialOnSesseon.getStockIds()) {
            StockDTO stockDTO = new StockDTO();
            Stock stock = stockService.findOne(id);
            stockDTO.setId(stock.getId());
            stockDTO.setCafeteria(stock.getCafetria());
            stockDTO.setLocation(stock.getLocation());
            stockList.add(stockDTO);
        }

        rowMaterialOnSesseon.setStocks(stockList);
        request.getSession().setAttribute("rowMaterialOnSession", rowMaterialOnSesseon);
        ModelAndView modelAndView = new ModelAndView("rowMaterials/addRowMaterial2");
        modelAndView.getModel().put("rowMaterial", rowMaterialOnSesseon);
        return modelAndView;
    }

    @RequestMapping(value = "/addRowMaterial2", method = RequestMethod.GET)
    public String initform2(Model model, HttpServletRequest request) {

        RowMaterialDTO rowMaterial = (RowMaterialDTO) request.getSession().getAttribute("rowMaterialOnSession");
        model.addAttribute("rowMaterial", rowMaterial);

        return "rowMaterials/addRowMaterial2";
    }

    @RequestMapping(value = "/addRowMaterial2", method = RequestMethod.POST)
    public ModelAndView addRowMaterial2(@ModelAttribute("rowMaterial") @Valid RowMaterialDTO rowMaterial,
            BindingResult result, Model model, HttpServletRequest request) {

        RowMaterialDTO rowMaterialDTO = (RowMaterialDTO) request.getSession().getAttribute("rowMaterialOnSession");
        try {
            if (result.hasErrors()) {
                return new ModelAndView("rowMaterials/addRowMaterial2");
            }

            for (int i = 0; i < rowMaterial.getStocks().size(); i++) {
                rowMaterialDTO.getStocks().get(i).setQuantity(rowMaterial.getStocks().get(i).getQuantity());
            }
            rowMaterialService.insert(rowMaterialDTO);
        } catch (Exception ex) {
        }
        return new ModelAndView("redirect:/showRowMaterials.htm");
    }

    @RequestMapping(value = "/showRowMaterials", method = RequestMethod.GET)
    public String showAllRowMaterials(Model model) {
        List<RowMaterialDTO> rowMaterials = rowMaterialService.getAll();
        model.addAttribute("allRowMaterials", rowMaterials);
        return "rowMaterials/showRowMaterials";
    }

    @RequestMapping(value = "/deleteRowMaterial", method = RequestMethod.GET)
    public ModelAndView removeRowMaterial(@RequestParam("id") Integer id) {
        rowMaterialService.delete(id);
        return new ModelAndView("redirect:/showRowMaterials.htm");
    }

    @RequestMapping(value = "/updateRowMaterial", method = RequestMethod.GET)
    public String showEditform(@RequestParam("id") Integer id, Model model, HttpServletRequest request) {
        RowMaterialDTO rowMaterial = rowMaterialService.findOne(id);

        request.getSession().setAttribute("rid", id);

        model.addAttribute("rowMaterial", rowMaterial);
        return "rowMaterials/updateRowMaterial";
    }

    @RequestMapping(value = "/updateRowMaterial", method = RequestMethod.POST)
    public ModelAndView updateOrder(@ModelAttribute("rowMaterial") RowMaterialDTO rowMaterial,
            BindingResult result, HttpServletRequest request) {
        if (result.hasErrors()) {
            return null;
        }

        RowMaterialDTO rowMaterialOnSesseon = rowMaterial;
        List<StockDTO> stockList = new ArrayList<>();
        for (Integer id : rowMaterialOnSesseon.getStockIds()) {
            StockDTO stockDTO = new StockDTO();
            Stock stock = stockService.findOne(id);
            stockDTO.setId(stock.getId());
            stockDTO.setCafeteria(stock.getCafetria());
            stockDTO.setLocation(stock.getLocation());
            stockList.add(stockDTO);
        }

        rowMaterialOnSesseon.setStocks(stockList);
        request.getSession().setAttribute("sessionRowMaterial", rowMaterialOnSesseon);
        return new ModelAndView("redirect:/updateRowMaterial2");
    }

    @RequestMapping(value = "/updateRowMaterial2", method = RequestMethod.GET)
    public String showEditform2(Model model, HttpServletRequest request) {
        RowMaterialDTO rowMaterial = (RowMaterialDTO) request.getSession().getAttribute("sessionRowMaterial");

        model.addAttribute("rowMaterial", rowMaterial);
        return "rowMaterials/updateRowMaterial2";
    }

    @RequestMapping(value = "/updateRowMaterial2", method = RequestMethod.POST)
    public ModelAndView updateOrder2(@ModelAttribute("rowMaterial") RowMaterialDTO rowMaterial,
            BindingResult result, HttpServletRequest request) {
        if (result.hasErrors()) {
            return new ModelAndView("rowMaterials/updateRowMaterial2");
        }

        RowMaterialDTO rowMaterialDTO = (RowMaterialDTO) request.getSession().getAttribute("sessionRowMaterial");

        for (int i = 0; i < rowMaterial.getStocks().size(); i++) {
            rowMaterialDTO.getStocks().get(i).setQuantity(rowMaterial.getStocks().get(i).getQuantity());
        }

        Integer id = (Integer) request.getSession().getAttribute("rid");
        rowMaterialDTO.setId(id);
        rowMaterialService.update(rowMaterialDTO);
        return new ModelAndView("redirect:/showRowMaterials.htm");
    }

}
