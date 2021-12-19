/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package eg.gov.iti.cafetria.model.dal.domain;

import java.io.Serializable;
import java.util.Collection;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;
import org.hibernate.validator.constraints.NotEmpty;

/**
 *
 * @author OsamaPC
 */
@Entity
@Table(name = "stock")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Stock.findAll", query = "SELECT s FROM Stock s")
    , @NamedQuery(name = "Stock.findById", query = "SELECT s FROM Stock s WHERE s.id = :id")
    , @NamedQuery(name = "Stock.findByLocation", query = "SELECT s FROM Stock s WHERE s.location = :location")})
public class Stock implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    @Basic(optional = false)
    @Column(name = "location")
    @Size(max=20, message="{stock.longLocation}")
    @Pattern(regexp="^[^-\\s][a-zA-Z_\\s-]+$", message="{stock.invalidLocation}")
    private String location;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "stock")
    private Collection<StockHasRowMaterial> stockHasRowMaterialCollection;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "stock")
    private Collection<ItemInStock> itemInStockCollection;
    @JoinColumn(name = "cafetria", referencedColumnName = "id")
    @ManyToOne(optional = false)
    @NotNull(message = "{stock.cafetriaErr}")
    private Cafetria cafetria;

    public Stock() {
    }

    public Stock(Integer id) {
        this.id = id;
    }

    public Stock(Integer id, String location) {
        this.id = id;
        this.location = location;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    @XmlTransient
    public Collection<StockHasRowMaterial> getStockHasRowMaterialCollection() {
        return stockHasRowMaterialCollection;
    }

    public void setStockHasRowMaterialCollection(Collection<StockHasRowMaterial> stockHasRowMaterialCollection) {
        this.stockHasRowMaterialCollection = stockHasRowMaterialCollection;
    }

    @XmlTransient
    public Collection<ItemInStock> getItemInStockCollection() {
        return itemInStockCollection;
    }

    public void setItemInStockCollection(Collection<ItemInStock> itemInStockCollection) {
        this.itemInStockCollection = itemInStockCollection;
    }

    public Cafetria getCafetria() {
        return cafetria;
    }

    public void setCafetria(Cafetria cafetria) {
        this.cafetria = cafetria;
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
        if (!(object instanceof Stock)) {
            return false;
        }
        Stock other = (Stock) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.mycompany.testnour.Stock[ id=" + id + " ]";
    }
    
}
