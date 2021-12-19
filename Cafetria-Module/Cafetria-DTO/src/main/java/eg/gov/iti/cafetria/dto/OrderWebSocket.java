/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package eg.gov.iti.cafetria.dto;

import java.util.Collection;
import java.util.List;

/**
 *
 * @author OsamaPC
 */
public class OrderWebSocket {

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
    
    private int id;
    private String userName;
    private String status;
    private int totalCost;
    Collection<ItemDTO> itemCollection;

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public int getTotalCost() {
        return totalCost;
    }

    public void setTotalCost(int totalCost) {
        this.totalCost = totalCost;
    }

    public Collection<ItemDTO> getItemCollection() {
        return itemCollection;
    }

    public void setItemCollection(List<ItemDTO> itemCollection) {
        this.itemCollection = itemCollection;
    }
    
}
