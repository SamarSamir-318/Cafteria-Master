/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package eg.gov.iti.cafetria.service;

import eg.gov.iti.cafetria.model.dal.domain.Cafetria;
import eg.gov.iti.cafetria.model.dal.domain.Category;
import java.util.Collection;

/**
 *
 * @author OsamaPC
 */
public interface CategoryService {

    public Category findOne(Integer id);

    public Iterable<Category> findAll();

    public void delete(Integer id);

    public void insert(Category cat);

    public void update(Category cat);

    public boolean exists(Integer id);

    public void addAll(Collection<Category> categories);
}
