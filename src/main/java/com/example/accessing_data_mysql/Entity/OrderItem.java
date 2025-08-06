package com.example.accessing_data_mysql.Entity;

import java.math.BigDecimal;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor
public class OrderItem {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO) 
    private Long id;
    
    @ManyToOne
    private Food food;
    
    
    @ManyToOne
    @JsonIgnoreProperties({"items","id","payment","totalPrice"})
    private Order order;
    
    @Enumerated(EnumType.STRING)
    private OrderStatus orderStatus;

    private Integer quantity;

    private BigDecimal totalPrice;

    @ManyToOne
    @JoinColumn(name = "restaurant_id") // FK in orderItem table
    @JsonIgnoreProperties({
        "events", "owner", "address", "images", "registrationDate","conatact","description","cuisineType"
    })
    private Restaurant restaurant;
    

    private List<String> ingredients; 

}
