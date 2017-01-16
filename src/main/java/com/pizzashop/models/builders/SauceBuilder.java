package com.pizzashop.models.builders;

import com.pizzashop.models.Sauce;
import com.pizzashop.models.Seasoning;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

public class SauceBuilder  extends ProductBuilder<SauceBuilder>{
    private Set<Seasoning> seasonings;

    public SauceBuilder setSeasonings(Set<Seasoning> seasonings) {
        this.seasonings = seasonings;
        return this;
    }

    public SauceBuilder addSeasonings(Seasoning... seasonings) {
        this.seasonings = new HashSet<>(Arrays.asList(seasonings));
        return this;
    }

    public Sauce createSauce() {
        return new Sauce(name, description, price, rebates, seasonings);
    }
}