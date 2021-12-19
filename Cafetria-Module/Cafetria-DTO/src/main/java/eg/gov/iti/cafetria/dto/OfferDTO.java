/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package eg.gov.iti.cafetria.dto;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

/**
 *
 * @author Administrator
 */
public class OfferDTO implements Serializable {

    private Integer id;
    private String name;
    private int percentage;
    private long endTime;
    private List<OfferItem> items;
    private int total;

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

    public int getPercentage() {
        return percentage;
    }

    public void setPercentage(int percentage) {
        this.percentage = percentage;
    }

    public long getEndTime() {
        return endTime;
    }

    public void setEndTime(long endTime) {
        this.endTime = endTime;
    }

    public List<OfferItem> getItems() {
        return items;
    }

    public void setItems(List<OfferItem> items) {
        this.items = items;
    }

    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }

//    public void calculateTotal() {
//        int initialTotal = 0;
//        for (OfferItem item : items) {
//            initialTotal += item.getPrice();
//        }
//        this.setTotal(initialTotal);
//        this.setPercentage(initialTotal * (this.percentage / 100));
//    }
}
