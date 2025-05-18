package Product;

import java.sql.SQLException;
import java.util.List;
import java.util.Scanner;


public class ProductController {
    private static final ProductService service = new ProductService();

    public static void runMenu() throws SQLException {
        Scanner sc = new Scanner(System.in);

        while (true) {
            System.out.println("\n--- PRODUKT-MENY ---");
            System.out.println("1. Lista alla produkter");
            System.out.println("2. Hämta produkt via ID");
            System.out.println("3. Sök produkter på namn");
            System.out.println("4. Lägg till produkt");
            System.out.println("5. Ändra pris");
            System.out.println("6. Ta bort produkt");
            System.out.println("0. Avsluta");
            System.out.print("Välj: ");

            String sel = sc.nextLine();
            switch (sel) {
                case "1" -> listAll(sc);
                case "2" -> getById(sc);
                case "3" -> searchByName(sc);
                case "4" -> insertProduct(sc);
                case "5" -> updatePrice(sc);
                case "6" -> deleteProduct(sc);
                case "0" -> { return;
                }
                default  -> System.out.println("Ogiltigt val, försök igen.");
            }
        }
    }

    private static void listAll(Scanner sc) throws SQLException {
        List<Product> list = service.getAllProducts();
        list.forEach(Product::introduce);
    }

    private static void getById(Scanner sc) throws SQLException {
        System.out.print("Ange produkt-ID: ");
        int id = Integer.parseInt(sc.nextLine());
        Product p = service.getProductById(id);
        if (p != null) p.introduce();
        else System.out.println("Produkt hittades ej.");
    }

    private static void searchByName(Scanner sc) throws SQLException {
        System.out.print("Sök på namn: ");
        String term = sc.nextLine();
        List<Product> hits = service.searchProducts(term);
        if (hits.isEmpty()) System.out.println("Inga resultat.");
        else hits.forEach(Product::introduce);
    }

    private static void insertProduct(Scanner sc) throws SQLException {
        System.out.print("Namn: ");
        String n = sc.nextLine();
        System.out.print("Beskrivning: ");
        String d = sc.nextLine();
        System.out.print("Pris (SEK): ");
        double p = Double.parseDouble(sc.nextLine());
        System.out.print("Lagersaldo: ");
        int qty = Integer.parseInt(sc.nextLine());

        service.insertProduct(n, d, p, qty);
        System.out.println("Ny produkt är tillagd.");
    }

    private static void updatePrice(Scanner sc) throws SQLException {
        System.out.print("Produkt-ID: ");
        int id = Integer.parseInt(sc.nextLine());
        System.out.print("Nytt pris: ");
        double p = Double.parseDouble(sc.nextLine());
        boolean ok = service.updatePrice(id, p);
        System.out.println(ok ? "Pris uppdaterat." : "Ingen produkt uppdaterad.");
    }

    private static void deleteProduct(Scanner sc) throws SQLException {
        System.out.print("Produkt-ID: ");
        int id = Integer.parseInt(sc.nextLine());
        boolean ok = service.deleteProduct(id);
        System.out.println(ok ? "Produkt borttagen." : "Ingen produkt borttagen.");
    }
}
