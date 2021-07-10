/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.se.phone.service.impl;

 
import com.se.phone.entity.Catagory;
import com.se.phone.exception.ApiRequestException;
import com.se.phone.reposity.CatagoryRepository;
import com.se.phone.service.CatagoryService;
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
public class CatagoryServiceImpl implements CatagoryService{
    private CatagoryRepository catagoryRepository;
    //private ModelMapper modelMapper;
    @Autowired
    public CatagoryServiceImpl(CatagoryRepository catagoryRepository) {
        this.catagoryRepository = catagoryRepository;
    }
    @Override
    public Catagory save(Catagory c) {
        return catagoryRepository.save(c);
    }

    

    @Override
    public void deleteById(int id) {
        catagoryRepository.deleteById(id);
    }

    @Override
    public Page<Catagory> getAllSort(
            @RequestParam Optional<Integer> page,
            @RequestParam Optional<String> sortBy){
        return catagoryRepository.findAll(
                PageRequest.of(
                    page.orElse(0),
                    25,
                    Sort.Direction.ASC,sortBy.orElse("id")
                )
        );
    }

    @Override
    public Catagory getById(int id) {
        Optional<Catagory> c= catagoryRepository.findById(id);
        Catagory catagory= null;
        if(c.isPresent()){
            catagory=c.get();
        }else{
            throw new ApiRequestException("Did not find Id "+ id);
        }
        return catagory;
    }

    @Override
    public List<Catagory> getAll() {
        return catagoryRepository.findAll();
    }

    @Override
    public List<Catagory> getAllSearch(String keyword) {
        if (keyword != null) {
            return catagoryRepository.search(keyword);
        }
        return catagoryRepository.findAll();
    }
//    //convert Entity to DTO
//    public CatagoryDTO mapToDTO(Catagory catagory){
//        CatagoryDTO catagoryDTO=modelMapper.map(catagory, CatagoryDTO.class);
//        return catagoryDTO;
//    }
//    //convert DTO to Entity 
//    public Catagory mapToEntity(CatagoryDTO catagoryDTO){
//        Catagory catagory= modelMapper.map(catagoryDTO, Catagory.class);
//        return catagory;
//    }
    
}
