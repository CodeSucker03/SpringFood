package com.example.accessing_data_mysql.Entity;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

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
@AllArgsConstructor
@NoArgsConstructor

public class Food {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(nullable = false)
    private String name;

    private String description;

    private BigDecimal price;

    @JsonIgnore
    @OneToMany(mappedBy = "food", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<CartItem> cartItems;

    @ManyToOne
    private Category foodCategory;

    @Column(length = 1000)
    private String image;

    private boolean available=true;


    @ManyToOne
    @JsonIgnoreProperties({
    "events", "owner", "address", "images", "registrationDate"
})
    private Restaurant restaurant;

    private boolean isSeasonal;

    @ManyToMany
    private List<IngredientsItem> ingredients = new ArrayList<>();

    private LocalDateTime createdAt;

}
