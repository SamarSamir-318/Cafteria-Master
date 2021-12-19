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
import eg.gov.iti.cafetria.model.dal.dao.UserRepository;
import eg.gov.iti.cafetria.model.dal.domain.Role;
import eg.gov.iti.cafetria.model.dal.domain.User;
import eg.gov.iti.cafetria.service.UserService;
import java.util.Collection;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {

	@Autowired
	private UserRepository userRepository;

	@Override
	public void add(User user) {
		userRepository.save(user);
	}

	@Override
	public void removeUser(Integer ID) {
		userRepository.delete(ID);
	}

	@Override
	public void updateUser(User user) {
		userRepository.save(user);
	}

	@Override
	public User findOne(Integer id) {
		return userRepository.findOne(id);
	}

	@Override
	public List<User> findAll() {
		return userRepository.findAll();
	}

	@Override
	public void addAll(Collection<User> users) {
		userRepository.save(users);
	}

	@Override
	public boolean exists(Integer id) {
		return userRepository.exists(id);
	}

	@Override
	public User findByEmail(String email) {
		return userRepository.findByEmail(email);
	}

	@Override
	public boolean isAdmin(User user) {
		String admin = "ADMIN";
		List<Role> roleCollection = (List<Role>) user.getRoleCollection();
		for (int i = 0; i < roleCollection.size(); i++) {
			if (admin.equalsIgnoreCase(roleCollection.get(i).getName())) {
				return true;
			}
		}
		return false;
	}
}
