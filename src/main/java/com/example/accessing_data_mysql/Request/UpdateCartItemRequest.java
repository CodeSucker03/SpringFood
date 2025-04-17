package com.example.accessing_data_mysql.Request;

import lombok.Data;

@Data
public class UpdateCartItemRequest {
    private Long cartItemid;
    private int quantity;
}
