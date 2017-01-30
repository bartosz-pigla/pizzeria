package com.pizzashop.aop;

import com.pizzashop.models.Drink;
import com.pizzashop.models.Pizza;
import com.pizzashop.models.Sauce;
import com.pizzashop.productFilters.DrinkFilter;
import com.pizzashop.productFilters.PizzaFilter;
import com.pizzashop.productFilters.SauceFilter;
import com.pizzashop.initializers.ProductFilterInitializer;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * Created by Bartosz Pigla on 31/12/2016.
 */
@Aspect
@Component
public class FilterChangedListener {
    @Autowired
    PizzaFilter pizzaFilter;

    @Autowired
    DrinkFilter drinkFilter;

    @Autowired
    SauceFilter sauceFilter;

    @Autowired
    ProductFilterInitializer productFilterInitializer;

    @After(value = "execution(* com.pizzashop.repositories.PizzaRepository.save (com.pizzashop.models.Pizza)) && args(pizza)")
    public void afterSavePizza(Pizza pizza){
        productFilterInitializer.updatePizzaFilter(pizzaFilter);
    }

    @After(value = "execution(* com.pizzashop.repositories.PizzaRepository.delete (com.pizzashop.models.Pizza)) && args(pizza)")
    public void afterDeletePizza(Pizza pizza){

        productFilterInitializer.updatePizzaFilter(pizzaFilter);
    }


    @After(value = "execution(* com.pizzashop.repositories.DrinkRepository.save (com.pizzashop.models.Drink)) && args(drink)")
    public void afterSaveDrink(Drink drink){
        productFilterInitializer.updateDrinkFilter(drinkFilter);
    }

    @After(value = "execution(* com.pizzashop.repositories.DrinkRepository.delete (com.pizzashop.models.Drink)) && args(drink)")
    public void afterDeleteDrink(Drink drink){
        productFilterInitializer.updateDrinkFilter(drinkFilter);
    }


    @After(value = "execution(* com.pizzashop.repositories.SauceRepository.save (com.pizzashop.models.Sauce)) && args(sauce)")
    public void afterSaveSauce(Sauce sauce){
        productFilterInitializer.updateSauceFilter(sauceFilter);
    }

    @After(value = "execution(* com.pizzashop.repositories.SauceRepository.delete (com.pizzashop.models.Sauce)) && args(sauce)")
    public void afterDeleteSauce(Sauce sauce){
        productFilterInitializer.updateSauceFilter(sauceFilter);
    }


}
