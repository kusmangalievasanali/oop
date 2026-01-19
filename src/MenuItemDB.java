import java.sql.*;
import java.util.ArrayList;

public class MenuItemDB {

    // ------------------------
    // 1) MENU ITEMS
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
            if (rs.next()) id = rs.getInt("id");
            item.setId(id); // обновляем id в объекте
            System.out.println("Added MenuItem: " + item.getName() + " with ID: " + id);

            rs.close();
            ps.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return id;
    }

    public void getAllMenuItems() {
        try (Connection conn = DBconnection.getConnection();
             Statement st = conn.createStatement();
             ResultSet rs = st.executeQuery("SELECT * FROM menu_items")) {

            while (rs.next()) {
                System.out.println(
                        "ID: " + rs.getInt("id") +
                                ", Name: " + rs.getString("name") +
                                ", Category: " + rs.getString("category") +
                                ", Price: " + rs.getInt("price") +
                                ", Duration: " + rs.getString("duration")
                );
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void updatePrice(int id, int newPrice) {
        try (Connection conn = DBconnection.getConnection()) {
            PreparedStatement ps = conn.prepareStatement(
                    "UPDATE menu_items SET price = ? WHERE id = ?"
            );
            ps.setInt(1, newPrice);
            ps.setInt(2, id);
            int rows = ps.executeUpdate();
            if (rows > 0) System.out.println("Price updated for MenuItem ID: " + id);
            ps.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void deleteMenuItem(int id) {
        try (Connection conn = DBconnection.getConnection()) {
            PreparedStatement ps = conn.prepareStatement(
                    "DELETE FROM menu_items WHERE id = ?"
            );
            ps.setInt(1, id);
            ps.executeUpdate();
            System.out.println("Deleted MenuItem ID: " + id);
            ps.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


    // 2) RESTAURANT
    public int addRestaurant(Restaurant rest) {
        int restId = 0;
        try (Connection conn = DBconnection.getConnection()) {
            PreparedStatement ps = conn.prepareStatement(
                    "INSERT INTO restaurant(rest_name, address) VALUES (?, ?) RETURNING rest_id"
            );
            ps.setString(1, rest.getRest_name());
            ps.setString(2, rest.getAddress());

            ResultSet rs = ps.executeQuery();
            if (rs.next()) restId = rs.getInt("rest_id");
            System.out.println("Added Restaurant: " + rest.getRest_name() + " with ID: " + restId);

            rs.close();
            ps.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return restId;
    }


    // 3) ORDERS
    public int addOrder(Order order) {
        int orderId = 0;
        try (Connection conn = DBconnection.getConnection()) {
            PreparedStatement ps = conn.prepareStatement(
                    "INSERT INTO orders(owner) VALUES (?) RETURNING order_id"
            );
            ps.setString(1, order.getOwner());

            ResultSet rs = ps.executeQuery();
            if (rs.next()) orderId = rs.getInt("order_id");
            System.out.println("Added Order for owner: " + order.getOwner() + " with ID: " + orderId);

            rs.close();
            ps.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return orderId;
    }


    // 4) ORDER ITEMS
    public void addOrderItems(int orderId, ArrayList<MenuItem> foodList) {
        try (Connection conn = DBconnection.getConnection()) {
            for (MenuItem item : foodList) {
                PreparedStatement ps = conn.prepareStatement(
                        "INSERT INTO order_items(order_id, menu_item_id, quantity) VALUES (?, ?, ?)"
                );
                ps.setInt(1, orderId);
                ps.setInt(2, item.getId());
                ps.setInt(3, 1); // quantity
                ps.executeUpdate();
                ps.close();
                System.out.println("Added " + item.getName() + " to Order ID " + orderId);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
