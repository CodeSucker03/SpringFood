package com.example.accessing_data_mysql.Service.Impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.accessing_data_mysql.Config.JwtProvider;
import com.example.accessing_data_mysql.Entity.User;
import com.example.accessing_data_mysql.Repo.UserRepository;
import com.example.accessing_data_mysql.Service.UserService;

@Service
public class UserServiceImp implements UserService {
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private JwtProvider jwtProvider;

    @Override
    public User findUserByJwtToken(String jwtToken) throws Exception {
        // TODO Auto-generated method stub
        String email = jwtProvider.getEmailFromJwtToken(jwtToken);
        User user = userRepository.findByEmail(email);
        if (user == null) {
            throw new Exception("User not found");
        }
        return user;
    }

    @Override
    public User findUserByEmail(String email) throws Exception {
        // TODO Auto-generated method stub
        User user = userRepository.findByEmail(email);
        if (user == null) {
            throw new Exception("User not found");
        }
        return user;
    }

    @Override
    public List<User> findAllUser() throws Exception {
        // TODO Auto-generated method stub
        return userRepository.findAll();
    }
    
}
