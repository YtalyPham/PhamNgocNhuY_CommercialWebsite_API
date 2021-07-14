/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.se.phone.service.impl;

import com.se.phone.entity.ProductDetail;
import com.se.phone.exception.ApiRequestException;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.se.phone.reposity.ProductDetailRepository;
import com.se.phone.service.ProductDetailService;

/**
 *
 * @author PhamNgocNhuY_18055121
 */
@Service
public class ProductDetailServiceImpl implements ProductDetailService{
    private ProductDetailRepository optionRepository;
    @Autowired
    public ProductDetailServiceImpl(ProductDetailRepository optionRepository) {
        this.optionRepository = optionRepository;
    }
    
   @Override
    public ProductDetail save(ProductDetail c) {
        return optionRepository.save(c);
    }

    @Override
    public void deleteById(int id) {
        optionRepository.deleteById(id);
    }

     
    @Override
    public ProductDetail getById(int id) {
        Optional<ProductDetail> o= optionRepository.findById(id);
        ProductDetail option= null;
        if(o.isPresent()){
            option=o.get();
        }else{
            throw new ApiRequestException("Did not find Id "+ id);
        }
        return option;
    }

    @Override
    public List<ProductDetail> getAll() {
        return optionRepository.findAll();
    }

     
}
