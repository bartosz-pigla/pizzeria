package com.pizzashop.models;

import com.pizzashop.models.interfaces.Nameable;
import org.hibernate.annotations.*;
import org.hibernate.validator.constraints.NotEmpty;

import javax.persistence.*;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;

/**
 * Created by Bartosz Pigla on 09/12/2016.
 */
@Entity
@PrimaryKeyJoinColumn(name = "productId")
@Cacheable
public class Sauce extends Product implements Serializable, Nameable {

    @NotEmpty
    @org.hibernate.annotations.Cache(usage = CacheConcurrencyStrategy.READ_ONLY)
    private Set<Seasoning> seasonings=new HashSet<>();

    public Sauce(String name, String description, Double price, Set<Rebate> rebates, String url, Set<Seasoning> seasonings, boolean archival) {
        super(name, description, price, rebates, url,archival);
        this.seasonings = seasonings;
        this.type="sos";
    }

    public Sauce(){
        this.type="sos";
    }


    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(
            name = "SauceSeasonings",
            joinColumns = @JoinColumn(name = "sauceId", referencedColumnName = "productId"),
            inverseJoinColumns = @JoinColumn(name = "seasoningId", referencedColumnName = "seasoningId")
    )
    public Set<Seasoning> getSeasonings() {
        return seasonings;
    }

    public void setSeasonings(Set<Seasoning> seasonings) {
        this.seasonings = seasonings;
    }

    public void addSeasoning(Seasoning seasoning){
        seasonings.add(seasoning);
    }
}
