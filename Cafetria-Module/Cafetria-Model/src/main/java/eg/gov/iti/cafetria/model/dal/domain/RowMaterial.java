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
@Table(name = "row_material")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "RowMaterial.findAll", query = "SELECT r FROM RowMaterial r")
    , @NamedQuery(name = "RowMaterial.findById", query = "SELECT r FROM RowMaterial r WHERE r.id = :id")
    , @NamedQuery(name = "RowMaterial.findByName", query = "SELECT r FROM RowMaterial r WHERE r.name = :name")})
public class RowMaterial implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    @Basic(optional = false)
    @Column(name = "name")
    @Size(max=20, message="{rowMaterial.longName}")
    @Pattern(regexp="^[^-\\s][a-zA-Z_\\s-]+$", message="{rowMaterial.invalidName}")
    private String name;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "rowMaterial")
    private Collection<StockHasRowMaterial> stockHasRowMaterialCollection;

    public RowMaterial() {
    }

    public RowMaterial(Integer id) {
        this.id = id;
    }

    public RowMaterial(Integer id, String name) {
        this.id = id;
        this.name = name;
    }

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

    @XmlTransient
    public Collection<StockHasRowMaterial> getStockHasRowMaterialCollection() {
        return stockHasRowMaterialCollection;
    }

    public void setStockHasRowMaterialCollection(Collection<StockHasRowMaterial> stockHasRowMaterialCollection) {
        this.stockHasRowMaterialCollection = stockHasRowMaterialCollection;
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
        if (!(object instanceof RowMaterial)) {
            return false;
        }
        RowMaterial other = (RowMaterial) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.mycompany.testnour.RowMaterial[ id=" + id + " ]";
    }
    
}
