package com.pizzashop.productFilters;

import com.pizzashop.models.Rebate;

import java.math.BigDecimal;
import java.util.List;

/**
 * Created by Bartosz Pigla on 31/12/2016.
 */
public class ProductFilter {
    protected List<Rebate> rebates;

    protected List<String> names;

    protected Double minPrice = new Double("0.0");
    protected Double maxPrice = new Double("0.0");

    public ProductFilter(List<Rebate> rebates, List<String> names, Double minPrice, Double maxPrice) {
        this.rebates = rebates;
        this.names = names;
        this.minPrice = minPrice;
        this.maxPrice = maxPrice;
    }

    public ProductFilter() {
    }

    public List<Rebate> getRebates() {
        return rebates;
    }

    public void setRebates(List<Rebate> rebates) {
        this.rebates = rebates;
    }

    public List<String> getNames() {
        return names;
    }

    public void setNames(List<String> names) {
            this.names = names;
    }

    public Double getMinPrice() {
        return minPrice;
    }

    public void setMinPrice(Double minPrice) {
        this.minPrice = minPrice;
    }

    public Double getMaxPrice() {
        return maxPrice;
    }

    public void setMaxPrice(Double maxPrice) {
        this.maxPrice = maxPrice;
    }
}
