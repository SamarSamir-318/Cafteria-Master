/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package eg.gov.iti.cafetria.service.implementation;

import eg.gov.iti.cafetria.model.dal.dao.StockRepository;
import eg.gov.iti.cafetria.model.dal.domain.Stock;
import eg.gov.iti.cafetria.service.StockService;
import java.util.Collection;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author Masoud
 */
@Service
public class StockServiceImpl implements StockService {

    @Autowired
    StockRepository stockRepository;

    @Override
    public Stock findOne(Integer id) {
        return stockRepository.getOne(id);
    }

    @Override
    @Transactional(readOnly = true)
    public Iterable<Stock> findAll() {
        return stockRepository.findAll();
    }

    @Override
    @Transactional
    public void delete(Integer id) {
        stockRepository.delete(id);
    }

    @Override
    @Transactional
    public void insert(Stock stock) {
        stockRepository.save(stock);
    }

    @Override
    @Transactional
    public void update(Stock stock) {

        stockRepository.save(stock);
    }

    @Override
    public boolean exists(Integer id) {
        return stockRepository.exists(id);
    }

    @Transactional
    @Override
    public void addAll(Collection<Stock> stocks) {
        for (Stock stock : stocks) {
            stockRepository.save(stock);
        }
    }
}
