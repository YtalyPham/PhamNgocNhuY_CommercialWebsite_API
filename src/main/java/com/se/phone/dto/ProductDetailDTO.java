/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.se.phone.dto;

import javax.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

/**
 *
 * @author PhamNgocNhuY_18055121
 */
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class ProductDetailDTO {
    private int id;
    @NotNull(message = "screenSize can not null")
    private String screenSize;
    
    @NotNull(message = "screenTechnology can not null")
    private String screenTechnology;
    
    @NotNull(message = "cameraBack can not null")
    private String cameraBack;
    
    @NotNull(message = "cameraFont can not null")
    private String cameraFont;
    
    @NotNull(message = "chipset can not null")
    private String chipset;
    
    @NotNull(message = "RAM can not null")
    private String RAM;
    
    @NotNull(message = "memory can not null")
    private String memory;
    
    @NotNull(message = "battery can not null")
    private String battery;
    
    @NotNull(message = "SIM can not null")
    private String SIM;
    
    @NotNull(message = "system can not null")
    private String system;
    
    @NotNull(message = "screenResolution can not null")
    private String screenResolution;
    
    @NotNull(message = "size can not null")
    private String size;
    
    @NotNull(message = "weight can not null")
    private String weight;
    
    @NotNull(message = "chargingTechnology can not null")
    private String chargingTechnology;
    
    @NotNull(message = "chargingPort can not null")
    private String chargingPort;
    
    @NotNull(message = "sensors can not null")
    private String sensors;
    
    @NotNull(message = "wiFi can not null")
    private String wiFi;
    
    @NotNull(message = "bluetooth can not null")
    private String bluetooth;
    
     
}
