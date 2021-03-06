/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.se.phone.exception;

import java.time.LocalDateTime;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
 
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

/**
 *
 * @author PhamNgocNhuY_18055121
 */
@ControllerAdvice
public class GlobalExceptionHandle extends ResponseEntityExceptionHandler{
    @ExceptionHandler(DataNotFoundException.class)
    public ResponseEntity dataNotFoundException(DataNotFoundException ex, WebRequest resquest){
        ErrorDetail errorDetail= new ErrorDetail(LocalDateTime.now(), ex.getMessage(), resquest.getDescription(false));
        return new ResponseEntity<>(errorDetail,HttpStatus.NOT_FOUND); 
    }
     @ExceptionHandler(CreateDataFailException.class)
    public ResponseEntity createDataFailException(CreateDataFailException ex, WebRequest resquest){
        ErrorDetail errorDetail= new ErrorDetail(LocalDateTime.now(), ex.getMessage(), resquest.getDescription(false));
        return new ResponseEntity<>(errorDetail,HttpStatus.BAD_REQUEST); 
    }
     @ExceptionHandler(DeleteDataFailException.class)
    public ResponseEntity deleteDataFailException(DeleteDataFailException ex, WebRequest resquest){
        ErrorDetail errorDetail= new ErrorDetail(LocalDateTime.now(), ex.getMessage(), resquest.getDescription(false));
        return new ResponseEntity<>(errorDetail,HttpStatus.BAD_REQUEST); 
    }
     @ExceptionHandler(UpdateDataFailException.class)
    public ResponseEntity updateDataFailException(UpdateDataFailException ex, WebRequest resquest){
        ErrorDetail errorDetail= new ErrorDetail(LocalDateTime.now(), ex.getMessage(), resquest.getDescription(false));
        return new ResponseEntity<>(errorDetail,HttpStatus.BAD_REQUEST); 
    }
     @ExceptionHandler(DuplicateDataException.class)
    public ResponseEntity duplicateDataException(DuplicateDataException ex, WebRequest resquest){
        ErrorDetail errorDetail= new ErrorDetail(LocalDateTime.now(), ex.getMessage(), resquest.getDescription(false));
        return new ResponseEntity<>(errorDetail,HttpStatus.BAD_REQUEST); 
    }
    @ExceptionHandler(UserFailException.class)
    public ResponseEntity userFailException(UserFailException ex, WebRequest resquest){
        ErrorDetail errorDetail= new ErrorDetail(LocalDateTime.now(), ex.getMessage(), resquest.getDescription(false));
        return new ResponseEntity<>(errorDetail,HttpStatus.UNAUTHORIZED); 
    }
    
     @ExceptionHandler(ConvertEntityDTOException.class)
    public ResponseEntity convertEntityDTOException(ConvertEntityDTOException ex, WebRequest resquest){
        ErrorDetail errorDetail= new ErrorDetail(LocalDateTime.now(), ex.getMessage(), resquest.getDescription(false));
        return new ResponseEntity<>(errorDetail,HttpStatus.BAD_REQUEST); 
    }

    //Global Exception
    @ExceptionHandler(Exception.class)
    public ResponseEntity<?> globalExceptionHandler(Exception ex, WebRequest resquest){
        ErrorDetail errorDetail= new ErrorDetail(LocalDateTime.now(), ex.getMessage(), resquest.getDescription(false));
        return new ResponseEntity<>(errorDetail,HttpStatus.INTERNAL_SERVER_ERROR); 
    }
    
    //handling custom validation errors
    @ExceptionHandler(DataValidationException.class)
     public ResponseEntity<?> customValidationErrorHandling(DataValidationException ex){
         ErrorDetail errorDetail = new ErrorDetail(LocalDateTime.now(), null,null);
         return new ResponseEntity<>(errorDetail,HttpStatus.BAD_REQUEST); 
     }
    
     
    
    
}
