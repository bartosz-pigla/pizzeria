package com;

import com.pizzashop.PizzaShopApplication;
import com.pizzashop.models.Pizza;
import com.pizzashop.repositories.IngredientRepository;
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

import java.util.ArrayList;
import java.util.HashSet;

import static com.pizzashop.repositories.initializers.DbInitializer.createPizza;

/**
 * Created by barte on 31/12/2016.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = PizzaShopApplication.class)
@WebAppConfiguration
@DirtiesContext
public class ProductDeleteTest {
    @Autowired
    private IngredientRepository ingredientRepository;
    @Autowired
    private RebateRepository rebateRepository;

    @Autowired
    ProductRepository productRepository;

    @Test
    public void deletePizzaTest() {
        Pizza pizza1 = createPizza(new HashSet<>(ingredientRepository.findAll()), rebateRepository.findAll().get(0));
        int pizza1Id = 0;
        int ingredient1Id = 0;
        int rebateId = 0;

        productRepository.save(pizza1);

        pizza1Id = pizza1.getProductId();
        ingredient1Id = new ArrayList<>(pizza1.getIngredients()).get(0).getIngredientId();
        rebateId = new ArrayList<>(pizza1.getRebates()).get(0).getRebateId();

//        try {
//            productRepository.save(pizza1);
//            pizza1Id= pizza1.getProductId();
//            ingredient1Id=new ArrayList<>(pizza1.getIngredients()).get(0).getIngredientId();
//            rebateId=new ArrayList<>(pizza1.getRebates()).get(0).getRebateId();
//        } catch (IngredientNotFoundException e) {
//            e.printStackTrace();
//        } catch (RebateNotFoundException e) {
//            e.printStackTrace();
//        }
        productRepository.delete(pizza1Id);

        Assert.assertEquals(productRepository.findOne(pizza1Id), null);
        Assert.assertNotNull(rebateRepository.findOne(rebateId));
        Assert.assertNotNull(ingredientRepository.findOne(ingredient1Id));
    }
}
