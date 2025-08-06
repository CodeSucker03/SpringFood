package com.example.accessing_data_mysql.Controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.accessing_data_mysql.Entity.User;
import com.example.accessing_data_mysql.Request.AddressRequest;
import com.example.accessing_data_mysql.Service.UserService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;



@RestController
@RequestMapping("/api/user")
public class UserController {

    @Autowired
    private UserService userService;

    
    @GetMapping("/all")
    public ResponseEntity<List<User>> findAllUser(@RequestHeader("Authorization") String jwtToken) throws Exception {
        List<User> user = userService.findAllUser();
        return new ResponseEntity<>(user, HttpStatus.OK);
    }

    @GetMapping("/profile")
    public ResponseEntity<User> findUserByJwtToken(@RequestHeader("Authorization") String jwtToken) throws Exception {
        User user = userService.findUserByJwtToken(jwtToken);
        return new ResponseEntity<>(user, HttpStatus.OK);
    }
    
    @PutMapping("/address")
    public ResponseEntity<User> updateUserAddress(@RequestBody AddressRequest req,
        @RequestHeader("Authorization") String jwtToken) throws Exception {
        User user = userService.findUserByJwtToken(jwtToken);
        User userUpdated = userService.updateUser(user,req);
        return new ResponseEntity<>(userUpdated, HttpStatus.OK);
    }
    
}
