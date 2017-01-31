package com.pizzashop.test;

import com.pizzashop.models.Pizza;
import com.pizzashop.models.Product;
import com.pizzashop.repositories.ProductRepository;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import static org.hamcrest.Matchers.is;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

/**
 * Created by bartek on 1/31/17.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
@WebAppConfiguration
@DirtiesContext
public class ProductControllerTest {
    @Autowired
    ProductRepository productRepository;

    private MockMvc mockMvc;

    @Autowired
    private WebApplicationContext webApplicationContext;

    @Before
    public void setup() {
        this.mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
    }


    @Test
    public void notDeletes(){
        int invalidProductId=10000;

        try {
            mockMvc.perform(delete("/product/delete/"+invalidProductId))
                    .andExpect(status().is5xxServerError())
                    .andExpect(jsonPath("$", is("db error")));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    public void deletes(){
        Product p1=productRepository.findAll().get(0);

        try {
            mockMvc.perform(delete("/product/delete/"+p1.getProductId()))
                    .andExpect(status().isOk());

            Assert.assertTrue(productRepository.findOne(p1.getProductId()).getArchival());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
