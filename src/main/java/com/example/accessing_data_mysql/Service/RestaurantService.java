package com.example.accessing_data_mysql.Service;

import java.util.List;

import com.example.accessing_data_mysql.DTO.RestaurantDto;
import com.example.accessing_data_mysql.Entity.Restaurant;
import com.example.accessing_data_mysql.Entity.User;
import com.example.accessing_data_mysql.Request.CreateRestaurantRequest;

public interface RestaurantService {
    public Restaurant createRestaurant(CreateRestaurantRequest req, User user) throws Exception;

    public Restaurant updateRestaurant(Long restaurantId, CreateRestaurantRequest reqUpdateRestaurant) throws Exception;

    public void deleteRestaurant(Long restaurantId) throws Exception;

    public List<Restaurant> getAllRestaurants() throws Exception;

    public List<Restaurant> searchRestaurant(String keyword);

    public Restaurant getRestaurantById(Long restaurantId) throws Exception;

    public Restaurant getRestaurantsByUserId(Long userId) throws Exception;

    public RestaurantDto addToFavorites(Long restaurantId, User user) throws Exception;

    public Restaurant updateRestaurantStatus(Long restaurantId) throws Exception;
}
