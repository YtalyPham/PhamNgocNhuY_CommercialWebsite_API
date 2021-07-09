/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.se.phone.reposity;

import com.se.phone.entity.Phone;
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
public interface PhoneRepository extends JpaRepository<Phone, Integer>{
    @Query("SELECT p FROM Phone p WHERE LOWER(p.name) LIKE %:name%")
    List<Phone> search(@Param("name") String name);
    
    @Query("SELECT p FROM Phone p WHERE catagory_fk = :catagory_id")
    List<Phone> searchByCatagory(@Param("catagory_id") int id);
    
    @Query("SELECT p FROM Phone p WHERE producer_fk = :producer_id")
    List<Phone> searchByProducer(@Param("producer_id") int id);
}
