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
import javax.persistence.Table;

/**
 *
 * @author PhamNgocNhuY_18055121
 */
@Entity
@Table(name = "option")
public class Option {
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
    
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getScreenSize() {
        return screenSize;
    }

    public void setScreenSize(String screenSize) {
        this.screenSize = screenSize;
    }

    public String getScreenTechnology() {
        return screenTechnology;
    }

    public void setScreenTechnology(String screenTechnology) {
        this.screenTechnology = screenTechnology;
    }

    public String getCameraBack() {
        return cameraBack;
    }

    public void setCameraBack(String cameraBack) {
        this.cameraBack = cameraBack;
    }

    public String getCameraFont() {
        return cameraFont;
    }

    public void setCameraFont(String cameraFont) {
        this.cameraFont = cameraFont;
    }

    public String getChipset() {
        return chipset;
    }

    public void setChipset(String chipset) {
        this.chipset = chipset;
    }

    public String getRAM() {
        return RAM;
    }

    public void setRAM(String RAM) {
        this.RAM = RAM;
    }

    public String getMemory() {
        return memory;
    }

    public void setMemory(String memory) {
        this.memory = memory;
    }

    public String getBattery() {
        return battery;
    }

    public void setBattery(String battery) {
        this.battery = battery;
    }

    public String getSIM() {
        return SIM;
    }

    public void setSIM(String SIM) {
        this.SIM = SIM;
    }

    public String getSystem() {
        return system;
    }

    public void setSystem(String system) {
        this.system = system;
    }

    public String getScreenResolution() {
        return screenResolution;
    }

    public void setScreenResolution(String screenResolution) {
        this.screenResolution = screenResolution;
    }

    public String getSize() {
        return size;
    }

    public void setSize(String size) {
        this.size = size;
    }

    public String getWeight() {
        return weight;
    }

    public void setWeight(String weight) {
        this.weight = weight;
    }

    public String getChargingTechnology() {
        return chargingTechnology;
    }

    public void setChargingTechnology(String chargingTechnology) {
        this.chargingTechnology = chargingTechnology;
    }

    public String getChargingPort() {
        return chargingPort;
    }

    public void setChargingPort(String chargingPort) {
        this.chargingPort = chargingPort;
    }

    public String getSensors() {
        return sensors;
    }

    public void setSensors(String sensors) {
        this.sensors = sensors;
    }

    public String getWiFi() {
        return wiFi;
    }

    public void setWiFi(String wiFi) {
        this.wiFi = wiFi;
    }

    public String getBluetooth() {
        return bluetooth;
    }

    public void setBluetooth(String bluetooth) {
        this.bluetooth = bluetooth;
    }

    public Option() {
    }

    public Option(int id, String screenSize, String screenTechnology, String cameraBack, String cameraFont, String chipset, String RAM, String memory, String battery, String SIM, String system, String screenResolution, String size, String weight, String chargingTechnology, String chargingPort, String sensors, String wiFi, String bluetooth) {
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

    @Override
    public String toString() {
        return "Option{" + "id=" + id + ", screenSize=" + screenSize + ", screenTechnology=" + screenTechnology + ", cameraBack=" + cameraBack + ", cameraFont=" + cameraFont + ", chipset=" + chipset + ", RAM=" + RAM + ", memory=" + memory + ", battery=" + battery + ", SIM=" + SIM + ", system=" + system + ", screenResolution=" + screenResolution + ", size=" + size + ", weight=" + weight + ", chargingTechnology=" + chargingTechnology + ", chargingPort=" + chargingPort + ", sensors=" + sensors + ", wiFi=" + wiFi + ", bluetooth=" + bluetooth + '}';
    }   
}
