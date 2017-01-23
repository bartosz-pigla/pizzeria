package com.pizzashop.repositories.initializers;

import com.pizzashop.models.Pizza;
import com.pizzashop.models.enums.DoughType;
import com.pizzashop.models.enums.PizzaSize;
import com.pizzashop.productFilters.DrinkFilter;
import com.pizzashop.productFilters.PizzaFilter;
import com.pizzashop.productFilters.SauceFilter;
import com.pizzashop.productFilters.builders.DrinkFilterBuilder;
import com.pizzashop.productFilters.builders.PizzaFilterBuilder;
import com.pizzashop.productFilters.builders.SauceFilterBuilder;
import com.pizzashop.repositories.FilterRepository;
import com.pizzashop.repositories.IngredientRepository;
import com.pizzashop.repositories.RebateRepository;
import com.pizzashop.repositories.SeasoningRepository;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Arrays;

/**
 * Created by barte on 31/12/2016.
 */
public class ProductFilterInitializer {
    @Autowired
    FilterRepository filterRepository;

    @Autowired
    RebateRepository rebateRepository;

    @Autowired
    IngredientRepository ingredientRepository;

    @Autowired
    SeasoningRepository seasoningRepository;

    public PizzaFilter createPizzaFilter() {
        return new PizzaFilterBuilder()
                .setNames(filterRepository.getNames(Pizza.class))
                .setRebates(rebateRepository.findAll())
                .setDoughTypes(Arrays.asList(DoughType.values()))
                .setIngredients(ingredientRepository.findAll())
                .setPizzaSizes(Arrays.asList(PizzaSize.values()))
                .createPizzaFilter();
    }

    public SauceFilter createSauceFilter() {
        return new SauceFilterBuilder()
                .setNames(filterRepository.getNames(Pizza.class))
                .setRebates(rebateRepository.findAll())
                .setSeasonings(seasoningRepository.findAll())
                .createSauceFilter();
    }

    public DrinkFilter createDrinkFilter() {
        return new DrinkFilterBuilder()
                .setNames(filterRepository.getNames(Pizza.class))
                .setRebates(rebateRepository.findAll())
                .setLiterCounts(filterRepository.getLiterCounts())
                .createDrinkFilter();
    }

    public void updatePizzaFilter(PizzaFilter pizzaFilter) {
        PizzaFilter newPizzaFilter=createPizzaFilter();

        pizzaFilter.setDoughTypes(newPizzaFilter.getDoughTypes());
        pizzaFilter.setIngredients(newPizzaFilter.getIngredients());
        pizzaFilter.setPizzaSizes(newPizzaFilter.getPizzaSizes());
        pizzaFilter.setNames(newPizzaFilter.getNames());
        pizzaFilter.setRebates(newPizzaFilter.getRebates());
    }

    public void updateDrinkFilter(DrinkFilter drinkFilter) {
        DrinkFilter newDrinkFilter=createDrinkFilter();

        drinkFilter.setNames(newDrinkFilter.getNames());
        drinkFilter.setRebates(newDrinkFilter.getRebates());
        drinkFilter.setLiterCounts(newDrinkFilter.getLiterCounts());
    }

    public void updateSauceFilter(SauceFilter sauceFilter){
        SauceFilter newSauceFilter=createSauceFilter();

        sauceFilter.setNames(newSauceFilter.getNames());
        sauceFilter.setRebates(newSauceFilter.getRebates());
        sauceFilter.setSeasonings(newSauceFilter.getSeasonings());
    }

}
