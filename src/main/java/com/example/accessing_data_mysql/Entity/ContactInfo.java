package com.example.accessing_data_mysql.Entity;

import jakarta.persistence.Embeddable;
import lombok.Data;

@Data
@Embeddable
public class ContactInfo {
    private String email;
    private String phone;
    private String facebook;

}
