package com.pizzashop.productFilters;

import com.pizzashop.models.Rebate;

import java.math.BigDecimal;
import java.util.List;

/**
 * Created by barte on 31/12/2016.
 */
public class ProductFilter {

    protected List<Rebate> rebates;


    protected List<String> names;


    protected BigDecimal minPrice, maxPrice;

    public ProductFilter(List<Rebate> rebates, List<String> names, BigDecimal minPrice, BigDecimal maxPrice) {
        this.rebates = rebates;
        this.names = names;
        this.minPrice = minPrice;
        this.maxPrice = maxPrice;
    }

    public ProductFilter() {
        this.minPrice=new BigDecimal("0.0");
        this.maxPrice=new BigDecimal("0.0");
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

    public BigDecimal getMinPrice() {
        return minPrice;
    }

    public void setMinPrice(BigDecimal minPrice) {
        this.minPrice = minPrice;
    }

    public BigDecimal getMaxPrice() {
        return maxPrice;
    }

    public void setMaxPrice(BigDecimal maxPrice) {
        this.maxPrice = maxPrice;
    }
}
