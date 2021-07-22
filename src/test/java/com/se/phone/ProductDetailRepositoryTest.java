/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.se.phone;

import com.se.phone.entity.Category;
import com.se.phone.entity.ProductDetail;
import com.se.phone.reposity.CategoryRepository;
import com.se.phone.reposity.ProductDetailRepository;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

/**
 *
 * @author PhamNgocNhuY_18055121
 */
public class ProductDetailRepositoryTest {
    @Autowired
    ProductDetailRepository productDetailRepository;
    
    @Test
    public void testCreateNewProductDetailSuccess(){
        ProductDetail productDetail = new ProductDetail();
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
            productDetail.setChargingPort("test1");
        assertNotNull(productDetailRepository.save(productDetail));
    }
}
