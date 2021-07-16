/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.se.phone.controller;

import com.se.phone.converter.ProductConverter;
import com.se.phone.dto.ProductDTO;
import com.se.phone.entity.Category;
import com.se.phone.entity.ProductDetail;
import com.se.phone.entity.Product;
import com.se.phone.entity.Brand;
import com.se.phone.exception.ApiRequestException;
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

/**
 *
 * @author PhamNgocNhuY_18055121
 */
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
    
    //SORT
    //http://localhost:8080/Ytalyphone/phone?sortBy=name
    @GetMapping("/phone")
     
    public List<ProductDTO> getPhones(
            @RequestParam Optional<Integer> page,
            @RequestParam Optional<String> sortBy){
        //getContent() output list of Category or not output go Page<Catagory>
        List<Product> list= phoneService.getAllSort(page,sortBy).getContent();
        return list.stream().map(phoneConverter::convertToDTO).collect(Collectors.toList()) ;
                
    }

    //http://localhost:8080/Ytalyphone/phone/search/Xiaomi
     @GetMapping("/phone/searchByName/{name}") 
    public List<ProductDTO> searchByName(@PathVariable String name){
        List<Product> list= phoneService.getAllSearchByName(name.toLowerCase());
        return list.stream().map(phoneConverter::convertToDTO).collect(Collectors.toList());
    }
    
     @GetMapping("/phone/searchByProducer/{id}") 
    public List<ProductDTO> searchByProducer(@PathVariable int id){
        List<Product> list= phoneService.getAllSearchByProducer(id);
        return list.stream().map(phoneConverter::convertToDTO).collect(Collectors.toList());
    }
    
     @GetMapping("/phone/searchByCatagory/{id}") 
    public List<ProductDTO> searchByCatagory(@PathVariable int id){
        List<Product> list= phoneService.getAllSearchByCatagory(id);
        return list.stream().map(phoneConverter::convertToDTO).collect(Collectors.toList());
    }
    
    @GetMapping("/phone/{Id}")
    public ProductDTO getPhone(@PathVariable int Id){
        Product phone = phoneService.getById(Id);
        ProductDTO phoneDTO= phoneConverter.convertToDTO(phone);
       
        return phoneDTO;
    }
    
    @PostMapping("/phone")
    @PreAuthorize("hasRole('PM') or hasRole('ADMIN')")
    public ProductDTO addPhone(@RequestBody ProductDTO p){
        List<Product> list= phoneService.getAll();
        int temp=0;
        for (int i=0;i<list.size();i++) {
            if(list.get(i).getName().equalsIgnoreCase(p.getName())==true){
                    temp++;
                    throw new ApiRequestException("Trùng name");   
                
            }
        }
        if(temp==0){
            Product phone= phoneConverter.convertToEntity(p);
            phoneService.save(phone);
            return p;//khong lay dc id
        }
        return null; 
    }
 
    @PutMapping("/phone")
    @PreAuthorize("hasRole('PM') or hasRole('ADMIN')")
    public ProductDTO updatePhone(@RequestBody ProductDTO p){
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
            if(o!=null&&c!=null&&pr!=null){
                phone.setProductDetail(o);  
                phone.setCategory(c);
                phone.setBrand(pr);  
            }
            phoneService.save(phone);
            return phoneConverter.convertToDTO(phone);
          
    }
    @PatchMapping("/phone/{id}")
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
    
    @DeleteMapping("/phone/{Id}")
    @PreAuthorize("hasRole('PM') or hasRole('ADMIN')")
    public String detetePhone(@PathVariable int Id){
        Product p= phoneService.getById(Id);
        phoneService.deleteById(Id);
        return "Delete sucess PhoneId= "+Id;
    }
 
}
