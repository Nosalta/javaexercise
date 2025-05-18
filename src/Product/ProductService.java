package Product;


import java.sql.SQLException;
import java.util.List;

public class ProductService {
    private final ProductRepository repo = new ProductRepository();

    public List<Product> getAllProducts()
            throws SQLException {
        return repo.getAll();
    }
    public Product getProductById(int id)
            throws SQLException {
        return repo.getById(id);
    }
    public List<Product> searchProducts(String t)
            throws SQLException {
        return repo.searchByName(t);
    }
    public void insertProduct(String n, String d, double p, int stockQty)
            throws SQLException {
        repo.insertProduct(n, d, p, stockQty);
    }
    public boolean updatePrice(int id, double p)
            throws SQLException {
        return repo.updatePrice(id, p);
    }

    public boolean deleteProduct(int id)
            throws SQLException {
        return repo.deleteProduct(id);
    }


}
