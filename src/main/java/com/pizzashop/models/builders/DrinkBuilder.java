package com.pizzashop.models.builders;

import com.pizzashop.models.Drink;
import com.pizzashop.models.Rebate;

import java.math.BigDecimal;
import java.util.HashSet;
import java.util.Set;

public class DrinkBuilder {
    private String name;
    private String description;
    private Double price;
    private Set<Rebate> rebates=new HashSet<>();
    private String url;
    private String literCount;
    private boolean archival;

    public DrinkBuilder setName(String name) {
        this.name = name;
        return this;
    }

    public DrinkBuilder setDescription(String description) {
        this.description = description;
        return this;
    }

    public DrinkBuilder setPrice(Double price) {
        this.price = price;
        return this;
    }

    public DrinkBuilder setRebates(Set<Rebate> rebates) {
        this.rebates = rebates;
        return this;
    }

    public DrinkBuilder setUrl(String url) {
        this.url = url;
        return this;
    }

    public DrinkBuilder setLiterCount(String literCount) {
        this.literCount = literCount;
        return this;
    }

    public DrinkBuilder setArchival(boolean archival) {
        this.archival=archival;
        return this;
    }

    public DrinkBuilder addRebates(Rebate... rebates) {
        for (Rebate rebate:rebates
                ) {
            this.rebates.add(rebate);
        }
        return this;
    }

    public Drink createDrink() {
        return new Drink(name, description, price, rebates, url, literCount,archival);
    }
}