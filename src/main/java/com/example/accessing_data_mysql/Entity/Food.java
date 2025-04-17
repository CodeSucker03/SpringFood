package com.example.accessing_data_mysql.Entity;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

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

public class Food {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO) 
    private Long id;

    private String name;

    private String description;

    private Double price;

    @ManyToOne
    private Category foodCategory;

    @Column(length = 1000)
    private String image;
    
    private boolean available;

    @ManyToOne
    private Restaurant restaurant;

    private boolean isSeasonal;

    @ManyToMany
    private List<IngredientsItem> ingredients = new ArrayList<>();

    private Date creantedAt;

}
