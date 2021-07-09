/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.se.phone.controller;

import com.se.phone.entity.Catagory;
import com.se.phone.entity.Option;
import com.se.phone.entity.Phone;
import com.se.phone.entity.Producer;
import com.se.phone.exception.ApiRequestException;
import com.se.phone.service.CatagoryService;
import com.se.phone.service.OptionService;
import com.se.phone.service.PhoneService;
import com.se.phone.service.ProducerService;
import java.lang.reflect.Field;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
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
public class PhoneController {
    private PhoneService phoneService;
    private OptionService optionService;
    private CatagoryService catagoryService;
    private ProducerService producerService;
    @Autowired
    public PhoneController(PhoneService phoneService, OptionService optionService, CatagoryService catagoryService, ProducerService producerService) {
        this.phoneService = phoneService;
        this.optionService = optionService;
        this.catagoryService = catagoryService;
        this.producerService = producerService;
    }
    //SORT
    //http://localhost:8080/Ytalyphone/phone?sortBy=name
    @GetMapping("/phone")
    public List<Phone> getCatagories(
            @RequestParam Optional<Integer> page,
            @RequestParam Optional<String> sortBy){
        //getContent() output list of Catagory or not output go Page<Catagory>
        return phoneService.getAllSort(page,sortBy).getContent();
                
    }

   
    //http://localhost:8080/Ytalyphone/phone/search/Xiaomi
     @GetMapping("/phone/search/{name}")
    public List<Phone> searchByName(@PathVariable String name){
        return phoneService.getAllSearch(name.toLowerCase());
    }
    
    
    @GetMapping("/phone/{Id}")
    public Phone getPhone(@PathVariable int Id){
        Phone p = phoneService.getById(Id);
        return p;
    }
    
    @PostMapping("/phone")
    public Phone addPhone(@RequestBody Phone p){
        List<Phone> list= phoneService.getAll();
        int temp=0;
        for (int i=0;i<list.size();i++) {
            if(list.get(i).getName().equalsIgnoreCase(p.getName())==true){
                 
                    temp++;
                    throw new ApiRequestException("Trùng name");   
                
            }
        }
        if(temp==0){
            phoneService.save(p);
            return p;
        }
        return null; 
    }
 
    @PutMapping("/phone")
    public Phone updatePhone(@RequestBody Phone p){
            Phone phone= phoneService.getById(p.getId());
            phone.setName(p.getName());  
            phone.setPrice(p.getPrice());
            phone.setAmount(p.getAmount());  
            phone.setStatus(p.getStatus());
            phone.setDiscountPer(p.getDiscountPer());  
            phone.setImage(p.getImage());
            ///////////////////////////////find and set by id for option catagory producer
            Option o= optionService.getById(p.getOption().getId());
            Catagory c= catagoryService.getById(p.getCatagory().getId());
            Producer pr= producerService.getById(p.getProducer().getId());
            if(o!=null&&c!=null&&pr!=null){
                phone.setOption(o);  
                phone.setCatagory(c);
                phone.setProducer(pr);  
            }
            phoneService.save(phone);
            return phone;
          
    }
    @PatchMapping("/phone/{id}")
     public Phone patchPhone(@PathVariable int id,@RequestBody Map<Object,Object> p){
            Phone phone= phoneService.getById(id);
            p.forEach((k,v)->{
                Field field=ReflectionUtils.findField(Phone.class, (String) k);
                field.setAccessible(true);
                ReflectionUtils.setField(field, phone, v);
            });
            phoneService.save(phone);
            return phone;
          
    }
    
    @DeleteMapping("/phone/{Id}")
    public String detetePhone(@PathVariable int Id){
        Phone p= phoneService.getById(Id);
        phoneService.deleteById(Id);
        return "Delete sucess PhoneId= "+Id;
    }
 
}
