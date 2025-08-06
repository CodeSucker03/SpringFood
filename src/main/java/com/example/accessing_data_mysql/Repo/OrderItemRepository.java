package com.example.accessing_data_mysql.Repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.accessing_data_mysql.Entity.OrderItem;

public interface OrderItemRepository extends JpaRepository<OrderItem, Long> {
    public List<OrderItem> findByRestaurantId(Long restaurantId);
}
