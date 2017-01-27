package com.pizzashop.controllers;

import com.pizzashop.productFilters.DrinkFilter;
import com.pizzashop.productFilters.PizzaFilter;
import com.pizzashop.productFilters.SauceFilter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * Kontroler zawierający REST servicy wyświetlające puste filtry zawierające liste kryterów(np. składniki pizzy do wyboru, rodzaje produktów)
 * Created by Bartosz Pigla on 01/01/2017.
 */
@RestController
@RequestMapping("/filter")
public class FilterController {
    /**
     * filtr pizz
     */
    @Autowired
    PizzaFilter pizzaFilter;
    /**
     * filtr napojów
     */
    @Autowired
    DrinkFilter drinkFilter;

    /**
     * filtr sosów
     */
    @Autowired
    SauceFilter sauceFilter;

    /**
     * zwraca filtr pizzy
     * @return filtr pizzy
     */
    @RequestMapping(value = "/pizza", method = RequestMethod.GET)
    public PizzaFilter getPizzaFilter() {
        return pizzaFilter;
    }

    /**
     * zwraca filtr napojów
     * @return filtr napojów
     */
    @RequestMapping(value = "/drink", method = RequestMethod.GET)
    public DrinkFilter getDrinkFilter() {
        return drinkFilter;
    }

    /**
     * zwraca filtr sosów
     * @return filtr sosów
     */
    @RequestMapping(value = "/sauce", method = RequestMethod.GET)
    public SauceFilter getSauceFilter() {
        return sauceFilter;
    }
}
