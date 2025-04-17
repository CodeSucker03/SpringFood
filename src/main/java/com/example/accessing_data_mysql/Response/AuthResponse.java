package com.example.accessing_data_mysql.Response;

import com.example.accessing_data_mysql.Entity.User_Role;

import lombok.Data;

@Data
public class AuthResponse {
    private String jwt;
    private String message;

    private User_Role role;
   
}
