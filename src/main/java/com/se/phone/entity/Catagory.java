/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.se.phone.entity;

import java.util.List;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

/**
 *
 * @author PhamNgocNhuY_18055121
 */
@Entity
@Table(name = "catagory")
public class Catagory {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id")
    private int id;
    
    @Column(name="name")
    private String name;

    @OneToMany(mappedBy="catagory")
    private List<Phone> phones;
    
    public Catagory() {
    }

    public Catagory(int id, String name, List<Phone> phone) {
        this.id = id;
        this.name = name;
        this.phones = phone;
    }

    

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "Catagory{" + "id=" + id + ", name=" + name + ", phone=" + phones + '}';
    }

    
    
    
}
