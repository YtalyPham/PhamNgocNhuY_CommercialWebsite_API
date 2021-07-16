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
            @Index(name = "cartitem_name_index" , columnList = "id , unit")
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
    
    @OneToOne(mappedBy = "cartItem")
    private Cart card;

    @Override
    public String toString() {
        return "CartItem{" + "id=" + id + ", unit=" + unit + ", amount=" + amount + ", totalPrice=" + totalPrice + ", price=" + price + ", card=" + card + '}';
    }

    
    
    
    
}
