public class FoodItem  extends MenuItem {
    public FoodItem(String name, String category, int price, String duration) {
        super(name, category, price, duration);
    }
    @Override
    public String getType() {
        return "Food";
    }

}
