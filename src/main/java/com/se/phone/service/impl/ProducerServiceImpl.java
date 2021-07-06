/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.se.phone.service.impl;

import com.se.phone.entity.Catagory;
import com.se.phone.entity.Producer;
import com.se.phone.reposity.ProducerRepository;
import com.se.phone.service.ProducerService;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestParam;

/**
 *
 * @author PhamNgocNhuY_18055121
 */
@Service
public class ProducerServiceImpl implements ProducerService{
    private ProducerRepository producerRepository;
    @Autowired
    public ProducerServiceImpl(ProducerRepository producerRepository) {
        this.producerRepository = producerRepository;
    }
    
    @Override
    public Producer save(Producer c) {
        return producerRepository.save(c);
    }

    @Override
    public void deleteById(int id) {
        producerRepository.deleteById(id);
    }

    @Override
    public Page<Producer> getAllSort(
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
    public Producer getById(int id) {
        Optional<Producer> p= producerRepository.findById(id);
        Producer producer= null;
        if(p.isPresent()){
            producer=p.get();
        }else{
            throw new RuntimeException("Did not find Id"+ id);
        }
        return producer;
    }

    @Override
    public List<Producer> getAll() {
        return producerRepository.findAll();
    }

    @Override
    public List<Producer> getAllSearch(String keyword) {
        if (keyword != null) {
            return producerRepository.search(keyword);
        }
        return producerRepository.findAll();
    }
    
}
