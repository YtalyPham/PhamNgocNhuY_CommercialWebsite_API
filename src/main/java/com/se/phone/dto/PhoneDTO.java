/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.se.phone.dto;

import java.util.List;

/**
 *
 * @author PhamNgocNhuY_18055121
 */

public class PhoneDTO {
    private int id;
    private String name;
    private Double price;
    private int amount;
    private String status;
    private Double discountPer;
    private double rating;
    private int optionId;
    private int catagoryId;
    private int producerId;
    private List<String> imagesId;

    public List<String> getImagesId() {
        return imagesId;
    }

    public void setImagesId(List<String> imagesId) {
        this.imagesId = imagesId;
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

    public double getRating() {
        return rating;
    }

    public void setRating(double rating) {
        this.rating = rating;
    }

    public int getOptionId() {
        return optionId;
    }

    public void setOptionId(int optionId) {
        this.optionId = optionId;
    }

    public int getCatagoryId() {
        return catagoryId;
    }

    public void setCatagoryId(int catagoryId) {
        this.catagoryId = catagoryId;
    }

    public int getProducerId() {
        return producerId;
    }

    public void setProducerId(int producerId) {
        this.producerId = producerId;
    }

    public PhoneDTO(int id, String name, Double price, int amount, String status, Double discountPer, double rating, int optionId, int catagoryId, int producerId, List<String> imagesId) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.amount = amount;
        this.status = status;
        this.discountPer = discountPer;
        this.rating = rating;
        this.optionId = optionId;
        this.catagoryId = catagoryId;
        this.producerId = producerId;
        this.imagesId = imagesId;
    }

    

    public PhoneDTO() {
    }

    @Override
    public String toString() {
        return "PhoneDTO{" + "id=" + id + ", name=" + name + ", price=" + price + ", amount=" + amount + ", status=" + status + ", discountPer=" + discountPer + ", rating=" + rating + ", optionId=" + optionId + ", catagoryId=" + catagoryId + ", producerId=" + producerId + ", imagesId=" + imagesId + '}';
    }

    

    
}
