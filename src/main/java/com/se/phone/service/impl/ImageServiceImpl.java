/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.se.phone.service.impl;

import com.se.phone.entity.Image;
import com.se.phone.reposity.ImageRepository;
import java.io.IOException;
import java.util.stream.Stream;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

/**
 *
 * @author PhamNgocNhuY_18055121
 */
@Service
public class ImageServiceImpl {

  @Autowired
  private ImageRepository imageRepository;

  public Image store(MultipartFile file) throws IOException {
    String fileName = StringUtils.cleanPath(file.getOriginalFilename());
    Image image = new Image(fileName, file.getContentType(), file.getBytes());

    return imageRepository.save(image);
  }

  public Image getFile(String id) {
   return imageRepository.findById(id).get();
  }
  
  public Stream<Image> getAllFiles() {
    return imageRepository.findAll().stream();
  }
}
