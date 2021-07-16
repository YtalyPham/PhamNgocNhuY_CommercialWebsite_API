/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.se.phone.service;

import com.se.phone.entity.ProductDetail;
import com.se.phone.exception.ApiRequestException;
import com.se.phone.reposity.ProductDetailRepository;
import com.se.phone.reposity.ProductDetailRepository;
import com.se.phone.service.ProductDetailService;
import com.se.phone.service.ProductDetailService;
import io.jsonwebtoken.lang.Assert;
import java.util.List;
import org.junit.experimental.categories.Categories;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
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
public class ProductDetailServiceImplTest {
        @Autowired
            private ProductDetailService productDetailService;
        @Autowired
            private ProductDetailRepository productDetailRepository;
        @BeforeAll
	public static void setUp() {
		System.out.println("Start Test");
	}

	@BeforeEach
	public void beforeEach() {
		
		System.out.println("Before each testcase");
	}

	@AfterEach
	public void afterEach() {
		System.out.println("After each testcase");
	}

	@AfterAll
	public static void tearDown() {
		System.out.println("End Test");
	}

	@Test
	public void GetProductDetailById() {
             ProductDetail c=productDetailService.getById(1);
              
             Assert.notNull(c);
	}
        
        
        
         @Test
	public void GetAllProductDetail() {
             List<ProductDetail> c=productDetailService.getAll();
             List<ProductDetail> temp= productDetailRepository.findAll();
             Assertions.assertEquals(temp.size(),c.size());
	}
        
        @Test
        public void SaveProductDetail(){
            ProductDetail productDetail= new ProductDetail();
            productDetail.setBattery("test1");
            productDetail.setBluetooth("test1");
            productDetail.setCameraBack("test1");
            productDetail.setCameraFont("test1");
            productDetail.setChargingTechnology("test1");
            productDetail.setChipset("test1");
            productDetail.setMemory("test1");
            productDetail.setRAM("test1");
            productDetail.setSIM("test1");
            productDetail.setScreenResolution("test1");
            productDetail.setScreenSize("test1");
            productDetail.setScreenTechnology("test1");
            productDetail.setSensors("test1");
            productDetail.setSize("test1");
            productDetail.setSystem("test1");
            productDetail.setWeight("test1");
            productDetail.setWiFi("test1");
            
            
            Assert.notNull(productDetailService.save(productDetail));
        }
        @Test void getIdNotFound(){
            ApiRequestException ex=null;
            try {
                ProductDetail temp=productDetailService.getById(10);
            } catch (ApiRequestException e) {
                ex=e;
            }
            
            Assert.notNull(ex);
        }
//        @Test
//        public void DeleteProductDetail(){
//
//            productDetailService.deleteById(8);
//        }
}
