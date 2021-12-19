/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package eg.gov.iti.cafetria.dto;

import java.util.ArrayList;
import java.util.Collection;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

/**
 *
 * @author Administrator
 */
public class RoleDTO {

    private Integer id;
    @Size(min = 2, max = 40, message = "{role.name.size}")
    @Pattern(regexp = "[a-zA-Z]+([\\s'-][a-zA-Z]+)*", message = "{user.nameformat}")
    private String name;
    @Size(min = 1, message = "{role.privilage.notnull}")
    private Collection<Integer> privilagesID = new ArrayList<>();
    private Collection<String> privilagesName = new ArrayList<>();

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

    public Collection<Integer> getPrivilagesID() {
        return privilagesID;
    }

    public void setPrivilagesID(Collection<Integer> privilagesID) {
        this.privilagesID = privilagesID;
    }

    public Collection<String> getPrivilagesName() {
        return privilagesName;
    }

    public void setPrivilagesName(Collection<String> privilagesName) {
        this.privilagesName = privilagesName;
    }
    
    
}
