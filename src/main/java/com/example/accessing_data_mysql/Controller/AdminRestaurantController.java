package com.example.accessing_data_mysql.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.accessing_data_mysql.Entity.Event;
import com.example.accessing_data_mysql.Entity.Restaurant;
import com.example.accessing_data_mysql.Entity.User;
import com.example.accessing_data_mysql.Request.CreateEventRequest;
import com.example.accessing_data_mysql.Request.CreateRestaurantRequest;
import com.example.accessing_data_mysql.Response.MessageResponse;
import com.example.accessing_data_mysql.Service.EventService;
import com.example.accessing_data_mysql.Service.RestaurantService;
import com.example.accessing_data_mysql.Service.UserService;

@RestController
@RequestMapping("/api/admin/restaurant")

public class AdminRestaurantController {
    @Autowired
    private RestaurantService restaurantService;

    @Autowired
    private EventService eventService;
    @Autowired
    private UserService userService;

    @PostMapping("")
    public ResponseEntity<Restaurant> createRestaurant(
            @RequestBody CreateRestaurantRequest req,
            @RequestHeader("Authorization") String jwtToken)
            throws Exception {
        User user = userService.findUserByJwtToken(jwtToken);
        Restaurant restaurant = restaurantService.createRestaurant(req, user);
        return new ResponseEntity<>(restaurant, HttpStatus.CREATED);
    }

    // RESTAURANT EVENTS    
    @PostMapping("/event")
    public ResponseEntity<Event> createEvent(
        @RequestBody CreateEventRequest req,
        @RequestHeader("Authorization") String jwtToken)
        throws Exception {
            User user = userService.findUserByJwtToken(jwtToken);
            Restaurant restaurant = restaurantService.getRestaurantsByUserId(user.getId());
            Event event = eventService.createEvent(req, restaurant);
            return new ResponseEntity<>(event, HttpStatus.CREATED);
        }

    @PutMapping("/{restaurantId}")
    public ResponseEntity<Restaurant> updateRestaurant(
            @RequestBody CreateRestaurantRequest req,
            @RequestHeader("Authorization") String jwtToken,
            @PathVariable Long restaurantId)
            throws Exception {
        // User user = userService.findUserByJwtToken(jwtToken);
        Restaurant restaurant = restaurantService.updateRestaurant(restaurantId, req);
        return new ResponseEntity<>(restaurant, HttpStatus.OK);
    }

    @DeleteMapping("/{restaurantId}")
    public ResponseEntity<MessageResponse> deleteRestaurant(
            @RequestHeader("Authorization") String jwtToken,
            @PathVariable Long restaurantId)
            throws Exception {
        // User user = userService.findUserByJwtToken(jwtToken);
        restaurantService.deleteRestaurant(restaurantId);
        MessageResponse res = new MessageResponse();
        res.setMessage("Restaurant deleted successfully");
        return new ResponseEntity<>(res, HttpStatus.OK);
    }

    @PutMapping("/{restaurantId}/status")
    public ResponseEntity<Restaurant> updateRestaurantStatus(
            @RequestHeader("Authorization") String jwtToken,
            @PathVariable Long restaurantId)
            throws Exception {
        // User user = userService.findUserByJwtToken(jwtToken);
        Restaurant restaurant = restaurantService.updateRestaurantStatus(restaurantId);
        return new ResponseEntity<>(restaurant, HttpStatus.OK);
    }

    @GetMapping("/user")
    public ResponseEntity<Restaurant> getRestaurantsByUserId(
            @RequestHeader("Authorization") String jwtToken)
            throws Exception {
        User user = userService.findUserByJwtToken(jwtToken);
        Restaurant restaurant = restaurantService.getRestaurantsByUserId(user.getId());
        return new ResponseEntity<>(restaurant, HttpStatus.OK);
    }

}
