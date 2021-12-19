/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package eg.gov.iti.cafetria.service.implementation;

import eg.gov.iti.cafetria.dto.RoleDTO;
import eg.gov.iti.cafetria.model.dal.dao.PrivilegeRepository;
import eg.gov.iti.cafetria.model.dal.dao.RoleRepository;
import eg.gov.iti.cafetria.model.dal.domain.Privilege;
import eg.gov.iti.cafetria.model.dal.domain.Role;
import eg.gov.iti.cafetria.service.RoleService;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author Nesma
 */
@Service
public class RoleServiceImpl implements RoleService {

    @Autowired
    RoleRepository roleRepository;
    @Autowired
    PrivilegeRepository privilegeRepository;

    @Override
    @Transactional
    public Role findOne(Integer id) {
        return roleRepository.findOne(id);
    }

    @Override
    @Transactional(readOnly = true)
    public Iterable<Role> findAll() {
        return roleRepository.findAll();
    }

    @Override
    @Transactional
    public void delete(Integer id) {
        List<Privilege> privileges = privilegeRepository.findAll();
        for (Privilege privilege : privileges) {
            for (Role role : privilege.getRoleCollection()) {
                if (role.getId() == id) {
                    privilege.getRoleCollection().remove(role);
                    privilegeRepository.save(privilege);
                    break;
                }
            }
        }
        roleRepository.delete(id);
    }

    @Override
    @Transactional
    public void insert(Role role) {
        roleRepository.save(role);
    }

    @Override
    @Transactional
    public void update(Role role) {
        roleRepository.save(role);
    }

    @Override
    @Transactional
    public boolean exists(Integer id) {
        return roleRepository.exists(id);
    }

    @Override
    @Transactional
    public void addAll(Collection<Role> roles) {
        for (Role role : roles) {
            roleRepository.save(role);
        }
    }

    @Override
    public RoleDTO findOneDTO(Integer id) {
        Role role = roleRepository.findOne(id);
        RoleDTO roledto = new RoleDTO();
        roledto.setId(role.getId());
        roledto.setName(role.getName());
        for (Privilege privilege : role.getPrivilegeCollection()) {
            roledto.getPrivilagesID().add(privilege.getId());
            roledto.getPrivilagesName().add(privilege.getName());
        }
        return roledto;
    }

    @Override
    public void insert(RoleDTO roledto) {
        Role role = new Role();
        role.setName(roledto.getName());
        if (role.getPrivilegeCollection() == null) {
            role.setPrivilegeCollection(new ArrayList<Privilege>());
        }
        for (Integer id : roledto.getPrivilagesID()) {
            Privilege privilege = privilegeRepository.findOne(id);
            role.getPrivilegeCollection().add(privilege);
        }
        Role newRole = roleRepository.save(role);
        for (Privilege privilege : newRole.getPrivilegeCollection()) {
            if (privilege.getRoleCollection() == null) {
                privilege.setRoleCollection(new ArrayList<Role>());
            }
            privilege.getRoleCollection().add(newRole);
            privilegeRepository.save(privilege);
        }
    }

    @Override
    public void update(RoleDTO roledto) {
        Integer roleid = roledto.getId();
        Role role = roleRepository.findOne(roleid);
        role.setName(roledto.getName());
        for (Privilege privilege : role.getPrivilegeCollection()) {
            privilege.getRoleCollection().remove(role);
            privilegeRepository.save(privilege);
        }
        role.setPrivilegeCollection(new ArrayList<Privilege>());
        Role newRole = roleRepository.save(role);
        for (Integer id : roledto.getPrivilagesID()) {
            Privilege privilege = privilegeRepository.findOne(id);
            newRole.getPrivilegeCollection().add(privilege);
        }
        newRole = roleRepository.save(newRole);
        for (Privilege privilege : newRole.getPrivilegeCollection()) {
            if (privilege.getRoleCollection() == null) {
                privilege.setRoleCollection(new ArrayList<Role>());
            }
            if (!privilege.getRoleCollection().contains(newRole)) {
                privilege.getRoleCollection().add(newRole);
            }
            privilegeRepository.save(privilege);
        }
    }

}
