package com.example.accessing_data_mysql.Service.Impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.accessing_data_mysql.Entity.Category;
import com.example.accessing_data_mysql.Entity.Restaurant;
import com.example.accessing_data_mysql.Repo.CategoryRepository;
import com.example.accessing_data_mysql.Service.CategoryService;
import com.example.accessing_data_mysql.Service.RestaurantService;

@Service
public class CategoryServiceImp implements CategoryService {
    @Autowired
    private CategoryRepository categoryRepository;

    @Autowired
    private RestaurantService restaurantService;

    @Override
    public Category createCategory(Category name, Long userId) throws Exception {
        Restaurant restaurant = restaurantService.getRestaurantsByUserId(userId);

        // Check if the category already exists
        Category category = categoryRepository.findByName(name.getName());
    
        if (category == null) {
            // If not exists, create a new one
            category = new Category();
            category.setName(name.getName());
        }
    
        // Add restaurant if not already associated
        if (!category.getRestaurants().contains(restaurant)) {
            category.getRestaurants().add(restaurant);
        }
    
        return categoryRepository.save(category);
    }

    // 
    @Override
    public List<Category> findCategoryByRestaurantId(Long id) throws Exception {
        // Restaurant restaurant = restaurantService.findByRestaurantById(id);
     return categoryRepository.findByRestaurantsId(id);
    }

    @Override
    public Category findCategoryById(Long id) throws Exception {
        Optional<Category> category = categoryRepository.findById(id);
        if (category.isEmpty()) {
            throw new Exception("Category not found");
        }
        return category.get();
    }

    @Override
    public List<Category> findAllCategories() throws Exception {
        List<Category> categories = categoryRepository.findAll();
        if (categories.isEmpty()) {
            throw new Exception("No categories found");
        }       
         return categories;
        // TODO Auto-generated method stub
    }
    
}
