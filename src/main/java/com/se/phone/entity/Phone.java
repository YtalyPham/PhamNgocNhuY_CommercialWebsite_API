/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.se.phone.entity;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;

/**
 *
 * @author PhamNgocNhuY_18055121
 */
@Entity
@Table(name = "phone")
public class Phone {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id")
    private int id;
    
    @Column(name="name")
    private String name;
    
    @Column(name="price")
    private Double price;
    
    @Column(name="amount")
    private int amount;
    
    @Column(name="status")
    private String status;
    
    @Column(name="discountPer")
    private Double discountPer;
    
    @Lob
    @Column(name = "image")
    private byte[] image;
    
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "option_fk", referencedColumnName = "id")
    private Option option;
    
   @ManyToOne
   @JoinColumn(name = "catagory_fk")
   private Catagory catagory;
   
   @ManyToOne
   @JoinColumn(name = "producer_fk")
   private Producer producer;

    public Phone(int id, String name, Double price, int amount, String status, Double discountPer, byte[] image, Option option, Catagory catagory, Producer producer) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.amount = amount;
        this.status = status;
        this.discountPer = discountPer;
        this.image = image;
        this.option = option;
        this.catagory = catagory;
        this.producer = producer;
    }

    public Phone() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Double getDiscountPer() {
        return discountPer;
    }

    public void setDiscountPer(Double discountPer) {
        this.discountPer = discountPer;
    }

    public byte[] getImage() {
        return image;
    }

    public void setImage(byte[] image) {
        this.image = image;
    }

    public Option getOption() {
        return option;
    }

    public void setOption(Option option) {
        this.option = option;
    }

    public Catagory getCatagory() {
        return catagory;
    }

    public void setCatagory(Catagory catagory) {
        this.catagory = catagory;
    }

    public Producer getProducer() {
        return producer;
    }

    public void setProducer(Producer producer) {
        this.producer = producer;
    }

    @Override
    public String toString() {
        return "Phone{" + "id=" + id + ", name=" + name + ", price=" + price + ", amount=" + amount + ", status=" + status + ", discountPer=" + discountPer + ", image=" + image + ", option=" + option + ", catagory=" + catagory + ", producer=" + producer + '}';
    }
    
    
}
