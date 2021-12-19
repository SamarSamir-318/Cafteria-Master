/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package eg.gov.iti.cafetria.converter;

import eg.gov.iti.cafetria.dto.RoleDTO;
import eg.gov.iti.cafetria.model.dal.domain.Privilege;
import eg.gov.iti.cafetria.model.dal.domain.Role;

/**
 *
 * @author Administrator
 */
public class RoleConverter {
    public static RoleDTO toDTO(Role role) {
        RoleDTO roledto=new RoleDTO();
        roledto.setId(role.getId());
        roledto.setName(role.getName());
        for (Privilege privilege : role.getPrivilegeCollection()) {
            roledto.getPrivilagesID().add(privilege.getId());
            roledto.getPrivilagesName().add(privilege.getName());
        }     
        return roledto;  
    }
}
