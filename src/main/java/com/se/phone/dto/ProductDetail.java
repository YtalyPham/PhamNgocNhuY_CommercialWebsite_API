/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.se.phone.dto;

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
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class ProductDetail {
    private int id;
    private String screenSize;
    private String screenTechnology;
    private String cameraBack;
    private String cameraFont;
    private String chipset;
    private String RAM;
    private String memory;
    private String battery;
    private String SIM;
    private String system;
    private String screenResolution;
    private String size;
    private String weight;
    private String chargingTechnology;
    private String chargingPort;
    private String sensors;
    private String wiFi;
    private String bluetooth;
}
