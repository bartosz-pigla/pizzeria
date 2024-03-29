package com.pizzashop.models;

import com.pizzashop.annotations.Price;
import com.pizzashop.models.enums.DoughType;
import com.pizzashop.models.enums.PizzaSize;
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
 * Created by Bartosz Pigla on 09/12/2016.
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
    private Double doughPrice;

    @Cache(usage = CacheConcurrencyStrategy.READ_ONLY)
    @NotEmpty
    private Set<Ingredient> ingredients=new HashSet<>();

    @Enumerated(EnumType.STRING)
    @NotNull
    private PizzaSize size;

//    public Pizza(String name, String description, BigDecimal price, Set<Rebate> rebates, String url, DoughType doughType, BigDecimal doughPrice, Set<Ingredient> ingredients) {
//        super(name, description, price, rebates, url);
//        this.doughType = doughType;
//        this.doughPrice = doughPrice;
//        this.ingredients = ingredients;
//
//        this.type="pizza";
//    }


    public Pizza(String name, String description, Double price, Set<Rebate> rebates, String url, DoughType doughType, Double doughPrice, Set<Ingredient> ingredients, PizzaSize size, boolean archival) {
        super(name, description, price, rebates, url,archival);
        this.doughType = doughType;
        this.doughPrice = doughPrice;
        this.ingredients = ingredients;
        this.size = size;
        this.type="pizza";
    }

    public Pizza() {
        this.type="pizza";
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
    public Double getDoughPrice() {
        return doughPrice;
    }

    public void setDoughPrice(Double doughPrice) {
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

    public PizzaSize getSize() {
        return size;
    }

    public void setSize(PizzaSize pizzaSize) {
        this.size = pizzaSize;
    }
}
