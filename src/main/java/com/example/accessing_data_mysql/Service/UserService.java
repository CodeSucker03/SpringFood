package com.example.accessing_data_mysql.Service;

import java.util.List;

import com.example.accessing_data_mysql.Entity.User;

public interface UserService {
    public User findUserByJwtToken(String jwtToken) throws Exception;

    public List<User> findAllUser() throws Exception;

    public User findUserByEmail(String email) throws Exception;
}
