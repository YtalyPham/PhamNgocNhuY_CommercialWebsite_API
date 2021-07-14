/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.se.phone.service.impl;

import com.se.phone.entity.Product;
import com.se.phone.entity.Brand;
import com.se.phone.exception.ApiRequestException;
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
    private ProductRepository phoneRepository;
    private CategoryRepository catagoryRepository;
    private BrandRepository producerRepository;
    @Autowired
     public ProductServiceImpl(ProductRepository phoneRepository, CategoryRepository catagoryRepository, BrandRepository producerRepository) {
        this.phoneRepository = phoneRepository;
        this.catagoryRepository = catagoryRepository;
        this.producerRepository = producerRepository;
    }
    
    @Override
    public Product save(Product c) {
        return phoneRepository.save(c);
    }

   
    @Override
    public void deleteById(int id) {
        phoneRepository.deleteById(id);
    }

    @Override
    public Page<Product> getAllSort(
            @RequestParam Optional<Integer> page,
            @RequestParam Optional<String> sortBy){
        return phoneRepository.findAll(
                PageRequest.of(
                    page.orElse(0),
                    25,
                    Sort.Direction.ASC,sortBy.orElse("id")
                )
        );
    }
    @Override
    public List<Product> getAll() {
        return phoneRepository.findAll();
    }

    @Override
    public Product getById(int id) {
        Optional<Product> p= phoneRepository.findById(id);
        Product phone= null;
        if(p.isPresent()){
            phone=p.get();
        }else{
            throw new ApiRequestException("Did not find Id "+ id);
        }
        return phone;
    }

    

    @Override
    public List<Product> getAllSearchByName(String keyword) {
        if (keyword != null) {
            return phoneRepository.search(keyword);
        }
        return phoneRepository.findAll();
    }

    @Override
    public List<Product> getAllSearchByCatagory(int id) {
         
        if (String.valueOf(id) != null) {
            return phoneRepository.searchByCatagory(id);
        }
        return phoneRepository.findAll();
    }

    @Override
    public List<Product> getAllSearchByProducer(int id) {
        if (String.valueOf(id) != null) {
            return phoneRepository.searchByProducer(id);
        }
        return phoneRepository.findAll();
    }
    
    
}
