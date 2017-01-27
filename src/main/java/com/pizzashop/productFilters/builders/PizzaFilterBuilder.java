package com.pizzashop.productFilters.builders;

import com.pizzashop.models.Ingredient;
import com.pizzashop.models.Rebate;
import com.pizzashop.models.enums.DoughType;
import com.pizzashop.models.enums.PizzaSize;
import com.pizzashop.productFilters.PizzaFilter;

import java.util.List;

public class PizzaFilterBuilder {
    private List<Rebate> rebates;
    private List<String> names;
    private Double minPrice;
    private Double maxPrice;
    private List<Ingredient> ingredients;
    private Double doughMinPrice;
    private Double doughMaxPrice;
    private List<DoughType> doughTypes;
    private List<PizzaSize> pizzaSizes;

    public PizzaFilterBuilder setRebates(List<Rebate> rebates) {
        this.rebates = rebates;
        return this;
    }

    public PizzaFilterBuilder setNames(List<String> names) {
        this.names = names;
        return this;
    }

    public PizzaFilterBuilder setMinPrice(Double minPrice) {
        this.minPrice = minPrice;
        return this;
    }

    public PizzaFilterBuilder setMaxPrice(Double maxPrice) {
        this.maxPrice = maxPrice;
        return this;
    }

    public PizzaFilterBuilder setIngredients(List<Ingredient> ingredients) {
        this.ingredients = ingredients;
        return this;
    }

    public PizzaFilterBuilder setDoughMinPrice(Double doughMinPrice) {
        this.doughMinPrice = doughMinPrice;
        return this;
    }

    public PizzaFilterBuilder setDoughMaxPrice(Double doughMaxPrice) {
        this.doughMaxPrice = doughMaxPrice;
        return this;
    }

    public PizzaFilterBuilder setDoughTypes(List<DoughType> doughTypes) {
        this.doughTypes = doughTypes;
        return this;
    }

    public PizzaFilterBuilder setPizzaSizes(List<PizzaSize> pizzaSizes){
        this.pizzaSizes=pizzaSizes;
        return this;
    }

    public PizzaFilter createPizzaFilter() {
        return new PizzaFilter(rebates, names, minPrice, maxPrice, ingredients, doughMinPrice, doughMaxPrice, doughTypes,pizzaSizes);
    }
}