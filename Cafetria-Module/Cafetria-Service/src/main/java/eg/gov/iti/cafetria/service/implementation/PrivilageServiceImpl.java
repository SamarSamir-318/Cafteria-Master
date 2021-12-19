/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package eg.gov.iti.cafetria.service.implementation;

import eg.gov.iti.cafetria.model.dal.dao.PrivilegeRepository;
import eg.gov.iti.cafetria.model.dal.domain.Privilege;
import eg.gov.iti.cafetria.service.PrivilageService;
import java.util.Collection;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author Administrator
 */
@Service
public class PrivilageServiceImpl implements PrivilageService {

    @Autowired
    PrivilegeRepository privilegeRepository;

    @Override
    public Privilege findOne(Integer id) {
        return privilegeRepository.findOne(id);
    }

    @Override
    public Iterable<Privilege> findAll() {
        return privilegeRepository.findAll();
    }

    @Override
    public void delete(Integer id) {
        privilegeRepository.delete(id);
    }

    @Override
    public void insert(Privilege privilege) {
        privilegeRepository.save(privilege);
    }

    @Override
    public void update(Privilege privilege) {
        privilegeRepository.save(privilege);
    }

    @Override
    public boolean exists(Integer id) {
        return privilegeRepository.exists(id);
    }

    @Override
    public void addAll(Collection<Privilege> privileges) {
        privilegeRepository.save(privileges);
    }

    @Override
    public List<Privilege> findAll(String email) {
        return privilegeRepository.findAll(email);
    }

}
