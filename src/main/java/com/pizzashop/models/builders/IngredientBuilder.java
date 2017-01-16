package com.pizzashop.models.builders;

import com.pizzashop.models.Ingredient;
import com.pizzashop.models.Pizza;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

public class IngredientBuilder {
    private String name;
    private Set<Pizza> pizzas;

    public IngredientBuilder setName(String name) {
        this.name = name;
        return this;
    }

    public IngredientBuilder setPizzas(Pizza... pizzas) {
        this.pizzas = new HashSet<>(Arrays.asList(pizzas));
        return this;
    }

    public Ingredient createIngredient() {
        return new Ingredient(name, pizzas);
    }
}