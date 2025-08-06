package com.example.accessing_data_mysql.Entity;

import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
public class IngredientCategory {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO) 
    private Long id;

    @Column(unique = true, nullable = false)
    private String name;
    @JsonIgnore
    @ManyToMany
    private List<Restaurant> restaurants = new ArrayList<>();

    @JsonIgnore
    @OneToMany(mappedBy = "category", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<IngredientsItem> ingredients = new ArrayList<>();
}
