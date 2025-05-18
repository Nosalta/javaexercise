package Product;


import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ProductRepository {
    private static final String URL = "jdbc:sqlite:/Users/BjornSkola/database0/TechGearWebShop.db";

    // hämta alla produkter
    public List<Product> getAll() throws SQLException {
        String sql = "SELECT product_id, name, description, price, stock_quantity FROM products";
        List<Product> list = new ArrayList<>();

        try (Connection conn = DriverManager.getConnection(URL);
             Statement  stmt = conn.createStatement();
             ResultSet  rs   = stmt.executeQuery(sql)) {

            while (rs.next()) {
                list.add(new Product(
                        rs.getInt("product_id"),
                        rs.getString("name"),
                        rs.getString("description"),
                        rs.getDouble("price"),
                        rs.getInt("stock_quantity")
                ));
            }
        }
        return list;
    }

    // Hämta genom ID
    public Product getById(int id) throws SQLException {
        String sql = """
      SELECT product_id, name, description, price, stock_quantity
      FROM products
      WHERE product_id = ?
    """;
        try (Connection conn = DriverManager.getConnection(URL);
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, id);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    return new Product(
                            rs.getInt("product_id"),
                            rs.getString("name"),
                            rs.getString("description"),
                            rs.getDouble("price"),
                            rs.getInt("stock_quantity")      // ← läs in här
                    );
                }
            }
        }
        return null;
    }

    // Söka på namn
    public List<Product> searchByName(String term) throws SQLException {
        String sql = "SELECT product_id, name, description, price, stock_quantity FROM products WHERE name LIKE ?";
        List<Product> list = new ArrayList<>();
        try (Connection conn = DriverManager.getConnection(URL);
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, "%" + term + "%");
            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    list.add(new Product(
                            rs.getInt("product_id"),
                            rs.getString("name"),
                            rs.getString("description"),
                            rs.getDouble("price"),
                            rs.getInt("stock_quantity")
                    ));
                }
            }
        }
        return list;
    }

    // lägg till en ny produkt
    public void insertProduct(String name, String description, double price, int stockQty) throws SQLException {
        String sql = "INSERT INTO products(name,description,price, stock_quantity) VALUES(?,?,?,?)";
        try (Connection conn = DriverManager.getConnection(URL);
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, name);
            ps.setString(2, description);
            ps.setDouble(3, price);
            ps.setInt(4, stockQty);


            ps.executeUpdate();
        }
    }

    // uppdatera priset
    public boolean updatePrice(int id, double newPrice) throws SQLException {
        String sql = "UPDATE products SET price = ? WHERE product_id = ?";
        try (Connection conn = DriverManager.getConnection(URL);
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setDouble(1, newPrice);
            ps.setInt(2, id);
            return ps.executeUpdate() > 0;
        }
    }

    // ta bort produkt
    public boolean deleteProduct(int id) throws SQLException {
        String sql = "DELETE FROM products WHERE product_id = ?";
        try (Connection conn = DriverManager.getConnection(URL);
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, id);
            return ps.executeUpdate() > 0;
        }
    }
}
