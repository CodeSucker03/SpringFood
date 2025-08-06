package com.example.accessing_data_mysql.Repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.accessing_data_mysql.Entity.Order;
import com.example.accessing_data_mysql.Entity.OrderItem;

public interface OrderRepository extends JpaRepository<Order, Long> {
    public List<Order> findByCustomerId(Long userId );

    // public List<OrderItem> findByRestaurantId(Long restaurantId);
    
}
