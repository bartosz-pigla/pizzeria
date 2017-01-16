package com;

import com.pizzashop.PizzaShopApplication;
import com.pizzashop.models.Client;
import com.pizzashop.models.Ingredient;
import com.pizzashop.models.Pizza;
import com.pizzashop.models.Rebate;
import com.pizzashop.repositories.ClientRepository;
import com.pizzashop.repositories.IngredientRepository;
import com.pizzashop.repositories.initializers.InitEntitiesRepository;
import com.pizzashop.repositories.PizzaRepository;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import java.util.Set;

import static com.pizzashop.repositories.initializers.DbInitializer.*;

/**
 * Created by barte on 30/12/2016.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = PizzaShopApplication.class)
@WebAppConfiguration
@DirtiesContext
public class InitEntitiesRepositoryTest {
    @Autowired
    ClientRepository clientRepository;

    @Autowired
    PizzaRepository pizzaRepository;

    @Autowired
    IngredientRepository ingredientRepository;

    @Autowired
    InitEntitiesRepository repository;

    @Test
    @DirtiesContext
    public void savesIngredients5(){
        repository.next();

        long ingredientCount=ingredientRepository.count();

        System.out.println("expected ingredient count: "+5);
        System.out.println("ingr count: "+ingredientCount);

        Assert.assertEquals(5,ingredientCount);
    }

    @Test
    @DirtiesContext
    public void savesPizzaFromRepository(){
        Set<Ingredient> ingredients=createIngredients();
        Rebate rebate=createRebate();

        Pizza pizza=createPizza(ingredients,rebate);
        pizza.setName("vx");

        pizzaRepository.save(pizza);
    }


    @Test
    @DirtiesContext
    public void savesIngredients10(){
        repository.next2();

        long ingredientCount=ingredientRepository.count();

        System.out.println("expected ingredient count: "+10);
        System.out.println("ingr count: "+ingredientCount);

        Assert.assertEquals(5,ingredientCount);
    }

    @Test
    @DirtiesContext
    public void savesIngredients() {
        Client client = createClient();
        client = clientRepository.save(client);

        Client client1=clientRepository.findOne(client.getClientId());

        Assert.assertEquals(client,client1);
    }

    @Test
    @DirtiesContext
    public void savesIngredients2() {
        Client client = createClient();
        client = clientRepository.save(client);

        Client client1=clientRepository.findOne(client.getClientId());

        Assert.assertEquals(client,client1);
    }
}
