/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package eg.gov.iti.cafetria.model.dal.domain;

import eg.gov.iti.cafetria.model.Custom.validator.UniqueFields;
import java.io.Serializable;
import java.util.Collection;
import java.util.Date;
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
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;
import org.hibernate.validator.constraints.NotEmpty;
import org.springframework.format.annotation.DateTimeFormat;

/**
 *
 * @author Nour
 */
@Entity
@Table(name = "cafetria")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Cafetria.findAll", query = "SELECT c FROM Cafetria c")
    , @NamedQuery(name = "Cafetria.findById", query = "SELECT c FROM Cafetria c WHERE c.id = :id")
    , @NamedQuery(name = "Cafetria.findByName", query = "SELECT c FROM Cafetria c WHERE c.name = :name")
    , @NamedQuery(name = "Cafetria.findByDescription", query = "SELECT c FROM Cafetria c WHERE c.description = :description")
    , @NamedQuery(name = "Cafetria.findByStartTime", query = "SELECT c FROM Cafetria c WHERE c.startTime = :startTime")
    , @NamedQuery(name = "Cafetria.findByEndTime", query = "SELECT c FROM Cafetria c WHERE c.endTime = :endTime")
    , @NamedQuery(name = "Cafetria.findByPhone", query = "SELECT c FROM Cafetria c WHERE c.phone = :phone")})
@UniqueFields(classType = Cafetria.class,id = "id",message = "dupliated",properties = {"name"})
public class Cafetria implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    @Basic(optional = false)
    @Column(name = "name")
    @Size(max = 20,message="{cafetria.longName}")
    @Pattern(regexp="^[^-\\s][a-zA-Z_\\s-]+$", message="{cafetria.invalidName}")
    private String name;
    @Column(name = "description")
    @Size(max = 50,message="{cafetria.longDescription}")
    private String description;
    @Basic(optional = false)
    @Column(name = "start_time")
    @DateTimeFormat(pattern = "MM/dd/yyyy")
    @Temporal(TemporalType.TIMESTAMP)
    @NotNull(message="{cafetria.nullStartTime}") 
    private Date startTime;
    @Basic(optional = false)
    @Column(name = "end_time")
    @Temporal(TemporalType.TIMESTAMP)
    @DateTimeFormat(pattern="MM/dd/yyyy")
    @NotNull(message="{cafetria.nullEndTime}") 
    private Date endTime;
    @Column(name = "phone")
    private Integer phone;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "cafetria")
    private Collection<Stock> stockCollection;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "cafetria")
    private Collection<Worker> workerCollection;

    public Cafetria() {
    }

    public Cafetria(Integer id) {
        this.id = id;
    }

    public Cafetria(Integer id, String name, Date startTime, Date endTime) {
        this.id = id;
        this.name = name;
        this.startTime = startTime;
        this.endTime = endTime;
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

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Date getStartTime() {
        return startTime;
    }

    public void setStartTime(Date startTime) {
        this.startTime = startTime;
    }

    public Date getEndTime() {
        return endTime;
    }

    public void setEndTime(Date endTime) {
        this.endTime = endTime;
    }

    public Integer getPhone() {
        return phone;
    }

    public void setPhone(Integer phone) {
        this.phone = phone;
    }

    @XmlTransient
    public Collection<Stock> getStockCollection() {
        return stockCollection;
    }

    public void setStockCollection(Collection<Stock> stockCollection) {
        this.stockCollection = stockCollection;
    }

    @XmlTransient
    public Collection<Worker> getWorkerCollection() {
        return workerCollection;
    }

    public void setWorkerCollection(Collection<Worker> workerCollection) {
        this.workerCollection = workerCollection;
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
        if (!(object instanceof Cafetria)) {
            return false;
        }
        Cafetria other = (Cafetria) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "xx.Cafetria[ id=" + id + " ]";
    }
    
}
