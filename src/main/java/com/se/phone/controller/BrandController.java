/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.se.phone.controller;

import com.se.phone.constants.ErrorCode;
import com.se.phone.constants.SuccessCode;
import com.se.phone.converter.BrandConverter;
import com.se.phone.dto.BrandDTO;
import com.se.phone.dto.ResponseDTO;
import com.se.phone.entity.Brand;
import com.se.phone.exception.ApiRequestException;
import com.se.phone.exception.CreateDataFailException;
import com.se.phone.exception.DataNotFoundException;
import com.se.phone.exception.DataValidationException;
import com.se.phone.exception.DeleteDataFailException;
import com.se.phone.exception.DuplicateDataException;
import com.se.phone.exception.UpdateDataFailException;
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
import javax.validation.Valid;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;

/**
 *
 * @author PhamNgocNhuY_18055121
 */
@CrossOrigin(origins = "http://localhost:3000", maxAge = 3600)
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
    private static final Logger LOGGER = LoggerFactory.getLogger(BrandController.class);
    
    
    //SORT
    //http://localhost:8080/Ytalyphone/producer?sortBy=name
    @GetMapping("/brand")
    public ResponseEntity<ResponseDTO> getBrands(
            @RequestParam Optional<Integer> page,
            @RequestParam Optional<String> sortBy) throws DataNotFoundException{
        //getContent() output list of Catagory or not output go Page<Catagory>
        ResponseDTO response = new ResponseDTO();
        try {
            List<Brand> list= brandService.getAllSort(page,sortBy).getContent();
            response.setData(list.stream().map(brandConverter::convertToDTO).collect(Collectors.toList()));
            response.setSuccessCode(SuccessCode.BRAND_FIND_SUCCESS);
        } catch (Exception e) {
            response.setErrorCode(ErrorCode.ERR_BRAND_NOT_FOUND);
            throw new DataNotFoundException(ErrorCode.ERR_BRAND_NOT_FOUND);
        }
        return ResponseEntity.ok().body(response);      
    }

    
    //http://localhost:8080/Ytalyphone/producer/search/Xiaomi
     @GetMapping("/brand/search/{name}")
    public ResponseEntity<ResponseDTO> searchByName(@PathVariable String name)throws DataNotFoundException{
        ResponseDTO response = new ResponseDTO();
        List<Brand> list=brandService.getAllSearch(name.toLowerCase());
        if(list.size()<=0){
            response.setData(list.stream().map(brandConverter::convertToDTO).collect(Collectors.toList()));
            response.setSuccessCode(SuccessCode.BRAND_FIND_SUCCESS);
        }else{
            response.setErrorCode(ErrorCode.ERR_BRAND_NOT_FOUND);
        }
        
        return ResponseEntity.ok().body(response); 
    }
    
    
    @GetMapping("/brand/{Id}")
    public ResponseEntity<ResponseDTO> getBrand(@PathVariable int Id)throws DataNotFoundException{
        ResponseDTO response = new ResponseDTO();
        try {
            Brand p = brandService.getById(Id);
            response.setData(brandConverter.convertToDTO(p));
            response.setSuccessCode(SuccessCode.BRAND_FIND_SUCCESS);
        } catch (Exception e) {
            response.setErrorCode(ErrorCode.ERR_BRAND_NOT_FOUND);
            throw new DataNotFoundException(ErrorCode.ERR_BRAND_NOT_FOUND);
        }
        return ResponseEntity.ok().body(response);
    }
    
    @PostMapping("/brand")
    public ResponseEntity<ResponseDTO> addBrand(@Valid @RequestBody BrandDTO p)throws CreateDataFailException,DuplicateDataException, DataValidationException{
        
        //validate name 
      //  if(name_duplicate){}
        //validate 
        
        //data valid
        ResponseDTO response = new ResponseDTO();
        List<Brand> list= brandService.getAll();
        int temp=0;
        for (int i=0;i<list.size();i++) {
            if(list.get(i).getName().equalsIgnoreCase(p.getName())==true){
                if(list.get(i).getCountry().equalsIgnoreCase(p.getCountry())==true){
                    temp++;
                    response.setErrorCode(ErrorCode.ERR_BRAND_EXISTED);
                    throw new DuplicateDataException(ErrorCode.ERR_BRAND_EXISTED);   
                }  
            }
        }
        try {
            if(temp==0){
            Brand b=brandService.save(brandConverter.convertToEntity(p));
            p.setId(b.getId());
            response.setData(p);
            response.setSuccessCode(SuccessCode.BRAND_CREATE_SUCCESS);
           }
        }
        
        catch (Exception e) {
            response.setErrorCode(ErrorCode.ERR_CREATE_BRAND_FAIL);
            throw new CreateDataFailException(ErrorCode.ERR_CREATE_BRAND_FAIL);
        }
        
       return ResponseEntity.ok().body(response);
    }
    
    @PutMapping("/brand")
    public ResponseEntity<ResponseDTO> updateBrand(@Valid @RequestBody BrandDTO p)throws UpdateDataFailException{
        ResponseDTO response = new ResponseDTO();
        try {
             Brand producer= brandService.getById(p.getId());
            producer.setName(p.getName());  
            producer.setCountry(p.getCountry());
            //producer.setImg(imageServiceImpl.getFile(p.getImgId()));
            Brand br=brandService.save(producer);
            response.setData(brandConverter.convertToDTO(producer));
            response.setSuccessCode(SuccessCode.BRAND_UPDATE_SUCCESS);
        } catch (Exception e) {
            response.setErrorCode(ErrorCode.ERR_UPDATE_BRAND_FAIL);
            throw new UpdateDataFailException(ErrorCode.ERR_UPDATE_BRAND_FAIL);
        }
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
    public ResponseEntity<ResponseDTO> deteteBrand(@PathVariable int Id)throws DeleteDataFailException{
        
        ResponseDTO response = new ResponseDTO(); 
        try {
            Brand p= brandService.getById(Id);
            brandService.deleteById(Id);
            String temp="Delete success id"+Id;
            response.setData(temp);
            response.setSuccessCode(SuccessCode.BRAND_DELETE_SUCCESS);
        } catch (Exception e) {
            response.setErrorCode(ErrorCode.ERR_DELETE_BRAND_FAIL);
            throw new DeleteDataFailException(ErrorCode.ERR_DELETE_BRAND_FAIL);
        }
        return ResponseEntity.ok().body(response);
    }
    
    
    
    
    
    
    
    
}
