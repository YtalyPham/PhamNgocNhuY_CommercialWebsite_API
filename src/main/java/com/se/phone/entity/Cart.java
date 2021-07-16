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
import javax.persistence.MapsId;
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
@Table(name = "Cart" ,
       indexes = {
            @Index(name = "card_name_index" , columnList = "id , createDate")
 })
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Cart {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
     
    @Column(name = "createDate")
    private Timestamp createDate;
    
    @Column(name = "updateDate")
    private Timestamp updateDate;
    
    @OneToOne  
    @JoinColumn(name = "user_id")  
    private User user; 
    
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "cartItem_id", referencedColumnName = "id")
    private CartItem cartItem;

    @Override
    public String toString() {
        return "CartItem{" + "id=" + id + ", createDate=" + createDate + ", updateDate=" + updateDate + '}';
    }
     
}
