/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package eg.gov.iti.cafetria.remote.service;

import eg.gov.iti.cafetria.converter.UserConverter;
import eg.gov.iti.cafetria.dto.CustomerDTO;
import eg.gov.iti.cafetria.dto.JResponse;
import eg.gov.iti.cafetria.dto.UserDTO;
import eg.gov.iti.cafetria.dto.WorkerDTO;
import eg.gov.iti.cafetria.model.dal.domain.Customer;
import eg.gov.iti.cafetria.model.dal.domain.User;
import eg.gov.iti.cafetria.service.CustomerService;
import eg.gov.iti.cafetria.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author Administrator
 */
@RestController
public class CustomerRestController {

    @Autowired
    UserService userService;
    @Autowired
    CustomerService customerService;

    @RequestMapping(value = "/addOrUpdateCustomer", method = RequestMethod.GET, headers = "Accept=application/json")
    public @ResponseBody
    JResponse addCustomer(@RequestParam int id, @RequestParam String name, @RequestParam String email, @RequestParam int balance) {
        try {

            User user = userService.findOne(id);

            if (user != null) {
                user.setName(name);
                user.setEmail(email);
                Customer updatedCustomer = user.getCustomer();
                updatedCustomer.setUser(user);
                updatedCustomer.setBalance(balance);
                userService.updateUser(user);
                customerService.updateCustomer(updatedCustomer);
            } else {
                CustomerDTO customerDTO = new CustomerDTO();
                customerDTO.setId(id);
                customerDTO.setName(name);
                customerDTO.setEmail(email);
                customerDTO.setBalance(balance);
                customerService.add(customerDTO);
            }
            return new JResponse(1, "customer added successfully");
        } catch (Exception ex) {
            ex.printStackTrace();
            return new JResponse(0, "problem occured while adding customer");
        }
    }
    
    @RequestMapping(value = "/removeCustomer", method = RequestMethod.GET)
    public @ResponseBody
    JResponse removeCustomer(@RequestParam int id) {
       try{
           customerService.removeCustomer(id);
           return new JResponse(1, "customer remove successfully"); 
       }catch (Exception ex) {
            ex.printStackTrace();
            return new JResponse(0, "problem occured while removing customer");
        }
    }
}
