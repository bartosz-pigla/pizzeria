package com;

import com.pizzashop.PizzaShopApplication;
import com.pizzashop.models.Ingredient;
import com.pizzashop.models.Pizza;
import com.pizzashop.models.Rebate;
import com.pizzashop.repositories.IngredientRepository;
import com.pizzashop.repositories.PizzaRepository;
import com.pizzashop.repositories.ProductRepository;
import com.pizzashop.repositories.RebateRepository;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import static com.pizzashop.repositories.initializers.DbInitializer.createPizza;

/**
 * Created by barte on 31/12/2016.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = PizzaShopApplication.class)
@WebAppConfiguration    @DirtiesContext
public class SecondLevelCacheTest {
    @Autowired
    PizzaRepository pizzaRepository;

    @Autowired
    IngredientRepository ingredientRepository;

    @Autowired
    RebateRepository rebateRepository;

    @Autowired
    ProductRepository productRepository;

    @Test
    public void caches() {
        List<Pizza> pizzas1,pizzas2;

        pizzas1=pizzaRepository.findAll();

        Set<Ingredient> ingredients = new HashSet<>(ingredientRepository.findAll());
        Rebate rebate = rebateRepository.findAll().get(0);
        Pizza expectedPizza = createPizza(ingredients, rebate);
        pizzaRepository.save(expectedPizza);

        pizzas2=pizzaRepository.findAll();
        pizzas2=pizzaRepository.findAll();
        pizzas2=pizzaRepository.findAll();

        Assert.assertEquals(pizzas1.size(),pizzas2.size()-1);

        //productRepository.getFromCache();
        //productRepository.getFromCache();
    }
}
