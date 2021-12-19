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

import eg.gov.iti.cafetria.model.dal.domain.User;
import java.util.Collection;
import java.util.List;

public interface UserService {
    
    public User findOne(Integer id);

    public void add(User user);

    public List<User> findAll();

    public void addAll(Collection<User> users);
    
    public void removeUser(Integer ID);
    
    public void updateUser(User user);
    
     public boolean exists(Integer id);
	 
	public User findByEmail(String email);
	
	public boolean isAdmin(User user);
}
