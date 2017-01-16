package com.pizzashop.models.builders;

import com.pizzashop.models.Drink;

public class DrinkBuilder  extends ProductBuilder<DrinkBuilder>{
    private String literCount;

    public DrinkBuilder setLiterCount(String literCount) {
        this.literCount = literCount;
        return this;
    }

    public Drink createDrink() {
        return new Drink(name, description, price, rebates, literCount);
    }


}