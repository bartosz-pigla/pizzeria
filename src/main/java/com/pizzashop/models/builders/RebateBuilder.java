package com.pizzashop.models.builders;

import com.pizzashop.models.Product;
import com.pizzashop.models.Rebate;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

public class RebateBuilder {
    private String name;
    private Set<Product> products;

    public RebateBuilder setName(String name) {
        this.name = name;
        return this;
    }

    public RebateBuilder addProducts(Product... products) {
        this.products = new HashSet<>(Arrays.asList(products));
        return this;
    }

    public Rebate createRebate() {
        return new Rebate(name, products);
    }
}