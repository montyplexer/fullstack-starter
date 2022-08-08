package com.starter.fullstack.rest;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.starter.fullstack.api.Product;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
@RunWith(SpringRunner.class)
public class InventoryControllerTest {


    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private MongoTemplate mongoTemplate;

    @Autowired
    private ObjectMapper objectMapper;

    private Product product;

    @Before
    public void setup() throws Throwable {
        this.product = new Product();
        this.product.setId("ID");
        this.product.setName("TEST");
        // Sets the Mongo ID for us
        this.product = this.mongoTemplate.save(this.product);
    }

    @After
    public void teardown() {
        this.mongoTemplate.dropCollection(Product.class);
    }

    /**
     * Test findAll endpoint.
     * @throws Throwable see MockMvc
     */
    @Test
    public void findAll() throws Throwable {
        this.mockMvc.perform(get("/products")
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().json("[" + this.objectMapper.writeValueAsString(product) + "]"));
    }

    /**
     * Test create endpoint.
     * @throws Throwable see MockMvc
     */
    @Test
    public void create() throws Throwable {
        this.product = new Product();
        this.product.setId("OTHER ID");
        this.product.setName("ALSO TEST");
        this.mockMvc.perform(post("/products")
                .accept(MediaType.APPLICATION_JSON)
                .contentType(MediaType.APPLICATION_JSON)
                .content(this.objectMapper.writeValueAsString(this.product)))
                .andExpect(status().isOk());

        Assert.assertEquals(2, this.mongoTemplate.findAll(Product.class).size());
    }

    /**
     * Test remove endpoint.
     * @throws Throwable see MockMvc
     */
    @Test
    public void remove() throws Throwable {
        this.mockMvc.perform(delete("/products")
                .accept(MediaType.APPLICATION_JSON)
                .contentType(MediaType.APPLICATION_JSON)
                .content("[\"" + this.product.getId() + "\"]"))
                .andExpect(status().isOk());

        Assert.assertEquals(0, this.mongoTemplate.findAll(Product.class).size());
    }
}

