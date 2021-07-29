/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.se.phone.reposity;

import com.se.phone.entity.Product;
import java.util.List;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

/**
 *
 * @author PhamNgocNhuY_18055121
 */
@Repository
public interface ProductRepository extends JpaRepository<Product, Integer>{
    @Query("SELECT p FROM Product p WHERE LOWER(p.name) LIKE %:name%")
    List<Product> searchByName(@Param("name") String name);
    
    @Query("SELECT p FROM Product p WHERE category_id = :id")
    List<Product> searchByCategory(@Param("id") int id);
    
    @Query("SELECT p FROM Product p WHERE brand_id = :id")
    List<Product> searchByBrand(@Param("id") int id);
    
    @Query("SELECT p FROM Product p WHERE LOWER(CONCAT(p.name, p.brand, p.category)) LIKE %:name%")
    public List<Product> searchAll(@Param("name") String keyword);
}
