/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package eg.gov.iti.cafetria.service.implementation;

/**
 *
 * @author nour
 */
import eg.gov.iti.cafetria.service.CustomerService;
import eg.gov.iti.cafetria.model.dal.dao.CustomerRepository;
import eg.gov.iti.cafetria.model.dal.dao.RoleRepository;
import eg.gov.iti.cafetria.model.dal.dao.UserRepository;
import eg.gov.iti.cafetria.model.dal.domain.Customer;
import eg.gov.iti.cafetria.model.dal.domain.Role;
import eg.gov.iti.cafetria.model.dal.domain.User;
import eg.gov.iti.cafetria.dto.CustomerDTO;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class CustomerServiceImpl implements CustomerService {

    @Autowired
    private CustomerRepository customerRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private RoleRepository roleRepository;

    @Transactional
    @Override
    public void add(CustomerDTO customerdto) {
        Customer customer = new Customer();
        customer.setBalance(customerdto.getBalance());
        User user = new User();
        user.setId(customerdto.getId());
        user.setType(true);
        user.setName(customerdto.getName());
        user.setEmail(customerdto.getEmail());
        user.setRoleCollection(new ArrayList<Role>());
        for (Integer roleId : customerdto.getRolesID()) {
            Role role = roleRepository.findOne(roleId);
            if (role != null) {
                user.getRoleCollection().add(role);
            }
        }
        User newUser = userRepository.save(user);
        for (Role role : user.getRoleCollection()) {
            if (role.getUserCollection() == null) {
                role.setUserCollection(new ArrayList<User>());
            }
            role.getUserCollection().add(newUser);
            roleRepository.save(role);
        }
        customer.setUser(newUser);
        customerRepository.save(customer);
    }

    @Transactional(readOnly = true)
    @Override
    public List<Customer> findAll() {
        return customerRepository.findAll();
    }

    @Transactional
    @Override
    public void addAll(Collection<CustomerDTO> customersdto) {
        for (CustomerDTO customerdto : customersdto) {
            add(customerdto);
        }
    }

    @Override
    public Customer getCustomer(Integer ID) {
        return customerRepository.findOne(ID);
    }

    @Override
    public void removeCustomer(Integer ID) {
        customerRepository.delete(ID);
        List<Role> roles = roleRepository.findAll();
        for (Role role : roles) {
            for (User user : role.getUserCollection()) {
                if (user.getId() == ID) {
                    role.getUserCollection().remove(user);
                    roleRepository.save(role);
                    break;
                }
            }
        }
        userRepository.delete(ID);
    }

    @Override
    public void updateCustomer(CustomerDTO customerdto) {
        Integer i = customerdto.getId();
        Customer customer = customerRepository.getOne(i);
        customer.setBalance(customerdto.getBalance());
        User user = userRepository.getOne(customerdto.getId());
        user.setName(customerdto.getName());
        for (Role role : user.getRoleCollection()) {
            role.getUserCollection().remove(user);
            roleRepository.save(role);
        }
        user.setRoleCollection(new ArrayList<Role>());
        User newUser = userRepository.save(user);
        if (customerdto.getRolesID() != null) {
            for (Integer roleId : customerdto.getRolesID()) {
                Role role = roleRepository.findOne(roleId);
                if (role != null) {
                    newUser.getRoleCollection().add(role);
                }
            }
        }
        newUser = userRepository.save(newUser);
        for (Role role : newUser.getRoleCollection()) {
            if (role.getUserCollection() == null) {
                role.setUserCollection(new ArrayList<User>());
            }
            role.getUserCollection().add(newUser);
            roleRepository.save(role);
        }
        customer.setUser(newUser);
        customerRepository.save(customer);
    }

    @Override
    public CustomerDTO findOne(Integer id) {
        Customer customer = customerRepository.getOne(id);
        CustomerDTO customerDTO = new CustomerDTO();
        customerDTO.setBalance(customer.getBalance());
        customerDTO.setId(id);
        customerDTO.setName(customer.getUser().getName());
        for (Role role : customer.getUser().getRoleCollection()) {
            customerDTO.getRolesID().add(role.getId());
        }
        return customerDTO;
    }

    @Override
    @Transactional
    public boolean exists(Integer id) {
        return customerRepository.exists(id);
    }

    @Override
    public void updateCustomer(Customer customer) {
        customerRepository.save(customer);
    }
}
