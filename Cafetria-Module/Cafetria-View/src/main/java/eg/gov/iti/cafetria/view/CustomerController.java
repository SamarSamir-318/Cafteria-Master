/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package eg.gov.iti.cafetria.view;

import eg.gov.iti.cafetria.model.dal.domain.Customer;
import eg.gov.iti.cafetria.model.dal.domain.Role;
import eg.gov.iti.cafetria.service.CustomerService;
import eg.gov.iti.cafetria.dto.CustomerDTO;
import eg.gov.iti.cafetria.service.RoleService;
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
import org.springframework.web.servlet.ModelAndView;

/**
 *
 * @author Nour
 */
@Controller
public class CustomerController {

    @Autowired
    CustomerService customerService;
    @Autowired
    RoleService roleService;

//    @RequestMapping(value = "/addCustomer", method = RequestMethod.GET)
    public String initform(Model model) {
        model.addAttribute("customer", new CustomerDTO());
        model.addAttribute("errorDiv", "none");
        return "customers/AddCustomer";
    }

    @ModelAttribute(value = "roleslist")
    public List<Role> getRoles() {
        List<Role> roles = (List<Role>) roleService.findAll();
        if (roles == null) {
            roles = new ArrayList<>();
        }
        return roles;
    }

//    @RequestMapping(value = "/addCustomer", method = RequestMethod.POST)
    public String addCustomer(@Valid @ModelAttribute("customer") CustomerDTO customerDTO, BindingResult result, Model model) {
        try {
            if (result.hasErrors()) {
                return "customers/AddCustomer";
            }
            customerService.add(customerDTO);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return "redirect:/showallcustomers";
    }

    @RequestMapping("/showallcustomers")
    public ModelAndView ShowAllCustomers() {
        List<Customer> customerList = customerService.findAll();
        if (customerList == null) {
            customerList = new ArrayList<>();
        }
        List<CustomerDTO> convertedcustomerlist = convertToDTO(customerList);

        return new ModelAndView("customers/showcustomers", "customerlist", convertedcustomerlist);
    }

    @RequestMapping(value = "/showSelectedCustomer", method = RequestMethod.GET)
    public ModelAndView showCustomer(@RequestParam("id") int id, Model model) {
        CustomerDTO customerDTO = customerService.findOne(id);
        if (customerDTO == null) {
            customerDTO = new CustomerDTO();
            customerDTO.setRolesID(new ArrayList<Integer>());
            customerDTO.setRolesName(new ArrayList<String>());
        }
        model.addAttribute("errorDiv", "none");
        model.addAttribute("customer", customerDTO);

        return new ModelAndView("customers/updateCustomer");
    }

    @RequestMapping("/deleteCustomer")
    public String removeCustomer(@RequestParam("id") int id) {
        customerService.removeCustomer(id);
        return "redirect:/showallcustomers";
    }

    @RequestMapping(value = "/showSelectedCustomer", method = RequestMethod.POST)
    public String updateCustomer(@Valid @ModelAttribute("customer") CustomerDTO customerDTO, BindingResult result, Model model) {
        try {
            if (result.hasErrors()) {
                model.addAttribute("errorDiv", "block");
                return "customers/updateCustomer";
            }
            customerService.updateCustomer(customerDTO);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return "redirect:/showallcustomers";
    }

    private List<CustomerDTO> convertToDTO(List<Customer> customerlist) {
        List<CustomerDTO> result = new ArrayList<>();
        for (Customer customer : customerlist) {
            result.add(toDTO(customer));
        }
        return result;
    }

    private CustomerDTO toDTO(Customer customer) {
        CustomerDTO cdto = new CustomerDTO();
        cdto.setId(customer.getId());
        cdto.setName(customer.getUser().getName());
        for (Role role : customer.getUser().getRoleCollection()) {
            cdto.getRolesID().add(role.getId());
            cdto.getRolesName().add(role.getName());
        }
        cdto.setBalance(customer.getBalance());
        return cdto;
    }

}
