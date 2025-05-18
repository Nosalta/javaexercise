package Order;

import Product.Product;
import Product.ProductRepository;

import java.sql.SQLException;


public class OrderService {

    private final OrderRepository orderRepo   = new OrderRepository();
    private final ProductRepository productRepo = new ProductRepository();


    public int createOrder(int customerId) throws SQLException {
        if (customerId <= 0) {
            throw new IllegalArgumentException("Customer ID måste vara > 0");
        }
        return orderRepo.createOrder(customerId);
    }


    public boolean addProductToOrder(int orderId,
                                     int productId,
                                     int qty) throws SQLException {
        if (orderId <= 0 || productId <= 0 || qty <= 0) {
            throw new IllegalArgumentException("OrderID, ProduktID och Antal måste vara > 0");
        }
        // hämta produkt för pris och lager
        Product p = productRepo.getById(productId);
        if (p == null) throw new IllegalArgumentException("Ingen produkt med ID=" + productId);
        if (p.getStockQuantity() < qty)
            throw new IllegalArgumentException("Otillräckligt lager: finns " + p.getStockQuantity());
        double unitPrice = p.getPrice();
        return orderRepo.addProductToOrder(orderId, productId, qty, unitPrice);
    }
}