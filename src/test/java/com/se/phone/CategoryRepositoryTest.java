/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.se.phone;

import com.se.phone.entity.Category;
import com.se.phone.reposity.CategoryRepository;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertNotNull;
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
public class CategoryRepositoryTest {
    
    @Autowired
    CategoryRepository categoryRepository;
    
    @Test
    public void testCreateNewCategorySuccess(){
        Category mobile = new Category();
        mobile.setName("Mobile");
        assertNotNull(categoryRepository.save(mobile));
    }
    
}
