/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.se.phone.dto;

import java.util.List;
import javax.persistence.Column;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
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
    
    @NotNull
    @Size(min=2, message="Name should have atleast 2 characters")
    private String name;
    
    @NotNull(message = "Price can not null")
    private Double price;
    
    @NotNull
    @Min(value = 1, message = "Amount atleast 1 unit")
    private int amount;
    
    @NotNull
    @Size(min=2, message="Status should have or not")
    private String status;
    
    @NotNull(message="DiscountPer should not null")
    @Column(name="discountPer")
    private Double discountPer;
    
    @NotNull(message="Rating should not null")
    @Column(name = "rating")
    private double rating;
    
     @NotNull(message="productDetail should not null")
    private int productDetailId;
     
     @NotNull(message="category should not null")
    private int categoryId;
     
     @NotNull(message="brand should not null")
    private int brandId;
     
    private List<String> imagesId;

    @Override
    public String toString() {
        return "ProductDTO{" + "id=" + id + ", name=" + name + ", price=" + price + ", amount=" + amount + ", status=" + status + ", discountPer=" + discountPer + ", rating=" + rating + ", productDetailId=" + productDetailId + ", categoryId=" + categoryId + ", brandId=" + brandId + ", imagesId=" + imagesId + '}';
    }

     

  

    

    
}
