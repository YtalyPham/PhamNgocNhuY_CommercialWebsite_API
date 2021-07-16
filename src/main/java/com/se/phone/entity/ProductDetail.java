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
    
    @Column(name="screenSize")
    private String screenSize;
    
    @Column(name="screenTechnology")
    private String screenTechnology;
    
    @Column(name="cameraBack")
    private String cameraBack;
    
    @Column(name="cameraFont")
    private String cameraFont;
    
    @Column(name="chipset")
    private String chipset;
    
    @Column(name="RAM")
    private String RAM;
    
    @Column(name="memory")
    private String memory;
    
    @Column(name="battery")
    private String battery;
    
    @Column(name="SIM")
    private String SIM;
    
    @Column(name="system")
    private String system;
    
    @Column(name="screenResolution")
    private String screenResolution;
    
    @Column(name="size")
    private String size;
    
    @Column(name="weight")
    private String weight;
    
    @Column(name="chargingTechnology")
    private String chargingTechnology;
    
    @Column(name="chargingPort")
    private String chargingPort;
    
    @Column(name="sensors")
    private String sensors;
    
    @Column(name="wiFi")
    private String wiFi;
    
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
