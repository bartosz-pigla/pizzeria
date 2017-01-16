package com.pizzashop.models.builders;

/**
 * Created by barte on 29/12/2016.
 */
public class ProductFactory {

    public static PizzaBuilder getPizzaBuilder(){
        return new PizzaBuilder();
    }

    public static DrinkBuilder getDrinkBuilder(){
        return new DrinkBuilder();
    }

    public static SauceBuilder getSauceBuilder(){
        return new SauceBuilder();
    }
}
