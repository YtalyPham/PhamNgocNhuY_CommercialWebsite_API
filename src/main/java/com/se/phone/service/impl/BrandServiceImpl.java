/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.se.phone.service.impl;


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
import com.se.phone.reposity.BrandRepository;
import com.se.phone.service.BrandService;

/**
 *
 * @author PhamNgocNhuY_18055121
 */
@Service
public class BrandServiceImpl implements BrandService{
    private BrandRepository producerRepository;
    @Autowired
    public BrandServiceImpl(BrandRepository producerRepository) {
        this.producerRepository = producerRepository;
    }
    
    @Override
    public Brand save(Brand c) {
        return producerRepository.save(c);
    }

    @Override
    public void deleteById(int id) {
        producerRepository.deleteById(id);
    }

    @Override
    public Page<Brand> getAllSort(
            @RequestParam Optional<Integer> page,
            @RequestParam Optional<String> sortBy){
        return producerRepository.findAll(
                PageRequest.of(
                    page.orElse(0),
                    25,
                    Sort.Direction.ASC,sortBy.orElse("id")
                )
        );
    }

    @Override
    public Brand getById(int id) {
        Optional<Brand> p= producerRepository.findById(id);
        Brand producer= null;
        if(p.isPresent()){
            producer=p.get();
        }else{
            throw new ApiRequestException("Did not find Id "+ id);
        }
        return producer;
    }

    @Override
    public List<Brand> getAll() {
        return producerRepository.findAll();
    }

    @Override
    public List<Brand> getAllSearch(String keyword) {
        if (keyword != null) {
            return producerRepository.search(keyword);
        }
        return producerRepository.findAll();
    }
    
}
