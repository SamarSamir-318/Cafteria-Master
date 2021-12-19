/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package eg.gov.iti.cafetria.dto;

import eg.gov.iti.cafetria.model.dal.domain.Stock;
import java.util.List;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import org.hibernate.validator.constraints.NotEmpty;

/**
 *
 * @author Masoud
 */
public class RowMaterialDTO {
    
    private Integer id;
//    @Size(max=20, message="{rowMaterial.longName}")
//    @Pattern(regexp="^[^-\\s][a-zA-Z_\\s-]+$", message="{rowMaterial.invalidName}")
    private String name;
//    @NotEmpty(message = "{rowMaterial.stockErr}")
    private List<Integer> stockIds;
    private List<String> stockLocatrions;
    private List<Integer> stockQuantities;
    private List<StockDTO> stocks;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<StockDTO> getStocks() {
        return stocks;
    }

    public void setStocks(List<StockDTO> stocks) {
        this.stocks = stocks;
    }

    public List<Integer> getStockIds() {
        return stockIds;
    }

    public void setStockIds(List<Integer> stockIds) {
        this.stockIds = stockIds;
    }

    public List<String> getStockLocations() {
        return stockLocatrions;
    }

    public void setStockLocations(List<String> stockLocatrions) {
        this.stockLocatrions = stockLocatrions;
    }

    public List<Integer> getStockQuantities() {
        return stockQuantities;
    }

    public void setStockQuantities(List<Integer> stockQuantities) {
        this.stockQuantities = stockQuantities;
    }
    
}
