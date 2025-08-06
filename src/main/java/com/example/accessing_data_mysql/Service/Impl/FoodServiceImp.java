package com.example.accessing_data_mysql.Service.Impl;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.accessing_data_mysql.Entity.Category;
import com.example.accessing_data_mysql.Entity.Food;
import com.example.accessing_data_mysql.Entity.Restaurant;
import com.example.accessing_data_mysql.Repo.FoodRepository;
import com.example.accessing_data_mysql.Request.CreateFoodReq;
import com.example.accessing_data_mysql.Service.FoodService;

@Service
public class FoodServiceImp implements FoodService {
    @Autowired
    private FoodRepository foodRepository;

    @Override
    public Food createFood(CreateFoodReq req, Category category, Restaurant restaurant) {
        Food food = new Food();
        food.setFoodCategory(category);
        food.setPrice(req.getPrice());
        food.setRestaurant(restaurant);
        food.setDescription(req.getDescription());
        food.setImage(req.getImage());
        food.setName(req.getName());
        food.setIngredients(req.getIngredients());
        food.setSeasonal(req.isSeasonal());

        Food saved = foodRepository.save(food);
        restaurant.getMenu().add(saved);
        return saved;
    }

    @Override
    public void deleteFood(Long foodId) throws Exception {
        try {
            Food food = foodRepository.findById(foodId)
                    .orElseThrow(() -> new RuntimeException("Food not found"));

            food.getIngredients().clear(); // just unlinks the join table

            foodRepository.save(food); // updates DB
            foodRepository.delete(food); // deletes the food
        } catch (Exception e) {
            throw new UnsupportedOperationException("Unimplemented method 'deleteFood'" + e.getMessage());
        }
    }

    @Override
    public List<Food> getRestaurantFood(Long restuarantId,
            boolean isSeasonal, String foodCategory) {
        // TODO Auto-generated method stub
        List<Food> foods = foodRepository.findByRestaurantId(restuarantId);
        if (isSeasonal) {
            foods = filterBySeason(foods, isSeasonal);
        }
        if (foodCategory != null && !foodCategory.equalsIgnoreCase("")) {
            foods = fiterByCategory(foods, foodCategory);
        }
        return foods;
    }

    private List<Food> fiterByCategory(List<Food> foods, String foodCategory) {
        return foods.stream().filter(food -> {
            if (food.getFoodCategory() != null) {
                return food.getFoodCategory().getName().equals(foodCategory);
            }
            return false;
        }).collect(Collectors.toList());
    }

    private List<Food> filterBySeason(List<Food> foods, boolean isSeasonal) {
        return foods.stream().filter(food -> food.isSeasonal() == isSeasonal).collect(Collectors.toList());
    }

    @Override
    public List<Food> searchFood(String keyword) {
        return foodRepository.searchFood(keyword);
    }

    @Override
    public Food findFoodById(Long foodId) throws Exception {
        Optional<Food> optionalFood = foodRepository.findById(foodId);
        if (optionalFood.isEmpty()) {
            throw new Exception("Food not found");
        }
        return optionalFood.get();
    }

    @Override
    public Food updateFoodAvailability(Long foodId) throws Exception {
        Food food = findFoodById(foodId);
        food.setAvailable(!food.isAvailable());
        return foodRepository.save(food);
    }

    @Override
    public List<Food> getTopFoods() {
        // TODO Auto-generated method stub
        return foodRepository.findTopFoods();
    }

}
