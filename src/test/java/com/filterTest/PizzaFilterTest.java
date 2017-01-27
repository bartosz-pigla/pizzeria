package com.filterTest;

import com.pizzashop.PizzaShopApplication;
import com.pizzashop.models.Ingredient;
import com.pizzashop.models.Pizza;
import com.pizzashop.models.Rebate;
import com.pizzashop.productFilters.PizzaFilter;
import com.pizzashop.repositories.IngredientRepository;
import com.pizzashop.repositories.PizzaRepository;
import com.pizzashop.repositories.ProductRepository;
import com.pizzashop.repositories.RebateRepository;
import com.pizzashop.specifications.PizzaSpecification;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import java.math.BigDecimal;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import static com.pizzashop.repositories.initializers.DbInitializer.createPizza;

/**
 * Created by barte on 31/12/2016.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = PizzaShopApplication.class)
@WebAppConfiguration
@DirtiesContext
public class PizzaFilterTest {
    @Autowired
    PizzaFilter pizzaFilter;

    @Autowired
    PizzaRepository pizzaRepository;

    @Autowired
    IngredientRepository ingredientRepository;

    @Autowired
    RebateRepository rebateRepository;

    @Autowired
    ProductRepository productRepository;

    @Test
    public void pizzaFilters() {
        Set<Ingredient> ingredients = new HashSet<>(ingredientRepository.findAll());
        Rebate rebate = rebateRepository.findAll().get(0);
        Pizza expectedPizza = createPizza(ingredients, rebate);
        pizzaRepository.save(expectedPizza);

        pizzaFilter.setDoughMinPrice(new Double("0.0"));
        pizzaFilter.setDoughMaxPrice(new Double("100.0"));

        Specification<Pizza> spec = new PizzaSpecification<>(pizzaFilter);
        List<Pizza> pizzaList = pizzaRepository.findAll(spec);

        Assert.assertEquals(pizzaList.size(),3);
        //List<Pizza> expectedPizzaList = pizzaRepository.findAll();

        //Assert.assertArrayEquals(pizzaList.toArray(), expectedPizzaList.toArray());
    }
}
