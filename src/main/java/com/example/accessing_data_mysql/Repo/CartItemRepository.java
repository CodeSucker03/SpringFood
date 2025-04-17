package com.example.accessing_data_mysql.Repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.accessing_data_mysql.Entity.CartItem;

public interface CartItemRepository extends JpaRepository<CartItem, Long> {

}
