/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.se.phone.entity;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.Lob;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
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
    
    @Column(name = "rating")
    private double rating;
    
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "option_fk", referencedColumnName = "id")
    private Option option;
    
   @ManyToOne
   @JoinColumn(name = "catagory_fk")
   private Catagory catagory;
   
   @ManyToOne
   @JoinColumn(name = "producer_fk")
   private Producer producer;
   
   @ManyToMany(fetch = FetchType.LAZY)
	@JoinTable(name = "product_image", 
		joinColumns = @JoinColumn(name = "product_id"), 
		inverseJoinColumns = @JoinColumn(name = "image_id"))
	private List<Image> images;
  
//   @OneToMany(mappedBy="phone")
//   private List<OrderDetail> orderDetails;

    @Override
    public String toString() {
        return "Phone{" + "id=" + id + ", name=" + name + ", price=" + price + ", amount=" + amount + ", status=" + status + ", discountPer=" + discountPer + ", rating=" + rating + ", option=" + option + ", catagory=" + catagory + ", producer=" + producer + ", images=" + images + '}';
    }

    public double getRating() {
        return rating;
    }

    public void setRating(double rating) {
        this.rating = rating;
    }

    public List<Image> getImages() {
        return images;
    }

    public void setImages(List<Image> images) {
        this.images = images;
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

    

    
    

    
    
    
}
