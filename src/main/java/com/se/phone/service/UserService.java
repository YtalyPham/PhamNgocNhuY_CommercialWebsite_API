/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.se.phone.service;

import com.se.phone.dto.UserDTO;
import com.se.phone.entity.User;
import java.util.List;

/**
 *
 * @author PhamNgocNhuY_18055121
 */
public interface UserService {
    
    public void deleteById(long id);
    //public Page<UserDTO> getAllSort(@RequestParam Optional<Integer> page,@RequestParam Optional<String> sortBy);
    public List<UserDTO> getAll();
   // public List<UserDTO> getByUserName(String keyword);
    public UserDTO getById(long id);
     
}
