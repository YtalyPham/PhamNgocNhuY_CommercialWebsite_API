/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.se.phone.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Index;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 *
 * @author PhamNgocNhuY_18055121
 */
@Entity
@Table(name = "brand",
       indexes = {
            @Index(name = "brand_name_index" , columnList = "id , name")
       })
@Getter
@Setter
@NoArgsConstructor
public class Brand {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id")
    private int id;
    
    @NotNull
    @Size(min=2, message="Name should have atleast 2 characters")
    @Column(name="name")
    private String name;
    
    @NotNull
    @Size(min=2, message="Country should have atleast 2 characters")
    @Column(name="country")
    private String country;
    
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "img_id")
    private Image img;
  
    public Brand(int id, String name, String country) {
        this.id = id;
        this.name = name;
        this.country = country;
    }
    
    @Override
    public String toString() {
        return "Producer{" + "id=" + id + ", name=" + name + ", country=" + country + '}';
    }

   
    
    
    
    
}
