package com.example.restaurant_api.controller;

import com.example.restaurant_api.db.MenuItemDB;
import com.example.restaurant_api.model.Order;
import com.example.restaurant_api.model.MenuItem;
import com.example.restaurant_api.model.OrderRequest;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/order")
public class OrderController {

    private final MenuItemDB menuItemDB = new MenuItemDB();


    @GetMapping
    public List<Order> getAllOrders() {
        return menuItemDB.getAllOrders();
    }


    @PostMapping
    public String createOrder(@RequestBody OrderRequest orderRequest) {

        Order order = new Order(orderRequest.getOwner());
        int orderId = menuItemDB.addOrder(order);


        List<MenuItem> items = menuItemDB.getMenuItemsByIds(orderRequest.getFoodIds());


        menuItemDB.addOrderItems(orderId, items);

        return "Order created with id: " + orderId;
    }
}
