/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.se.phone.controller;

import com.se.phone.converter.CategoryConverter;
import com.se.phone.dto.CategoryDTO;
import com.se.phone.dto.ResponseDTO;
import com.se.phone.entity.Category;
import com.se.phone.exception.ApiRequestException;
import com.se.phone.exception.DataNotFoundException;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import com.se.phone.service.CategoryService;
import com.se.phone.service.impl.ImageServiceImpl;
import java.util.stream.Collectors;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;

/**
 *
 * @author PhamNgocNhuY_18055121
 */
@RestController
@PreAuthorize("hasRole('PM') or hasRole('ADMIN')")
public class CategoryController {
    @Autowired
    private CategoryService categoryService;
    @Autowired
    private CategoryConverter categoryConverter;
    @Autowired
    private ImageServiceImpl imageServiceImpl;
    
    private static final Logger LOGGER = LoggerFactory.getLogger(CategoryController.class);
    
    public CategoryController(CategoryService catagoryService) {
        this.categoryService = catagoryService;
    }
    
    @GetMapping("/category/{cateId}")
    public ResponseEntity<ResponseDTO> getCategory(@PathVariable("cateId")Integer cateId) throws DataNotFoundException {
        ResponseDTO response = new ResponseDTO();
        Category category = categoryService.getById(cateId);
        //convert category --> categoryDTO
        CategoryDTO cateDao = categoryConverter.convertToDTO(category);
        //set response data to categoryDTO
        response.setData(cateDao);
        return ResponseEntity.ok().body(response);
    }

    //SORT
    //http://localhost:8080/Ytalyphone/category?sortBy=name
    @GetMapping("/category")
    public ResponseEntity<ResponseDTO> getCatagories(
            @RequestParam Optional<Integer> page,
            @RequestParam Optional<String> sortBy)throws DataNotFoundException{
        //getContent() output list of Category or not output go Page<Catagory>
        ResponseDTO response = new ResponseDTO();
        List<Category> list= categoryService.getAllSort(page,sortBy).getContent();
        response.setData(list.stream().map(categoryConverter::convertToDTO).collect(Collectors.toList()));
        return ResponseEntity.ok().body(response);
         
                
    }

    //http://localhost:8080/Ytalyphone/category/search/Tablet
     @GetMapping("/category/search/{name}")
    public ResponseEntity<ResponseDTO> searchByName(@PathVariable String name)throws DataNotFoundException{
        ResponseDTO response = new ResponseDTO();
        List<Category> list=categoryService.getAllSearch(name.toLowerCase());
        response.setData(list.stream().map(categoryConverter::convertToDTO).collect(Collectors.toList()));
        return  ResponseEntity.ok().body(response);
    }
    
    
    @PostMapping("/category")
    public ResponseEntity<ResponseDTO> addCatagory(@RequestBody CategoryDTO c)throws DataNotFoundException{
        int temp=0;
        ResponseDTO response = new ResponseDTO();
        List<Category> l= categoryService.getAll();
        for (int i=0;i<l.size();i++) {
            if (l.get(i).getName().equalsIgnoreCase(c.getName())==true) {
                temp++;
               throw new ApiRequestException("Thêm trùng name");
            }  
        }
        if(temp==0){
            
            Category category=categoryConverter.convertToEntity(c);
            categoryService.save(category);
            response.setData(c);
            return ResponseEntity.ok().body(response);
        }
        return null;
    }
    
    @PutMapping("/category")
    public ResponseEntity<ResponseDTO> updateCatagory(@RequestBody CategoryDTO c)throws DataNotFoundException{
            ResponseDTO response = new ResponseDTO();    
            Category catagory= categoryService.getById(c.getId());
            catagory.setName(c.getName());    
         //   catagory.setImg(imageServiceImpl.getFile(c.getImgId()));
            categoryService.save(catagory);
            
            response.setData(c);
            return ResponseEntity.ok().body(response);
    }
    
    
    @DeleteMapping("/category/{Id}")
    public ResponseEntity<ResponseDTO> deteteCatagory(@PathVariable int Id) throws DataNotFoundException{
         
        Category c= categoryService.getById(Id);
        categoryService.deleteById(Id);
        ResponseDTO response = new ResponseDTO(); 
        String temp="Delete success id"+Id;
        response.setData(temp);
        return ResponseEntity.ok().body(response);
         
    }
    
}
