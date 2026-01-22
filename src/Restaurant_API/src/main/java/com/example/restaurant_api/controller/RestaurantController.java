package com.example.restaurant_api.controller;

import com.example.restaurant_api.db.MenuItemDB;
import com.example.restaurant_api.model.Restaurant;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/restaurant")
public class RestaurantController {

    private final MenuItemDB menuItemDB = new MenuItemDB();


    @GetMapping
    public List<Restaurant> getAllRestaurants() {
        return menuItemDB.getAllRestaurants();
    }


    @PostMapping
    public String addRestaurant(@RequestBody Restaurant restaurant) {
        menuItemDB.addRestaurant(restaurant);
        return "Restaurant added: " + restaurant.getRestName();
    }
}
