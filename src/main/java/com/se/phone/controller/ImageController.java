/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.se.phone.controller;

import com.se.phone.entity.Image;
import com.se.phone.payload.respone.MessageResponse;
import com.se.phone.payload.respone.ResponseImage;
import com.se.phone.reposity.ImageRepository;
import com.se.phone.service.impl.ImageServiceImpl;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

/**
 *
 * @author PhamNgocNhuY_18055121
 */
@RestController
public class ImageController {

  @Autowired
  private ImageServiceImpl imageServiceImpl;
  
  @Autowired
  private ImageRepository imageRepository;

  @PostMapping("/image/upload")
  public ResponseEntity<MessageResponse> uploadFile(@RequestParam("file") MultipartFile file) {
    String message = "";
    try {
      imageServiceImpl.store(file);

      message = "Uploaded the file successfully: " + file.getOriginalFilename();
      return ResponseEntity.status(HttpStatus.OK).body(new MessageResponse(message));
    } catch (Exception e) {
      message = "Could not upload the file: " + file.getOriginalFilename() + "!";
      return ResponseEntity.status(HttpStatus.EXPECTATION_FAILED).body(new MessageResponse(message));
    }
  }

  @GetMapping("/image")
  public ResponseEntity<List<ResponseImage>> getListFiles() {
    List<ResponseImage> files = imageServiceImpl.getAllFiles().map(dbFile -> {
      String fileDownloadUri = ServletUriComponentsBuilder
          .fromCurrentContextPath()
          .path("/image/")
          .path(dbFile.getId())
          .toUriString();

      return new ResponseImage(
          dbFile.getName(),
          fileDownloadUri,
          dbFile.getType(),
          dbFile.getData().length);
    }).collect(Collectors.toList());

    return ResponseEntity.status(HttpStatus.OK).body(files);
  }

  @GetMapping("/image/download/{id}")
  public ResponseEntity<byte[]> DownloadFile(@PathVariable String id) {
    Image fileDB = imageServiceImpl.getFile(id);

    return ResponseEntity.ok()
        .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + fileDB.getName() + "\"")
        .body(fileDB.getData());
  }
    @GetMapping("/image/{id}")
  public ResponseImage getFiles(@PathVariable String id) {
     
    String fileDownloadUri;
     List<Image> list= imageRepository.findAll();
        for (Image image : list) {
            if(image.getName().equalsIgnoreCase(id)==true){
                return new ResponseImage(image.getName(),fileDownloadUri = ServletUriComponentsBuilder
                .fromCurrentContextPath()
                .path("/image/")
                .path(image.getId())
                .toUriString(),image.getType(),image.getData().length);
            }
        }
    
    return null;
  }
  
}
