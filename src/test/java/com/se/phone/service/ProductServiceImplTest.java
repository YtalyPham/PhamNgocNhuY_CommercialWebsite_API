/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.se.phone.service;

import com.se.phone.converter.ProductConverter;
import com.se.phone.dto.ProductDTO;
import com.se.phone.entity.Image;
import com.se.phone.entity.Product;
import com.se.phone.reposity.ProductRepository;
import com.se.phone.service.impl.BrandServiceImpl;
import com.se.phone.service.impl.CategoryServiceImpl;
import com.se.phone.service.impl.ProductDetailServiceImpl;
import com.se.phone.service.impl.ProductServiceImpl;
import io.jsonwebtoken.lang.Assert;
import java.util.ArrayList;
import java.util.List;
import org.junit.jupiter.api.Assertions;
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
public class ProductServiceImplTest {
    @Autowired
    private ProductRepository productRepository;
    @Autowired
    private ProductServiceImpl productServiceImpl;
    @Autowired
    private ProductConverter productConverter;
    @Autowired
    private CategoryServiceImpl categoryServiceImpl;
    @Autowired
    private BrandServiceImpl brandServiceImpl;
    @Autowired
    private ProductDetailServiceImpl productDetailServiceImpl;
    
    @Test
    public void findById(){
        Product p= productServiceImpl.getById(1);
        Product product= new Product();
        product.setId(1);
        product.setName("Xiaomi Redmi Note 10 Pro 8GB");
        Assertions.assertEquals(product.getName(), p.getName());
    }
    @Test
    public void findByName(){
        String s= "Xiaomi Redmi Note 10 Pro 8GB";
        List<Product> p= productServiceImpl.getAllSearchByName(s.toLowerCase());
        Assertions.assertEquals(s, p.get(0).getName());
    }
     @Test
    public void getAll(){
        List<Product> p= productServiceImpl.getAll();
        Assertions.assertEquals(1, p.size());
    }
    @Test
    public void save(){
        Product p= new Product();
        List<Image> img=new ArrayList<>();
        p.setName("test1");
        p.setPrice(1500000.00);
        p.setAmount(12);
        p.setStatus("still");
        p.setDiscountPer(0.5);
        
        p.setRating(4.0);
        
        p.setCategory(categoryServiceImpl.getById(1));
        p.setBrand(brandServiceImpl.getById(5));
        p.setProductDetail(productDetailServiceImpl.getById(2));
        
        p.setImages(img);
         
        productServiceImpl.save(p);
        
    }
    @Test
    public void delete(){
        int id=productServiceImpl.getAllSearchByName("test1").get(0).getId();
        productServiceImpl.deleteById(id);
    }
    
    
    
}
