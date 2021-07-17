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
        productDetail.setBattery("5000");
        assertNotNull(productDetailRepository.save(productDetail));
    }
}
