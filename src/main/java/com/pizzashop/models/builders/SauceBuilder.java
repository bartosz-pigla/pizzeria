package com.pizzashop.models.builders;

import com.pizzashop.models.Rebate;
import com.pizzashop.models.Sauce;
import com.pizzashop.models.Seasoning;

import java.math.BigDecimal;
import java.util.HashSet;
import java.util.Set;

public class SauceBuilder {
    private String name;
    private String description;
    private Double price;
    private Set<Rebate> rebates=new HashSet<>();
    private String url;
    private Set<Seasoning> seasonings=new HashSet<>();
    private boolean archival;

    public SauceBuilder setName(String name) {
        this.name = name;
        return this;
    }

    public SauceBuilder setDescription(String description) {
        this.description = description;
        return this;
    }

    public SauceBuilder setPrice(Double price) {
        this.price = price;
        return this;
    }

    public SauceBuilder setRebates(Set<Rebate> rebates) {
        this.rebates = rebates;
        return this;
    }

    public SauceBuilder setUrl(String url) {
        this.url = url;
        return this;
    }

    public SauceBuilder setSeasonings(Set<Seasoning> seasonings) {
        this.seasonings = seasonings;
        return this;
    }

    public SauceBuilder setArchival(boolean archival){
        this.archival=archival;
        return this;
    }

    public Sauce createSauce() {
        return new Sauce(name, description, price, rebates, url, seasonings,archival);
    }

    public SauceBuilder addRebates(Rebate... rebates) {
        for (Rebate rebate:rebates
                ) {
            this.rebates.add(rebate);
        }
        return this;
    }
}