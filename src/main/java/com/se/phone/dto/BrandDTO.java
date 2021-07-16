/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.se.phone.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 *
 * @author PhamNgocNhuY_18055121
 */
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class BrandDTO {
    private int id;
    private String name;
    private String country;
    private String imgId;
}
