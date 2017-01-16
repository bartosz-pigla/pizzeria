package com.pizzashop.productFilters.builders;

import com.pizzashop.models.Rebate;
import com.pizzashop.models.Seasoning;
import com.pizzashop.productFilters.SauceFilter;

import java.math.BigDecimal;
import java.util.List;

public class SauceFilterBuilder {
    private List<Rebate> rebates;
    private List<String> names;
    private BigDecimal minPrice;
    private BigDecimal maxPrice;
    private List<Seasoning> seasonings;

    public SauceFilterBuilder setRebates(List<Rebate> rebates) {
        this.rebates = rebates;
        return this;
    }

    public SauceFilterBuilder setNames(List<String> names) {
        this.names = names;
        return this;
    }

    public SauceFilterBuilder setMinPrice(BigDecimal minPrice) {
        this.minPrice = minPrice;
        return this;
    }

    public SauceFilterBuilder setMaxPrice(BigDecimal maxPrice) {
        this.maxPrice = maxPrice;
        return this;
    }

    public SauceFilterBuilder setSeasonings(List<Seasoning> seasonings) {
        this.seasonings = seasonings;
        return this;
    }

    public SauceFilter createSauceFilter() {
        return new SauceFilter(rebates, names, minPrice, maxPrice, seasonings);
    }
}