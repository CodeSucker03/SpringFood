package com.example.accessing_data_mysql.Repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.accessing_data_mysql.Entity.Cart;

public interface CartRepository extends JpaRepository<Cart, Long> {
    public Cart findByUserId(Long userId);
}
