package com.example.accessing_data_mysql.Repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.example.accessing_data_mysql.Entity.Food;

public interface FoodRepository extends JpaRepository<Food, Long> {
    List<Food> findByRestaurantId(Long restaurantId);

    // @Query("SELECT f FROM Food f WHERE f.name LIKE %:keyword% OR
    // f.foodCategory.name LIKE %:keyword%")
    // List<Food> searchFood(@Param("keyword") String keyword);
    @Query("SELECT f FROM Food f WHERE LOWER(f.name) LIKE LOWER(CONCAT('%', :keyword, '%')) OR LOWER(f.foodCategory.name) LIKE LOWER(CONCAT('%', :keyword, '%'))")
    List<Food> searchFood(@Param("keyword") String keyword);

    @Query(value = "SELECT f.* FROM food f " +
            "JOIN (SELECT food_id, COUNT(*) AS total_orders " +
            "FROM order_item " +
            "GROUP BY food_id " +
            "ORDER BY total_orders DESC " +
            "LIMIT 10) AS topOrder " +
            "ON f.id = topOrder.food_id", nativeQuery = true)
    List<Food> findTopFoods();

}
