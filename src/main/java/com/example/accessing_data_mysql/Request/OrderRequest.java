package com.example.accessing_data_mysql.Request;

import com.example.accessing_data_mysql.Entity.Address;

import lombok.Data;

@Data
public class OrderRequest {
    private Long restaurantId;
    private Address deliveryAddress;

}
