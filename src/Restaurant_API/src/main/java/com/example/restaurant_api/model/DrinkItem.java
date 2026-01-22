package com.example.restaurant_api.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;

@Entity
@Table(name = "drink_items")
public class DrinkItem extends MenuItem {

    public DrinkItem() {}

    public DrinkItem(String name, String category, int price, String duration) {
        super(name, category, price, duration);
    }

    @Override
    public String getType() {
        return "Drink";
    }
}
