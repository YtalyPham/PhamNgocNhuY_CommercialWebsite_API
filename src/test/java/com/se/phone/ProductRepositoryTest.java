/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.se.phone;

import com.se.phone.entity.Product;
import com.se.phone.reposity.BrandRepository;
import com.se.phone.reposity.CategoryRepository;
import com.se.phone.reposity.ProductDetailRepository;
import com.se.phone.reposity.ProductRepository;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

/**
 *
 * @author PhamNgocNhuY_18055121
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class ProductRepositoryTest {
    @Autowired
    ProductRepository productRepository;
    
    @Autowired
    CategoryRepository categoryRepository;
    
    @Autowired
    BrandRepository brandRepository;
    
    @Autowired
    ProductDetailRepository productDetailRepository;
    
    @Test
    public void testCreateNewProductSuccess(){
        Product p = new Product();
        p.setName("Iphone 10");
        p.setName("test1");
        p.setPrice(1500000.00);
        p.setAmount(12);
        p.setStatus("still");
        p.setDiscountPer(0.5);
        
        p.setRating(4.0);
        
        p.setCategory(categoryRepository.getById(1));
        p.setBrand(brandRepository.getById(5));
        p.setProductDetail(productDetailRepository.getById(2));
        assertNotNull(productRepository.save(p));
    }
}
