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
public class IngredientServiceImp implements IngredientService {
    @Autowired
    private IngredientItemRepository ingredientItemRepository;

    @Autowired
    private IngredientCategoryRepository ingredientCategoryRepository;

    @Autowired
    private RestaurantService restaurantService;

    @Override
    public IngredientCategory createIngredientCategory(String name, Long restaurantId) throws Exception {
        Restaurant restaurant = restaurantService.getRestaurantById(restaurantId);
        IngredientCategory category = ingredientCategoryRepository.findByName(name);

        if (category == null) {
            category = new IngredientCategory();
            category.setName(name);
        }

        if (!category.getRestaurants().contains(restaurant)) {
            category.getRestaurants().add(restaurant);
        }

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
        return ingredientCategoryRepository.findByRestaurantsId(id);
    }

    @Override
    public IngredientsItem createIngredientItem(Long restaurantId, String ingredientName, long categoryId)
            throws Exception {
        Restaurant restaurant = restaurantService.getRestaurantById(restaurantId);
        IngredientCategory category = findIngredientCategoryById(categoryId);
        IngredientsItem item = ingredientItemRepository.findByName(ingredientName);
        if (item == null) {
            item = new IngredientsItem();
            item.setName(ingredientName);
            item.setCategory(category);
        }
    
        // Only add restaurant if not already linked
        if (!item.getRestaurants().contains(restaurant)) {
            item.getRestaurants().add(restaurant);
        }
    
        // Only add ingredient if not already linked to category
        if (!category.getIngredients().contains(item)) {
            category.getIngredients().add(item);
        }
    
        // Save only the item (cascading should handle category if mapped correctly)
        return ingredientItemRepository.save(item);
        
    }

    @Override
    public List<IngredientsItem> findRestaurantIngredients(long restaurantId) {
        return ingredientItemRepository.findByRestaurantsId(restaurantId);
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

    @Override
    public List<IngredientsItem> findAllIngredients() {
        return ingredientItemRepository.findAll();
    }

    @Override
    public List<IngredientCategory> findAll() throws Exception {
        // TODO Auto-generated method stub
        return ingredientCategoryRepository.findAll();
    }

}
