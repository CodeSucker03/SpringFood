package com.example.accessing_data_mysql.Service.Impl;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;

import com.example.accessing_data_mysql.DTO.RestaurantDto;
import com.example.accessing_data_mysql.Entity.Address;
import com.example.accessing_data_mysql.Entity.Restaurant;
import com.example.accessing_data_mysql.Entity.User;
import com.example.accessing_data_mysql.Repo.AddressRepository;
import com.example.accessing_data_mysql.Repo.RestaurantRepository;
import com.example.accessing_data_mysql.Repo.UserRepository;
import com.example.accessing_data_mysql.Request.CreateRestaurantRequest;
import com.example.accessing_data_mysql.Service.RestaurantService;

import org.springframework.stereotype.Service;

@Service
public class RestaurantServiceImp implements RestaurantService {

    @Autowired
    private RestaurantRepository restaurantRepository;

    @Autowired
    private AddressRepository addressRepository;

    @Autowired
    private UserRepository userRepository;

    @Override
    public Restaurant createRestaurant(CreateRestaurantRequest req, User user) throws Exception {
        // TODO Auto-generated method stub
        try {
            Address address = addressRepository.save(req.getAddress());
            Restaurant restaurant = new Restaurant();
            restaurant.setAddress(address);
            restaurant.setContact(req.getContact());
            restaurant.setCuisineType(req.getCuisineType());
            restaurant.setDescription(req.getDescription());
            restaurant.setImages(req.getImages());
            restaurant.setName(req.getName());
            restaurant.setOpeningHours(req.getOpeningHours());
            restaurant.setRegistrationDate(LocalDateTime.now());
            restaurant.setOwner(user);

            return restaurantRepository.save(restaurant);
        } catch (Exception e) {
            throw new UnsupportedOperationException("Unimplemented method 'createRestaurant'");
        }
    }

    @Override
    public Restaurant updateRestaurant(Long restaurantId, CreateRestaurantRequest reqUpdateRestaurant) throws Exception {
        try {
            Restaurant restaurant = restaurantRepository.findById(restaurantId).orElseThrow();
    
            if (reqUpdateRestaurant.getName() != null) {
                restaurant.setName(reqUpdateRestaurant.getName());
            }
    
            if (reqUpdateRestaurant.getDescription() != null) {
                restaurant.setDescription(reqUpdateRestaurant.getDescription());
            }
    
            if (reqUpdateRestaurant.getContact() != null) {
                restaurant.setContact(reqUpdateRestaurant.getContact());
            }
    
            if (reqUpdateRestaurant.getCuisineType() != null) {
                restaurant.setCuisineType(reqUpdateRestaurant.getCuisineType());
            }
    
            if (reqUpdateRestaurant.getOpeningHours() != null) {
                restaurant.setOpeningHours(reqUpdateRestaurant.getOpeningHours());
            }
    
            if (reqUpdateRestaurant.getImages() != null) {
                restaurant.setImages(reqUpdateRestaurant.getImages());
            }
    
            return restaurantRepository.save(restaurant);
        } catch (Exception e) {
            // You can add more custom handling here if needed
            throw new Exception("Failed to update restaurant with ID: " + restaurantId, e);
        }
    }
    

    @Override
    public void deleteRestaurant(Long restaurantId) throws Exception {
        try {
            Restaurant restaurant = getRestaurantById(restaurantId);
            restaurantRepository.delete(restaurant);
        } catch (Exception e) {
            throw new UnsupportedOperationException("Unimplemented method 'deleteRestaurant'");
        }
    }

    @Override
    public List<Restaurant> getAllRestaurants() throws Exception {
        try {
            return restaurantRepository.findAll();
        } catch (Exception e) {
            throw new UnsupportedOperationException("Unimplemented method 'getAllRestaurants'");
        }
    }

    @Override
    public List<Restaurant> searchRestaurant(String keyword) {
        return restaurantRepository.findBySearchQuery(keyword);
    }

    @Override
    public Restaurant getRestaurantById(Long restaurantId) throws Exception {
        Optional<Restaurant> restaurant = restaurantRepository.findById(restaurantId);
        if (restaurant.isPresent()) {
            return restaurant.get();
        } else {
            throw new Exception("Restaurant not found with id: " + restaurantId);
        }
    }

    @Override
    public Restaurant getRestaurantsByUserId(Long userId) throws Exception {
        Restaurant restaurant = restaurantRepository.findByOwnerId(userId);
        if (restaurant == null) {
            throw new Exception("Restaurant not found for user with id: " + userId);
        }
        return restaurant;
    }

    @Override
    public RestaurantDto addToFavorites(Long restaurantId, User user) throws Exception {
        try {
            Restaurant restaurant = getRestaurantById(restaurantId);
            RestaurantDto restaurantDto = new RestaurantDto();
            restaurantDto.setDescription(restaurant.getDescription());
            restaurantDto.setName(restaurant.getName());
            restaurantDto.setImages(restaurant.getImages());
            restaurantDto.setId(restaurantId);

            boolean isFavorited = false;
            List<RestaurantDto> favorites = user.getFavorites();
            for (RestaurantDto favorite : favorites) {
                if (favorite.getId().equals(restaurantId)) {
                    isFavorited = true;
                    break;
                }
            }
            if (isFavorited) {
                favorites.removeIf(favorite -> favorite.getId().equals(restaurantId));
            }else{
                favorites.add(restaurantDto);
            }
           
            userRepository.save(user);
            return restaurantDto;
        } catch (Exception e) {
            throw new UnsupportedOperationException("Unimplemented method 'addToFavorites'");

        }

    }

    @Override
    public Restaurant updateRestaurantStatus(Long restaurantId) throws Exception {
        try {
            Restaurant restaurant = getRestaurantById(restaurantId);
            restaurant.setOpen(!restaurant.isOpen());
            return restaurantRepository.save(restaurant);
        } catch (Exception e) {
            throw new UnsupportedOperationException("Unimplemented method 'updateRestaurantStatus'");
        }
    }
}
