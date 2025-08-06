package com.example.accessing_data_mysql.Service;

import java.util.List;

import com.example.accessing_data_mysql.Entity.Event;
import com.example.accessing_data_mysql.Entity.Restaurant;
import com.example.accessing_data_mysql.Request.CreateEventRequest;

public interface EventService {
    public List<Event> getAllEventsByRestaurantId(Long id) throws Exception;

    public List<Event> getAllEvents() throws Exception;

    public Event createEvent(CreateEventRequest createEventReq, Restaurant restaurant) throws Exception;
}
