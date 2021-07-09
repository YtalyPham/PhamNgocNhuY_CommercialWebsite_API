/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.se.phone.service.impl;

import com.se.phone.entity.Phone;
import com.se.phone.entity.Producer;
import com.se.phone.exception.ApiRequestException;
import com.se.phone.reposity.PhoneRepository;
import com.se.phone.reposity.ProducerRepository;
import com.se.phone.service.PhoneService;
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
public class PhoneServiceImpl implements PhoneService{
    private PhoneRepository phoneRepository;
    @Autowired
    public PhoneServiceImpl(PhoneRepository phoneRepository) {
        this.phoneRepository = phoneRepository;
    }
    
    @Override
    public Phone save(Phone c) {
        return phoneRepository.save(c);
    }
    @Override
    public void deleteById(int id) {
        phoneRepository.deleteById(id);
    }

    @Override
    public Page<Phone> getAllSort(
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
    public List<Phone> getAll() {
        return phoneRepository.findAll();
    }

    @Override
    public Phone getById(int id) {
        Optional<Phone> p= phoneRepository.findById(id);
        Phone phone= null;
        if(p.isPresent()){
            phone=p.get();
        }else{
            throw new ApiRequestException("Did not find Id "+ id);
        }
        return phone;
    }

    

    @Override
    public List<Phone> getAllSearch(String keyword) {
        if (keyword != null) {
            return phoneRepository.search(keyword);
        }
        return phoneRepository.findAll();
    }
}
