package com.pizzashop.productFilters.builders;

import com.pizzashop.models.Rebate;
import com.pizzashop.productFilters.DrinkFilter;

import java.util.List;

public class DrinkFilterBuilder {
    private List<Rebate> rebates;
    private List<String> names;
    private Double minPrice;
    private Double maxPrice;
    private List<String> literCounts;

    public DrinkFilterBuilder setRebates(List<Rebate> rebates) {
        this.rebates = rebates;
        return this;
    }

    public DrinkFilterBuilder setNames(List<String> names) {
        this.names = names;
        return this;
    }

    public DrinkFilterBuilder setMinPrice(Double minPrice) {
        this.minPrice = minPrice;
        return this;
    }

    public DrinkFilterBuilder setMaxPrice(Double maxPrice) {
        this.maxPrice = maxPrice;
        return this;
    }

    public DrinkFilterBuilder setLiterCounts(List<String> literCounts) {
        this.literCounts = literCounts;
        return this;
    }

    public DrinkFilter createDrinkFilter() {
        return new DrinkFilter(rebates, names, minPrice, maxPrice, literCounts);
    }
}