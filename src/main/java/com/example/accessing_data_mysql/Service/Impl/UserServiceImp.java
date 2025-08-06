package com.example.accessing_data_mysql.Service.Impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.accessing_data_mysql.Config.JwtProvider;
import com.example.accessing_data_mysql.Entity.Address;
import com.example.accessing_data_mysql.Entity.User;
import com.example.accessing_data_mysql.Repo.AddressRepository;
import com.example.accessing_data_mysql.Repo.UserRepository;
import com.example.accessing_data_mysql.Request.AddressRequest;
import com.example.accessing_data_mysql.Service.UserService;

@Service
public class UserServiceImp implements UserService {
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private AddressRepository addressRepository;

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

    @Override
    public User updateUser(User user, AddressRequest reqData) throws Exception {

        Address address = addressRepository.findByUserId(user.getId());
        if (address == null) {
            Address newAddress = new Address();
            newAddress.setCity(reqData.getCity());
            newAddress.setStreet(reqData.getStreet());
            newAddress.setUser(user);
            addressRepository.save(newAddress);
            return userRepository.findById(user.getId()).orElseThrow();

        } else {
            if (reqData.getCity() != null) {
                address.setCity(reqData.getCity());
            }
            if (reqData.getStreet() != null) {
                address.setStreet(reqData.getStreet());
            }
        }
        addressRepository.save(address);
        return userRepository.findById(user.getId()).orElseThrow();
    }

}
