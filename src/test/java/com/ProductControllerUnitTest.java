package com;

import com.pizzashop.PizzaShopApplication;
import com.pizzashop.models.Drink;
import com.pizzashop.models.Product;
import com.pizzashop.models.Rebate;
import com.pizzashop.models.builders.DrinkBuilder;
import com.pizzashop.models.builders.RebateBuilder;
import com.pizzashop.repositories.DrinkRepository;
import com.pizzashop.repositories.RebateRepository;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Matchers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.http.MediaType;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.mock.http.MockHttpOutputMessage;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.web.context.WebApplicationContext;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.io.IOException;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.hamcrest.Matchers.hasSize;
import static org.junit.Assert.assertNotNull;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.springframework.test.web.servlet.setup.MockMvcBuilders.webAppContextSetup;

/**
 * Created by barte on 14/12/2016.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = PizzaShopApplication.class)
@WebAppConfiguration
@DirtiesContext
public class ProductControllerUnitTest {
    private MediaType contentType = new MediaType(MediaType.APPLICATION_JSON.getType(),
            MediaType.APPLICATION_JSON.getSubtype(),
            Charset.forName("utf8"));

    private MockMvc mockMvc;

    private HttpMessageConverter mappingJackson2HttpMessageConverter;

    @Autowired
    DrinkRepository drinkRepository;

    @Autowired
    RebateRepository rebateRepository;

    @PersistenceContext
    EntityManager entityManagerPC;

    Drink drink;
    List<Rebate> rebates = new ArrayList<>();

    @Autowired
    private WebApplicationContext webApplicationContext;

    @Autowired
    void setConverters(HttpMessageConverter<?>[] converters) {

        this.mappingJackson2HttpMessageConverter = Arrays.asList(converters).stream()
                .filter(hmc -> hmc instanceof MappingJackson2HttpMessageConverter)
                .findAny()
                .orElse(null);

        assertNotNull("the JSON message converter must not be null",
                this.mappingJackson2HttpMessageConverter);
    }

    @Before
    public void setup() throws Exception {
        this.mockMvc = webAppContextSetup(webApplicationContext).build();

        Rebate rebate1 = new RebateBuilder().createRebate();
        rebate1.setName("2 w 1");

        Rebate rebate2 = new RebateBuilder().createRebate();
        rebate2.setName("promocja");

        rebate1 = rebateRepository.save(rebate1);
        rebate2 = rebateRepository.save(rebate2);
        rebates.add(rebate1);
        rebates.add(rebate2);

        drink = new DrinkBuilder().createDrink();
        drink.setName("pepsi");
        drink.addRebate(rebate1);
        drink.addRebate(rebate2);

        drink = drinkRepository.save(drink);
    }


    @Test
    public void persistenceContextExists() {
        Assert.assertNotNull(entityManagerPC);
    }

    @Test
    public void persistenceContext() {
        Product p = entityManagerPC.find(Product.class, 1);
        Assert.assertNotNull(p);
    }

    @Test
    public void postDrink() throws Exception {
        mockMvc
                .perform(post("/drink/create"));
    }

    @Test
    public void drinkRebatesAreArray() throws Exception {
        mockMvc.perform(get("/drink/read/{drinkId}", drink.getProductId()))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.rebates").isArray());

    }

    @Test
    public void drinkRebatesAreOk() throws Exception {
//        MvcResult result=
//        mockMvc.perform(get("/drink/read/{drinkId}", drink.getProductId()))
//                .andExpect(status().isOk())
//                .andExpect(content().contentType(contentType))
//                .andReturn();
//
//        Object obj = result.getAsyncResult();
//
//        ResponseEntity<Drink> responseEntity=(ResponseEntity)obj;

        mockMvc.perform(get("/drink/read/{drinkId}", drink.getProductId()))
                .andExpect(status().isOk())
                .andExpect(content().contentType(contentType))
                .andExpect(jsonPath("$.rebates", hasSize(2)))
                .andExpect(jsonPath("$").value(Matchers.eq(json(drink))));
    }
//
//    @Test
//    public void readDrinkRebates() throws Exception{
//        Drink drinkToTest=drinkRepository.findOne(drink.getProductId());
//        assertEquals(drinkToTest.getRebates(),drink.getRebates());
//    }

    protected String json(Object o) throws IOException {
        MockHttpOutputMessage mockHttpOutputMessage = new MockHttpOutputMessage();
        this.mappingJackson2HttpMessageConverter.write(
                o, MediaType.APPLICATION_JSON, mockHttpOutputMessage);
        return mockHttpOutputMessage.getBodyAsString();
    }


}
