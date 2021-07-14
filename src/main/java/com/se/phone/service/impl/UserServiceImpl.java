/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.se.phone.service.impl;

import com.se.phone.converter.ConverterService;
import com.se.phone.dto.UserDTO;
import com.se.phone.entity.Product;
import com.se.phone.entity.User;
import com.se.phone.exception.ApiRequestException;
import com.se.phone.reposity.UserRepository;
import com.se.phone.service.UserService;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author PhamNgocNhuY_18055121
 */
@Service
public class UserServiceImpl implements UserService{
    private UserRepository userRepository;
    private ModelMapper modelMapper;
    private ConverterService converterService;
    @Autowired
    public UserServiceImpl(UserRepository userRepository, ModelMapper modelMapper, ConverterService converterService) {
        this.userRepository = userRepository;
        this.modelMapper = modelMapper;
        this.converterService = converterService;
    }
       
    @Override
    public void deleteById(long id) {
        userRepository.deleteById(id);
    }
    

    @Override
    public List<UserDTO> getAll() {
        List<User> list= userRepository.findAll();
        return list.stream().map(converterService::convertToDTO).collect(Collectors.toList());
    }

     
        //convert Entity to DTO
    public UserDTO mapToDTO(User user){
        UserDTO userDTO=modelMapper.map(user, UserDTO.class);
       return userDTO;
    }
   //convert DTO to Entity 
    public User mapToEntity(UserDTO catagoryDTO){
       User user= modelMapper.map(catagoryDTO, User.class);
       return user;
   }

    @Override
    public UserDTO getById(long id) {
        User u= userRepository.getById(id);
        return mapToDTO(u);
    }

//    @Override
//    public List<UserDTO> getByUserName(String keyword) {
//        List<User> list= userRepository.getByUserName(keyword);
//        return list.stream().map(converterService::convertToDTO).collect(Collectors.toList());
//    }
    
}
