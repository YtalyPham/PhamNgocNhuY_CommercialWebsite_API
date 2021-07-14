/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.se.phone.serviceImplTest;

import com.se.phone.entity.Brand;
import com.se.phone.entity.Brand;
import com.se.phone.reposity.BrandRepository;
import com.se.phone.reposity.BrandRepository;
import com.se.phone.service.BrandService;
import com.se.phone.service.BrandService;
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
public class BrandServiceImplTest {
    
        @Autowired
	private BrandService brandService;
        @Autowired
	private BrandRepository brandRepository;

	@Test
	public void GetBrandById() {
             Brand b=brandService.getById(1);
             Brand temp= new Brand(1,"Xiaomi", "China");
             Assertions.assertEquals(temp.getName(),b.getName());
	}
        
        @Test
	public void SearchBrandByName() {
             List<Brand> c=brandService.getAllSearch("xiaomi");
             Brand temp= new Brand(1,"Xiaomi", "China");
             Assertions.assertEquals(temp.getName(),c.get(0).getName());
	}
        
         @Test
	public void GetAllBrand() {
             List<Brand> c=brandService.getAll();
             List<Brand> temp= brandRepository.findAll();
             Assertions.assertEquals(temp.size(),c.size());
	}
        
        @Test
        public void SaveBrand(){
            Brand brand= new Brand();
            brand.setName("test1");
            brand.setCountry("test1");
            Assert.notNull(brandService.save(brand));
        }
        
        @Test
        public void DeleteBrand(){
            int id=brandService.getAllSearch("test1").get(0).getId();
            brandService.deleteById(id);
        }
}
