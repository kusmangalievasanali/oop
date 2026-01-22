package com.example.restaurant_api.model;

import java.util.List;

public class OrderRequest {
    private String owner;
    private List<Integer> foodIds;

    public String getOwner() { return owner; }
    public void setOwner(String owner) { this.owner = owner; }

    public List<Integer> getFoodIds() { return foodIds; }
    public void setFoodIds(List<Integer> foodIds) { this.foodIds = foodIds; }
}
