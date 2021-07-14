/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.se.phone.service.impl;

 
import com.se.phone.entity.Category;
import com.se.phone.exception.ApiRequestException;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestParam;
import com.se.phone.reposity.CategoryRepository;
import com.se.phone.service.CategoryService;

/**
 *
 * @author PhamNgocNhuY_18055121
 */
@Service
public class CategoryServiceImpl implements CategoryService{
    private CategoryRepository catagoryRepository;
    //private ModelMapper modelMapper;
    @Autowired
    public CategoryServiceImpl(CategoryRepository catagoryRepository) {
        this.catagoryRepository = catagoryRepository;
    }
    @Override
    public Category save(Category c) {
        return catagoryRepository.save(c);
    }

    

    @Override
    public void deleteById(int id) {
        catagoryRepository.deleteById(id);
    }

    @Override
    public Page<Category> getAllSort(
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
    public Category getById(int id) {
        Optional<Category> c= catagoryRepository.findById(id);
        Category catagory= null;
        if(c.isPresent()){
            catagory=c.get();
        }else{
            throw new ApiRequestException("Did not find Id "+ id);
        }
        return catagory;
    }

    @Override
    public List<Category> getAll() {
        return catagoryRepository.findAll();
    }

    @Override
    public List<Category> getAllSearch(String keyword) {
        if (keyword != null) {
            return catagoryRepository.search(keyword);
        }
        return catagoryRepository.findAll();
    }
//    //convert Entity to DTO
//    public CatagoryDTO mapToDTO(Category catagory){
//        CatagoryDTO catagoryDTO=modelMapper.map(catagory, CatagoryDTO.class);
//        return catagoryDTO;
//    }
//    //convert DTO to Entity 
//    public Category mapToEntity(CatagoryDTO catagoryDTO){
//        Category catagory= modelMapper.map(catagoryDTO, Category.class);
//        return catagory;
//    }
    
}
