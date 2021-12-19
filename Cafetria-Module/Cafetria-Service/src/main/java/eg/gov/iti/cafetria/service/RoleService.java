/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package eg.gov.iti.cafetria.service;

import eg.gov.iti.cafetria.dto.RoleDTO;
import eg.gov.iti.cafetria.model.dal.domain.Role;
import java.util.Collection;
import java.util.List;

/**
 *
 * @author OsamaPC
 */
public interface RoleService {

    public Role findOne(Integer id);

    public RoleDTO findOneDTO(Integer id);

    public Iterable<Role> findAll();

    public void delete(Integer id);

    public void insert(Role role);

    public void insert(RoleDTO roledto);

    public void update(Role role);

    public void update(RoleDTO roledto);

    public boolean exists(Integer id);

    public void addAll(Collection<Role> roles);

}
