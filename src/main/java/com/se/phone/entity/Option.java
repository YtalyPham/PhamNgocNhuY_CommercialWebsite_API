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
import javax.persistence.Lob;
import javax.persistence.OneToOne;
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
    
    @OneToOne(mappedBy = "option")
    private Phone phone;

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

    public Phone getPhone() {
        return phone;
    }

    public void setPhone(Phone phone) {
        this.phone = phone;
    }
    

    public Option(int id, String screenSize, String screenTechnology, String cameraBack, String cameraFont, String chipset, String RAM, String memory, String battery, String SIM, String system, Phone phone) {
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
        this.phone = phone;
    }

    

    public Option() {
    }

    @Override
    public String toString() {
        return "Option{" + "id=" + id + ", screenSize=" + screenSize + ", screenTechnology=" + screenTechnology + ", cameraBack=" + cameraBack + ", cameraFont=" + cameraFont + ", chipset=" + chipset + ", RAM=" + RAM + ", memory=" + memory + ", battery=" + battery + ", SIM=" + SIM + ", system=" + system + '}';
    }

    

    
    
    
}
