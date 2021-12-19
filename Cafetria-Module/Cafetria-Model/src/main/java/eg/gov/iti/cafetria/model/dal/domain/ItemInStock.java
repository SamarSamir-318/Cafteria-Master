/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package eg.gov.iti.cafetria.model.dal.domain;

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
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Nour
 */
@Entity
@Table(name = "item_in_stock")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "ItemInStock.findAll", query = "SELECT i FROM ItemInStock i")
    , @NamedQuery(name = "ItemInStock.findByQuantity", query = "SELECT i FROM ItemInStock i WHERE i.quantity = :quantity")
    , @NamedQuery(name = "ItemInStock.findById", query = "SELECT i FROM ItemInStock i WHERE i.id = :id")})
public class ItemInStock implements Serializable {

    private static final long serialVersionUID = 1L;
    @Column(name = "quantity")
    private Integer quantity;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    @JoinColumn(name = "item", referencedColumnName = "id")
    @ManyToOne(optional = false)
    private Item item;
    @JoinColumn(name = "stock", referencedColumnName = "id")
    @ManyToOne(optional = false)
    private Stock stock;

    public ItemInStock() {
    }

    public ItemInStock(Integer id) {
        this.id = id;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Item getItem() {
        return item;
    }

    public void setItem(Item item) {
        this.item = item;
    }

    public Stock getStock() {
        return stock;
    }

    public void setStock(Stock stock) {
        this.stock = stock;
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
        if (!(object instanceof ItemInStock)) {
            return false;
        }
        ItemInStock other = (ItemInStock) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "xx.ItemInStock[ id=" + id + " ]";
    }
    
}
