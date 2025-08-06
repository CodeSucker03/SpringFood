package com.example.accessing_data_mysql.Entity;

import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
    
@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor

public class IngredientsItem {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(unique = true, nullable = false)
    private String name;

    @ManyToOne
    private IngredientCategory category;

    @JsonIgnore
    @ManyToMany
    private List<Restaurant> restaurants = new ArrayList<>();

    private boolean inStock = true;
}
