package com.example.restaurant_api.model;

import jakarta.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "orders")
public class Order {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int orderId;

    private String owner;

    @ManyToMany
    @JoinTable(
            name = "order_items",
            joinColumns = @JoinColumn(name = "order_id"),
            inverseJoinColumns = @JoinColumn(name = "menu_item_id")
    )

    private List<MenuItem> foodList = new ArrayList<>();

    public Order() {}

    public Order(String owner) {
        this.owner = owner;
    }

    public int getOrderId() { return orderId; }
    public void setOrderId(int orderId) { this.orderId = orderId; }

    public String getOwner() { return owner; }
    public void setOwner(String owner) { this.owner = owner; }

    public List<MenuItem> getFoodList() { return foodList; }
    public void setFoodList(List<MenuItem> foodList) { this.foodList = foodList; }
}
