/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package eg.gov.iti.cafetria.service;

import eg.gov.iti.cafetria.model.dal.domain.Privilege;
import java.util.Collection;
import java.util.List;

/**
 *
 * @author OsamaPC
 */
public interface PrivilageService {

    public Privilege findOne(Integer id);

    public Iterable<Privilege> findAll();

    public List<Privilege> findAll(String email);

    public void delete(Integer id);

    public void insert(Privilege privilege);

    public void update(Privilege privilege);

    public boolean exists(Integer id);

    public void addAll(Collection<Privilege> privileges);

}
