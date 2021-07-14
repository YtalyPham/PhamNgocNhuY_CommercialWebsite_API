/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.se.phone.service;

import com.se.phone.entity.Category;
import java.util.List;
import java.util.Optional;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.RequestParam;

/**
 *
 * @author PhamNgocNhuY_18055121
 */
public interface CategoryService {
    public Category save(Category c);
    public void deleteById(int id);
    public Page<Category> getAllSort(@RequestParam Optional<Integer> page,@RequestParam Optional<String> sortBy);
    public List<Category> getAll();
    public List<Category> getAllSearch(String keyword);
    public Category getById(int id);
    
}
