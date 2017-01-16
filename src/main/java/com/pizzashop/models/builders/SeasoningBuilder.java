package com.pizzashop.models.builders;

import com.pizzashop.models.Seasoning;

public class SeasoningBuilder {
    private String name;

    public SeasoningBuilder setName(String name) {
        this.name = name;
        return this;
    }

    public Seasoning createSeasoning() {
        return new Seasoning(name);
    }
}