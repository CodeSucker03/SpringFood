package com.example.accessing_data_mysql.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.accessing_data_mysql.Entity.Food;
import com.example.accessing_data_mysql.Entity.Restaurant;
import com.example.accessing_data_mysql.Entity.User;
import com.example.accessing_data_mysql.Request.CreateFoodReq;
import com.example.accessing_data_mysql.Response.MessageResponse;
import com.example.accessing_data_mysql.Service.FoodService;
import com.example.accessing_data_mysql.Service.RestaurantService;
import com.example.accessing_data_mysql.Service.UserService;

@RestController
@RequestMapping("/api/admin/food")
public class AdminFoodController {
    @Autowired
    private FoodService foodService;

    @Autowired
    private UserService userService;

    @Autowired
    private RestaurantService restaurantService;

    @PostMapping("")
    public ResponseEntity<Food> createFood(@RequestBody CreateFoodReq req,
            @RequestHeader("Authorization") String jwt) throws Exception {
        User user = userService.findUserByJwtToken(jwt);
        Restaurant restaurant = restaurantService.getRestaurantById(req.getRestaurantId());
        Food food = foodService.createFood(req, req.getCategory(), restaurant);
        return new ResponseEntity<>(food, HttpStatus.CREATED);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Food> deleteFood(@PathVariable Long id,
            @RequestHeader("Authorization") String jwt) throws Exception {
        User user = userService.findUserByJwtToken(jwt);
        Food food = foodService.findFoodById(id);
        foodService.deleteFood(id);
        // MessageResponse res = new MessageResponse();
        // res.setMessage("Food deleted");

        return new ResponseEntity<>(food, HttpStatus.OK);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Food> updateFoodAvailabilityStatus(@PathVariable Long id,
            @RequestHeader("Authorization") String jwt) throws Exception {
        User user = userService.findUserByJwtToken(jwt);
        Food food = foodService.updateFoodAvailability(id);
        return new ResponseEntity<>(food, HttpStatus.OK);
    }

}
