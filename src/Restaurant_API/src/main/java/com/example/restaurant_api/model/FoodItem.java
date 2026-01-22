package com.example.restaurant_api.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;

@Entity
@Table(name = "food_items")
public class FoodItem extends MenuItem {

    public FoodItem() {}

    public FoodItem(String name, String category, int price, String duration) {
        super(name, category, price, duration);
    }

    @Override
    public String getType() {
        return "Food";
    }
}
