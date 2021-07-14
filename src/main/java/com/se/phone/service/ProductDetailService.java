/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.se.phone.service;

import com.se.phone.entity.ProductDetail;
import java.util.List;

/**
 *
 * @author PhamNgocNhuY_18055121
 */
public interface ProductDetailService {
    public ProductDetail save(ProductDetail c);
    public void deleteById(int id);
    public List<ProductDetail> getAll();  
    public ProductDetail getById(int id);
}
