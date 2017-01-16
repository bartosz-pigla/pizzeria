package com.pizzashop.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.pizzashop.annotations.Price;
import com.pizzashop.models.enums.DoughType;
import org.hibernate.annotations.*;
import org.hibernate.annotations.Cache;
import org.hibernate.validator.constraints.NotEmpty;

import javax.persistence.*;
import javax.persistence.Entity;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.*;

/**
 * Created by barte on 09/12/2016.
 */
@Entity
@PrimaryKeyJoinColumn(name = "productId")
@Cacheable
@org.hibernate.annotations.Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class Pizza extends Product  implements Serializable {
    @Enumerated(EnumType.STRING)
    @NotNull
    private DoughType doughType;

    @NotNull
    @Price
    private BigDecimal doughPrice;

    @Cache(usage = CacheConcurrencyStrategy.TRANSACTIONAL)
    @NotEmpty
    private Set<Ingredient> ingredients=new HashSet<>();

    public Pizza(
            String name,
            String description,
            BigDecimal price,
            Set<Rebate> rebates,
            DoughType doughType,
            BigDecimal doughPrice,
            Set<Ingredient> ingredients)
    {
        super(name,description,price,rebates);
        this.doughType=doughType;
        this.doughPrice=doughPrice;
        this.ingredients=ingredients;
    }

    public Pizza() {

    }

    @Basic
    @Column(name = "doughTypes")
    public DoughType getDoughType() {
        return doughType;
    }

    public void setDoughType(DoughType doughType) {
        this.doughType = doughType;
    }

    @Basic
    @Column(name = "doughPrice")
    public BigDecimal getDoughPrice() {
        return doughPrice;
    }

    public void setDoughPrice(BigDecimal doughPrice) {
        this.doughPrice = doughPrice;
    }

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(
            name = "PizzaIngredient",
            joinColumns = @JoinColumn(name = "pizzaId", referencedColumnName = "productId"),
            inverseJoinColumns = @JoinColumn(name = "ingredientId", referencedColumnName = "ingredientId")
    )
    public Set<Ingredient> getIngredients() {
        return ingredients;
    }

    public void setIngredients(Set<Ingredient> ingredients) {
        this.ingredients = ingredients;
    }

    public void addIngredient(Ingredient ingredient) {
        ingredients.add(ingredient);
    }
}
