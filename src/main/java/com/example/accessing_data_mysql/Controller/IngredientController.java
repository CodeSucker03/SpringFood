package com.example.accessing_data_mysql.Controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.accessing_data_mysql.Entity.IngredientCategory;
import com.example.accessing_data_mysql.Entity.IngredientsItem;
import com.example.accessing_data_mysql.Request.IngredientCategoryRequest;
import com.example.accessing_data_mysql.Request.IngredientRequest;
import com.example.accessing_data_mysql.Service.IngredientService;

@RestController
@RequestMapping("/api/admin/ingredient")
public class IngredientController {
    
    @Autowired
    private IngredientService ingredientService;

    @PostMapping("/category")
    public ResponseEntity<IngredientCategory> createIngredientCategory(@RequestBody IngredientCategoryRequest req)
            throws Exception {
        IngredientCategory item = ingredientService.createIngredientCategory(req.getName(), req.getRestaurantId());
        return new ResponseEntity<>(item, HttpStatus.CREATED);
    }

    @PostMapping("")
    public ResponseEntity<IngredientsItem> createIngredientItem(@RequestBody IngredientRequest req) throws Exception {
        IngredientsItem item = ingredientService.createIngredientItem(req.getRestaurantId(), req.getName(),
                req.getCategoryId());
        return new ResponseEntity<>(item, HttpStatus.CREATED);
    }

    @PutMapping("/{id}/stock")
    public ResponseEntity<IngredientsItem> updateIngredientStock(@PathVariable Long id) throws Exception {
        IngredientsItem item = ingredientService.updateStock(id);
        return new ResponseEntity<>(item, HttpStatus.OK);
    }

    @GetMapping("/restaurant/{id}")
    public ResponseEntity<List<IngredientsItem>> getRestaurantIngredient(@PathVariable Long id) throws Exception {
        List<IngredientsItem> items = ingredientService.findRestaurantIngredients(id);
        return new ResponseEntity<>(items, HttpStatus.OK);
    }
    
    @GetMapping("/all")
    public ResponseEntity<List<IngredientsItem>> getAllIngredient() throws Exception {
        List<IngredientsItem> items = ingredientService.findAllIngredients();
        return new ResponseEntity<>(items, HttpStatus.OK);
    }

    @GetMapping("/category/all")
    public ResponseEntity<List<IngredientCategory>> getAllIngredientCategory() throws Exception {
        List<IngredientCategory> ingredientCategories = ingredientService.findAll();
        return new ResponseEntity<>(ingredientCategories, HttpStatus.OK);
    }

    @GetMapping("/restaurant/{id}/category")
    public ResponseEntity<List<IngredientCategory>> getRestaurantIngredientCategory(@PathVariable Long id) throws Exception {
        List<IngredientCategory> items = ingredientService.findIngredientCategoryByRestaurantId(id);
        return new ResponseEntity<>(items, HttpStatus.OK);
    }
}
