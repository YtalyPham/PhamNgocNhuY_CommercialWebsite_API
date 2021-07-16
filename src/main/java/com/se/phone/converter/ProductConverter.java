/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.se.phone.converter;

import com.se.phone.dto.ProductDTO;
import com.se.phone.entity.Category;
import com.se.phone.entity.Image;
import com.se.phone.entity.ProductDetail;
import com.se.phone.entity.Product;
import com.se.phone.entity.Brand;
import com.se.phone.exception.ApiRequestException;
import com.se.phone.reposity.ImageRepository;
import java.util.ArrayList;
import java.util.List;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import com.se.phone.reposity.CategoryRepository;
import com.se.phone.reposity.ProductDetailRepository;
import com.se.phone.reposity.BrandRepository;

/**
 *
 * @author PhamNgocNhuY_18055121
 */
@Component
public class ProductConverter {
    private ModelMapper modelMapper;
    private CategoryRepository catagoryRepository;
    private BrandRepository producerRepository;
    private ProductDetailRepository optionRepository;
    private ImageRepository imageRepository;
    
  @Autowired
    public ProductConverter(ModelMapper modelMapper, CategoryRepository catagoryRepository, BrandRepository producerRepository, ProductDetailRepository optionRepository, ImageRepository imageRepository) {
        this.modelMapper = modelMapper;
        this.catagoryRepository = catagoryRepository;
        this.producerRepository = producerRepository;
        this.optionRepository = optionRepository;
        this.imageRepository = imageRepository;
    }

    public ProductConverter() {
    }

    //converToEntity
    public Product convertToEntity(ProductDTO dto){
        try {
            Product phone= modelMapper.map(dto,Product.class);
            Category category= catagoryRepository.getById(dto.getCategoryId());
            Brand brand= producerRepository.getById(dto.getBrandId());
            ProductDetail productDetail= optionRepository.getById(dto.getProductDetailId());
            //Image image= imageRepository.findById(dto.getImagesId()).get();
            List<Image> list= new ArrayList<>();
            List<String> temp= dto.getImagesId();
            if(temp!=null){
               for (String a : temp) {
                list.add(imageRepository.findById(a).get());
                }
               phone.setImages(list);
            }
            else{
                phone.setImages(list);
            }
            
            phone.setCategory(category);
            phone.setBrand(brand);
            phone.setProductDetail(productDetail);
            
            return phone;
            
        } catch (Exception e) {
            throw new ApiRequestException("Can't convert to Entity",e);
        }
    }
    
    public ProductDTO convertToDTO(Product phone){
        try {
            ProductDTO phoneDTO=modelMapper.map(phone,ProductDTO.class);
            //String temp= phone.getImg().getId();
            List<Image> list= phone.getImages();
            List<String> temp= new ArrayList<>();
            for (Image a : list) {
                temp.add(a.getId());
            }
            phoneDTO.setCategoryId(phone.getCategory().getId());
            phoneDTO.setBrandId(phone.getBrand().getId());
            phoneDTO.setProductDetailId(phone.getProductDetail().getId());
            
            phoneDTO.setImagesId(temp);
            return phoneDTO;
            
       } catch (Exception e) {
           throw new ApiRequestException("Can't convert to DTO",e);
        }
    }
}
