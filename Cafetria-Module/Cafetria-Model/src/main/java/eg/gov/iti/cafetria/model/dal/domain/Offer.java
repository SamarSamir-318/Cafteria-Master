/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package eg.gov.iti.cafetria.model.dal.domain;

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
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.Future;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Past;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;
import org.hibernate.validator.constraints.NotEmpty;
import org.hibernate.validator.constraints.Range;
import org.springframework.format.annotation.DateTimeFormat;

/**
 *
 * @author Administrator
 */
@Entity
@Table(name = "offer")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Offer.findAll", query = "SELECT o FROM Offer o")
    , @NamedQuery(name = "Offer.findById", query = "SELECT o FROM Offer o WHERE o.id = :id")
    , @NamedQuery(name = "Offer.findByDescription", query = "SELECT o FROM Offer o WHERE o.description = :description")
    , @NamedQuery(name = "Offer.findByDiscountPercentage", query = "SELECT o FROM Offer o WHERE o.discountPercentage = :discountPercentage")
    , @NamedQuery(name = "Offer.findByStartTime", query = "SELECT o FROM Offer o WHERE o.startTime = :startTime")
    , @NamedQuery(name = "Offer.findByEndTime", query = "SELECT o FROM Offer o WHERE o.endTime = :endTime")})
public class Offer implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    @Column(name = "description")
    @NotEmpty
    private String description;
    @Basic(optional = false)
    @Column(name = "discount_percentage")
    @Range(min = 1, max = 100)
    private int discountPercentage;
    @Column(name = "start_time")
    @Temporal(TemporalType.TIMESTAMP)
    @DateTimeFormat(pattern="MM/dd/yyyy")
    @NotNull 
    //@Past
    private Date startTime;
    @Column(name = "end_time")
    @Temporal(TemporalType.TIMESTAMP)
    @DateTimeFormat(pattern="dd/MM/yyyy")
    @NotNull
    //@Future
    private Date endTime;
    @JoinTable(name = "corder_has_offer", joinColumns = {
        @JoinColumn(name = "Offer_id", referencedColumnName = "id")}, inverseJoinColumns = {
        @JoinColumn(name = "cOrder_id", referencedColumnName = "id")})
    @ManyToMany
    private Collection<Order> corderCollection;
    @ManyToMany(mappedBy = "offerCollection",cascade = CascadeType.MERGE)
    private Collection<Item> itemCollection;

    public Offer() {
    }

    public Offer(Integer id) {
        this.id = id;
    }

    public Offer(Integer id, int discountPercentage) {
        this.id = id;
        this.discountPercentage = discountPercentage;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getDiscountPercentage() {
        return discountPercentage;
    }

    public void setDiscountPercentage(int discountPercentage) {
        this.discountPercentage = discountPercentage;
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

    @XmlTransient
    public Collection<Order> getCorderCollection() {
        return corderCollection;
    }

    public void setCorderCollection(Collection<Order> corderCollection) {
        this.corderCollection = corderCollection;
    }

    @XmlTransient
    public Collection<Item> getItemCollection() {
        return itemCollection;
    }

    public void setItemCollection(Collection<Item> itemCollection) {
        this.itemCollection = itemCollection;
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
        if (!(object instanceof Offer)) {
            return false;
        }
        Offer other = (Offer) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "pk.Offer[ id=" + id + " ]";
    }
    
}
