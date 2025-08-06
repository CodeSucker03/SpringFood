package com.example.accessing_data_mysql.Request;

import lombok.Data;

@Data
public class CreateEventRequest {
    private String name;
    private String image;
    private String location;
    private String startedAt;
}
