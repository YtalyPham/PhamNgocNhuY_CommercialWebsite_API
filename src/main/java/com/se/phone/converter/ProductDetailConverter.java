/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.se.phone.converter;

import com.se.phone.constants.ErrorCode;
import com.se.phone.dto.ProductDetailDTO;
import com.se.phone.entity.ProductDetail;
import com.se.phone.exception.ApiRequestException;
import com.se.phone.exception.ConvertEntityDTOException;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 *
 * @author PhamNgocNhuY_18055121
 */
@Component
public class ProductDetailConverter {
     private ModelMapper modelMapper;
     @Autowired
    public ProductDetailConverter(ModelMapper modelMapper) {
        this.modelMapper = modelMapper;
    }
     public ProductDetail convertToEntity(ProductDetailDTO dto) {
        try {
            ProductDetail productDetail= modelMapper.map(dto,ProductDetail.class);
            return productDetail;
        } catch (Exception e) {
             throw new ApiRequestException(ErrorCode.ERR_CONVERTENTITY_FAIL);
        }
    }
     
     public ProductDetailDTO convertToDTO(ProductDetail productDetail){
        try {
            ProductDetailDTO productDetailDTO=modelMapper.map(productDetail,ProductDetailDTO.class);            
            return productDetailDTO;
        } catch (Exception e) {
             throw new ApiRequestException(ErrorCode.ERR_CONVERTDTO_FAIL);
        }
     }
}
