package com.pizzashop.productFilters;

import com.pizzashop.models.Ingredient;
import com.pizzashop.models.Rebate;
import com.pizzashop.models.enums.DoughType;

import java.math.BigDecimal;
import java.util.List;

/**
 * Created by barte on 31/12/2016.
 */
public class PizzaFilter extends ProductFilter {
    protected List<Ingredient> ingredients;

    protected BigDecimal doughMinPrice, doughMaxPrice;

    protected List<DoughType> doughTypes;

    public PizzaFilter(List<Rebate> rebates, List<String> names, BigDecimal minPrice, BigDecimal maxPrice, List<Ingredient> ingredients, BigDecimal doughMinPrice, BigDecimal doughMaxPrice, List<DoughType> doughTypes) {
        super(rebates, names, minPrice, maxPrice);
        this.ingredients = ingredients;
        this.doughMinPrice = doughMinPrice;
        this.doughMaxPrice = doughMaxPrice;
        this.doughTypes = doughTypes;
    }

    public List<Ingredient> getIngredients() {
        return ingredients;
    }

    public void setIngredients(List<Ingredient> ingredients) {
        this.ingredients = ingredients;
    }

    public BigDecimal getDoughMinPrice() {
        return doughMinPrice;
    }

    public void setDoughMinPrice(BigDecimal doughMinPrice) {
        this.doughMinPrice = doughMinPrice;
    }

    public BigDecimal getDoughMaxPrice() {
        return doughMaxPrice;
    }

    public void setDoughMaxPrice(BigDecimal doughMaxPrice) {
        this.doughMaxPrice = doughMaxPrice;
    }

    public List<DoughType> getDoughTypes() {
        return doughTypes;
    }

    public void setDoughTypes(List<DoughType> doughTypes) {
        this.doughTypes = doughTypes;
    }
}
