/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.se.phone.entity;

import java.util.ArrayList;
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
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 *
 * @author PhamNgocNhuY_18055121
 */
@Entity
@Table(name = "product")
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
    
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "option_fk", referencedColumnName = "id")
    private ProductDetail productDetail;
    
   @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "category_fk")
   private Category category;
   
   @ManyToOne
   @JoinColumn(name = "brand_fk")
   private Brand brand;
   
  @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "img_fk")
    private Image img;
  
//   @OneToMany(mappedBy="phone")
//   private List<OrderDetail> orderDetails;
   

     

 

    
    

    
    

    
    
    
}
