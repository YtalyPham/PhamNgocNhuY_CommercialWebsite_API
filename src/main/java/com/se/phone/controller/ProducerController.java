/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.se.phone.controller;

import com.se.phone.entity.Catagory;
import com.se.phone.entity.Producer;
import com.se.phone.exception.CatagoryException;
import com.se.phone.exception.ProducerException;
import com.se.phone.service.ProducerService;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author PhamNgocNhuY_18055121
 */
@RestController
public class ProducerController {
    private ProducerService producerService;
    @Autowired
    public ProducerController(ProducerService producerService) {
        this.producerService = producerService;
    }
    
    //SORT
    //http://localhost:8080/produce?sortBy=name
    @GetMapping("/producer")
    public List<Producer> getCatagories(
            @RequestParam Optional<Integer> page,
            @RequestParam Optional<String> sortBy){
        //getContent() output list of Catagory or not output go Page<Catagory>
        return producerService.getAllSort(page,sortBy).getContent();
                
    }
    //http://localhost:8080/producer/search/Xiaomi
     @GetMapping("/producer/search/{name}")
    public List<Producer> searchByName(@PathVariable String name){
        return producerService.getAllSearch(name.toLowerCase());
    }
    
    
    @GetMapping("/producer/{Id}")
    public Producer getProducer(@PathVariable int Id){
        Producer p = producerService.getById(Id);
        return p;
    }
    
    @PostMapping("/producer")
    public Producer addProducer(@RequestBody Producer p){
        producerService.save(p);
        return p;
    }
    
    @PutMapping("/producer")
    public Producer updateProducer(@RequestBody Producer p){
            Producer producer= producerService.getById(p.getId());
            producer.setName(p.getName());  
            producer.setCountry(p.getCountry());
            producerService.save(producer);
            return producer;
          
    }
    
    @DeleteMapping("/producer/{Id}")
    public String deteteCatagory(@PathVariable int Id){
        Producer p= producerService.getById(Id);
        if(p==null){
            throw new ProducerException(Id);
        }else{
            producerService.deleteById(Id);
            return "Delete sucess ProducerId= "+Id;
        }
        
    }
    
    
    
    
    
    
    
    
}
