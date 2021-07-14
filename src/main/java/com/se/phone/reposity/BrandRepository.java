/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.se.phone.reposity;

import com.se.phone.entity.Brand;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

/**
 *
 * @author PhamNgocNhuY_18055121
 */
@Repository
public interface BrandRepository extends JpaRepository<Brand, Integer>{
    @Query("SELECT b FROM Brand b WHERE LOWER(b.name) LIKE %:name%")
    List<Brand> search(@Param("name") String name);
    
   
}
