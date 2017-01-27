package com;

import com.pizzashop.PizzaShopApplication;
import com.pizzashop.models.*;
import com.pizzashop.repositories.*;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import java.util.HashSet;
import java.util.Set;

import static com.pizzashop.repositories.initializers.DbInitializer.*;

/**
 * Created by Bartosz Pigla on 30/12/2016.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = PizzaShopApplication.class)
@WebAppConfiguration    @DirtiesContext
public class ProductSaveTest {
    @Autowired
    ProductRepository productRepository;

    @Autowired
    SauceRepository sauceRepository;

    @Autowired
    IngredientRepository ingredientRepository;

    @Autowired
    SeasoningRepository seasoningRepository;

    @Autowired
    RebateRepository rebateRepository;

    @Test
    public void savePizzaTest(){
        Set<Ingredient> ingredients=new HashSet<>(ingredientRepository.findAll());
        Rebate rebate=rebateRepository.findAll().get(0);
        Pizza expectedPizza=createPizza(ingredients,rebate);

        productRepository.save(expectedPizza);

//        Pizza pizza=null;
//        try {
//            pizza = productRepository.save(expectedPizza);
//        } catch (IngredientNotFoundException e) {
//            e.printStackTrace();
//        } catch (RebateNotFoundException e) {
//            e.printStackTrace();
//        }
//
//        Assert.assertEquals(pizza,expectedPizza);
    }

    @Test
    public void saveSauceTest(){
        Set<Seasoning> seasonings=new HashSet<>(seasoningRepository.findAll());
        Rebate rebate=rebateRepository.findAll().get(0);
        Sauce expectedSauce=createSauce(seasonings,rebate);

        productRepository.save(expectedSauce);

//        Sauce sauce=null;
//        try {
//            sauce = productRepository.save(expectedSauce);
//        } catch (SeasoningNotFoundException e) {
//            e.printStackTrace();
//        } catch (RebateNotFoundException e) {
//            e.printStackTrace();
//        }

        //Assert.assertEquals(sauce,expectedSauce);
    }

    @Test
    public void saveDrinkTest(){
        Rebate rebate=rebateRepository.findAll().get(0);
        Drink expectedDrink=createDrink(rebate);

        productRepository.save(expectedDrink);
//        Drink drink=null;
//        try {
//            drink = productRepository.save(expectedDrink);
//        } catch (RebateNotFoundException e) {
//            e.printStackTrace();
//        }

        //Assert.assertEquals(drink,expectedDrink);
    }
}
