package com.example.accessing_data_mysql.Request;

import lombok.Data;

@Data
public class LoginRequest {
    private String email;
    private String password;
}
