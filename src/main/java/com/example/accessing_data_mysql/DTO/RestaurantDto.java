package com.example.accessing_data_mysql.DTO;

import java.util.List;


import jakarta.persistence.Column;
import jakarta.persistence.ElementCollection;
import jakarta.persistence.Embeddable;
import lombok.Data;

@Data
@Embeddable
public class RestaurantDto {
    
    private Long id;

    private String title;

    @Column(length = 1000)
    private List<String> images;

    private String description;

    // private Address address; ///

}
