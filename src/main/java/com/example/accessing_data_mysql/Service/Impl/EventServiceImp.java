package com.example.accessing_data_mysql.Service.Impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.accessing_data_mysql.Entity.Event;
import com.example.accessing_data_mysql.Entity.Restaurant;
import com.example.accessing_data_mysql.Repo.EventRepository;
import com.example.accessing_data_mysql.Request.CreateEventRequest;
import com.example.accessing_data_mysql.Service.EventService;

@Service
public class EventServiceImp implements EventService {
    @Autowired
    private EventRepository eventRepository;

    @Override
    public List<Event> getAllEventsByRestaurantId(Long id) throws Exception {
        return eventRepository.findByRestaurantId(id);
    }

    @Override
    public Event createEvent(CreateEventRequest createEventReq, Restaurant restaurant) throws Exception {
        if (createEventReq == null || restaurant == null) {
            throw new Exception("Invalid request or restaurant.");
        }
        Event event = new Event();
        event.setName(createEventReq.getName());
        event.setLocation(createEventReq.getLocation());
        event.setStartedAt(createEventReq.getStartedAt());
        event.setImage(createEventReq.getImage());
        event.setRestaurant(restaurant);
    
        // If you're using Spring Data JPA, inject and save via repository
        return eventRepository.save(event); // assuming you have eventRepository
    }

    @Override
    public List<Event> getAllEvents() throws Exception {
        List<Event> events = eventRepository.findAll();
        return events;
    }
}
