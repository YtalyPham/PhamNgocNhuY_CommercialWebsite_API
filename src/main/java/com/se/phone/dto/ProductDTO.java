/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.se.phone.dto;

import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 *
 * @author PhamNgocNhuY_18055121
 */
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ProductDTO {
    private int id;
    private String name;
    private Double price;
    private int amount;
    private String status;
    private Double discountPer;
    private double rating;
    private int productDetailId;
    private int categoryId;
    private int brandId;
    private String imagesId;

    @Override
    public String toString() {
        return "ProductDTO{" + "id=" + id + ", name=" + name + ", price=" + price + ", amount=" + amount + ", status=" + status + ", discountPer=" + discountPer + ", rating=" + rating + ", productDetailId=" + productDetailId + ", categoryId=" + categoryId + ", brandId=" + brandId + ", imagesId=" + imagesId + '}';
    }

     

  

    

    
}
