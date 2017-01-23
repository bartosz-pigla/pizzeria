package com.pizzashop.models.builders;

import com.pizzashop.models.Ingredient;
import com.pizzashop.models.Pizza;
import com.pizzashop.models.Rebate;
import com.pizzashop.models.enums.DoughType;
import com.pizzashop.models.enums.PizzaSize;

import java.math.BigDecimal;
import java.util.HashSet;
import java.util.Set;

public class PizzaBuilder {
    private String name;
    private String description;
    private BigDecimal price;
    private Set<Rebate> rebates=new HashSet<>();
    private String url;
    private DoughType doughType;
    private BigDecimal doughPrice;
    private Set<Ingredient> ingredients=new HashSet<>();
    private PizzaSize pizzaSize;

    public PizzaBuilder setName(String name) {
        this.name = name;
        return this;
    }

    public PizzaBuilder setDescription(String description) {
        this.description = description;
        return this;
    }

    public PizzaBuilder setPrice(BigDecimal price) {
        this.price = price;
        return this;
    }

    public PizzaBuilder setRebates(Set<Rebate> rebates) {
        this.rebates = rebates;
        return this;
    }

    public PizzaBuilder setUrl(String url) {
        this.url = url;
        return this;
    }

    public PizzaBuilder setDoughType(DoughType doughType) {
        this.doughType = doughType;
        return this;
    }

    public PizzaBuilder setDoughPrice(BigDecimal doughPrice) {
        this.doughPrice = doughPrice;
        return this;
    }

    public PizzaBuilder setIngredients(Set<Ingredient> ingredients) {
        this.ingredients = ingredients;
        return this;
    }

    public PizzaBuilder setPizzaSize(PizzaSize pizzaSize) {
        this.pizzaSize = pizzaSize;
        return this;
    }

    public PizzaBuilder addRebates(Rebate... rebates) {
        for (Rebate rebate:rebates
                ) {
            this.rebates.add(rebate);
        }
        return this;
    }

    public Pizza createPizza() {
        return new Pizza(name, description, price, rebates, url, doughType, doughPrice, ingredients, pizzaSize);
    }
}