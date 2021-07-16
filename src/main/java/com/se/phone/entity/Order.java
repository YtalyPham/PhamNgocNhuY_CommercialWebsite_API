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
@Table(name = "orders",indexes = {
            @Index(name = "orders_name_index" , columnList = "id , discountPer")
})
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;
    
    @Column(name = "discountPer")
    private Double discountPer;
    
    @Column(name = "VAT")
    private Double VAT;
    
    @Column(name = "date")
    private Timestamp date;
    
    @ManyToOne
    @JoinColumn(name = "user_fk")
    private User user;
    
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "orderDetail_fk", referencedColumnName = "id")
    private OrderDetail orderDetail;

    @Override
    public String toString() {
        return "Order{" + "id=" + id + ", discountPer=" + discountPer + ", VAT=" + VAT + ", date=" + date + ", user=" + user + ", orderDetail=" + orderDetail + '}';
    }

    
    
    
}
