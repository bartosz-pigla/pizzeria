package com.pizzashop.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.pizzashop.models.interfaces.Nameable;
import org.hibernate.annotations.*;
import org.hibernate.annotations.Cache;
import org.hibernate.validator.constraints.Length;

import javax.persistence.*;
import javax.persistence.Entity;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

/**
 * Created by Bartosz Pigla on 09/12/2016.
 */
@Entity
@Cacheable
@org.hibernate.annotations.Cache(usage = CacheConcurrencyStrategy.READ_ONLY)
public class Ingredient implements Serializable, Nameable{
    private Integer ingredientId;

    @NotNull    @Length(max=20)
    private String name;

    @JsonIgnore
    private Set<Pizza> pizzas=new HashSet<>();

    public Ingredient(String name, Set<Pizza> pizzas) {
        this.name = name;
        this.pizzas = pizzas;
    }

    public Ingredient() {
    }

    @Id @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "ingredientId")
    public Integer getIngredientId() {
        return ingredientId;
    }

    public void setIngredientId(Integer idIngredient) {
        this.ingredientId = idIngredient;
    }

    @Basic
    @Column(name = "name")
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @ManyToMany(mappedBy = "ingredients", fetch = FetchType.LAZY)
    public Set<Pizza> getPizzas() {
        return pizzas;
    }

    public void setPizzas(Set<Pizza> pizzaList) {
        this.pizzas = pizzaList;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Ingredient that = (Ingredient) o;
        return Objects.equals(name, that.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name);
    }


}
