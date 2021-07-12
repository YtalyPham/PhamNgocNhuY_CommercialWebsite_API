/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.se.phone.controller;

import com.se.phone.entity.Catagory;
import com.se.phone.exception.ApiRequestException;
import com.se.phone.service.CatagoryService;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
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
public class CatagoryController {
    @Autowired
    private CatagoryService catagoryService;

    public CatagoryController(CatagoryService catagoryService) {
        this.catagoryService = catagoryService;
    }

    //SORT
    //http://localhost:8080/Ytalyphone/catagory?sortBy=name
    @GetMapping("/catagory")
    public List<Catagory> getCatagories(
            @RequestParam Optional<Integer> page,
            @RequestParam Optional<String> sortBy){
        //getContent() output list of Catagory or not output go Page<Catagory>
        return catagoryService.getAllSort(page,sortBy).getContent();
                
    }

    //http://localhost:8080/Ytalyphone/catagory/search/Tablet
     @GetMapping("/catagory/search/{name}")
    public List<Catagory> searchByName(@PathVariable String name){
        return catagoryService.getAllSearch(name.toLowerCase());
    }
    
    
    @GetMapping("/catagory/{Id}")
    public Catagory getCatagory(@PathVariable int Id){
        Catagory c = catagoryService.getById(Id);
        return c;
    }
    
    @PostMapping("/catagory")
    public Catagory addCatagory(@RequestBody Catagory c){
        int temp=0;
        List<Catagory> l= catagoryService.getAll();
        for (int i=0;i<l.size();i++) {
            if (l.get(i).getName().equalsIgnoreCase(c.getName())==true) {
                temp++;
               throw new ApiRequestException("Thêm trùng name");
            }  
        }
        if(temp==0){
            catagoryService.save(c);
            return c;
        }
        return null;
    }
    
    @PutMapping("/catagory")
    public Catagory updateCatagory(@RequestBody Catagory c){
            Catagory catagory= catagoryService.getById(c.getId());
            catagory.setName(c.getName());    
            catagoryService.save(catagory);
            return catagory;
          
    }
    
    
    @DeleteMapping("/catagory/{Id}")
    public String deteteCatagory(@PathVariable int Id){
        Catagory c= catagoryService.getById(Id);
        catagoryService.deleteById(Id);
        return "Delete sucess CatagoryId= "+Id;    
    }
    
}
