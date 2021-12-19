/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package eg.gov.iti.cafetria.dto;

import java.util.ArrayList;
import java.util.Collection;
import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

/**
 *
 * @author Nour
 */
public class CustomerDTO {

    private Integer id;
    @Size(min = 3, max = 40, message = "{user.name.size}")
    @Pattern(regexp = "[a-zA-Z]+([\\s'-][a-zA-Z]+)*", message = "{user.nameformat}")
    private String name;
    private String email;
    @Size(min = 1, message = "{user.role.notnull}")
    private Collection<Integer> rolesID = new ArrayList<>();
    private Collection<String> rolesName = new ArrayList<>();

//    @DecimalMin(value = "0", message = "{customer.balance.value}")
    @NotNull(message = "{user.notnull}")
    private Integer balance;

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

    public Collection<Integer> getRolesID() {
        return rolesID;
    }

    public void setRolesID(Collection<Integer> rolesID) {
        this.rolesID = rolesID;
    }

    public Integer getBalance() {
        return balance;
    }

    public void setBalance(Integer balance) {
        this.balance = balance;
    }

    public Collection<String> getRolesName() {
        return rolesName;
    }

    public void setRolesName(Collection<String> rolesName) {
        this.rolesName = rolesName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

}
