package com.spring.mongo.controller;

import com.spring.mongo.model.Address;
import com.spring.mongo.model.Product;
import com.spring.mongo.model.User;
import com.spring.mongo.repo.AmazonRepo;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.Mockito.when;

@SpringBootTest
@RunWith(SpringRunner.class)
public class TestAmazonController {
    @Autowired
    private AmazonController controller;
    @MockBean
    private AmazonRepo repo;

    @Test
    public void testGetUserByName() {
        Product product = new Product("phone", 2, 89999);
        Product product1 = new Product("jeans", 1, 6999);
        List<Product> products = new ArrayList<>();
        products.add(product);
        products.add(product1);

        User user = new User(8745, "Mukesh", "male",
                new Address("Jamshedpur", "Jharkhand", "831018"),
                products);

        when(repo.findByName("Mukesh")).thenReturn(user);

        Assert.assertEquals(user, controller.getUserByName("Mukesh"));
        Assert.assertTrue(controller.getUserByName("Mukesh").getProducts().contains(product));
    }

    @Test
    public void testGetUserByAddress(){
        Product product = new Product("phone", 2, 89999);
        Product product1 = new Product("jeans", 1, 6999);
        List<Product> products = new ArrayList<>();
        products.add(product);
        products.add(product1);

        User user = new User(8745, "Mukesh", "male",
                new Address("Jamshedpur", "Jharkhand", "831018"),
                products);
        when(repo.findByAddress("Jamshedpur")).thenReturn(user);
        Assert.assertEquals(user,controller.getUserByAddress("Jamshedpur"));
    }
}
