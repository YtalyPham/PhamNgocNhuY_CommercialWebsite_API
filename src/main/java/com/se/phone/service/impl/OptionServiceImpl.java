/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.se.phone.service.impl;

import com.se.phone.entity.Option;
import com.se.phone.exception.ApiRequestException;
import com.se.phone.reposity.OptionRepository;
import com.se.phone.service.OptionService;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author PhamNgocNhuY_18055121
 */
@Service
public class OptionServiceImpl implements OptionService{
    private OptionRepository optionRepository;
    @Autowired
    public OptionServiceImpl(OptionRepository optionRepository) {
        this.optionRepository = optionRepository;
    }
    
   @Override
    public Option save(Option c) {
        return optionRepository.save(c);
    }

    @Override
    public void deleteById(int id) {
        optionRepository.deleteById(id);
    }

     
    @Override
    public Option getById(int id) {
        Optional<Option> o= optionRepository.findById(id);
        Option option= null;
        if(o.isPresent()){
            option=o.get();
        }else{
            throw new ApiRequestException("Did not find Id "+ id);
        }
        return option;
    }

    @Override
    public List<Option> getAll() {
        return optionRepository.findAll();
    }

     
}
