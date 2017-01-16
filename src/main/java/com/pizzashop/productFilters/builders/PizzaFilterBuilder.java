package com.pizzashop.productFilters.builders;

import com.pizzashop.models.Ingredient;
import com.pizzashop.models.Rebate;
import com.pizzashop.models.enums.DoughType;
import com.pizzashop.productFilters.PizzaFilter;

import java.math.BigDecimal;
import java.util.List;

public class PizzaFilterBuilder {
    private List<Rebate> rebates;
    private List<String> names;
    private BigDecimal minPrice;
    private BigDecimal maxPrice;
    private List<Ingredient> ingredients;
    private BigDecimal doughMinPrice;
    private BigDecimal doughMaxPrice;
    private List<DoughType> doughTypes;

    public PizzaFilterBuilder setRebates(List<Rebate> rebates) {
        this.rebates = rebates;
        return this;
    }

    public PizzaFilterBuilder setNames(List<String> names) {
        this.names = names;
        return this;
    }

    public PizzaFilterBuilder setMinPrice(BigDecimal minPrice) {
        this.minPrice = minPrice;
        return this;
    }

    public PizzaFilterBuilder setMaxPrice(BigDecimal maxPrice) {
        this.maxPrice = maxPrice;
        return this;
    }

    public PizzaFilterBuilder setIngredients(List<Ingredient> ingredients) {
        this.ingredients = ingredients;
        return this;
    }

    public PizzaFilterBuilder setDoughMinPrice(BigDecimal doughMinPrice) {
        this.doughMinPrice = doughMinPrice;
        return this;
    }

    public PizzaFilterBuilder setDoughMaxPrice(BigDecimal doughMaxPrice) {
        this.doughMaxPrice = doughMaxPrice;
        return this;
    }

    public PizzaFilterBuilder setDoughTypes(List<DoughType> doughTypes) {
        this.doughTypes = doughTypes;
        return this;
    }

    public PizzaFilter createPizzaFilter() {
        return new PizzaFilter(rebates, names, minPrice, maxPrice, ingredients, doughMinPrice, doughMaxPrice, doughTypes);
    }
}