package com.example.accessing_data_mysql.Service.Impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.accessing_data_mysql.Entity.IngredientCategory;
import com.example.accessing_data_mysql.Entity.IngredientsItem;
import com.example.accessing_data_mysql.Entity.Restaurant;
import com.example.accessing_data_mysql.Repo.IngredientCategoryRepository;
import com.example.accessing_data_mysql.Repo.IngredientItemRepository;
import com.example.accessing_data_mysql.Service.IngredientService;
import com.example.accessing_data_mysql.Service.RestaurantService;

@Service
public class IngredientServiceImp implements IngredientService{
    @Autowired
    private IngredientItemRepository ingredientItemRepository;

    @Autowired
    private IngredientCategoryRepository ingredientCategoryRepository;

    @Autowired
    private RestaurantService restaurantService;

    @Override
    public IngredientCategory createIngredientCategory(String name, Long restaurantId) throws Exception {
        Restaurant restaurant = restaurantService.getRestaurantById(restaurantId);
        IngredientCategory category = new IngredientCategory();
        category.setRestaurant(restaurant);
        category.setName(name);
        return ingredientCategoryRepository.save(category);
    }

    @Override
    public IngredientCategory findIngredientCategoryById(long id) throws Exception {
        Optional<IngredientCategory> opt = ingredientCategoryRepository.findById(id);
        if (opt.isEmpty()) {
            throw new Exception("ingredient category not found");
        }
        return opt.get();
    }

    @Override
    public List<IngredientCategory> findIngredientCategoryByRestaurantId(Long id) throws Exception {
        restaurantService.getRestaurantById(id);
        return ingredientCategoryRepository.findByRestaurantId(id);
    }

    @Override
    public IngredientsItem createIngredientItem(Long restaurantId, String ingredientName, long categoryId)
            throws Exception {
                Restaurant restaurant = restaurantService.getRestaurantById(restaurantId);
                IngredientCategory category = findIngredientCategoryById(categoryId);
                IngredientsItem item = new IngredientsItem();
                item.setName(ingredientName);
                item.setRestaurant(restaurant);
                item.setCategory(category);
                IngredientsItem ingredient = ingredientItemRepository.save(item);
                category.getIngredients().add(ingredient);
                return ingredient;
    }

    @Override
    public List<IngredientsItem> findRestaurantIngredients(long restaurantId) {
           return ingredientItemRepository.findByRestaurantId(restaurantId);
    }

    @Override
    public IngredientsItem updateStock(Long id) throws Exception {
        Optional<IngredientsItem> optIngredientItem = ingredientItemRepository.findById(id);
        if (optIngredientItem.isEmpty()) {
            throw new Exception("ingredient not found");
        }
        IngredientsItem ingredientsItem = optIngredientItem.get();
        ingredientsItem.setInStock(!ingredientsItem.isInStock());
        return ingredientItemRepository.save(ingredientsItem);
    }
    
}
