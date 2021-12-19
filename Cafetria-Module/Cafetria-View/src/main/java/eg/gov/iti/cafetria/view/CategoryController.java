/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package eg.gov.iti.cafetria.view;

import eg.gov.iti.cafetria.model.dal.domain.Category;
import eg.gov.iti.cafetria.model.dal.domain.Item;
import eg.gov.iti.cafetria.service.CategoryService;
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
public class CategoryController {

	@Autowired
	CategoryService categoryService;

	@RequestMapping(value = "/addCategory", method = RequestMethod.GET)
	public String showAddform(Model model) {
		model.addAttribute("cat", new Category());
		model.addAttribute("errorDiv", "none");
		return "Categories/addCategory";
	}

	@RequestMapping(value = "/addCategory", method = RequestMethod.POST)
	public String addCategory(@ModelAttribute("cat") @Valid Category cat, BindingResult result, Model model) {
		try {
			if (result.hasErrors()) {
				model.addAttribute("errorDiv", "block");
				return "Categories/addCategory";
			}
			categoryService.insert(cat); //insert
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return ("redirect:/showCategories.htm");
	}

	@RequestMapping(value = "/showCategories", method = RequestMethod.GET)
	public String showAllCategories(Model model) {
		ArrayList<Category> categories = (ArrayList<Category>) categoryService.findAll();
		if (categories == null) {
			categories = new ArrayList<>();
		}
		model.addAttribute("allcategories", categories);

		return "Categories/showCategories";
	}

	@RequestMapping(value = "/deleteCategory", method = RequestMethod.GET)
	public ModelAndView delete(@RequestParam("id") Integer id) {
		categoryService.delete(id);
		return new ModelAndView("redirect:/showCategories.htm");
	}

	@RequestMapping(value = "/updateCategory", method = RequestMethod.GET)
	public String showEditform(@RequestParam("id") Integer id, Model model) {
		model.addAttribute("errorDiv", "none");
		Category cat = categoryService.findOne(id);
		if (cat == null) {
			cat = new Category();
			cat.setItemCollection(new ArrayList<Item>());
		}
		model.addAttribute("cat", cat);

		return "Categories/updateCategory";
	}

	@RequestMapping(value = "/updateCategory", method = RequestMethod.POST)
	public String editCategory(@ModelAttribute("cat") @Valid Category cat, BindingResult result, Model model) {
		if (result.hasErrors()) {
			model.addAttribute("errorDiv", "block");
			return ("Categories/updateCategory");
		}
		categoryService.update(cat);
		return ("redirect:/showCategories.htm");
	}
}
