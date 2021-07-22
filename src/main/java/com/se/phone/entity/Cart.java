/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.se.phone.entity;

import java.sql.Timestamp;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Index;
import javax.persistence.JoinColumn;
import javax.persistence.MapsId;
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
@Table(name = "Cart" ,
       indexes = {
            @Index(name = "card_userid_index" , columnList = "id , user_id")
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
    
    @OneToMany(mappedBy = "cart", fetch = FetchType.LAZY)//one to many
    private List<CartItem> cartItems;

    @Override
    public String toString() {
        return "CartItem{" + "id=" + id + ", createDate=" + createDate + ", updateDate=" + updateDate + '}';
    }
     
}
