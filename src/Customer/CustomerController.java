package Customer;

import java.sql.SQLException;
import java.util.List;
import java.util.Scanner;

public class CustomerController {

    private final CustomerService customerService = new CustomerService();



    public void runMenu(Scanner scanner) throws SQLException {
        while (true) {
            System.out.println("\n--- KUNDMENY ---");
            System.out.println("1. Hämta alla kunder");
            System.out.println("2. Hämta en kund efter id");
            System.out.println("3. Lägg in en ny kund");
            System.out.println("4. Uppdatera email");
            System.out.println("5. Radera kund");
            System.out.println("0. Tillbaka");
            System.out.print("Välj: ");

            String select = scanner.nextLine();
            switch (select) {
                case "1" -> showAllCustomers();
                case "2" -> showCustomerById(scanner);
                case "3" -> insertUser(scanner);
                case "4" -> updateEmail(scanner);
                case "5" -> deleteCustomer(scanner);
                case "0" -> { return; }
                default  -> System.out.println("Ogiltigt val, försök igen.");
            }
        }
    }

    private void showAllCustomers() throws SQLException {
        List<Customer> customers = customerService.getAllCustomers();
        for (Customer c : customers) {
            System.out.printf("ID: %d, Namn: %s, Email: %s%n",
                    c.getCustomerId(), c.getName(), c.getEmail());
        }
    }

    private void showCustomerById(Scanner scanner) throws SQLException {
        System.out.print("Ange kund‑ID: ");
        int id = Integer.parseInt(scanner.nextLine());
        Customer c = customerService.getCustomerById(id);
        if (c != null) {
            System.out.printf("ID: %d, Namn: %s, Email: %s%n",
                    c.getCustomerId(), c.getName(), c.getEmail());
        } else {
            System.out.println("Kund hittades ej.");
        }
    }

    private void insertUser(Scanner scanner) throws SQLException {
        System.out.print("Ange namn: ");
        String name = scanner.nextLine();
        System.out.print("Ange email: ");
        String email = scanner.nextLine();
        System.out.print("Ange lösenord: ");
        String password = scanner.nextLine();

        customerService.insertUser(name, email, password);
        System.out.println("Ny kund tillagd.");
    }

    private void updateEmail(Scanner scanner) throws SQLException {
        System.out.print("Ange kund‑ID: ");
        int id = Integer.parseInt(scanner.nextLine());
        System.out.print("Ange ny email: ");
        String email = scanner.nextLine();

        boolean ok = customerService.updateEmail(id, email);
        System.out.println(ok ? "Email uppdaterad." : "Kund hittades ej.");
    }

    private void deleteCustomer(Scanner scanner) throws SQLException {
        System.out.print("Ange kund‑ID: ");
        int id = Integer.parseInt(scanner.nextLine());

        boolean ok = customerService.deleteCustomer(id);
        System.out.println(ok ? "Kund raderad." : "Kund hittades ej.");
    }


}
