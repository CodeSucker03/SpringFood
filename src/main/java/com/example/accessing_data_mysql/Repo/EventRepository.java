package com.example.accessing_data_mysql.Repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.accessing_data_mysql.Entity.Event;

public interface EventRepository extends JpaRepository<Event, Long> {
    List<Event> findByRestaurantId(Long id);
}
