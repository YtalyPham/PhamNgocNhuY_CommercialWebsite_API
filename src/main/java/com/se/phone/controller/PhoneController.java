/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.se.phone.controller;

import com.se.phone.converter.PhoneConverter;
import com.se.phone.dto.PhoneDTO;
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
import java.util.stream.Collectors;
import org.modelmapper.ModelMapper;
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
public class PhoneController {
    private PhoneService phoneService;
    private OptionService optionService;
    private CatagoryService catagoryService;
    private ProducerService producerService;
    //private ModelMapper modelMapper;
    private PhoneConverter phoneConverter;
    @Autowired
    public PhoneController(PhoneService phoneService, OptionService optionService, CatagoryService catagoryService, ProducerService producerService, PhoneConverter phoneConverter) {
        this.phoneService = phoneService;
        this.optionService = optionService;
        this.catagoryService = catagoryService;
        this.producerService = producerService;
        this.phoneConverter = phoneConverter;
    }
    
    //SORT
    //http://localhost:8080/Ytalyphone/phone?sortBy=name
    @GetMapping("/phone")
     
    public List<PhoneDTO> getPhones(
            @RequestParam Optional<Integer> page,
            @RequestParam Optional<String> sortBy){
        //getContent() output list of Catagory or not output go Page<Catagory>
        List<Phone> list= phoneService.getAllSort(page,sortBy).getContent();
        return list.stream().map(phoneConverter::convertToDTO).collect(Collectors.toList()) ;
                
    }

    //http://localhost:8080/Ytalyphone/phone/search/Xiaomi
     @GetMapping("/phone/searchByName/{name}") 
    public List<PhoneDTO> searchByName(@PathVariable String name){
        List<Phone> list= phoneService.getAllSearchByName(name.toLowerCase());
        return list.stream().map(phoneConverter::convertToDTO).collect(Collectors.toList());
    }
    
     @GetMapping("/phone/searchByProducer/{id}") 
    public List<PhoneDTO> searchByProducer(@PathVariable int id){
        List<Phone> list= phoneService.getAllSearchByProducer(id);
        return list.stream().map(phoneConverter::convertToDTO).collect(Collectors.toList());
    }
    
     @GetMapping("/phone/searchByCatagory/{id}") 
    public List<PhoneDTO> searchByCatagory(@PathVariable int id){
        List<Phone> list= phoneService.getAllSearchByCatagory(id);
        return list.stream().map(phoneConverter::convertToDTO).collect(Collectors.toList());
    }
    
    @GetMapping("/phone/{Id}")
    public PhoneDTO getPhone(@PathVariable int Id){
        Phone phone = phoneService.getById(Id);
        PhoneDTO phoneDTO= phoneConverter.convertToDTO(phone);
       
        return phoneDTO;
    }
    
    @PostMapping("/phone")
    @PreAuthorize("hasRole('PM') or hasRole('ADMIN')")
    public PhoneDTO addPhone(@RequestBody PhoneDTO p){
        List<Phone> list= phoneService.getAll();
        int temp=0;
        for (int i=0;i<list.size();i++) {
            if(list.get(i).getName().equalsIgnoreCase(p.getName())==true){
                    temp++;
                    throw new ApiRequestException("Trùng name");   
                
            }
        }
        if(temp==0){
            Phone phone= phoneConverter.convertToEntity(p);
            phoneService.save(phone);
            return p;
        }
        return null; 
    }
 
    @PutMapping("/phone")
    @PreAuthorize("hasRole('PM') or hasRole('ADMIN')")
    public PhoneDTO updatePhone(@RequestBody PhoneDTO p){
            Phone phone= phoneService.getById(p.getId());
            phone.setName(p.getName());  
            phone.setPrice(p.getPrice());
            phone.setAmount(p.getAmount());  
            phone.setRating(p.getRating());  
            phone.setStatus(p.getStatus());
            phone.setDiscountPer(p.getDiscountPer());  
             
            ///////////////////////////////find and set by id for option catagory producer
            Option o= optionService.getById(p.getOptionId());
            Catagory c= catagoryService.getById(p.getCatagoryId());
            Producer pr= producerService.getById(p.getProducerId());
            if(o!=null&&c!=null&&pr!=null){
                phone.setOption(o);  
                phone.setCatagory(c);
                phone.setProducer(pr);  
            }
            phoneService.save(phone);
            return phoneConverter.convertToDTO(phone);
          
    }
    @PatchMapping("/phone/{id}")
    @PreAuthorize("hasRole('PM') or hasRole('ADMIN')")
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
    @PreAuthorize("hasRole('PM') or hasRole('ADMIN')")
    public String detetePhone(@PathVariable int Id){
        Phone p= phoneService.getById(Id);
        phoneService.deleteById(Id);
        return "Delete sucess PhoneId= "+Id;
    }
 
}
