/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package eg.gov.iti.cafetria.view.dto;

/**
 *
 * @author Nesma
 */

import eg.gov.iti.cafetria.model.dal.domain.Category;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import org.hibernate.validator.constraints.Range;
import org.springframework.web.multipart.commons.CommonsMultipartFile;
public class ItemDto {
    
    
        @Size(min=1, max=45)
        @Pattern(regexp="^[^-\\s][a-zA-Z_\\s-]+$")
        private String name;
        @Range(min =1, max = 2147483647)
        private int price;
        @NotNull
        private Category category;
        private CommonsMultipartFile file;
        @Size(min=1, max=45)
        @Pattern(regexp="^[^-\\s][a-zA-Z_\\s-]+$")
        private String description;
        @Range(min =1, max = 2147483647)
        private int quantity;
        private String image;
        private int id ;
        
        
        
        
        

    public ItemDto() {
    }

    public ItemDto(String name, int price, Category category, CommonsMultipartFile file) {
        this.name = name;
        this.price = price;
        this.category = category;
        this.file = file;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public CommonsMultipartFile getFile() {
        return file;
    }

    public void setFile(CommonsMultipartFile file) {
        this.file = file;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
       
       
       
       
}
