package com.example.accessing_data_mysql.Request;

import lombok.Data;

@Data
public class IngredientCategoryRequest {
    private String name;
    private Long restaurantId;

    
}
