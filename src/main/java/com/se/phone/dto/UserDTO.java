/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.se.phone.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.se.phone.entity.Role;
import java.util.HashSet;
import java.util.Set;

/**
 *
 * @author PhamNgocNhuY_18055121
 */
public class UserDTO {
	private Long id;
	private String username;
	private String email;
        @JsonIgnore
	private String password;
	private Set<Role> roles = new HashSet<>();

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Set<Role> getRoles() {
        return roles;
    }

    public void setRoles(Set<Role> roles) {
        this.roles = roles;
    }

    public UserDTO() {
    }

    public UserDTO(Long id, String username, String email, String password) {
        this.id = id;
        this.username = username;
        this.email = email;
        this.password = password;
    }

    @Override
    public String toString() {
        return "UserDTO{" + "id=" + id + ", username=" + username + ", email=" + email + ", roles=" + roles + '}';
    }
        
}
