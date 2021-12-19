/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package eg.gov.iti.cafetria.service.implementation;

import eg.gov.iti.cafetria.model.dal.dao.CategoryRepository;
import eg.gov.iti.cafetria.model.dal.domain.Category;
import eg.gov.iti.cafetria.model.dal.domain.Order;
import eg.gov.iti.cafetria.service.CategoryService;
import java.util.Collection;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author OsamaPC
 */
@Service
public class CategoryImpl implements CategoryService {

    @Autowired
    CategoryRepository categoryRepository;

    @Override
    public Category findOne(Integer id) {
        return categoryRepository.getOne(id);

    }

    @Transactional(readOnly = true)
    @Override
    public Iterable<Category> findAll() {
        return categoryRepository.findAll();
    }

    @Transactional
    @Override
    public void delete(Integer id) {
        categoryRepository.delete(id);
    }

    @Transactional
    @Override
    public void insert(Category cat) {
        categoryRepository.save(cat);
    }

    @Transactional
    @Override
    public void update(Category cat) {
        categoryRepository.save(cat);
    }

    @Transactional
    @Override
    public boolean exists(Integer id) {
        return categoryRepository.exists(id);
    }

    @Transactional
    @Override
    public void addAll(Collection<Category> categories) {
        for (Category cat : categories) {
            categoryRepository.save(cat);
        }
    }
}
