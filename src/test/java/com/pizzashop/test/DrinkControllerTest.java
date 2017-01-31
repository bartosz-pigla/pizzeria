package com.pizzashop.test;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.pizzashop.models.Drink;
import com.pizzashop.models.Ingredient;
import com.pizzashop.models.Pizza;
import com.pizzashop.models.Rebate;
import com.pizzashop.models.builders.DrinkBuilder;
import com.pizzashop.models.builders.PizzaBuilder;
import com.pizzashop.models.enums.DoughType;
import com.pizzashop.models.enums.PizzaSize;
import com.pizzashop.productFilters.DrinkFilter;
import com.pizzashop.productFilters.PizzaFilter;
import com.pizzashop.productFilters.builders.DrinkFilterBuilder;
import com.pizzashop.productFilters.builders.PizzaFilterBuilder;
import com.pizzashop.repositories.DrinkRepository;
import com.pizzashop.repositories.IngredientRepository;
import com.pizzashop.repositories.RebateRepository;
import com.pizzashop.specifications.DrinkSpecification;
import com.pizzashop.specifications.PizzaSpecification;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.http.MediaType;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;

import static com.pizzashop.initializers.DbInitializer.createDrink;
import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.is;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

/**
 * Created by bartek on 1/31/17.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
@WebAppConfiguration
@DirtiesContext
public class DrinkControllerTest {
    @Autowired
    DrinkRepository drinkRepository;

    @Autowired
    RebateRepository rebateRepository;

    private MockMvc mockMvc;

    @Autowired
    private WebApplicationContext webApplicationContext;

    @Before
    public void setup() {
        this.mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
    }


    @Test
    public void notValidateByUpdate(){
        Drink drink=drinkRepository.findAll().get(0);
        drink.setPrice(new Double("60.0"));

        ObjectMapper mapper = new ObjectMapper();

        String drinkString=null;
        try {
            drinkString=mapper.writeValueAsString(drink);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }

        try {
            mockMvc.perform(put("/drink/update")
                    .contentType(MediaType.APPLICATION_JSON).content(drinkString))
                    .andExpect(status().is4xxClientError())
                    .andExpect(jsonPath("$", hasSize(1)))
                    .andExpect(jsonPath("$[0].fieldName",is("price")));
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    @Test
    public void updates(){
        Drink drink = drinkRepository.findAll().get(0);

        String literCount=drink.getLiterCount();

        String newLiterCount="2.25l";
        drink.setLiterCount(newLiterCount);

        ObjectMapper mapper = new ObjectMapper();

        String drinkString=null;
        try {
            drinkString=mapper.writeValueAsString(drink);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }

        try {
            mockMvc.perform(put("/drink/update")
                    .contentType(MediaType.APPLICATION_JSON).content(drinkString))
                    .andExpect(status().isOk());
        } catch (Exception e) {
            e.printStackTrace();
        }

        Drink updatedDrink = drinkRepository.findOne(drink.getProductId());

        Assert.assertEquals(updatedDrink.getLiterCount(),newLiterCount);
    }

    @Test
    public void notValidateByCreate(){
        Drink drink=drinkRepository.findAll().get(0);
        drink.setImageUrl("df.gif");

        ObjectMapper mapper = new ObjectMapper();

        String drinkString=null;
        try {
            drinkString=mapper.writeValueAsString(drink);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }

        try {
            mockMvc.perform(put("/drink/update")
                    .contentType(MediaType.APPLICATION_JSON).content(drinkString))
                    .andExpect(status().is4xxClientError())
                    .andExpect(jsonPath("$", hasSize(1)))
                    .andExpect(jsonPath("$[0].fieldName",is("imageUrl")));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    public void creates(){
        long drinksBefore=drinkRepository.count();
        Rebate rebate=rebateRepository.findAll().get(0);

        ObjectMapper mapper = new ObjectMapper();

        Drink drink = createDrink(rebate);

        String drinkString=null;

        try {
            drinkString=mapper.writeValueAsString(drink);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }

        try {
            mockMvc.perform(post("/drink/create")
                    .contentType(MediaType.APPLICATION_JSON).content(drinkString))
                    .andExpect(status().isOk());
        } catch (Exception e) {
            e.printStackTrace();
        }

        long drinksAfter=drinkRepository.count();

        Assert.assertEquals(drinksBefore,drinksAfter-1);

    }

    @Test
    public void notCreates(){
        long drinksBefore=drinkRepository.count();

        ObjectMapper mapper = new ObjectMapper();

        Drink drink = new DrinkBuilder()
                .setPrice(new Double("3.30"))
                .createDrink();

        String drinkString=null;

        try {
            drinkString=mapper.writeValueAsString(drink);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }

        try {
            mockMvc.perform(post("/drink/create")
                    .contentType(MediaType.APPLICATION_JSON).content(drinkString))
                    .andExpect(status().is5xxServerError());
        } catch (Exception e) {
            e.printStackTrace();
        }

        long drinksAfter=drinkRepository.count();

        Assert.assertEquals(drinksBefore,drinksAfter);

    }

    @Test
    public void filters(){
        DrinkFilter drinkFilter=new DrinkFilterBuilder()
                .setLiterCounts(Arrays.asList("0.33"))
                .createDrinkFilter();

        Specification<Drink> specification=new DrinkSpecification<>(drinkFilter);

        long count=drinkRepository.count(specification);

        ObjectMapper mapper = new ObjectMapper();

        String drinkFilterString=null;
        try {
            drinkFilterString = mapper.writeValueAsString(drinkFilter);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }

        try {
            mockMvc.perform(post("/drink/count")
                    .contentType(MediaType.APPLICATION_JSON).content(drinkFilterString))
                    .andExpect(status().isOk())
                    .andExpect(jsonPath("$", is((int)count)));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    public void paginationTest(){
        DrinkFilter drinkFilter=new DrinkFilterBuilder()
                .setMaxPrice(20.00)
                .createDrinkFilter();

        Specification<Drink>specification=new DrinkSpecification<>(drinkFilter);

        long count=drinkRepository.count(specification);

        ObjectMapper mapper = new ObjectMapper();

        String drinkFilterString=null;
        try {
            drinkFilterString = mapper.writeValueAsString(drinkFilter);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }

        try {
            mockMvc.perform(post("/drink/read?pageSize=3&pageNumber=1")
                    .contentType(MediaType.APPLICATION_JSON).content(drinkFilterString))
                    .andExpect(status().isOk())
                    .andExpect(jsonPath("$", hasSize((int)count)));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
