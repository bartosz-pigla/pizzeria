package com.pizzashop.models.builders;

import com.pizzashop.models.Rebate;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

public class ProductBuilder<T> {
    protected String name;
    protected String description;
    protected BigDecimal price;
    protected Set<Rebate> rebates;

    public T setName(String name) {
        this.name = name;
        return (T)this;
    }

    public T setDescription(String description) {
        this.description = description;
        return (T)this;
    }

    public T setPrice(BigDecimal price) {
        this.price = price;
        return (T)this;
    }

    public T setRebates(Set<Rebate> rebates) {
        this.rebates = rebates;
        return (T)this;
    }
    public T addRebates(Rebate... rebate) {
        this.rebates=new HashSet<>(Arrays.asList(rebate));
        return (T)this;
    }
    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public Set<Rebate> getRebates() {
        return rebates;
    }
}