/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.se.phone.controller;

import com.se.phone.constants.ErrorCode;
import com.se.phone.constants.SuccessCode;
import com.se.phone.converter.ProductConverter;
import com.se.phone.dto.ProductDTO;
import com.se.phone.dto.ResponseDTO;
import com.se.phone.entity.Category;
import com.se.phone.entity.ProductDetail;
import com.se.phone.entity.Product;
import com.se.phone.entity.Brand;
import com.se.phone.exception.ApiRequestException;
import com.se.phone.exception.CreateDataFailException;
import com.se.phone.exception.DataNotFoundException;
import com.se.phone.exception.DeleteDataFailException;
import com.se.phone.exception.DuplicateDataException;
import com.se.phone.exception.UpdateDataFailException;
import java.lang.reflect.Field;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;
import org.modelmapper.ModelMapper;
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
import com.se.phone.service.CategoryService;
import com.se.phone.service.ProductService;
import com.se.phone.service.ProductDetailService;
import com.se.phone.service.BrandService;
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
public class ProductController {
    private ProductService phoneService;
    private ProductDetailService optionService;
    private CategoryService catagoryService;
    private BrandService producerService;
    //private ModelMapper modelMapper;
    private ProductConverter phoneConverter;
    @Autowired
    public ProductController(ProductService phoneService, ProductDetailService optionService, CategoryService catagoryService, BrandService producerService, ProductConverter phoneConverter) {
        this.phoneService = phoneService;
        this.optionService = optionService;
        this.catagoryService = catagoryService;
        this.producerService = producerService;
        this.phoneConverter = phoneConverter;
    }
    private static final Logger LOGGER = LoggerFactory.getLogger(ProductController.class);
    //SORT
    //http://localhost:8080/Ytalyphone/product?sortBy=name
    @GetMapping("/product")
    public ResponseEntity<ResponseDTO> getPhones(
            @RequestParam Optional<Integer> page,
            @RequestParam Optional<String> sortBy)throws DataNotFoundException{
        //getContent() output list of Category or not output go Page<Catagory>
        ResponseDTO response = new ResponseDTO(); 
        try {
            List<Product> list= phoneService.getAllSort(page,sortBy).getContent();
            response.setData(list.stream().map(phoneConverter::convertToDTO).collect(Collectors.toList()));
            response.setSuccessCode(SuccessCode.PRODUCT_FIND_SUCCESS);
        } catch (Exception e) {
            response.setErrorCode(ErrorCode.ERR_PRODUCT_NOT_FOUND);
            throw new DataNotFoundException(ErrorCode.ERR_PRODUCT_NOT_FOUND);
        }
        
        return ResponseEntity.ok().body(response);
                
    }

    //http://localhost:8080/Ytalyphone/phone/search/Xiaomi
     @GetMapping("/product/searchByName/{name}") 
    public ResponseEntity<ResponseDTO> searchByName(@PathVariable String name)throws DataNotFoundException{
        ResponseDTO response = new ResponseDTO();
        List<Product> list= phoneService.getAllSearchByName(name.toLowerCase());
        response.setData(list.stream().map(phoneConverter::convertToDTO).collect(Collectors.toList()));
        return ResponseEntity.ok().body(response);
    }
    
     @GetMapping("/product/searchByProducer/{id}") 
    public ResponseEntity<ResponseDTO> searchByBrand(@PathVariable int id)throws DataNotFoundException{
        ResponseDTO response = new ResponseDTO();
        List<Product> list= phoneService.getAllSearchByBrand(id);
        response.setData(list.stream().map(phoneConverter::convertToDTO).collect(Collectors.toList()));
        return ResponseEntity.ok().body(response);
    }
    
     @GetMapping("/product/searchByCatagory/{id}") 
    public ResponseEntity<ResponseDTO> searchByCategory(@PathVariable int id)throws DataNotFoundException{
        ResponseDTO response = new ResponseDTO();
        List<Product> list= phoneService.getAllSearchByCategory(id);
        response.setData(list.stream().map(phoneConverter::convertToDTO).collect(Collectors.toList()));
        return ResponseEntity.ok().body(response);
    }
     @GetMapping("/product/searchAll/{name}") 
    public ResponseEntity<ResponseDTO> searchAll(@PathVariable String name)throws DataNotFoundException{
        ResponseDTO response = new ResponseDTO(); 
            List<Product> list= phoneService.getAllSearch(name.toLowerCase());
            if(list.size()>0){
                response.setData(list.stream().map(phoneConverter::convertToDTO).collect(Collectors.toList()));
                response.setSuccessCode(SuccessCode.PRODUCT_FIND_SUCCESS);
            }else{
                response.setErrorCode(ErrorCode.ERR_PRODUCT_NOT_FOUND);
            }
        return ResponseEntity.ok().body(response);
    }
    
    @GetMapping("/product/{Id}")
    public ResponseEntity<ResponseDTO> getPhone(@PathVariable int Id)throws DataNotFoundException{
        
        ResponseDTO response = new ResponseDTO();
        try {
            Product phone = phoneService.getById(Id);
            ProductDTO phoneDTO= phoneConverter.convertToDTO(phone);
            response.setData(phoneDTO);
            response.setSuccessCode(SuccessCode.PRODUCT_FIND_SUCCESS);
        } catch (Exception e) {
            response.setErrorCode(ErrorCode.ERR_PRODUCT_NOT_FOUND);
            throw new DataNotFoundException(ErrorCode.ERR_PRODUCT_NOT_FOUND);
        }
       return ResponseEntity.ok().body(response);
    }
    
    @PostMapping("/product")
    @PreAuthorize("hasRole('PM') or hasRole('ADMIN')")
    public ResponseEntity<ResponseDTO> addPhone(@Valid @RequestBody ProductDTO p)throws CreateDataFailException,DuplicateDataException{
        ResponseDTO response = new ResponseDTO();
        List<Product> list= phoneService.getAll();
        int temp=0;
        for (int i=0;i<list.size();i++) {
            if(list.get(i).getName().equalsIgnoreCase(p.getName())==true){
                    temp++;
                    response.setErrorCode(ErrorCode.ERR_PRODUCT_EXISTED);
                    throw new DuplicateDataException(ErrorCode.ERR_PRODUCT_EXISTED);   
                
            }
        }
        try {
            if(temp==0){
                Product phone= phoneConverter.convertToEntity(p);
                phoneService.save(phone);
                p.setId(phone.getId());
                response.setData(p);
                response.setSuccessCode(SuccessCode.PRODUCT_CREATE_SUCCESS);
            }
        } catch (Exception e) {
            response.setErrorCode(ErrorCode.ERR_CREATE_PRODUCT_FAIL);
            throw new CreateDataFailException(ErrorCode.ERR_CREATE_PRODUCT_FAIL);
        }
        return ResponseEntity.ok().body(response);
    }
 
    @PutMapping("/product")
    @PreAuthorize("hasRole('PM') or hasRole('ADMIN')")
    public ResponseEntity<ResponseDTO> updatePhone(@Valid @RequestBody ProductDTO p)throws UpdateDataFailException{
         ResponseDTO response = new ResponseDTO();
        try {
             Product phone= phoneService.getById(p.getId());
            phone.setName(p.getName());  
            phone.setPrice(p.getPrice());
            phone.setAmount(p.getAmount());  
            phone.setRating(p.getRating());  
            phone.setStatus(p.getStatus());
            phone.setDiscountPer(p.getDiscountPer());  
             
            ///////////////////////////////find and set by id for option catagory producer
            ProductDetail o= optionService.getById(p.getProductDetailId());
            Category c= catagoryService.getById(p.getCategoryId());
            Brand pr= producerService.getById(p.getBrandId());
            //if(o!=null&&c!=null&&pr!=null){
                phone.setProductDetail(o);  
                phone.setCategory(c);
                phone.setBrand(pr);  
           //}
            phoneService.save(phone);
             response.setData(phoneConverter.convertToDTO(phone));
             response.setSuccessCode(SuccessCode.PRODUCT_UPDATE_SUCCESS);
        } catch (Exception e) {
            response.setErrorCode(ErrorCode.ERR_UPDATE_PRODUCT_FAIL);
            throw new UpdateDataFailException(ErrorCode.ERR_UPDATE_PRODUCT_FAIL);
        }
            return ResponseEntity.ok().body(response);
          
    }
    @PatchMapping("/product/{id}")
    @PreAuthorize("hasRole('PM') or hasRole('ADMIN')")
     public Product patchPhone(@PathVariable int id,@RequestBody Map<Object,Object> p){
            Product phone= phoneService.getById(id);
            p.forEach((k,v)->{
                Field field=ReflectionUtils.findField(Product.class, (String) k);
                field.setAccessible(true);
                ReflectionUtils.setField(field, phone, v);
            });
            phoneService.save(phone);
            return phone;
          
    }
    
    @DeleteMapping("/product/{Id}")
    @PreAuthorize("hasRole('PM') or hasRole('ADMIN')")
    public ResponseEntity<ResponseDTO> detetePhone(@PathVariable int Id)throws DeleteDataFailException{
        ResponseDTO response = new ResponseDTO(); 
        try {
             Product p= phoneService.getById(Id);
            phoneService.deleteById(Id);
            String temp="Delete success id"+Id;
            response.setData(temp);
            response.setSuccessCode(SuccessCode.PRODUCT_DELETE_SUCCESS);
        } catch (Exception e) {
            response.setErrorCode(ErrorCode.ERR_DELETE_PRODUCT_FAIL);
            throw new DeleteDataFailException(ErrorCode.ERR_DELETE_PRODUCT_FAIL);
        }
       
        return ResponseEntity.ok().body(response);
    }
 
}
