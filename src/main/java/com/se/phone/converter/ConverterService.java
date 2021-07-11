/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.se.phone.converter;

import com.se.phone.dto.UserDTO;
import com.se.phone.entity.User;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 *
 * @author PhamNgocNhuY_18055121
 */
@Component
public class ConverterService {
    @Autowired
    private ModelMapper modelMapper;
    
    public UserDTO convertToDTO(User userObject){
        return modelMapper.map(userObject, UserDTO.class);
    }
}
