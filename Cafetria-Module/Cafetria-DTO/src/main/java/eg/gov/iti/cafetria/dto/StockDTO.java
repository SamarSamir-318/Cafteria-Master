/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package eg.gov.iti.cafetria.dto;

import eg.gov.iti.cafetria.model.dal.domain.Cafetria;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

/**
 *
 * @author Masoud
 */
public class StockDTO {
    
    private Integer id;
    private String location;
    private Cafetria cafeteria;
    private Integer quantity;

    public String getLocation() {
        return location;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public Cafetria getCafeteria() {
        return cafeteria;
    }

    public void setCafeteria(Cafetria cafeteria) {
        this.cafeteria = cafeteria;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }
    
}
