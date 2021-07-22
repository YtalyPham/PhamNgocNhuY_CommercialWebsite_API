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
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
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
@Table(name = "CartItem" ,
       indexes = {
            @Index(name = "cartitem_cartid_index" , columnList = "id , cart_id")
 })
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CartItem {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    
     @Column(name = "unit")
    private String unit;
    
    @Column(name = "amount")
    private int amount;
    
    @Column(name = "totalPrice")
    private Double totalPrice;
    
     @Column(name = "price")
    private Double price;
    
    @ManyToOne
    @JoinColumn(name = "cart_id")
    private Cart cart;

    //many to one to product
    @ManyToOne
    @JoinColumn(name = "product_id")
    private Product product;

    @Override
    public String toString() {
        return "CartItem{" + "id=" + id + ", unit=" + unit + ", amount=" + amount + ", totalPrice=" + totalPrice + ", price=" + price + ", cart=" + cart + ", product=" + product + '}';
    }
}
