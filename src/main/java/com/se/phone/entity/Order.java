/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.se.phone.entity;

import java.sql.Timestamp;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;

/**
 *
 * @author PhamNgocNhuY_18055121
 */
@Entity
@Table(name = "orders")
public class Order {
    @Id
    @Column(name = "orderid")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int orderid;
    
    @Column(name = "discountPer")
    private Double discountPer;
    
    @Column(name = "VAT")
    private Double VAT;
    
    @Column(name = "date")
    private Timestamp date;
    
    @ManyToOne
    @JoinColumn(name = "person_fk")
    private Person person;
    
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "orderDetail_fk", referencedColumnName = "id")
    private OrderDetail orderDetail;
    

    public int getOrderid() {
        return orderid;
    }

    public void setOrderid(int orderid) {
        this.orderid = orderid;
    }

    public Double getDiscountPer() {
        return discountPer;
    }

    public void setDiscountPer(Double discountPer) {
        this.discountPer = discountPer;
    }

    public Double getVAT() {
        return VAT;
    }

    public void setVAT(Double VAT) {
        this.VAT = VAT;
    }

    public Timestamp getDate() {
        return date;
    }

    public void setDate(Timestamp date) {
        this.date = date;
    }

    public Person getPerson() {
        return person;
    }

    public void setPerson(Person person) {
        this.person = person;
    }

    public OrderDetail getOrderDetail() {
        return orderDetail;
    }

    public void setOrderDetail(OrderDetail orderDetail) {
        this.orderDetail = orderDetail;
    }

    public Order(int orderid, Double discountPer, Double VAT, Timestamp date, Person person, OrderDetail orderDetail) {
        this.orderid = orderid;
        this.discountPer = discountPer;
        this.VAT = VAT;
        this.date = date;
        this.person = person;
        this.orderDetail = orderDetail;
    }

     

    public Order() {
    }

    @Override
    public String toString() {
        return "Order{" + "orderid=" + orderid + ", discountPer=" + discountPer + ", VAT=" + VAT + ", date=" + date + ", person=" + person + ", orderDetail=" + orderDetail + '}';
    }

     
    
    
   
    
    
}
