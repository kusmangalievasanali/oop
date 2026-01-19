public abstract class MenuItem { //Name of special food
    private int id;
    private String name;
    private String category;
    private int price;
    private String duration;


    public MenuItem(String name, String category, int price, String duration) {

        this.name = name;
        this.category = category;
        this.price = price;
        this.duration = duration;
    }
    public int getId(){
        return id;

    }
    public void setId(int id){
        this.id = id;
    }
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public String getDuration() {
        return duration;
    }

    public void setDuration(String duration) {
        this.duration = duration;
    }



    public void showMenu() {
        System.out.println("Name of food: " + name + "\n" + "Category: " + category + "\n" + "Price: " + price + "\n" + "Duration: " + duration);
        System.out.println("\n");
        System.out.println("------------------------");
    }

    public abstract String getType();

    @Override
    public String toString() {
        return getType() + ": " + name +
                ", category=" + category +
                ", price=" + price +
                ", duration=" + duration;
    }
    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (!(obj instanceof MenuItem)) return false;
        MenuItem other = (MenuItem) obj;
        return name.equals(other.name) && category.equals(other.category);
    }

    @Override
    public int hashCode() {
        return name.hashCode() + category.hashCode();
    }
}




