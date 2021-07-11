/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.se.phone.reposity;

import com.se.phone.entity.User;
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
public interface UserRepository extends JpaRepository<User, Long> {
    
    Optional<User> findByUsername(String username);
    
    Boolean existsByUsername(String username);
    
    Boolean existsByEmail(String email);
    
//    @Query("select u,r from users u join user_roles ur ON u.id =ur.user_id join roles r on r.id =ur.role_id WHERE LOWER(u.username) LIKE %:name%")
//    List<User> getByUserName(@Param("name") String name);
     
}
