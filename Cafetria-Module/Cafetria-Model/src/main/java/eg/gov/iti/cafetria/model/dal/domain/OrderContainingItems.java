/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package eg.gov.iti.cafetria.model.dal.domain;

import eg.gov.iti.cafetria.model.dal.dao.ItemRepository;
import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.xml.bind.annotation.XmlRootElement;
import org.hibernate.validator.constraints.Range;
import org.springframework.beans.factory.annotation.Autowired;

/**
 *
 * @author Ahmed labib
 */
@Entity
@Table(name = "order_containing_items")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "OrderContainingItems.findAll", query = "SELECT o FROM OrderContainingItems o")
    , @NamedQuery(name = "OrderContainingItems.findById", query = "SELECT o FROM OrderContainingItems o WHERE o.id = :id")
    , @NamedQuery(name = "OrderContainingItems.findByQuantity", query = "SELECT o FROM OrderContainingItems o WHERE o.quantity = :quantity")})
public class OrderContainingItems implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    @Basic(optional = false)
    @Column(name = "quantity")
    @Range(min = 1)
    @NotNull
    private int quantity;
    @JoinColumn(name = "item", referencedColumnName = "id")
    @ManyToOne(optional = false)
    private Item item;
    @JoinColumn(name = "corder", referencedColumnName = "id")
    @ManyToOne(optional = false)
    private Order order1;
  
    
    
    
    
    public OrderContainingItems() {
    }

    public OrderContainingItems(Integer id) {
        this.id = id;
    }

    public OrderContainingItems(Integer id, int quantity) {
        this.id = id;
        this.quantity = quantity;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public Item getItem() {
        return item;
    }

    public void setItem(Item item) {
        this.item = item;
    }

    public Order getOrder1() {
        return order1;
    }

    public void setOrder1(Order order1) {
        this.order1 = order1;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof OrderContainingItems)) {
            return false;
        }
        OrderContainingItems other = (OrderContainingItems) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "egy.gov.iti.testjp.OrderContainingItems[ id=" + id + " ]";
    }
    
    
    
    
    
}
