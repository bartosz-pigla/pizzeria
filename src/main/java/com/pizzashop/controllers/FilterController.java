package com.pizzashop.controllers;

import com.pizzashop.productFilters.DrinkFilter;
import com.pizzashop.productFilters.PizzaFilter;
import com.pizzashop.productFilters.SauceFilter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by barte on 01/01/2017.
 */
@RestController
@RequestMapping("/filter")
public class FilterController {
    @Autowired
    PizzaFilter pizzaFilter;

    @Autowired
    DrinkFilter drinkFilter;

    @Autowired
    SauceFilter sauceFilter;

    @RequestMapping(value = "/pizza", method = RequestMethod.GET)
    public PizzaFilter getPizzaFilter() {
        return pizzaFilter;
    }

    @RequestMapping(value = "/drink", method = RequestMethod.GET)
    public DrinkFilter getDrinkFilter() {
        return drinkFilter;
    }

    @RequestMapping(value = "/sauce", method = RequestMethod.GET)
    public SauceFilter getSauceFilter() {
        return sauceFilter;
    }
}
