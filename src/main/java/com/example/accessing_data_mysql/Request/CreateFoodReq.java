package com.example.accessing_data_mysql.Request;

import java.math.BigDecimal;
import java.util.List;

import com.example.accessing_data_mysql.Entity.Category;
import com.example.accessing_data_mysql.Entity.IngredientsItem;

import lombok.Data;

@Data
public class CreateFoodReq {
   private String name;
   private String description;
   private BigDecimal price;

   private Category category;
   private String image;

   private Long restaurantId;
   private boolean seasonal;

   private List<IngredientsItem> ingredients;

}
