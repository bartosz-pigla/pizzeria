package com.pizzashop.productFilters;

import com.pizzashop.models.Ingredient;
import com.pizzashop.models.Rebate;
import com.pizzashop.models.enums.DoughType;
import com.pizzashop.models.enums.PizzaSize;

import java.math.BigDecimal;
import java.util.List;

/**
 * Created by Bartosz Pigla on 31/12/2016.
 */
public class PizzaFilter extends ProductFilter {
    protected List<Ingredient> ingredients;

    protected Double doughMinPrice = new Double("0.0");
    protected Double doughMaxPrice = new Double("0.0");

    protected List<DoughType> doughTypes;

    protected List<PizzaSize> pizzaSizes;

    public PizzaFilter(List<Rebate> rebates, List<String> names, Double minPrice, Double maxPrice, List<Ingredient> ingredients, Double doughMinPrice, Double doughMaxPrice, List<DoughType> doughTypes, List<PizzaSize> pizzaSizes) {
        super(rebates, names, minPrice, maxPrice);
        this.ingredients = ingredients;
        this.doughMinPrice = doughMinPrice;
        this.doughMaxPrice = doughMaxPrice;
        this.doughTypes = doughTypes;
        this.pizzaSizes = pizzaSizes;
    }

    public PizzaFilter(){
    }

    //    public PizzaFilter(List<Rebate> rebates, List<String> names, BigDecimal minPrice, BigDecimal maxPrice, List<Ingredient> ingredients, BigDecimal doughMinPrice, BigDecimal doughMaxPrice, List<DoughType> doughTypes) {
//        super(rebates, names, minPrice, maxPrice);
//        this.ingredients = ingredients;
//        this.doughMinPrice = doughMinPrice;
//        this.doughMaxPrice = doughMaxPrice;
//        this.doughTypes = doughTypes;
//    }

    public List<Ingredient> getIngredients() {
        return ingredients;
    }

    public void setIngredients(List<Ingredient> ingredients) {
        this.ingredients = ingredients;
    }

    public Double getDoughMinPrice() {
        return doughMinPrice;
    }

    public void setDoughMinPrice(Double doughMinPrice) {
        this.doughMinPrice = doughMinPrice;
    }

    public Double getDoughMaxPrice() {
        return doughMaxPrice;
    }

    public void setDoughMaxPrice(Double doughMaxPrice) {
        this.doughMaxPrice = doughMaxPrice;
    }

    public List<DoughType> getDoughTypes() {
        return doughTypes;
    }

    public void setDoughTypes(List<DoughType> doughTypes) {
        this.doughTypes = doughTypes;
    }

    public List<PizzaSize> getPizzaSizes() {
        return pizzaSizes;
    }

    public void setPizzaSizes(List<PizzaSize> pizzaSizes) {
        this.pizzaSizes = pizzaSizes;
    }
}
