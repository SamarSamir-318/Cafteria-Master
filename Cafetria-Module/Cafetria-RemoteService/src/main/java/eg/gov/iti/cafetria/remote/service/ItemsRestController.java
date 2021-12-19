/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package eg.gov.iti.cafetria.remote.service;

import eg.gov.iti.cafetria.converter.ItemConverter;
import eg.gov.iti.cafetria.dto.ItemDTO;
import eg.gov.iti.cafetria.model.dal.domain.Category;
import eg.gov.iti.cafetria.model.dal.domain.Item;
import eg.gov.iti.cafetria.service.CategoryService;
import eg.gov.iti.cafetria.service.ItemService;
import java.util.ArrayList;
import java.util.List;
import javax.servlet.ServletContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author Nour
 */
@RestController
public class ItemsRestController {

    @Autowired
    CategoryService categoryService;

    @Autowired
    ItemService itemService;
    @Autowired
    ServletContext servletContext;

    @RequestMapping(value = "/getItemsRS/{category}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public @ResponseBody
    List<ItemDTO> getItemsByCategory(@PathVariable("category") String categoryName) {
        if (categoryName.equals("all")) {
            List<Item> items = (List<Item>) itemService.findAll();
            return convertListToDTO(items);
        } else {
            Iterable<Category> categorys = categoryService.findAll();
            for (Category category : categorys) {
                if (category.getName().equalsIgnoreCase(categoryName)) {
                    List<Item> items = (List<Item>) category.getItemCollection();
                    return convertListToDTO(items);
                }
            }
        }
        return null;
    }

    private List<ItemDTO> convertListToDTO(List<Item> items) {
        List<ItemDTO> result = new ArrayList<>();
        for (Item item : items) {
            result.add(new ItemConverter().toDTO(servletContext.getContextPath(),item));
        }
        return result;
    }
}
