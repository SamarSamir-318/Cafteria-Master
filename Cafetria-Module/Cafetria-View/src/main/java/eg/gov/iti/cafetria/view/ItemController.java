/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package eg.gov.iti.cafetria.view;

import eg.gov.iti.cafetria.dto.CustomerDTO;
import eg.gov.iti.cafetria.model.dal.domain.Category;
import eg.gov.iti.cafetria.model.dal.domain.Customer;
import eg.gov.iti.cafetria.model.dal.domain.Item;
import eg.gov.iti.cafetria.model.dal.domain.ItemInStock;
import eg.gov.iti.cafetria.model.dal.domain.User;
import eg.gov.iti.cafetria.service.CategoryService;
import eg.gov.iti.cafetria.service.CustomerService;
import eg.gov.iti.cafetria.service.ItemService;
import eg.gov.iti.cafetria.service.UserService;
import eg.gov.iti.cafetria.view.dto.ItemDto;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.FileSystems;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import org.apache.poi.util.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 *
 * @author Nesma
 */
@Controller
public class ItemController {

    @Autowired
    ItemService itemService;
    @Autowired
    CategoryService categoryService;
    @Autowired
    ServletContext context;

    @Autowired
    UserService userService;
    @Autowired
    CustomerService customerService;
 
    
    
    // private final static String UPLOAD_DIRECTORY = "C:\\Users\\Nesma\\Desktop\\nesma\\CafeteriaWeb\\Cafetria-Module\\Cafetria-View\\src\\main\\webapp\\WEB-INF\\images";

    @RequestMapping(value = "/addItem", method = RequestMethod.GET)
    public String showAddform(Model model) {
        model.addAttribute("item", new ItemDto());
        
		Iterable<Category> categoryIter = categoryService.findAll();
		if(categoryIter == null)
			categoryIter = new Iterable<Category>() {
			@Override
			public Iterator<Category> iterator() {
				return new ArrayList<Category>().iterator();
			}
		};
		model.addAttribute("categorylist", categoryIter);
     
        return "items/addItem";
    }

    @ModelAttribute("categorylist")
    public void getcategorylist(Model model) {
		Iterable<Category> categoryIter = categoryService.findAll();
		if(categoryIter == null)
			categoryIter = new Iterable<Category>() {
			@Override
			public Iterator<Category> iterator() {
				return new ArrayList<Category>().iterator();
			}
		};
        model.addAttribute("categorylist", categoryIter);
    }
    
//      @ModelAttribute(value = "itemInStock")
//    public List<ItemInStock> getItems() {
//        List<ItemInStock> items = (List<ItemInStock>) itemInStock.findAll();
//        if (items == null) {
//            items = new ArrayList<>();
//        }
//        return items;
//    }
    
    
    @RequestMapping(value = "/addItem", method = RequestMethod.POST)
    public String addProduct(@ModelAttribute("item") @Valid ItemDto itemdto, BindingResult bindingResult) {
        try {
            if (bindingResult.hasErrors()) {
                return "items/addItem";
            }

            //** insert item in database ********
            Item item = new Item();
            Item item1 = new Item();
            item.setCategory(itemdto.getCategory());
            item.setName(itemdto.getName());
            item.setPrice(itemdto.getPrice());
            item.setQuantity(itemdto.getQuantity());
            item.setDescription(itemdto.getDescription());
            item1 = itemService.insert(item);
            item.setImagePath("img" + item1.getId() + ".jpg");
            itemService.update(item);

            //**** end insert*****
            //************** upload image ******
            String fullPath = context.getRealPath("/resources");
            System.out.println(fullPath + ",,,,,,,,,,,,,,,,,,,,,,,nnnnnnnnnnnnnnnnnnnnnnnnnnnnnnn,,,,,,,,,,,,");
            String filename = itemdto.getFile().getOriginalFilename();
            System.out.println(filename);
            byte[] bytes = itemdto.getFile().getBytes();
            BufferedOutputStream stream = new BufferedOutputStream(new FileOutputStream(
                    new File(fullPath + File.separator + "img" + item1.getId() + ".jpg")));
            stream.write(bytes);
            stream.flush();
            stream.close();
            //********************** end upload*************

        } catch (Exception ex) {
            ex.printStackTrace();
            return "items/addItem";
        }
        //return ("items/success");
        return ("redirect:/showItem.htm");
    }

    @RequestMapping(value = "/showItem", method = RequestMethod.GET)
    public String showAllCafetries(Model model) {
        ArrayList<Item> items = (ArrayList<Item>) itemService.findAll();
		if(items == null)
			items = new ArrayList<>();
        model.addAttribute("allItems", items);
        return "items/showItem";
    }

    @RequestMapping(value = "/deleteItem", method = RequestMethod.GET)
    public String delete(@RequestParam("id") Integer id) {
        itemService.delete(id);
        return ("redirect:/showItem.htm");
    }

    @RequestMapping(value = "/updateItem", method = RequestMethod.GET)
    public String showEditform(@RequestParam("id") Integer id, Model model) {
        Item item = itemService.findOne(id);
		ItemDto itemdo = new ItemDto();
		if(item != null) {
			itemdo.setName(item.getName());
			itemdo.setCategory(item.getCategory());
			itemdo.setDescription(item.getDescription());
			itemdo.setPrice(item.getPrice());
			itemdo.setQuantity(item.getQuantity());
			itemdo.setImage(item.getImagePath());
			itemdo.setId(id);
		}
        model.addAttribute("item", itemdo);

        return "items/updateItem";
    }

    @RequestMapping(value = "/updateItem", method = RequestMethod.POST)
    public String editItem(@ModelAttribute("item") @Valid ItemDto item, BindingResult bindingResult) {
        try {
            if (bindingResult.hasErrors()) {
                return "items/updateItem";
            }
            Item i = itemService.findOne(item.getId());
            if (i != null && item.getFile().isEmpty()) {
                //i.setImagePath(item.getImage());
                i.setName(item.getName());
                i.setPrice(item.getPrice());
                i.setCategory(item.getCategory());
                i.setDescription(item.getDescription());
                i.setQuantity(item.getQuantity());
                itemService.update(i);
            } else {

                //** delete image from path****  
                // String fullPath = context.getRealPath("/WEB-INF/images");
                String fullPath = context.getRealPath("/resources");

                Path path = Paths.get(fullPath + File.separator + "img" + item.getId() + ".jpg");
                //     Path path = FileSystems.getDefault().getPath(fullPath + File.separator + "img" + item.getId() + ".jpg");

                if (Files.exists(path)) {
                    Files.delete(path);
                }
                //************** upload image ******
                String filename = item.getFile().getOriginalFilename();
                byte[] bytes = item.getFile().getBytes();
                BufferedOutputStream stream = new BufferedOutputStream(new FileOutputStream(
                        new File(fullPath + File.separator + "img" + item.getId() + ".jpg")));
                stream.write(bytes);
                stream.flush();
                stream.close();
                //********************** end upload*************
                i.setName(item.getName());
                i.setPrice(item.getPrice());
                i.setCategory(item.getCategory());
                i.setDescription(item.getDescription());
                i.setQuantity(item.getQuantity());
                i.setImagePath("img" + item.getId() + ".jpg");

                itemService.update(i);

            }

            //   item.setImagePath(i.getImagePath());
        } catch (Exception ex) {
            ex.printStackTrace();
            return "items/updateItem";
        }
        return ("redirect:/showItem.htm");
    }

    @RequestMapping(value = "/homeItem", method = RequestMethod.GET)
    public String homeItem(@ModelAttribute("item") Item item) {
        return "items/homeItem";
    }
    // ******code image display*****
    @Autowired
    ServletContext servletContext;

    @ResponseBody
    @RequestMapping(value = "photo/{name}", method = RequestMethod.GET, produces = MediaType.IMAGE_JPEG_VALUE)
    public byte[] testphoto(@PathVariable String name) throws IOException {
        InputStream in = servletContext.getResourceAsStream("/resources/" + name + ".jpg");
        System.out.println(in.toString());
        if (in != null) {
            return IOUtils.toByteArray(in);
        } else {
            return null;
        }
    }
//          getquantity

    @RequestMapping(value = "getQuantityById", method = RequestMethod.GET)
    @ResponseBody
    public String getQuantityById(@RequestParam("id") int id, @RequestParam("quantity")  int quantity)
    {
        System.out.println(">>>>>>>>>>>>>>*********************************************"+id);
        Item item    =   itemService.findOne(id);
		if(item == null) {
                   return ("Unavailable!<i style=\"color: red;\"class=\"fa fa-times\" aria-hidden=\"true\"></i>");

                }
		
			System.out.println(item.getName());
        
			System.out.println(item.getQuantity());
			int x= item.getQuantity();
        
			if (quantity>=1 && quantity<= x)
			{
			//  return ( "Available! <img src='checked.gif'/>");
				return ( "Available! <i style=\"color: green;\" class=\"fa fa-check\" aria-hidden=\"true\"></i>");
			}
       
       return ("Unavailable!<i style=\"color: red;\"class=\"fa fa-times\" aria-hidden=\"true\"></i>");
    }

    @RequestMapping(value = "/selectFavItems", method = RequestMethod.GET)
    public String selectItems(Model model) {
        List<Item> items = (List<Item>) itemService.findAll();
		if(items == null)
			items = new ArrayList<>();
        model.addAttribute("allItems", items);
        return "items/selectFavItems";
    }

    @RequestMapping(value = "/addItemToFav", method = RequestMethod.GET)
    public String addItemToFav(@RequestParam("id") List<Integer> itemsID,HttpServletRequest request) {
        List<Item> items = new ArrayList<>();
       User user= (User) request.getSession().getAttribute("user");
        Customer customer = user.getCustomer();                   //customerService.getCustomer(1);
        if (customer == null) {
            return "false";
        }

        Iterator it = itemsID.iterator();

        for (int i = 0; i < itemsID.size(); i++) {
            Item item = new Item();
            item = itemService.findOne(itemsID.get(i));
            System.out.println(item.getName() + "***************************************************nesma");
            if (customer.getItemCollection().contains(item)) {
                continue;
            }
            customer.getItemCollection().add(item);

            customerService.updateCustomer(customer);

        }

        return "items/showFavItems";
    }

    @RequestMapping(value = "/deleteItemToFav", method = RequestMethod.GET)
    public String deleteItemToFav(@RequestParam("id") int id,HttpServletRequest request) {
       User user= (User) request.getSession().getAttribute("user");
        Customer customer = user.getCustomer();            //customerService.getCustomer(1);
        if (customer == null) {
            return "false";
        }
        Item item = itemService.findOne(id);
        System.out.println(item.getName() + "***************************************************nesma");
        if (customer.getItemCollection().contains(item)) {
            customer.getItemCollection().remove(item);
            customerService.updateCustomer(customer);
        }

        return ("redirect:/showFavItems.htm");

    }

    @RequestMapping(value = "/showFavItems", method = RequestMethod.GET)
    public String showFavItems(Model model,HttpServletRequest request) {
       //User user = userService.findOne(1);  // get User from session
       User user= (User) request.getSession().getAttribute("user");
	   List<Item> item = null;
	   if(user != null) {
			Customer c = user.getCustomer();
			item = (List<Item>) c.getItemCollection();
			System.out.println("++++++++++"+ item.size());
	   }
	   else
		   item = new ArrayList<>();
        model.addAttribute("allItems", item);
        return "items/showFavItems";
    }

}
