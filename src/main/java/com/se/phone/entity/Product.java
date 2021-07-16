/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.se.phone.entity;

import java.util.ArrayList;
import java.util.List;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Index;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 *
 * @author PhamNgocNhuY_18055121
 */
@Entity
@Table(name = "Product" ,
       indexes = {
            @Index(name = "product_name_index" , columnList = "id , name")
 })
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Product {
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
    
    @ManyToOne
    @JoinColumn(name = "productDetail_id")
    private ProductDetail productDetail;
    
   @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "category_id")
   private Category category;
   
   @ManyToOne
   @JoinColumn(name = "brand_id")
   private Brand brand;
   
   @OneToMany(mappedBy = "product", fetch = FetchType.LAZY)
   private List<Image> images;
  
//   @OneToMany(mappedBy="phone")
//   private List<OrderDetail> orderDetails;
   

     

 

    
    

    
    

    
    
    
}
