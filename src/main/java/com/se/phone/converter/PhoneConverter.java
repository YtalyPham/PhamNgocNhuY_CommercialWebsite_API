/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.se.phone.converter;

import com.se.phone.dto.PhoneDTO;
import com.se.phone.entity.Catagory;
import com.se.phone.entity.Image;
import com.se.phone.entity.Option;
import com.se.phone.entity.Phone;
import com.se.phone.entity.Producer;
import com.se.phone.exception.ApiRequestException;
import com.se.phone.reposity.CatagoryRepository;
import com.se.phone.reposity.ImageRepository;
import com.se.phone.reposity.OptionRepository;
import com.se.phone.reposity.ProducerRepository;
import java.util.ArrayList;
import java.util.List;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 *
 * @author PhamNgocNhuY_18055121
 */
@Component
public class PhoneConverter {
    private ModelMapper modelMapper;
    private CatagoryRepository catagoryRepository;
    private ProducerRepository producerRepository;
    private OptionRepository optionRepository;
    private ImageRepository imageRepository;
    
  @Autowired
    public PhoneConverter(ModelMapper modelMapper, CatagoryRepository catagoryRepository, ProducerRepository producerRepository, OptionRepository optionRepository, ImageRepository imageRepository) {
        this.modelMapper = modelMapper;
        this.catagoryRepository = catagoryRepository;
        this.producerRepository = producerRepository;
        this.optionRepository = optionRepository;
        this.imageRepository = imageRepository;
    }

    public PhoneConverter() {
    }

    //converToEntity
    public Phone convertToEntity(PhoneDTO dto){
        try {
            Phone phone= modelMapper.map(dto,Phone.class);
            Catagory catagory= catagoryRepository.getById(dto.getCatagoryId());
            Producer producer= producerRepository.getById(dto.getProducerId());
            Option option= optionRepository.getById(dto.getOptionId());
            List<Image> list= new ArrayList<>();
            List<String> temp= dto.getImagesId();
            for (String a : temp) {
                list.add(imageRepository.findById(a).get());
            }
            phone.setCatagory(catagory);
            phone.setProducer(producer);
            phone.setOption(option);
            phone.setImages(list);
            return phone;
            
        } catch (Exception e) {
            throw new ApiRequestException("Can't convert to Entity",e);
        }
    }
    
    public PhoneDTO convertToDTO(Phone phone){
        try {
            PhoneDTO phoneDTO=modelMapper.map(phone,PhoneDTO.class);
            List<Image> list= phone.getImages();
            List<String> temp= new ArrayList<>();
            for (Image a : list) {
                temp.add(a.getId());
            }
            phoneDTO.setCatagoryId(phone.getCatagory().getId());
            phoneDTO.setProducerId(phone.getProducer().getId());
            phoneDTO.setOptionId(phone.getOption().getId());
            
            phoneDTO.setImagesId(temp);
            return phoneDTO;
            
       } catch (Exception e) {
           throw new ApiRequestException("Can't convert to DTO",e);
        }
    }
}
