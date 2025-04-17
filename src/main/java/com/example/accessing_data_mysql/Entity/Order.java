package com.example.accessing_data_mysql.Entity;

import java.util.Date;
import java.util.List;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "customer_order")
public class Order{
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "customer_id")
    private User customer;
    
    private Long totalAmount;
    private String orderStatus;
    private Date createdAt;

    @ManyToOne
    @JoinColumn(name = "restaurant_id") // FK in order table
    private Restaurant restaurant;
    
    // Each order has one delivery address
    @ManyToOne
    @JoinColumn(name = "address_id") // Foreign key in Order table
    private Address deliveryAddress;

    @OneToMany
    private List<OrderItem> items;
    
    private String payment;

    private Long totalPrice;
}
