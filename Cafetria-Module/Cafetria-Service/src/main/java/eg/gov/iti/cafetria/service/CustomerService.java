/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package eg.gov.iti.cafetria.service;

/**
 *
 * @author nour
 */

import eg.gov.iti.cafetria.model.dal.domain.Customer;
import eg.gov.iti.cafetria.dto.CustomerDTO;
import java.util.Collection;
import java.util.List;

public interface CustomerService {
    
    public CustomerDTO findOne(Integer id);

    public void add(CustomerDTO customer);

    public List<Customer> findAll();

    public void addAll(Collection<CustomerDTO> customers);
    
    public Customer getCustomer(Integer ID);
    
    public void removeCustomer(Integer ID);
    
    public void updateCustomer(CustomerDTO customer);
    public void updateCustomer(Customer customer);
    public boolean exists(Integer id);
}
