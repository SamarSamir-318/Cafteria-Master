/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package eg.gov.iti.cafetria.service;

import eg.gov.iti.cafetria.model.dal.domain.Stock;
import java.util.Collection;

/**
 *
 * @author Masoud
 */
public interface StockService {

    public Stock findOne(Integer id);

    public Iterable<Stock> findAll();

    public void delete(Integer id);

    public void insert(Stock stock);

    public void update(Stock stock);

    public boolean exists(Integer id);

    public void addAll(Collection<Stock> stocks);

}
