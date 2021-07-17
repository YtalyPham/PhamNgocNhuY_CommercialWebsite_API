/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.se.phone.service;

import com.se.phone.entity.Product;
import com.se.phone.entity.Brand;
import java.util.List;
import java.util.Optional;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.RequestParam;

/**
 *
 * @author PhamNgocNhuY_18055121
 */
public interface ProductService {
    public Product save(Product p);
    public void deleteById(int id);
    public Page<Product> getAllSort(@RequestParam Optional<Integer> page,@RequestParam Optional<String> sortBy);
    public List<Product> getAll();
    public List<Product> getAllSearchByName(String keyword);
    public List<Product> getAllSearchByCategory(int id);
    public List<Product> getAllSearchByBrand(int id);
    public Product getById(int id);
}
