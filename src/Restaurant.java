import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

public class Restaurant {
    private String Rest_name;
    private String address;
    private ArrayList<MenuItem> menu = new ArrayList<>();
    private ArrayList<Order> orders = new ArrayList<>();

    public Restaurant(String Rest_name, String address) {
        this.Rest_name = Rest_name;
        this.address = address;
    }

    public String getRest_name() {
        return Rest_name;
    }

    public void setRest_name(String Rest_name) {
        this.Rest_name = Rest_name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public ArrayList<MenuItem> getMenu() {
        return menu;
    }

    public void setMenu(ArrayList<MenuItem> menu) {
        this.menu = menu;
    }

    public ArrayList<Order> getOrders() {
        return orders;
    }

    public void setOrders(ArrayList<Order> orders) {
        this.orders = orders;
    }

    public void AddMenuItem(MenuItem food) {
        this.menu.add(food);
    }

    public void addOrder(Order order) {
        this.orders.add(order);
    }

    public void Printmenu(){
        System.out.println("Menu: ");
        for (MenuItem food : menu) {
            food.showMenu();
        }

    }
    public void infoAll(){
        System.out.println("Restaurant: " + Rest_name);
        System.out.println("Information of Order: "+"\n");
        for (Order orderInfo : orders) {
            orderInfo.infoOrder();
        }


    }

    public MenuItem findByName(String name) {
        for (MenuItem item : menu) {
            if (item.getName().equalsIgnoreCase(name)) {
                return item;
            }
        }
        return null;
    }


    public ArrayList<MenuItem> filterByCategory(String category) {
        ArrayList<MenuItem> result = new ArrayList<>();
        for (MenuItem item : menu) {
            if (item.getCategory().equalsIgnoreCase(category)) {
                result.add(item);
            }
        }
        return result;
    }


    public void sortByPrice() {
        Collections.sort(menu, Comparator.comparingInt(MenuItem::getPrice));
    }



}

