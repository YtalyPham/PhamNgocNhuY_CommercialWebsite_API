/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.se.phone.controller;

import com.se.phone.entity.Producer;
import com.se.phone.exception.ApiRequestException;
import com.se.phone.service.ProducerService;
import java.lang.reflect.Field;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.util.ReflectionUtils;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
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
@PreAuthorize("hasRole('PM') or hasRole('ADMIN')")
public class ProducerController {
    private ProducerService producerService;
    @Autowired
    public ProducerController(ProducerService producerService) {
        this.producerService = producerService;
    }
    
    //SORT
    //http://localhost:8080/Ytalyphone/producer?sortBy=name
    @GetMapping("/producer")
    public List<Producer> getCatagories(
            @RequestParam Optional<Integer> page,
            @RequestParam Optional<String> sortBy){
        //getContent() output list of Catagory or not output go Page<Catagory>
        return producerService.getAllSort(page,sortBy).getContent();
                
    }
    //http://localhost:8080/Ytalyphone/producer/search/Xiaomi
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
        List<Producer> list= producerService.getAll();
        int temp=0;
        for (int i=0;i<list.size();i++) {
            if(list.get(i).getName().equalsIgnoreCase(p.getName())==true){
                if(list.get(i).getCountry().equalsIgnoreCase(p.getCountry())==true){
                    temp++;
                    throw new ApiRequestException("Trùng name và Trùng country");   
                }  
            }
        }
        if(temp==0){
            producerService.save(p);
            return p;
        }
        return null; 
    }
    
    @PutMapping("/producer")
    public Producer updateProducer(@RequestBody Producer p){
            Producer producer= producerService.getById(p.getId());
            producer.setName(p.getName());  
            producer.setCountry(p.getCountry());
            producerService.save(producer);
            return producer;
          
    }
    @PatchMapping("/producer/{id}")
     public Producer patchProducer(@PathVariable int id,@RequestBody Map<Object,Object> p){
            Producer producer= producerService.getById(id);
            p.forEach((k,v)->{
                Field field=ReflectionUtils.findField(Producer.class, (String) k);
                field.setAccessible(true);
                ReflectionUtils.setField(field, producer, v);
            });
            producerService.save(producer);
            return producer;
          
    }
    @DeleteMapping("/producer/{Id}")
    public String deteteCatagory(@PathVariable int Id){
        Producer p= producerService.getById(Id);
        producerService.deleteById(Id);
        return "Delete sucess ProducerId= "+Id;
    }
    
    
    
    
    
    
    
    
}
