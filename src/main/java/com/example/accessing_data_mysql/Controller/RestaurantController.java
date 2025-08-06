package com.example.accessing_data_mysql.Controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.accessing_data_mysql.DTO.RestaurantDto;
import com.example.accessing_data_mysql.Entity.Event;
import com.example.accessing_data_mysql.Entity.Restaurant;
import com.example.accessing_data_mysql.Entity.User;
import com.example.accessing_data_mysql.Service.EventService;
import com.example.accessing_data_mysql.Service.RestaurantService;
import com.example.accessing_data_mysql.Service.UserService;

@RestController
@RequestMapping("/api/restaurant")
public class RestaurantController {

    @Autowired
    private RestaurantService restaurantService;

    @Autowired
    private EventService eventService;
    @Autowired
    private UserService userService;

    @GetMapping("/search")
    public ResponseEntity<List<Restaurant>> searchRestaurant(
            @RequestParam String keyword,
            @RequestHeader("Authorization") String jwtToken)
            throws Exception {
        User user = userService.findUserByJwtToken(jwtToken);
        List<Restaurant> restaurants = restaurantService.searchRestaurant(keyword);
        return new ResponseEntity<>(restaurants, HttpStatus.OK);
    }

    @GetMapping("")
    public ResponseEntity<List<Restaurant>> getAllRestaurant(
            @RequestHeader("Authorization") String jwtToken)
            throws Exception {
        User user = userService.findUserByJwtToken(jwtToken);
        List<Restaurant> restaurants = restaurantService.getAllRestaurants();
        return new ResponseEntity<>(restaurants, HttpStatus.OK);
    }

    @GetMapping("/{restaurantId}")
    public ResponseEntity<Restaurant> findRestaurantById(
            @RequestHeader("Authorization") String jwtToken,
            @PathVariable Long restaurantId)
            throws Exception {
        User user = userService.findUserByJwtToken(jwtToken);
        Restaurant restaurant = restaurantService.getRestaurantById(restaurantId);
        return new ResponseEntity<>(restaurant, HttpStatus.OK);
    }

    @PutMapping("/{restaurantId}/add-favorites")
    public ResponseEntity<RestaurantDto> addToFavorites(
            @RequestHeader("Authorization") String jwtToken,
            @PathVariable Long restaurantId)
            throws Exception {
        User user = userService.findUserByJwtToken(jwtToken);
        RestaurantDto restaurant = restaurantService.addToFavorites(restaurantId, user);
        return new ResponseEntity<>(restaurant, HttpStatus.OK);
    }

    @GetMapping("/{restaurantId}/event")
    public ResponseEntity<List<Event>> getRestaurantEvent(
            @RequestHeader("Authorization") String jwtToken,
            @PathVariable Long restaurantId)
            throws Exception {

        List<Event> events = eventService.getAllEventsByRestaurantId(restaurantId);
        return new ResponseEntity<>(events, HttpStatus.OK);
    }

    @GetMapping("/events")
    public ResponseEntity<List<Event>> getAllEvent(
            @RequestHeader("Authorization") String jwtToken,
            @PathVariable Long restaurantId)
            throws Exception {

        List<Event> events = eventService.getAllEvents();
        return new ResponseEntity<>(events, HttpStatus.OK);
    }

}
