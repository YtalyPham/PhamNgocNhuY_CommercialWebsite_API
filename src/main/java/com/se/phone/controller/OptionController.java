/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.se.phone.controller;

import com.se.phone.entity.Option;
import com.se.phone.exception.OptionException;
import com.se.phone.service.OptionService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author PhamNgocNhuY_18055121
 */
@RestController
public class OptionController {
    private OptionService optionService;
    @Autowired
    public OptionController(OptionService optionService) {
        this.optionService = optionService;
    }
    
    @GetMapping("/option")
    public List<Option> getAllOption(){
        return optionService.getAll();
                
    }
    @GetMapping("/option/{Id}")
    public Option getOption(@PathVariable int Id){
        Option o = optionService.getById(Id);
        return o;
    }
    
    @PostMapping("/option")
    public Option addOption(@RequestBody Option o){
        optionService.save(o);
        return o;
    }

    @PutMapping("/option")
    public Option updateOption(@RequestBody Option o){
            Option option= optionService.getById(o.getId());
            option.setScreenSize(o.getScreenSize());  
            option.setScreenTechnology(o.getScreenTechnology());
            option.setCameraBack(o.getCameraBack());
            option.setCameraFont(o.getCameraFont());
            option.setChipset(o.getChipset());
            option.setRAM(o.getRAM());
            option.setMemory(o.getMemory());
            option.setBattery(o.getBattery());
            option.setSIM(o.getSIM());
            option.setSystem(o.getSystem());
            optionService.save(option);
            return option;    
    }
    
    @DeleteMapping("/option/{Id}")
    public String deteteOption(@PathVariable int Id){
        Option o= optionService.getById(Id);
        if(o==null){
            throw new OptionException(Id);
        }else{
            optionService.deleteById(Id);
            return "Delete sucess OptionId= "+Id;
        }
        
    }
    
}
