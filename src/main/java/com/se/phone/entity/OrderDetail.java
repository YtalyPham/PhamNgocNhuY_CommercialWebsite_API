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
@Table(name = "orderdetail",
       indexes = {
            @Index(name = "orderdetail_name_index" , columnList = "id , unit")
})
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor

public class OrderDetail {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id")
    private int id;
    
    @Column(name = "unit")
    private String unit;
    
    @Column(name = "amount")
    private int amount;
    
    @Column(name = "totalPrice")
    private Double totalPrice;
    
    @OneToOne(mappedBy = "orderDetail")
    private Order order;
    
    @ManyToOne
    @JoinColumn(name = "product_id")
    private Product product; 
    
    @ManyToOne
    @JoinColumn(name = "cartItem_id")
    private CartItem cartItem; 

    @Override
    public String toString() {
        return "OrderDetail{" + "id=" + id + ", unit=" + unit + ", amount=" + amount + ", totalPrice=" + totalPrice + ", order=" + order + ", product=" + product + ", cartItem=" + cartItem + '}';
    }

    
    
}
