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
@Table(name = "stock_has_row_material")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "StockHasRowMaterial.findAll", query = "SELECT s FROM StockHasRowMaterial s")
    , @NamedQuery(name = "StockHasRowMaterial.findByQuantity", query = "SELECT s FROM StockHasRowMaterial s WHERE s.quantity = :quantity")
    , @NamedQuery(name = "StockHasRowMaterial.findById", query = "SELECT s FROM StockHasRowMaterial s WHERE s.id = :id")})
public class StockHasRowMaterial implements Serializable {

    private static final long serialVersionUID = 1L;
    @Column(name = "quantity")
    private Integer quantity;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    @JoinColumn(name = "row_material", referencedColumnName = "id")
    @ManyToOne(optional = false)
    private RowMaterial rowMaterial;
    @JoinColumn(name = "stock", referencedColumnName = "id")
    @ManyToOne(optional = false)
    private Stock stock;

    public StockHasRowMaterial() {
    }

    public StockHasRowMaterial(Integer id) {
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

    public RowMaterial getRowMaterial() {
        return rowMaterial;
    }

    public void setRowMaterial(RowMaterial rowMaterial) {
        this.rowMaterial = rowMaterial;
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
        if (!(object instanceof StockHasRowMaterial)) {
            return false;
        }
        StockHasRowMaterial other = (StockHasRowMaterial) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "xx.StockHasRowMaterial[ id=" + id + " ]";
    }
    
}
