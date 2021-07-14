/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.se.phone.controller;

import com.se.phone.entity.Category;
import com.se.phone.exception.ApiRequestException;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import com.se.phone.service.CategoryService;

/**
 *
 * @author PhamNgocNhuY_18055121
 */
@RestController
@PreAuthorize("hasRole('PM') or hasRole('ADMIN')")
public class CategoryController {
    @Autowired
    private CategoryService catagoryService;

    public CategoryController(CategoryService catagoryService) {
        this.catagoryService = catagoryService;
    }

    //SORT
    //http://localhost:8080/Ytalyphone/catagory?sortBy=name
    @GetMapping("/catagory")
    public List<Category> getCatagories(
            @RequestParam Optional<Integer> page,
            @RequestParam Optional<String> sortBy){
        //getContent() output list of Category or not output go Page<Catagory>
        return catagoryService.getAllSort(page,sortBy).getContent();
                
    }

    //http://localhost:8080/Ytalyphone/catagory/search/Tablet
     @GetMapping("/catagory/search/{name}")
    public List<Category> searchByName(@PathVariable String name){
        return catagoryService.getAllSearch(name.toLowerCase());
    }
    
    
    @GetMapping("/catagory/{Id}")
    public Category getCatagory(@PathVariable int Id){
        Category c = catagoryService.getById(Id);
        return c;
    }
    
    @PostMapping("/catagory")
    public Category addCatagory(@RequestBody Category c){
        int temp=0;
        List<Category> l= catagoryService.getAll();
        for (int i=0;i<l.size();i++) {
            if (l.get(i).getName().equalsIgnoreCase(c.getName())==true) {
                temp++;
               throw new ApiRequestException("Thêm trùng name");
            }  
        }
        if(temp==0){
            catagoryService.save(c);
            return c;
        }
        return null;
    }
    
    @PutMapping("/catagory")
    public Category updateCatagory(@RequestBody Category c){
            Category catagory= catagoryService.getById(c.getId());
            catagory.setName(c.getName());    
            catagoryService.save(catagory);
            return catagory;
          
    }
    
    
    @DeleteMapping("/catagory/{Id}")
    public String deteteCatagory(@PathVariable int Id){
        Category c= catagoryService.getById(Id);
        catagoryService.deleteById(Id);
        return "Delete sucess CatagoryId= "+Id;    
    }
    
}
