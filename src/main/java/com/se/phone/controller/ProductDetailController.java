/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.se.phone.controller;

import com.se.phone.constants.ErrorCode;
import com.se.phone.constants.SuccessCode;
import com.se.phone.converter.ProductDetailConverter;
import com.se.phone.dto.ProductDetailDTO;
import com.se.phone.dto.ResponseDTO;
import com.se.phone.entity.ProductDetail;
import com.se.phone.exception.CreateDataFailException;
import com.se.phone.exception.DataNotFoundException;
import com.se.phone.exception.DeleteDataFailException;
import com.se.phone.exception.UpdateDataFailException;
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
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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
    private static final Logger LOGGER = LoggerFactory.getLogger(ProductDetailController.class);
    @GetMapping("/productdetail")
    //@PreAuthorize("hasRole('PM') or hasRole('ADMIN')")
    public ResponseEntity<ResponseDTO> getAllOption() throws DataNotFoundException{
        ResponseDTO response = new ResponseDTO();
        List<ProductDetail>list= productDetailService.getAll();
        if(list.size()==0){
            response.setData(list.stream().map(productDetailConverter::convertToDTO).collect(Collectors.toList()));
            response.setSuccessCode(SuccessCode.PRODUCTDETAIL_FIND_SUCCESS);
        }else{
            response.setErrorCode(ErrorCode.ERR_PRODUCTDETAIL_NOT_FOUND);
        }
        return ResponseEntity.ok().body(response);        
    }

   
    @GetMapping("/productdetail/{Id}")
    //@PreAuthorize("hasRole('PM') or hasRole('ADMIN')")
    public ResponseEntity<ResponseDTO> getOption(@PathVariable int Id)throws DataNotFoundException{
        ResponseDTO response = new ResponseDTO();
        try {
            ProductDetail o = productDetailService.getById(Id);
            ProductDetailDTO productDetailDTO= productDetailConverter.convertToDTO(o);
            response.setData(productDetailDTO);
            response.setSuccessCode(SuccessCode.PRODUCTDETAIL_FIND_SUCCESS);
        } catch (Exception e) {
            response.setErrorCode(ErrorCode.ERR_PRODUCTDETAIL_NOT_FOUND);
            throw new DataNotFoundException(ErrorCode.ERR_PRODUCTDETAIL_NOT_FOUND);
        }
        
        return ResponseEntity.ok().body(response);
    }
    
    @PostMapping("/productdetail")
    @PreAuthorize("hasRole('PM') or hasRole('ADMIN')")
    public ResponseEntity<ResponseDTO> addOption(@RequestBody ProductDetailDTO o)throws CreateDataFailException{
        ResponseDTO response = new ResponseDTO();
        try {
            ProductDetail productDetail= productDetailConverter.convertToEntity(o);
            productDetailService.save(productDetail);
            response.setData(productDetailConverter.convertToDTO(productDetail));//o
            response.setSuccessCode(SuccessCode.PRODUCTDETAIL_CREATE_SUCCESS);
        } catch (Exception e) {
            response.setErrorCode(ErrorCode.ERR_CREATE_ORDERDETAIL_FAIL);
            throw new CreateDataFailException(ErrorCode.ERR_CREATE_ORDERDETAIL_FAIL);
        }
        return ResponseEntity.ok().body(response);
    }

    @PutMapping("/productdetail")
    @PreAuthorize("hasRole('PM') or hasRole('ADMIN')")
    public ResponseEntity<ResponseDTO> updateOption(@RequestBody ProductDetail o)throws UpdateDataFailException{
        ResponseDTO response = new ResponseDTO();
        try {
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
            response.setData(productDetailConverter.convertToDTO(productDetail));//o 
            response.setSuccessCode(SuccessCode.PRODUCTDETAIL_UPDATE_SUCCESS);
        } catch (Exception e) {
            response.setErrorCode(ErrorCode.ERR_UPDATE_PRODUCTDETAIL_FAIL);
            throw new UpdateDataFailException(ErrorCode.ERR_UPDATE_PRODUCTDETAIL_FAIL);
        }
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
    public ResponseEntity<ResponseDTO> deteteOption(@PathVariable int Id)throws DeleteDataFailException{
        ResponseDTO response = new ResponseDTO();
        try {
            ProductDetail o= productDetailService.getById(Id);
            productDetailService.deleteById(Id);
            response.setData("Delete sucess OptionId= "+Id);//o 
            response.setSuccessCode(SuccessCode.PRODUCTDETAIL_DELETE_SUCCESS);
        } catch (Exception e) {
            response.setErrorCode(ErrorCode.ERR_DELETE_PRODUCTDETAIL_FAIL);
            throw new DeleteDataFailException(ErrorCode.ERR_DELETE_PRODUCTDETAIL_FAIL);
        }
        return ResponseEntity.ok().body(response);
    }
    
}
