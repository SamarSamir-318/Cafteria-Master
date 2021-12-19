/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package eg.gov.iti.cafetria.service.implementation;

import eg.gov.iti.cafetria.model.dal.dao.CafetriaRepository;
import eg.gov.iti.cafetria.model.dal.domain.Cafetria;
import eg.gov.iti.cafetria.service.CafetriaService;
import java.util.Collection;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author OsamaPC
 */
@Service
public class CafetriaImpl implements CafetriaService {

    @Autowired
    CafetriaRepository cafetriaRepository;

    @Override
    @Transactional
    public Cafetria findOne(Integer id) {
        return cafetriaRepository.findOne(id);
    }

    @Override
    @Transactional(readOnly = true)
    public Iterable<Cafetria> findAll() {

        return cafetriaRepository.findAll();
    }

    @Override
    @Transactional
    public void delete(Integer id) {
        cafetriaRepository.delete(id);
    }

    @Override
    @Transactional
    public void insert(Cafetria cafetria) {
        cafetriaRepository.save(cafetria);
    }

    @Override
    @Transactional
    public void update(Cafetria cafetria) {
        cafetriaRepository.save(cafetria);
    }

    @Override
    @Transactional
    public boolean exists(Integer id) {
        return cafetriaRepository.exists(id);
    }
    
    @Transactional
    @Override
    public void addAll(Collection<Cafetria> cafetries) {
        for (Cafetria cafe: cafetries) {
            cafetriaRepository.save(cafe);
        }
    }

}
