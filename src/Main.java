import Customer.CustomerController;
import Order.OrderController;
import Product.ProductController;


import java.sql.SQLException;
import java.util.Scanner;


@SuppressWarnings("ALL")
public class Main {



        public static void main(String[] args) throws SQLException {
            Scanner sc = new Scanner(System.in);      // skapa Scanner
            CustomerController cc = new CustomerController();
           // cc.runMenu(sc);

        CustomerController customerController = new CustomerController();
        ProductController productController = new ProductController();
        OrderController orderController = new OrderController();

        while (true) {
            System.out.println("\n--- Välkommen till din HUVUDMENY ---");
            System.out.println("1. Kundmeny");
            System.out.println("2. Produktmeny");
            System.out.println("3. Skapa order");
            System.out.println("0. Avsluta");
            System.out.print("Välj: ");

            String choice = sc.nextLine();

            switch (choice) {
                case "1" -> {
                    try {
                        customerController.runMenu(sc);
                    } catch (SQLException e) {
                        e.printStackTrace();
                    }
                }
                case "2" -> {
                    try {
                        ProductController.runMenu();
                    } catch (SQLException e) {
                        e.printStackTrace();
                    }
                }
                case "3" -> {
                    orderController.createOrderFlow(sc);
                }
                case "0" -> {
                    System.out.println("Avslutar…");
                    return;
                }
                default -> System.out.println("Ogiltigt val, försök igen.");
            }
        }
    }
}

