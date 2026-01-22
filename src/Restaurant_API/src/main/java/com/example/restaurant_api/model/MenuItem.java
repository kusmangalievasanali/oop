package com.example.restaurant_api.model;

import jakarta.persistence.*;

@Entity
@Table(name = "menu_items")
@Inheritance(strategy = InheritanceType.JOINED)
public abstract class MenuItem {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String name;
    private String category;
    private int price;
    private String duration;

    public MenuItem() {}

    public MenuItem(String name, String category, int price, String duration) {
        this.name = name;
        this.category = category;
        this.price = price;
        this.duration = duration;
    }

    public int getId() { return id; }
    public void setId(int id) { this.id = id; }

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public String getCategory() { return category; }
    public void setCategory(String category) { this.category = category; }

    public int getPrice() { return price; }
    public void setPrice(int price) { this.price = price; }

    public String getDuration() { return duration; }
    public void setDuration(String duration) { this.duration = duration; }

    public abstract String getType();
}
