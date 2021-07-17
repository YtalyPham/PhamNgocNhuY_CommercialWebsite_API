/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.se.phone;

import com.se.phone.entity.Brand;
import com.se.phone.entity.Product;
import com.se.phone.reposity.BrandRepository;
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
public class BrandRepositoryTest {
    @Autowired
    BrandRepository brandRepository;
    
    @Test
    public void testCreateNewBrandSuccess(){
        Brand brand = new Brand();
        brand.setName("Nasaaa2");
        brand.setCountry("Japan");
        assertNotNull(brandRepository.save(brand));
    }
}
