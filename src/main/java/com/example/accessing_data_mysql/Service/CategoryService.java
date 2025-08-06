package com.example.accessing_data_mysql.Service;

import java.util.List;

import com.example.accessing_data_mysql.Entity.Category;

public interface CategoryService {
    public Category createCategory(Category name, Long userId) throws Exception;

    public List<Category> findAllCategories() throws Exception;

    public List<Category> findCategoryByRestaurantId(Long id) throws Exception;

    public Category findCategoryById(Long id) throws Exception;
}
