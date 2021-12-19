/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package eg.gov.iti.cafetria.dto;

import java.io.Serializable;
import java.util.List;

/**
 *
 * @author Nour
 */
public class OrderRequestDTO implements Serializable {

    private List<OrderItem> items;
    private Integer userId;
    private String paymentType;
    private long deliveryTime;
    private List<Integer> offers;

    public List<OrderItem> getItems() {
        return items;
    }

    public void setItems(List<OrderItem> items) {
        this.items = items;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public List<Integer> getOffers() {
        return offers;
    }

    public void setOffers(List<Integer> offers) {
        this.offers = offers;
    }

    public long getDeliveryTime() {
        return deliveryTime;
    }

    public void setDeliveryTime(long deliveryTime) {
        this.deliveryTime = deliveryTime;
    }

    public String getPaymentType() {
        return paymentType;
    }

    public void setPaymentType(String paymentType) {
        this.paymentType = paymentType;
    }
}
