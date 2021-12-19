/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package eg.gov.iti.cafetria.dto;

import java.io.Serializable;

/**
 *
 * @author Ahmed labib
 */
//@JsonAutoDetect
public class UserDTO implements Serializable {

    private int id;
    private String name;
    private int balance;
    private String type;
    private int age;
    private String history;
    private int cafetriaId;
    private String cafetriaName;
     
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getBalance() {
        return balance;
    }

    public void setBalance(int balance) {
        this.balance = balance;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getHistory() {
        return history;
    }

    public void setHistory(String history) {
        this.history = history;
    }

    public int getCafetriaId() {
        return cafetriaId;
    }

    public void setCafetriaId(int cafetriaId) {
        this.cafetriaId = cafetriaId;
    }

    public String getCafetriaName() {
        return cafetriaName;
    }

    public void setCafetriaName(String cafetriaName) {
        this.cafetriaName = cafetriaName;
    }
    
}
