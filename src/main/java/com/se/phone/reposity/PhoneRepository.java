/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.se.phone.reposity;

import com.se.phone.entity.Phone;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 *
 * @author PhamNgocNhuY_18055121
 */
public interface PhoneRepository extends JpaRepository<Phone, Integer>{
    
}
