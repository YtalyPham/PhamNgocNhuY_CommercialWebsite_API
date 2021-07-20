/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.se.phone.controller;

import com.se.phone.constants.ErrorCode;
import com.se.phone.constants.SuccessCode;
import com.se.phone.dto.ResponseDTO;
import com.se.phone.dto.UserDTO;
import com.se.phone.exception.DataNotFoundException;
import com.se.phone.service.UserService;
import java.util.ArrayList;
import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author PhamNgocNhuY_18055121
 */
@RestController
@PreAuthorize("hasRole('ADMIN')")
public class UserController {
    private UserService userService;
    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }
    private static final Logger LOGGER = LoggerFactory.getLogger(UserController.class);
    //http://localhost:8080/Ytalyphone/account/
    @GetMapping("/account")
	public ResponseEntity<ResponseDTO> getAllAccounts() throws DataNotFoundException{
            ResponseDTO response = new ResponseDTO();	
            List<UserDTO> list= userService.getAll();
            if(list.size()==0){
                response.setData(list);
                response.setSuccessCode(SuccessCode.USER_LOADED_SUCCESS);
            }else{
                response.setErrorCode(ErrorCode.ERR_USER_NOT_FOUND);
            }
            return ResponseEntity.ok().body(response);
	}
        //http://localhost:8080/Ytalyphone/account/searchByName/aaa phai dung ten
        @GetMapping("/account/searchByName/{name}")
	//@PreAuthorize("hasRole('ADMIN')")
	public ResponseEntity<ResponseDTO> getByUserName(@PathVariable String name) throws DataNotFoundException{
            List<UserDTO> list=userService.getAll();
            List<UserDTO> l= new ArrayList<>();
            for (UserDTO userDTO : list) {
                if(userDTO.getUsername().equalsIgnoreCase(name)){
                    l.add(userDTO);
                }
            }
            ResponseDTO response = new ResponseDTO();
            response.setData(l);
            return ResponseEntity.ok().body(response);
	}
        //http://localhost:8080/Ytalyphone/account/searchById/1
        @GetMapping("/account/searchById/{id}")
	//@PreAuthorize("hasRole('ADMIN')")
	public ResponseEntity<ResponseDTO> getById(@PathVariable int id) throws DataNotFoundException{
            ResponseDTO response = new ResponseDTO();
            try {
                response.setData(userService.getById(id));
                response.setSuccessCode(SuccessCode.USER_LOADED_SUCCESS);
            } catch (Exception e) {
                response.setErrorCode(ErrorCode.ERR_USER_NOT_FOUND);
            }
            
            return ResponseEntity.ok().body(response);	
	}
}
