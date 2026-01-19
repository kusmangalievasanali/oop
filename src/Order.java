import java.util.ArrayList;

public class Order {
    private int order_Id;
    private String owner;
    private ArrayList<MenuItem> food_list = new ArrayList<>();

    public Order(int order_Id, String owner) {
        this.order_Id = order_Id;
        this.owner = owner;
    }
    public int getOrder_Id() {
        return order_Id;
    }
    public void setOrder_Id(int order_Id) {
        this.order_Id = order_Id;
    }
    public String getOwner() {
        return owner;
    }
    public void setOwner(String owner) {
        this.owner = owner;
    }

    public ArrayList<MenuItem> getFood_list() {
        return food_list;
    }
    public void setFood_list(ArrayList<MenuItem> food_list) {
        this.food_list = food_list;
    }

    public void addFood(MenuItem food) {
        this.food_list.add(food);
    }


    public int sumOrder() {
        int sum = 0;
        for (MenuItem food : food_list) {
            sum += food.getPrice();
        }
        return sum;
    }

    public void infoOrder(){
        System.out.println("Id of Order: " + order_Id +"\n" + "Owner Name: " + owner + "\n" + "Food List: " + food_list);
        System.out.println("Total Order sum: " + sumOrder());
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (!(obj instanceof Order)) return false;
        Order other = (Order) obj;
        return order_Id == other.order_Id;
    }

    @Override
    public int hashCode() {
        return Integer.hashCode(order_Id);
    }

    @Override
    public String toString() {
        return "Order ID: " + order_Id +
                ", owner=" + owner +
                ", Sum of order=" + sumOrder();
    }


}

