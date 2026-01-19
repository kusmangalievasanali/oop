import java.sql.Connection;

public class Main {
    public static void main(String[] args) {
        {
            Restaurant rest_1 = new Restaurant("ASNUIL", "Kabanbai b. 169");


            MenuItem Beshbarmak = new FoodItem("Beshbarmak", "main food", 5000, "1 hour");
            MenuItem Kymyz = new DrinkItem("Kymyz", "national_drink", 1500, "1 min");
            MenuItem Chechevica = new FoodItem("Chechevica", "first", 1500, "15 min");
            MenuItem Cola = new DrinkItem("Cola", "drink", 800, "1 min");


            rest_1.AddMenuItem(Beshbarmak);
            rest_1.AddMenuItem(Kymyz);
            rest_1.AddMenuItem(Chechevica);
            rest_1.AddMenuItem(Cola);


            rest_1.Printmenu();

            System.out.println("\nSORTED BY PRICE: ");
            rest_1.sortByPrice();
            rest_1.Printmenu();

            System.out.println("\nSEARCH: ");
            System.out.println(rest_1.findByName("Cola"));

            System.out.println("\nFILTER: ");
            for (MenuItem food : rest_1.filterByCategory("national_drink")) {
                System.out.println(food);
            }


            Order ord_1 = new Order(1, "Arman");

            ord_1.addFood(Beshbarmak);
            ord_1.addFood(Kymyz);

            rest_1.addOrder(ord_1);
            System.out.println("\n");


            Order ord_2 = new Order(2, "Dias");
            ord_2.addFood(Chechevica);
            rest_1.addOrder(ord_2);

            System.out.println("--------------------");

            rest_1.infoAll();






            System.out.println("\n--- ADD RESTAURANT, MENU ITEMS, ORDERS AND ORDER_ITEMS ---");

            MenuItemDB db = new MenuItemDB();





            db.addMenuItem(Beshbarmak);
            db.addMenuItem(Kymyz);
            db.addMenuItem(Chechevica);
            db.addMenuItem(Cola);


            int order1Id = db.addOrder(ord_1);


            db.addOrderItems(order1Id, ord_1.getFood_list());


            System.out.println("Restaurant, MenuItems, Orders and OrderItems added successfully.");






        }

    }
}
