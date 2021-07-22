/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.se.phone.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Index;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

/**
 *
 * @author PhamNgocNhuY_18055121
 */
@Entity
@Table(name = "ProductDetail" ,
       indexes = {
            @Index(name = "productdetail_name_index" , columnList = "id , RAM")
 })
@Getter
@Setter
@NoArgsConstructor
@ToString
public class ProductDetail {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    
    @NotNull(message = "screenSize can not null")
    @Column(name="screenSize")
    private String screenSize;
    
    @NotNull(message = "screenTechnology can not null")
    @Column(name="screenTechnology")
    private String screenTechnology;
    
    @NotNull(message = "cameraBack can not null")
    @Column(name="cameraBack")
    private String cameraBack;
    
    @NotNull(message = "cameraFont can not null")
    @Column(name="cameraFont")
    private String cameraFont;
    
    @NotNull(message = "chipset can not null")
    @Column(name="chipset")
    private String chipset;
    
    @NotNull(message = "RAM can not null")
    @Column(name="RAM")
    private String RAM;
    
    @NotNull(message = "memory can not null")
    @Column(name="memory")
    private String memory;
    
    @NotNull(message = "battery can not null")
    @Column(name="battery")
    private String battery;
    
    @NotNull(message = "SIM can not null")
    @Column(name="SIM")
    private String SIM;
    
    @NotNull(message = "system can not null")
    @Column(name="system")
    private String system;
    
    @NotNull(message = "screenResolution can not null")
    @Column(name="screenResolution")
    private String screenResolution;
    
    @NotNull(message = "size can not null")
    @Column(name="size")
    private String size;
    
    @NotNull(message = "weight can not null")
    @Column(name="weight")
    private String weight;
    
    @NotNull(message = "chargingTechnology can not null")
    @Column(name="chargingTechnology")
    private String chargingTechnology;
    
    @NotNull(message = "chargingPort can not null")
    @Column(name="chargingPort")
    private String chargingPort;
    
    @NotNull(message = "sensors can not null")
    @Column(name="sensors")
    private String sensors;
    
    @NotNull(message = "wiFi can not null")
    @Column(name="wiFi")
    private String wiFi;
    
    @NotNull(message = "bluetooth can not null")
    @Column(name="bluetooth")
    private String bluetooth;
    
     

    public ProductDetail(int id, String screenSize, String screenTechnology, String cameraBack, String cameraFont, String chipset, String RAM, String memory, String battery, String SIM, String system, String screenResolution, String size, String weight, String chargingTechnology, String chargingPort, String sensors, String wiFi, String bluetooth) {
        this.id = id;
        this.screenSize = screenSize;
        this.screenTechnology = screenTechnology;
        this.cameraBack = cameraBack;
        this.cameraFont = cameraFont;
        this.chipset = chipset;
        this.RAM = RAM;
        this.memory = memory;
        this.battery = battery;
        this.SIM = SIM;
        this.system = system;
        this.screenResolution = screenResolution;
        this.size = size;
        this.weight = weight;
        this.chargingTechnology = chargingTechnology;
        this.chargingPort = chargingPort;
        this.sensors = sensors;
        this.wiFi = wiFi;
        this.bluetooth = bluetooth;
    }

     
}
