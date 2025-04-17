package com.example.accessing_data_mysql.Repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.accessing_data_mysql.Entity.User;

public interface UserRepository extends JpaRepository<User, Long> {
    public User findByEmail(String email);
}
