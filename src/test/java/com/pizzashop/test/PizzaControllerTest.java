package com.pizzashop.test;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.pizzashop.PizzaShopApplication;
import com.pizzashop.controllers.PizzaController;
import com.pizzashop.controllers.ProductController;
import com.pizzashop.models.Ingredient;
import com.pizzashop.models.Pizza;
import com.pizzashop.models.builders.PizzaBuilder;
import com.pizzashop.models.enums.DoughType;
import com.pizzashop.models.enums.PizzaSize;
import com.pizzashop.productFilters.PizzaFilter;
import com.pizzashop.productFilters.builders.PizzaFilterBuilder;
import com.pizzashop.repositories.IngredientRepository;
import com.pizzashop.repositories.PizzaRepository;
import com.pizzashop.repositories.RebateRepository;
import com.pizzashop.specifications.PizzaSpecification;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.context.WebApplicationContext;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

import static org.hamcrest.Matchers.*;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.springframework.test.web.servlet.setup.MockMvcBuilders.standaloneSetup;

/**
 * Created by Bartosz Pigla on 1/27/17.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
@WebAppConfiguration
@DirtiesContext
public class PizzaControllerTest {
    @Autowired
    PizzaRepository pizzaRepository;

    @Autowired
    RebateRepository rebateRepository;

    @Autowired
    IngredientRepository ingredientRepository;

    private MockMvc mockMvc;

    @Autowired
    private WebApplicationContext webApplicationContext;

    @Before
    public void setup() {
        this.mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
    }

    @Test
    public void notValidateByUpdate(){
        Pizza pizza1=pizzaRepository.findAll().get(0);
        pizza1.setPrice(new Double("60.0"));

        ObjectMapper mapper = new ObjectMapper();

        String pizzaString=null;
        try {
            pizzaString=mapper.writeValueAsString(pizza1);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }

        try {
            mockMvc.perform(put("/pizza/update")
                    .contentType(MediaType.APPLICATION_JSON).content(pizzaString))
                    .andExpect(status().is4xxClientError())
                    .andExpect(jsonPath("$", hasSize(1)))
                    .andExpect(jsonPath("$[0].fieldName",is("price")));
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    @Test
    public void updates(){
        Pizza pizza1 = pizzaRepository.findAll().get(0);
        long ingredientCount=pizza1.getIngredients().size();

        List<Ingredient> ingredients=new ArrayList<>(pizza1.getIngredients());

        int ingredientsCountBefore=ingredients.size();

        ingredients.remove(0);

        pizza1.setIngredients(new HashSet<>(ingredients));

        ObjectMapper mapper = new ObjectMapper();

        String pizzaString=null;
        try {
            pizzaString=mapper.writeValueAsString(pizza1);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }

        try {
            mockMvc.perform(put("/pizza/update")
                    .contentType(MediaType.APPLICATION_JSON).content(pizzaString))
                    .andExpect(status().isOk());
        } catch (Exception e) {
            e.printStackTrace();
        }

        Pizza updatedPizza = pizzaRepository.findOne(pizza1.getProductId());

        Assert.assertEquals(updatedPizza.getIngredients().size(),ingredientsCountBefore-1);

    }

    @Test
    public void notValidateByCreate(){
        Pizza pizza1=pizzaRepository.findAll().get(0);
        pizza1.setImageUrl("df.gif");

        ObjectMapper mapper = new ObjectMapper();

        String pizzaString=null;
        try {
            pizzaString=mapper.writeValueAsString(pizza1);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }

        try {
            mockMvc.perform(put("/pizza/update")
                    .contentType(MediaType.APPLICATION_JSON).content(pizzaString))
                    .andExpect(status().is4xxClientError())
                    .andExpect(jsonPath("$", hasSize(1)))
                    .andExpect(jsonPath("$[0].fieldName",is("imageUrl")));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    public void creates(){
        long pizzasBefore=pizzaRepository.count();

        ObjectMapper mapper = new ObjectMapper();

        Pizza pizza=new PizzaBuilder()
                .setPizzaSize(PizzaSize.duża)
                .setArchival(false)
                .setDescription("opis")
                .setDoughPrice(2.12)
                .setDoughType(DoughType.cienkie)
                .setName("pizzaTestowa")
                .setPrice(12.12)
                .addRebates(rebateRepository.findAll().get(0))
                .setUrl("https://upload.wikimedia.org/wikipedia/commons/thumb/a/a4/Pizza.jpg/2000px-Pizza.jpg")
                .setIngredients(new HashSet<>(ingredientRepository.findAll()))
                .createPizza();

        String pizzaString=null;

        try {
            pizzaString=mapper.writeValueAsString(pizza);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }

        try {
            mockMvc.perform(post("/pizza/create")
                    .contentType(MediaType.APPLICATION_JSON).content(pizzaString))
                    .andExpect(status().isOk());
        } catch (Exception e) {
            e.printStackTrace();
        }

        long pizzaAfter=pizzaRepository.count();

        Assert.assertEquals(pizzasBefore,pizzaAfter-1);

    }

    @Test
    public void notCreates(){
        long pizzasBefore=pizzaRepository.count();

        ObjectMapper mapper = new ObjectMapper();

        Pizza pizza=new PizzaBuilder()
                .setArchival(false)
                .setDescription("opis")
                .createPizza();

        String pizzaString=null;

        try {
            pizzaString=mapper.writeValueAsString(pizza);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }

        try {
            mockMvc.perform(post("/pizza/create")
                    .contentType(MediaType.APPLICATION_JSON).content(pizzaString))
                    .andExpect(status().is5xxServerError());
        } catch (Exception e) {
            e.printStackTrace();
        }

        long pizzaAfter=pizzaRepository.count();

        Assert.assertEquals(pizzasBefore,pizzaAfter);

    }

    @Test
    public void filters(){
        PizzaFilter pizzaFilter=new PizzaFilterBuilder()
                .setMaxPrice(20.00)
                .createPizzaFilter();

        Specification<Pizza>specification=new PizzaSpecification<>(pizzaFilter);

        long count=pizzaRepository.count(specification);

        ObjectMapper mapper = new ObjectMapper();

        String pizzaFilterString=null;
        try {
            pizzaFilterString = mapper.writeValueAsString(pizzaFilter);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }

        try {
            mockMvc.perform(post("/pizza/count")
                    .contentType(MediaType.APPLICATION_JSON).content(pizzaFilterString))
                    .andExpect(status().isOk())
                    .andExpect(jsonPath("$", is((int)count)));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    public void paginationTest(){
        PizzaFilter pizzaFilter=new PizzaFilterBuilder()
                .setMaxPrice(20.00)
                .createPizzaFilter();

        Specification<Pizza>specification=new PizzaSpecification<>(pizzaFilter);

        long count=pizzaRepository.count(specification);

        ObjectMapper mapper = new ObjectMapper();

        String pizzaFilterString=null;
        try {
            pizzaFilterString = mapper.writeValueAsString(pizzaFilter);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }

        try {
            mockMvc.perform(post("/pizza/read?pageSize=3&pageNumber=1")
                    .contentType(MediaType.APPLICATION_JSON).content(pizzaFilterString))
                    .andExpect(status().isOk())
                    .andExpect(jsonPath("$", hasSize((int)count)));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
