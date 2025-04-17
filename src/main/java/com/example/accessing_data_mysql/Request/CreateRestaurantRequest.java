package com.example.accessing_data_mysql.Request;

import java.util.List;

import com.example.accessing_data_mysql.Entity.Address;
import com.example.accessing_data_mysql.Entity.ContactInfo;

import lombok.Data;

@Data
public class CreateRestaurantRequest {
    private Long id;
    private String name;
    private String description;
    private String cuisineType;

    private Address address;
    
    private ContactInfo contact;

    private String openingHours;

    private List<String> images;


}
