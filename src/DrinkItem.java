public class DrinkItem extends MenuItem {
    public DrinkItem(String name, String category, int price, String duration) {
        super(name, category, price, duration);
    }
    @Override
    public String getType() {
        return "Drink";
    }

}
