/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.se.phone.service.impl;

import com.se.phone.entity.Product;
import com.se.phone.entity.Brand;
import com.se.phone.exception.ApiRequestException;
import com.se.phone.exception.DataNotFoundException;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestParam;
import com.se.phone.reposity.CategoryRepository;
import com.se.phone.reposity.ProductRepository;
import com.se.phone.service.ProductService;
import com.se.phone.reposity.BrandRepository;

/**
 *
 * @author PhamNgocNhuY_18055121
 */
@Service
public class ProductServiceImpl implements ProductService{
    private ProductRepository productRepository;
    private CategoryRepository categoryRepository;
    private BrandRepository brandRepository;
    @Autowired
     public ProductServiceImpl(ProductRepository productRepository, CategoryRepository categoryRepository, BrandRepository brandRepository) {
        this.productRepository = productRepository;
        this.categoryRepository = categoryRepository;
        this.brandRepository = brandRepository;
    }
    
    @Override
    public Product save(Product c) {
        return productRepository.save(c);
    }

   
    @Override
    public void deleteById(int id) {
        productRepository.deleteById(id);
    }

    @Override
    public Page<Product> getAllSort(
            @RequestParam Optional<Integer> page,
            @RequestParam Optional<String> sortBy){
        return productRepository.findAll(
                PageRequest.of(
                    page.orElse(0),
                    20,
                    Sort.Direction.ASC,sortBy.orElse("id")
                )
        );
    }
    @Override
    public List<Product> getAll() {
        return productRepository.findAll();
    }

    @Override
    public Product getById(int id) {
        Optional<Product> p= productRepository.findById(id);
        Product phone= null;
        if(p.isPresent()){
            phone=p.get();
        }else{
           // throw new ApiRequestException("Did not find Id "+ id);
        }
        return phone;
    }

    

    @Override
    public List<Product> getAllSearchByName(String keyword) {
        if (keyword != null) {
            return productRepository.searchByName(keyword);
        }
        return productRepository.findAll();
    }

    @Override
    public List<Product> getAllSearchByCategory(int id) {
         
        if (String.valueOf(id) != null) {
            return productRepository.searchByCategory(id);
        }
        return productRepository.findAll();
    }

    @Override
    public List<Product> getAllSearchByBrand(int id) {
        if (String.valueOf(id) != null) {
            return productRepository.searchByBrand(id);
        }
        return productRepository.findAll();
    }

    @Override
    public Optional<Product> getAllSearch(String keyword) {
        return productRepository.searchAll(keyword); 
    }
    
    
}
