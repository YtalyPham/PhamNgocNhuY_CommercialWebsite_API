/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.se.phone.serviceImplTest;


import com.se.phone.entity.Category;
import com.se.phone.reposity.CategoryRepository;
import com.se.phone.service.CategoryService;
import io.jsonwebtoken.lang.Assert;
import java.util.List;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.junit.runner.RunWith;
import org.springframework.test.context.junit4.SpringRunner;

/**
 *
 * @author PhamNgocNhuY_18055121
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class CategoryServiceImplTest {
	
        @Autowired
	private CategoryService categoryService;
        @Autowired
	private CategoryRepository categoryRepository;

	@Test
	public void GetCatagoryById() {
             Category c=categoryService.getById(1);
             Category temp= new Category(1, "SmartPhone");
             Assertions.assertEquals(temp.getName(),c.getName());
	}
        
        @Test
	public void SearchCategoryByName() {
             List<Category> c=categoryService.getAllSearch("tablet");
             Category temp= new Category(1, "Tablet");
             Assertions.assertEquals(temp.getName(),c.get(0).getName());
	}
        
         @Test
	public void GetAllCategory() {
             List<Category> c=categoryService.getAll();
             List<Category> temp= categoryRepository.findAll();
             Assertions.assertEquals(temp.size(),c.size());
	}
        
        @Test
        public void SaveCategory(){
            Category category= new Category();
            category.setName("test1");
            Assert.notNull(categoryService.save(category));
        }
        
        @Test
        public void DeleteCategory(){
            int id=categoryService.getAllSearch("test1").get(0).getId();
            categoryService.deleteById(id);
        }
  
        
}
