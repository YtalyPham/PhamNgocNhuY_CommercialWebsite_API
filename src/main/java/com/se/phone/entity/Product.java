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
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
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
    
    @NotNull
    @Size(min=2, message="Name should have atleast 2 characters")
    @Column(name="name")
    private String name;
    
    @NotNull(message = "Price can not null")
    @Column(name="price")
    private Double price;
    
    @NotNull
    @Min(value = 1, message = "Amount atleast 1 unit")
    @Column(name="amount")
    private int amount;
    
    @NotNull
    @Size(min=2, message="Status should have or not")
    @Column(name="status")
    private String status;
    
    @NotNull(message="DiscountPer should not null")
    @Column(name="discountPer")
    private Double discountPer;
    
    @NotNull(message="Rating should not null")
    @Column(name = "rating")
    private double rating;
    
    @NotNull(message="productDetail should not null")
    @ManyToOne
    @JoinColumn(name = "productDetail_id")
    private ProductDetail productDetail;
    
    @NotNull(message="category should not null")
   @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "category_id")
   private Category category;
   
   @NotNull(message="brand should not null")
   @ManyToOne
   @JoinColumn(name = "brand_id")
   private Brand brand;
   
   @OneToMany(mappedBy = "product", fetch = FetchType.LAZY)
   private List<Image> images;
  
//   @OneToMany(mappedBy="phone")
//   private List<OrderDetail> orderDetails;
   

     

 

    
    

    
    

    
    
    
}
