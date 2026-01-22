package com.example.restaurant_api.controller;

import com.example.restaurant_api.db.MenuItemDB;
import com.example.restaurant_api.model.*;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class MenuItemController {

    private final MenuItemDB menuItemDB = new MenuItemDB();

    @PostMapping("/menu")
    public MenuItem addMenuItem(@RequestBody FoodItem item) {
        menuItemDB.addMenuItem(item);
        return item;
    }

    @GetMapping("/menu")
    public List<MenuItem> getMenu() {
        return menuItemDB.getAllMenuItems();
    }


}
