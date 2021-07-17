/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.se.phone.controller;

import com.se.phone.converter.BrandConverter;
import com.se.phone.dto.BrandDTO;
import com.se.phone.dto.ResponseDTO;
import com.se.phone.entity.Brand;
import com.se.phone.exception.ApiRequestException;
import com.se.phone.exception.DataNotFoundException;
import java.lang.reflect.Field;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.util.ReflectionUtils;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import com.se.phone.service.BrandService;
import com.se.phone.service.impl.ImageServiceImpl;
import java.util.stream.Collectors;
import org.springframework.http.ResponseEntity;

/**
 *
 * @author PhamNgocNhuY_18055121
 */
@RestController
@PreAuthorize("hasRole('PM') or hasRole('ADMIN')")
public class BrandController {
    private BrandService brandService;
    private BrandConverter brandConverter;
    private ImageServiceImpl imageServiceImpl;
    @Autowired
    public BrandController(BrandService brandService, BrandConverter brandConverter, ImageServiceImpl imageServiceImpl) {
        this.brandService = brandService;
        this.brandConverter = brandConverter;
        this.imageServiceImpl = imageServiceImpl;
    }
  
    
    
    //SORT
    //http://localhost:8080/Ytalyphone/producer?sortBy=name
    @GetMapping("/brand")
    public ResponseEntity<ResponseDTO> getBrands(
            @RequestParam Optional<Integer> page,
            @RequestParam Optional<String> sortBy) throws DataNotFoundException{
        //getContent() output list of Catagory or not output go Page<Catagory>
        ResponseDTO response = new ResponseDTO();
        List<Brand> list= brandService.getAllSort(page,sortBy).getContent();
        response.setData(list.stream().map(brandConverter::convertToDTO).collect(Collectors.toList()));
        return ResponseEntity.ok().body(response);
                
    }

    
    //http://localhost:8080/Ytalyphone/producer/search/Xiaomi
     @GetMapping("/brand/search/{name}")
    public ResponseEntity<ResponseDTO> searchByName(@PathVariable String name)throws DataNotFoundException{
        ResponseDTO response = new ResponseDTO();
        List<Brand> list=brandService.getAllSearch(name.toLowerCase());
        response.setData(list.stream().map(brandConverter::convertToDTO).collect(Collectors.toList()));
        return ResponseEntity.ok().body(response); 
    }
    
    
    @GetMapping("/brand/{Id}")
    public ResponseEntity<ResponseDTO> getBrand(@PathVariable int Id)throws DataNotFoundException{
        Brand p = brandService.getById(Id);
        ResponseDTO response = new ResponseDTO();
        response.setData(brandConverter.convertToDTO(p));
        return ResponseEntity.ok().body(response);
    }
    
    @PostMapping("/brand")
    public ResponseEntity<ResponseDTO> addBrand(@RequestBody BrandDTO p)throws DataNotFoundException{
        
        List<Brand> list= brandService.getAll();
        int temp=0;
        for (int i=0;i<list.size();i++) {
            if(list.get(i).getName().equalsIgnoreCase(p.getName())==true){
                if(list.get(i).getCountry().equalsIgnoreCase(p.getCountry())==true){
                    temp++;
                    throw new ApiRequestException("Trùng name và Trùng country");   
                }  
            }
        }
        if(temp==0){
            brandService.save(brandConverter.convertToEntity(p));
            ResponseDTO response = new ResponseDTO();
            response.setData(p);
            return ResponseEntity.ok().body(response);
        }
        return null; 
    }
    
    @PutMapping("/brand")
    public ResponseEntity<ResponseDTO> updateBrand(@RequestBody BrandDTO p)throws DataNotFoundException{
            Brand producer= brandService.getById(p.getId());
            producer.setName(p.getName());  
            producer.setCountry(p.getCountry());
            //producer.setImg(imageServiceImpl.getFile(p.getImgId()));
            brandService.save(producer);
            
            ResponseDTO response = new ResponseDTO();
            response.setData(brandConverter.convertToDTO(producer));
            return ResponseEntity.ok().body(response);
          
    }
    @PatchMapping("/brand/{id}")
     public ResponseEntity<ResponseDTO> patchBrand(@PathVariable int id,@RequestBody Map<Object,Object> p)throws DataNotFoundException{
            Brand brand= brandService.getById(id);
            p.forEach((k,v)->{
                Field field=ReflectionUtils.findField(Brand.class, (String) k);
                field.setAccessible(true);
                ReflectionUtils.setField(field, brand, v);
            });
            brandService.save(brand);
            ResponseDTO response = new ResponseDTO();
            response.setData(brandConverter.convertToDTO(brand));
            return ResponseEntity.ok().body(response);
          
    }
    @DeleteMapping("/brand/{Id}")
    public ResponseEntity<ResponseDTO> deteteBrand(@PathVariable int Id)throws DataNotFoundException{
        Brand p= brandService.getById(Id);
        brandService.deleteById(Id);
        ResponseDTO response = new ResponseDTO(); 
        String temp="Delete success id"+Id;
        response.setData(temp);
        return ResponseEntity.ok().body(response);
    }
    
    
    
    
    
    
    
    
}
