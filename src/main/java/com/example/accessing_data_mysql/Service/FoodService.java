package com.example.accessing_data_mysql.Service;

import java.util.List;

import com.example.accessing_data_mysql.Entity.Category;
import com.example.accessing_data_mysql.Entity.Food;
import com.example.accessing_data_mysql.Entity.Restaurant;
import com.example.accessing_data_mysql.Request.CreateFoodReq;

public interface FoodService {
    public Food createFood(CreateFoodReq req, Category category,Restaurant restaurant );
    void deleteFood(Long foodId) throws Exception;

    public List<Food> getRestaurantFood( Long restuarantId, boolean isSeasonal,
     String foodCategory );
    public List<Food> searchFood(String keyword);
    public Food findFoodById (Long foodId) throws Exception;
    public Food updateFoodAvailability(Long foodId) throws Exception;
}
