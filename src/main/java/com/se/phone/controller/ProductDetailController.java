/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.se.phone.controller;

import com.se.phone.converter.ProductDetailConverter;
import com.se.phone.dto.ProductDetailDTO;
import com.se.phone.dto.ResponseDTO;
import com.se.phone.entity.ProductDetail;
import com.se.phone.exception.DataNotFoundException;
import java.lang.reflect.Field;
import java.util.List;
import java.util.Map;
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
import org.springframework.web.bind.annotation.RestController;
import com.se.phone.service.ProductDetailService;
import java.util.stream.Collectors;
import org.springframework.http.ResponseEntity;

/**
 *
 * @author PhamNgocNhuY_18055121
 */
@RestController
public class ProductDetailController {
    private ProductDetailService productDetailService;
    private ProductDetailConverter productDetailConverter;
    @Autowired
     public ProductDetailController(ProductDetailService productDetailService, ProductDetailConverter productDetailConverter) {
        this.productDetailService = productDetailService;
        this.productDetailConverter = productDetailConverter;
    }
    
    @GetMapping("/productdetail")
    //@PreAuthorize("hasRole('PM') or hasRole('ADMIN')")
    public ResponseEntity<ResponseDTO> getAllOption() throws DataNotFoundException{
        ResponseDTO response = new ResponseDTO();
        List<ProductDetail>list= productDetailService.getAll();
        response.setData(list.stream().map(productDetailConverter::convertToDTO).collect(Collectors.toList()));
        return ResponseEntity.ok().body(response);
                
    }

   
    @GetMapping("/productdetail/{Id}")
    //@PreAuthorize("hasRole('PM') or hasRole('ADMIN')")
    public ResponseEntity<ResponseDTO> getOption(@PathVariable int Id)throws DataNotFoundException{
        ResponseDTO response = new ResponseDTO();
        ProductDetail o = productDetailService.getById(Id);
        ProductDetailDTO productDetailDTO= productDetailConverter.convertToDTO(o);
        response.setData(productDetailDTO);
        return ResponseEntity.ok().body(response);
    }
    
    @PostMapping("/productdetail")
    @PreAuthorize("hasRole('PM') or hasRole('ADMIN')")
    public ResponseEntity<ResponseDTO> addOption(@RequestBody ProductDetailDTO o)throws DataNotFoundException{
        ProductDetail productDetail= productDetailConverter.convertToEntity(o);
        productDetailService.save(productDetail);
        ResponseDTO response = new ResponseDTO();
        response.setData(productDetailConverter.convertToDTO(productDetail));//o 
        return ResponseEntity.ok().body(response);
    }

    @PutMapping("/productdetail")
    @PreAuthorize("hasRole('PM') or hasRole('ADMIN')")
    public ResponseEntity<ResponseDTO> updateOption(@RequestBody ProductDetail o)throws DataNotFoundException{
            ProductDetail productDetail= productDetailService.getById(o.getId());
            productDetail.setScreenSize(o.getScreenSize());  
            productDetail.setScreenTechnology(o.getScreenTechnology());
            productDetail.setCameraBack(o.getCameraBack());
            productDetail.setCameraFont(o.getCameraFont());
            productDetail.setChipset(o.getChipset());
            productDetail.setRAM(o.getRAM());
            productDetail.setMemory(o.getMemory());
            productDetail.setBattery(o.getBattery());
            productDetail.setSIM(o.getSIM());
            productDetail.setSystem(o.getSystem());
            //option.set
            productDetailService.save(productDetail);
            ResponseDTO response = new ResponseDTO();
            response.setData(productDetailConverter.convertToDTO(productDetail));//o 
            return ResponseEntity.ok().body(response);
              
    }
//    @PatchMapping("/option/{id}")
//    @PreAuthorize("hasRole('PM') or hasRole('ADMIN')")
//     public ProductDetail patchOption(@PathVariable int id,@RequestBody Map<Object,Object> p){
//            ProductDetail option= optionService.getById(id);
//            p.forEach((k,v)->{
//                Field field=ReflectionUtils.findField(ProductDetail.class, (String) k);
//                field.setAccessible(true);
//                ReflectionUtils.setField(field, option, v);
//            });
//            optionService.save(option);
//            return option;
//          
//    }
    
    @DeleteMapping("/productdetail/{Id}")
    @PreAuthorize("hasRole('PM') or hasRole('ADMIN')")
    public ResponseEntity<ResponseDTO> deteteOption(@PathVariable int Id)throws DataNotFoundException{
        ProductDetail o= productDetailService.getById(Id);
        productDetailService.deleteById(Id);
        ResponseDTO response = new ResponseDTO();
        response.setData("Delete sucess OptionId= "+Id);//o 
        return ResponseEntity.ok().body(response);
    }
    
}
