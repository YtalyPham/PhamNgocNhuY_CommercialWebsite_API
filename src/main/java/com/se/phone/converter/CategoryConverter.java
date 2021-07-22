/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.se.phone.converter;

import com.se.phone.constants.ErrorCode;
import com.se.phone.dto.CategoryDTO;
import com.se.phone.dto.ProductDTO;
import com.se.phone.entity.Category;
import com.se.phone.entity.Product;
import com.se.phone.exception.ApiRequestException;
import com.se.phone.exception.ConvertEntityDTOException;
import com.se.phone.reposity.CategoryRepository;
import com.se.phone.reposity.ImageRepository;
import com.se.phone.reposity.ProductRepository;
import java.util.ArrayList;
import java.util.List;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 *
 * @author PhamNgocNhuY_18055121
 */
@Component
public class CategoryConverter {
    private ModelMapper modelMapper;
    private CategoryRepository catagoryRepository;
    private ProductRepository productRepository;
    private ImageRepository imageRepository;
    @Autowired
     public CategoryConverter(ModelMapper modelMapper, CategoryRepository catagoryRepository, ProductRepository productRepository, ImageRepository imageRepository) {
        this.modelMapper = modelMapper;
        this.catagoryRepository = catagoryRepository;
        this.productRepository = productRepository;
        this.imageRepository = imageRepository;
    }
    public Category convertToEntity(CategoryDTO dto){
        try {
            Category category= modelMapper.map(dto,Category.class);
           // category.setImg(imageRepository.findById(dto.getImgId()).get());
             List<Product> list= new ArrayList<>();
            List<String> temp= dto.getProductId();
            if(temp!=null){
               for (String a : temp) {
                list.add(productRepository.getById(Integer.valueOf(a)));
                }
               category.setProducts(list);
            }
            else{
                 category.setProducts(list);
            }
            return category;
            
        } catch (Exception e) {
             throw new ApiRequestException(ErrorCode.ERR_CONVERTENTITY_FAIL);
        }
        
    }
    public CategoryDTO convertToDTO(Category category){
        try {
            CategoryDTO categoryDTO=modelMapper.map(category,CategoryDTO.class);
            //String temp= phone.getImg().getId();
            List<Product> list= category.getProducts();
            List<String> temp= new ArrayList<>();
            for (Product a : list) {
                temp.add(String.valueOf(a.getId()));
            }
            categoryDTO.setProductId(temp);
          //  categoryDTO.setImgId(category.getImg().getId());
            return categoryDTO;
        } catch (Exception e) {
           throw new ApiRequestException(ErrorCode.ERR_CONVERTDTO_FAIL);
        }
        
        
        
    }

   
    
    
}
