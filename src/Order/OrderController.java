package Order;

import java.util.Scanner;
import java.sql.SQLException;


public class OrderController {

    private final OrderService orderService = new OrderService();


    public void runMenu() {
        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.println("\n--- ORDERMENY ---");
            System.out.println("1. Skapa ny order");
            System.out.println("0. Tillbaka");
            System.out.print("Välj: ");

            String choice = scanner.nextLine();
            switch (choice) {
                case "1" -> createOrderFlow(scanner);
                case "0" -> {
                    return;
                }
                default -> System.out.println("Ogiltigt val, försök igen.");
            }
        }
    }


    public void createOrderFlow(Scanner scanner) {
        try {
            // Skapa order
            System.out.print("Ange kund‑ID: ");
            int customerId = Integer.parseInt(scanner.nextLine());
            int orderId = orderService.createOrder(customerId);
            System.out.println("Ny order skapad med ID: " + orderId);

            // lägg till produkter
            while (true) {
                System.out.print("Produkt‑ID (Tryck 'Enter' för att gå tillbaka till menyn): ");
                String pidInput = scanner.nextLine();
                if (pidInput.isBlank()) {
                    break;
                }
                int productId = Integer.parseInt(pidInput);

                System.out.print("Antal: ");
                int qty = Integer.parseInt(scanner.nextLine());

                try {
                    boolean added = orderService.addProductToOrder(orderId, productId, qty);
                    if (added) {
                        System.out.printf("✔ Produkt %d (antal %d) tillagd.%n", productId, qty);
                    } else {
                        System.out.println("⚠ Kunde inte lägga till – otillräckligt lager.");
                    }
                } catch (IllegalArgumentException e) {
                    System.out.println("Valideringsfel: " + e.getMessage());
                } catch (SQLException e) {
                    System.out.println("Databasfel vid tillägg av produkt: " + e.getMessage());
                }
            }

            System.out.println("Order avslutad. Order‑ID: " + orderId);

        } catch (NumberFormatException e) {
            System.out.println("Ogiltigt nummerformat, försök igen.");
        } catch (IllegalArgumentException e) {
            System.out.println("Valideringsfel: " + e.getMessage());
        } catch (SQLException e) {
            System.out.println("Databasfel vid skapande av order: " + e.getMessage());
        }
    }
}