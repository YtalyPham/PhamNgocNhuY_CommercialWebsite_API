/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.se.phone.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 *
 * @author PhamNgocNhuY_18055121
 */
@ResponseStatus(value = HttpStatus.NOT_FOUND)
public class DataNotFoundException extends Exception {

    private static final long serialVersionUID = 4L;

    public DataNotFoundException(String message){
        super( message);
    }
}
