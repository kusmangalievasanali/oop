package com.example.restaurant_api.db;

import com.example.restaurant_api.model.MenuItem;
import com.example.restaurant_api.model.FoodItem;
import com.example.restaurant_api.model.DrinkItem;
import com.example.restaurant_api.model.Order;
import com.example.restaurant_api.model.Restaurant; // <-- Добавить сюда
import java.sql.*;
import java.util.ArrayList;
import java.util.List;


public class MenuItemDB {

    public List<MenuItem> getAllMenuItems() {
        List<MenuItem> items = new ArrayList<>();

        try (Connection conn = DBconnection.getConnection();
             Statement st = conn.createStatement();
             ResultSet rs = st.executeQuery("SELECT * FROM menu_items")) {

            while (rs.next()) {
                String category = rs.getString("category");
                MenuItem item;


                if(category.equalsIgnoreCase("drink") || category.equalsIgnoreCase("national_drink")) {
                    item = new DrinkItem();
                } else {
                    item = new FoodItem();
                }

                item.setId(rs.getInt("id"));
                item.setName(rs.getString("name"));
                item.setCategory(category);
                item.setPrice(rs.getInt("price"));
                item.setDuration(rs.getString("duration"));

                items.add(item);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return items;
    }

    public int addMenuItem(MenuItem item) {
        int id = 0;
        try (Connection conn = DBconnection.getConnection()) {
            PreparedStatement ps = conn.prepareStatement(
                    "INSERT INTO menu_items(name, category, price, duration) VALUES (?, ?, ?, ?) RETURNING id"
            );
            ps.setString(1, item.getName());
            ps.setString(2, item.getCategory());
            ps.setInt(3, item.getPrice());
            ps.setString(4, item.getDuration());

            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                id = rs.getInt("id");
                item.setId(id);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return id;
    }




    public int addOrder(Order order) {
        int id = 0;
        try (Connection conn = DBconnection.getConnection()) {
            PreparedStatement ps = conn.prepareStatement(
                    "INSERT INTO orders(owner) VALUES (?) RETURNING order_id"
            );
            ps.setString(1, order.getOwner());
            ResultSet rs = ps.executeQuery();
            if (rs.next()) id = rs.getInt("order_id");
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return id;
    }

    public void addOrderItems(int orderId, List<MenuItem> items) {
        try (Connection conn = DBconnection.getConnection()) {
            for (MenuItem item : items) {
                PreparedStatement ps = conn.prepareStatement(
                        "INSERT INTO order_items(order_id, menu_item_id, quantity) VALUES (?, ?, ?)"
                );
                ps.setInt(1, orderId);
                ps.setInt(2, item.getId());
                ps.setInt(3, 1);
                ps.executeUpdate();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    public List<Order> getAllOrders() {
        List<Order> orders = new ArrayList<>();
        try (Connection conn = DBconnection.getConnection();
             Statement st = conn.createStatement();
             ResultSet rs = st.executeQuery("SELECT * FROM orders")) {

            while (rs.next()) {
                Order order = new Order();
                order.setOrderId(rs.getInt("order_id"));
                order.setOwner(rs.getString("owner"));


                PreparedStatement ps = conn.prepareStatement(
                        "SELECT m.id, m.name, m.category, m.price, m.duration " +
                                "FROM menu_items m " +
                                "JOIN order_items oi ON m.id = oi.menu_item_id " +
                                "WHERE oi.order_id = ?"
                );
                ps.setInt(1, order.getOrderId());
                ResultSet rsItems = ps.executeQuery();
                while (rsItems.next()) {
                    MenuItem item;
                    String category = rsItems.getString("category");
                    if(category.equalsIgnoreCase("drink") || category.equalsIgnoreCase("national_drink")) {
                        item = new com.example.restaurant_api.model.DrinkItem();
                    } else {
                        item = new com.example.restaurant_api.model.FoodItem();
                    }
                    item.setId(rsItems.getInt("id"));
                    item.setName(rsItems.getString("name"));
                    item.setCategory(category);
                    item.setPrice(rsItems.getInt("price"));
                    item.setDuration(rsItems.getString("duration"));
                    order.getFoodList().add(item);
                }

                orders.add(order);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return orders;
    }


    public List<Restaurant> getAllRestaurants() {
        List<Restaurant> restaurants = new ArrayList<>();
        try (Connection conn = DBconnection.getConnection();
             Statement st = conn.createStatement();
             ResultSet rs = st.executeQuery("SELECT * FROM restaurant")) {

            while (rs.next()) {
                Restaurant r = new Restaurant();
                r.setRestId(rs.getInt("rest_id"));
                r.setRestName(rs.getString("rest_name"));
                r.setAddress(rs.getString("address"));
                restaurants.add(r);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return restaurants;
    }


    public void addRestaurant(Restaurant restaurant) {
        try (Connection conn = DBconnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(
                     "INSERT INTO restaurant(rest_name, address) VALUES (?, ?)"
             )) {

            ps.setString(1, restaurant.getRestName());
            ps.setString(2, restaurant.getAddress());
            ps.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    public List<MenuItem> getMenuItemsByIds(List<Integer> ids) {
        List<MenuItem> items = new ArrayList<>();
        if(ids == null || ids.isEmpty()) return items;

        String placeholders = String.join(",", ids.stream().map(i -> "?").toArray(String[]::new));

        try (Connection conn = DBconnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(
                     "SELECT * FROM menu_items WHERE id IN (" + placeholders + ")"
             )) {

            for(int i = 0; i < ids.size(); i++) {
                ps.setInt(i + 1, ids.get(i));
            }

            ResultSet rs = ps.executeQuery();
            while(rs.next()) {
                String category = rs.getString("category");
                MenuItem item;
                if(category.equalsIgnoreCase("drink") || category.equalsIgnoreCase("national_drink")) {
                    item = new DrinkItem();
                } else {
                    item = new FoodItem();
                }
                item.setId(rs.getInt("id"));
                item.setName(rs.getString("name"));
                item.setCategory(category);
                item.setPrice(rs.getInt("price"));
                item.setDuration(rs.getString("duration"));
                items.add(item);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return items;
    }



}
