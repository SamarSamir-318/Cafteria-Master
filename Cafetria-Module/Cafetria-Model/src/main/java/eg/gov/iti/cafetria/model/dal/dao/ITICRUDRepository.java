/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package eg.gov.iti.cafetria.model.dal.dao;

import java.io.Serializable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.NoRepositoryBean;

/**
 *
 * @author JEDiver
 */
@NoRepositoryBean
public interface ITICRUDRepository<T extends Object, ID extends Serializable> extends JpaRepository<T, ID> {

    
//    /**
//     * No need to define findAll() here, because inherited from JpaRepository
//     * with many other basic JPA operations.*
//     */
//    //public List<Product> findAll();
//    /**
//     * spring-jpa-data understands this method name, because it supports the
//     * resolution of specific keywords inside method names. *
//     */
//        public List<T> findByNameContainingIgnorseCase(String searchString);
//
//    /**
//     * You can define a JPA query.*
//     */
    
}
