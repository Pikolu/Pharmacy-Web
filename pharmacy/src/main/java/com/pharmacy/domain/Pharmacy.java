package com.pharmacy.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;
import org.springframework.data.elasticsearch.annotations.Document;
import org.springframework.data.elasticsearch.annotations.Field;
import org.springframework.data.elasticsearch.annotations.FieldIndex;
import org.springframework.data.elasticsearch.annotations.FieldType;

import javax.persistence.*;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

/**
 * A Pharmacy.
 */
@Entity
@Table(name = "pharmacy")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
@Document(indexName = "pharmacy")
public class Pharmacy implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(name = "name")
    private String name;

    @Column(name = "shipping")
    private Double shipping;

    @Column(name = "logo_url")
    private String logoURL;

    @Column(name = "total_evaluation_points")
    private Integer totalEvaluationPoints;

    @ManyToMany
    @Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
    @JoinTable(name = "pharmacy_payment",
               joinColumns = @JoinColumn(name="pharmacys_id", referencedColumnName="ID"),
               inverseJoinColumns = @JoinColumn(name="payments_id", referencedColumnName="ID"))
    private Set<Payment> payments = new HashSet<>();

    @OneToOne
    private User user;

    @OneToMany(mappedBy = "pharmacy")
    @JsonIgnore
    @Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
    private Set<Evaluation> evaluations = new HashSet<>();

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Double getShipping() {
        return shipping;
    }

    public void setShipping(Double shipping) {
        this.shipping = shipping;
    }

    public String getLogoURL() {
        return logoURL;
    }

    public void setLogoURL(String logoURL) {
        this.logoURL = logoURL;
    }

    public Integer getTotalEvaluationPoints() {
        return totalEvaluationPoints;
    }

    public void setTotalEvaluationPoints(Integer totalEvaluationPoints) {
        this.totalEvaluationPoints = totalEvaluationPoints;
    }

    public Set<Payment> getPayments() {
        if (payments == null) {
            payments = new HashSet<>();
        }
        return payments;
    }

    public void setPayments(Set<Payment> payments) {
        this.payments = payments;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Set<Evaluation> getEvaluations() {
        if (evaluations == null) {
            evaluations = new HashSet<>();
        }
        return evaluations;
    }

    public void setEvaluations(Set<Evaluation> evaluations) {
        this.evaluations = evaluations;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        Pharmacy pharmacy = (Pharmacy) o;

        if ( ! Objects.equals(id, pharmacy.id)) return false;

        return true;
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(id);
    }

    @Override
    public String toString() {
        return "Pharmacy{" +
            "id=" + id +
            ", name='" + name + "'" +
            ", shipping='" + shipping + "'" +
            ", logoURL='" + logoURL + "'" +
            ", totalEvaluationPoints='" + totalEvaluationPoints + "'" +
            '}';
    }
}
