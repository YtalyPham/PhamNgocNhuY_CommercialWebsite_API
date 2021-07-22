/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.se.phone.converter;

import com.se.phone.constants.ErrorCode;
import com.se.phone.dto.BrandDTO;
import com.se.phone.entity.Brand;
import com.se.phone.exception.ApiRequestException;
import com.se.phone.exception.ConvertEntityDTOException;
import com.se.phone.reposity.BrandRepository;
import com.se.phone.reposity.ImageRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 *
 * @author PhamNgocNhuY_18055121
 */
@Component
public class BrandConverter {
    private ModelMapper modelMapper;
    private BrandRepository brandRepository;
    private ImageRepository imageRepository;
    @Autowired
    public BrandConverter(ModelMapper modelMapper, BrandRepository brandRepository, ImageRepository imageRepository) {
        this.modelMapper = modelMapper;
        this.brandRepository = brandRepository;
        this.imageRepository = imageRepository;
    }
    public Brand convertToEntity(BrandDTO dto) {
        try {
            Brand brand= modelMapper.map(dto,Brand.class);
            //brand.setImg(imageRepository.findById(dto.getImgId()).get());
            return brand;
        } catch (Exception e) {
             throw new ApiRequestException(ErrorCode.ERR_CONVERTENTITY_FAIL);
        }
    }

  
    public BrandDTO convertToDTO(Brand brand) {
        try {
            BrandDTO brandDTO=modelMapper.map(brand,BrandDTO.class);
            
//            if(brand.getImg().getId()!=null){
//                brandDTO.setImgId(brand.getImg().getId());
//            }else{}
            
            return brandDTO;
        } catch (Exception e) {
            throw new ApiRequestException(ErrorCode.ERR_CONVERTDTO_FAIL);
        }
        
        
        
    }

   
}
