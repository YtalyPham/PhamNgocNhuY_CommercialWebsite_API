/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.se.phone.dto;

import lombok.Data;

/**
 *
 * @author PhamNgocNhuY_18055121
 */
@Data
public class ResponseDTO {
    private String errorCode;
    private Object data;
    private String successCode;
}
