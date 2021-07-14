/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.se.phone.serviceImplTest;

import com.se.phone.entity.ProductDetail;
import com.se.phone.reposity.ProductDetailRepository;
import com.se.phone.reposity.ProductDetailRepository;
import com.se.phone.service.ProductDetailService;
import com.se.phone.service.ProductDetailService;
import io.jsonwebtoken.lang.Assert;
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
public class ProductDetailServiceImplTest {
    @Autowired
	private ProductDetailService productDetailService;
        @Autowired
	private ProductDetailRepository productDetailRepository;

	@Test
	public void GetCatagoryById() {
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
        
//        @Test
//        public void DeleteProductDetail(){
//
//            productDetailService.deleteById(8);
//        }
}
