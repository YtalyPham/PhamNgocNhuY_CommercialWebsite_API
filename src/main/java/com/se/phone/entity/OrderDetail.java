/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.se.phone.entity;

import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

/**
 *
 * @author PhamNgocNhuY_18055121
 */
@Entity
@Table(name = "orderDetail")
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
    @JoinColumn(name = "phone_fk")
    private Phone phone; 

    public OrderDetail() {
    }

    public OrderDetail(int id, String unit, int amount, Double totalPrice, Order order, Phone phone) {
        this.id = id;
        this.unit = unit;
        this.amount = amount;
        this.totalPrice = totalPrice;
        this.order = order;
        this.phone = phone;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUnit() {
        return unit;
    }

    public void setUnit(String unit) {
        this.unit = unit;
    }

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

    public Double getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(Double totalPrice) {
        this.totalPrice = totalPrice;
    }

    public Order getOrder() {
        return order;
    }

    public void setOrder(Order order) {
        this.order = order;
    }

    public Phone getPhone() {
        return phone;
    }

    public void setPhone(Phone phone) {
        this.phone = phone;
    }

    @Override
    public String toString() {
        return "OrderDetail{" + "id=" + id + ", unit=" + unit + ", amount=" + amount + ", totalPrice=" + totalPrice + ", order=" + order + ", phone=" + phone + '}';
    }
    
    
}
