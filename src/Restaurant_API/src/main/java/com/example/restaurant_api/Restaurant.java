package com.example.restaurant_api.model;

import jakarta.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "restaurant")
public class Restaurant {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int restId;

    private String restName;
    private String address;

    public Restaurant() {}

    public Restaurant(String restName, String address) {
        this.restName = restName;
        this.address = address;
    }

    public int getRestId() { return restId; }
    public void setRestId(int restId) { this.restId = restId; }

    public String getRestName() { return restName; }
    public void setRestName(String restName) { this.restName = restName; }

    public String getAddress() { return address; }
    public void setAddress(String address) { this.address = address; }
}
