/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.se.phone.exception;

import java.time.LocalDateTime;
import java.time.LocalTime;
import org.springframework.web.context.request.WebRequest;

/**
 *
 * @author PhamNgocNhuY_18055121
 */
public class ErrorDetail {
    private final LocalDateTime time;
    private final String message;
    private final String description;

    public ErrorDetail(LocalDateTime time, String message, String description) {
        this.time = time;
        this.message = message;
        this.description = description;
    }

    public LocalDateTime getTime() {
        return time;
    }

    public String getMessage() {
        return message;
    }

    public String getDescription() {
        return description;
    }

   

     

     

    
    

    
    
}
