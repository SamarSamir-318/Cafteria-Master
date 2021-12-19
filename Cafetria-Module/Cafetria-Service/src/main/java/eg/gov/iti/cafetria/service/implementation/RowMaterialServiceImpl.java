/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package eg.gov.iti.cafetria.service.implementation;

import eg.gov.iti.cafetria.dto.RowMaterialDTO;
import eg.gov.iti.cafetria.dto.StockDTO;
import eg.gov.iti.cafetria.model.dal.dao.RowMaterialRepository;
import eg.gov.iti.cafetria.model.dal.dao.StockHasRowMaterialRepository;
import eg.gov.iti.cafetria.model.dal.dao.StockRepository;
import eg.gov.iti.cafetria.model.dal.domain.RowMaterial;
import eg.gov.iti.cafetria.model.dal.domain.Stock;
import eg.gov.iti.cafetria.model.dal.domain.StockHasRowMaterial;
import eg.gov.iti.cafetria.service.RowMaterialService;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author Masoud
 */
@Service
public class RowMaterialServiceImpl implements RowMaterialService {

    @Autowired
    RowMaterialRepository rowMaterialRepository;

    @Autowired
    StockHasRowMaterialRepository stockHasRowMaterialRepository;

    @Autowired
    StockRepository stockRepository;

    @Override
    public RowMaterialDTO findOne(Integer id) {
        RowMaterialDTO rowMaterialDTO = new RowMaterialDTO();
        List<StockDTO> stocks = new ArrayList<>();
        List<String> locations = new ArrayList<>();
        List<Integer> quantities = new ArrayList<>();
        List<Integer> ids = new ArrayList<>();
        List<StockHasRowMaterial> stockHasRowMaterial = stockHasRowMaterialRepository.findAll();
        RowMaterial rowMaterial = rowMaterialRepository.findOne(id);
        for (StockHasRowMaterial sHasR : stockHasRowMaterial) {
            if (rowMaterial.getId().equals(sHasR.getRowMaterial().getId())) {
                StockDTO stockDTO = new StockDTO();
                stockDTO.setId(sHasR.getStock().getId());
                stockDTO.setLocation(sHasR.getStock().getLocation());
                stockDTO.setQuantity(sHasR.getQuantity());
                stockDTO.setCafeteria(sHasR.getStock().getCafetria());
                locations.add(stockDTO.getLocation());
                ids.add(stockDTO.getId());
                quantities.add(stockDTO.getQuantity());
                stocks.add(stockDTO);

            }
        }
        rowMaterialDTO.setId(rowMaterial.getId());
        rowMaterialDTO.setName(rowMaterial.getName());
        rowMaterialDTO.setStocks(stocks);
        return rowMaterialDTO;
    }

    @Override
//    @Transactional(readOnly = true)
    public List<RowMaterialDTO> getAll() {
        List<RowMaterialDTO> rowMaterialDTOs = new ArrayList<>();
        List<RowMaterial> rowMaterials = rowMaterialRepository.findAll();
        List<StockHasRowMaterial> stockHasRowMaterial = stockHasRowMaterialRepository.findAll();
        RowMaterialDTO rowMaterialDTO = null;
        for (RowMaterial rowMaterial : rowMaterials) {
            List<String> stockLocations = new ArrayList<>();
            List<Integer> stockQuantities = new ArrayList<>();
            rowMaterialDTO = new RowMaterialDTO();
            for (StockHasRowMaterial sHasR : stockHasRowMaterial) {
                if (rowMaterial.getId().equals(sHasR.getRowMaterial().getId())) {
                    stockLocations.add(sHasR.getStock().getLocation());
                    stockQuantities.add(sHasR.getQuantity());
                }
            }

            rowMaterialDTO.setId(rowMaterial.getId());
            rowMaterialDTO.setName(rowMaterial.getName());
            rowMaterialDTO.setStockLocations(stockLocations);
            rowMaterialDTO.setStockQuantities(stockQuantities);
            rowMaterialDTOs.add(rowMaterialDTO);
        }
        return rowMaterialDTOs;
    }

    @Override
    //@Transactional
    public void delete(Integer id) {

        List<StockHasRowMaterial> stockHasRowMaterial = stockHasRowMaterialRepository.findAll();
        for (StockHasRowMaterial sHasR : stockHasRowMaterial) {
            if (id.equals(sHasR.getRowMaterial().getId())) {

                stockHasRowMaterialRepository.delete(sHasR.getId());
            }
        }

        rowMaterialRepository.delete(id);
    }

    @Override
    @Transactional
    public void insert(RowMaterialDTO rowMaterialDTO) {
        RowMaterial rowMaterial = new RowMaterial();
        rowMaterial.setName(rowMaterialDTO.getName());
        rowMaterialRepository.save(rowMaterial);

        Collection<Stock> stocks = new ArrayList<>();
        for (StockDTO stockDTO : rowMaterialDTO.getStocks()) {
            Stock stock = stockRepository.findOne(stockDTO.getId());
            StockHasRowMaterial sHasR = new StockHasRowMaterial();
            if (stockDTO.getQuantity() > 0) {

                sHasR.setStock(stock);
                sHasR.setRowMaterial(rowMaterial);
                sHasR.setQuantity(stockDTO.getQuantity());
                stockHasRowMaterialRepository.save(sHasR);
            }

        }
    }

    @Override
//    @Transactional
    public void update(RowMaterialDTO rowMaterialDTO) {
        RowMaterial rowMaterial = rowMaterialRepository.getOne(rowMaterialDTO.getId());
        rowMaterial.setName(rowMaterialDTO.getName());
        
        List<StockHasRowMaterial> hasRowMaterials = stockHasRowMaterialRepository.findAll();
        
        for (StockHasRowMaterial material : hasRowMaterials){
            if (material.getRowMaterial().getId() == rowMaterial.getId()){
                stockHasRowMaterialRepository.delete(material.getId());
            }
        }
        
        for (StockDTO stockDTO : rowMaterialDTO.getStocks()){
            Stock stock = stockRepository.getOne(stockDTO.getId());
            StockHasRowMaterial stockHasRowMaterial = new StockHasRowMaterial();
            stockHasRowMaterial.setQuantity(stockDTO.getQuantity());
            stockHasRowMaterial.setRowMaterial(rowMaterial);
            stockHasRowMaterial.setStock(stock);
            stockHasRowMaterialRepository.save(stockHasRowMaterial);
        }
    }

    @Override
    public boolean exists(Integer id) {
        return rowMaterialRepository.exists(id);
    }

    @Transactional
    @Override
    public void addAll(Collection<RowMaterial> rowMaterials) {
        for (RowMaterial rowMaterial : rowMaterials) {
            rowMaterialRepository.save(rowMaterial);
        }
    }
}
