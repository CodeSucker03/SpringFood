package com.example.accessing_data_mysql.Repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.accessing_data_mysql.Entity.IngredientsItem;

public interface IngredientItemRepository extends JpaRepository<IngredientsItem, Long> {
    List<IngredientsItem> findByRestaurantsId(Long id);
    IngredientsItem findByName(String name);
}
