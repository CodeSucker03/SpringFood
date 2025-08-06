package com.example.accessing_data_mysql.Service;

import java.util.List;

import com.example.accessing_data_mysql.Entity.IngredientCategory;
import com.example.accessing_data_mysql.Entity.IngredientsItem;

public interface IngredientService {
    public IngredientCategory createIngredientCategory(String name,
            Long restaurantId) throws Exception;

    public IngredientCategory findIngredientCategoryById(long id) throws Exception;

    public List<IngredientCategory> findIngredientCategoryByRestaurantId(Long id)
            throws Exception;

    public IngredientsItem createIngredientItem(Long restaurantId, String ingredientName,
            long categoryId) throws Exception;

    public List<IngredientsItem> findRestaurantIngredients(long restaurantId);

    public List<IngredientCategory> findAll() throws Exception;
    public List<IngredientsItem> findAllIngredients();

    public IngredientsItem updateStock(Long id) throws Exception;

}
