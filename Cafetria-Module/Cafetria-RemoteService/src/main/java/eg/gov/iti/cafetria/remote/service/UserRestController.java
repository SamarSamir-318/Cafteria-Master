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
import eg.gov.iti.cafetria.model.dal.domain.Role;
import eg.gov.iti.cafetria.model.dal.domain.User;
import eg.gov.iti.cafetria.model.dal.domain.Worker;
import eg.gov.iti.cafetria.service.CustomerService;
import eg.gov.iti.cafetria.service.RoleService;
import eg.gov.iti.cafetria.service.UserService;
import eg.gov.iti.cafetria.service.WorkerService;
import java.util.ArrayList;
import java.util.List;
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
public class UserRestController {

    @Autowired
    UserService userService;
    @Autowired
    CustomerService customerService;
    @Autowired
    WorkerService workerService;

    @RequestMapping(value = "/getUser", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE, headers = "Accept=application/json")
    public @ResponseBody
    UserDTO getUser(@RequestParam int id) {
        if (userService.exists(id)) {
            User user = userService.findOne(id);
            Customer customer = customerService.getCustomer(id);
            Worker worker = workerService.getWorker(id);
            return UserConverter.convertToDto(user, customer, worker);
        } else {
            return null;
        }

    }

    @RequestMapping(value = "/updateUser", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
    public JResponse updateUser(@RequestBody UserDTO userdto) {
        try {
            if ("customer".equals(userdto.getType())) {
                CustomerDTO customer=UserConverter.convertCustomerFromDto(userdto);
                customerService.updateCustomer(customer);
            } else {
                WorkerDTO worker=UserConverter.convertWorkerFromDto(userdto);
                workerService.updateWorker(worker);
            }
            return new JResponse(1, "user updated successfully");
        } catch (Exception ex) {
            return new JResponse(0, "problem occured while updating user");
        }
    }
    
    @RequestMapping(value = "/getAllCustomers", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE, headers = "Accept=application/json")
    public @ResponseBody
    List<UserDTO> getAllCustomers() {
        List<UserDTO> customers  =new ArrayList<>();
        List<Customer> allCustomers = customerService.findAll();
        for(Customer customer:allCustomers){
            UserDTO user = UserConverter.convertToDto(customer.getUser(),customer,null);
            customers.add(user);
        }
        return customers;
    }
    
    @RequestMapping(value = "/getAllWorkers", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE, headers = "Accept=application/json")
    public @ResponseBody
    List<UserDTO> getAllWorkers() {
        List<UserDTO> workers  =new ArrayList<>();
        List<Worker> allWorkers = workerService.findAll();
        for(Worker worker:allWorkers){
            UserDTO user = UserConverter.convertToDto(worker.getUser(),null,worker);
            workers.add(user);
        }
        return workers;
    }
}
