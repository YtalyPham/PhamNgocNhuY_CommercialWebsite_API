/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.se.phone.service;

import com.se.phone.entity.Option;
import java.util.List;

/**
 *
 * @author PhamNgocNhuY_18055121
 */
public interface OptionService {
    public Option save(Option c);
    public void deleteById(int id);
    public List<Option> getAll();  
    public Option getById(int id);
}
