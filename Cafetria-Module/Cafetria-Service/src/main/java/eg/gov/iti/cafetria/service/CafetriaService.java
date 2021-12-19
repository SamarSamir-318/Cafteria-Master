/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package eg.gov.iti.cafetria.service;

import eg.gov.iti.cafetria.model.dal.domain.Cafetria;
import java.util.Collection;

/**
 *
 * @author OsamaPC
 */
public interface CafetriaService {

    public Cafetria findOne(Integer id);

    public Iterable<Cafetria> findAll();

    public void delete(Integer id);

    public void insert(Cafetria cafetria);

    public void update(Cafetria cafetria);

    public boolean exists(Integer id);

    public void addAll(Collection<Cafetria> cafetries);

}
