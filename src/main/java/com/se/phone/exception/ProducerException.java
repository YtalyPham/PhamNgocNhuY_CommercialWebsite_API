/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.se.phone.exception;

/**
 *
 * @author PhamNgocNhuY_18055121
 */
public class ProducerException extends RuntimeException{

    public ProducerException(int id) {
        super("Could not found Producer id= "+ id);
    }
    
}
