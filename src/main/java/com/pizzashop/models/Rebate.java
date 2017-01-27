package com.pizzashop.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.pizzashop.models.interfaces.Nameable;
import org.hibernate.annotations.*;
import org.hibernate.validator.constraints.Length;

import javax.persistence.*;
import javax.persistence.Entity;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

/**
 * Created by Bartosz Pigla on 10/12/2016.
 */
@Entity
@Cacheable
@org.hibernate.annotations.Cache(usage = CacheConcurrencyStrategy.READ_ONLY)
public class Rebate  implements Serializable, Nameable {
    private Integer rebateId;
    @NotNull
    @Length(max = 30)
    private String name;

    @JsonIgnore
    private Set<Product> products=new HashSet<>();

    public Rebate(String name, Set<Product> products) {
        this.name = name;
        this.products = products;
    }

    public Rebate() {
    }

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "rebateId")
    public Integer getRebateId() {
        return rebateId;
    }

    public void setRebateId(Integer rebateId) {
        this.rebateId = rebateId;
    }
    @Basic
    @Column(name = "name")
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @ManyToMany(mappedBy = "rebates", fetch = FetchType.LAZY)
    public Set<Product> getProducts() {
        return products;
    }

    public void setProducts(Set<Product> products) {
        this.products = products;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Rebate rebate = (Rebate) o;
        return Objects.equals(name, rebate.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name);
    }
}
