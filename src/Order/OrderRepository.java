package Order;

import java.sql.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;


public class OrderRepository {

    private static final String URL = "jdbc:sqlite:/Users/BjornSkola/database0/TechGearWebShop.db";


    public int createOrder(int customerId) throws SQLException {
        String sql = "INSERT INTO orders(customer_id, order_date) VALUES(?, CURRENT_TIMESTAMP)";
        try (Connection conn = DriverManager.getConnection(URL);
             PreparedStatement ps = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            ps.setInt(1, customerId);
            ps.executeUpdate();

            // Hämta det autogenererade key
            try (ResultSet keys = ps.getGeneratedKeys()) {
                if (keys.next()) {
                    return keys.getInt(1);
                } else {
                    throw new SQLException("Misslyckades hämta order_id");
                }
            }
        }
    }


    public boolean addProductToOrder(int orderId,
                                     int productId,
                                     int qty,
                                     double unitPrice) throws SQLException {
        String insertSql = """
            INSERT INTO orders_products(order_id, product_id, quantity, unit_price)
            VALUES (?, ?, ?, ?)
            """;
        String updateSql = """
            UPDATE products
            SET stock_quantity = stock_quantity - ?
            WHERE product_id = ? AND stock_quantity >= ?
            """;
        try (Connection conn = DriverManager.getConnection(URL)) {
            conn.setAutoCommit(false);
            try (PreparedStatement ins = conn.prepareStatement(insertSql);
                 PreparedStatement upd = conn.prepareStatement(updateSql)) {

                // 1) INSERT
                ins.setInt(1, orderId);
                ins.setInt(2, productId);
                ins.setInt(3, qty);
                ins.setDouble(4, unitPrice);
                ins.executeUpdate();

                // 2) UPDATE stock
                upd.setInt(1, qty);
                upd.setInt(2, productId);
                upd.setInt(3, qty);
                int updated = upd.executeUpdate();

                if (updated == 0) {
                    conn.rollback();
                    return false; // otillräckligt lager
                }

                conn.commit();
                return true;
            } catch (SQLException ex) {
                conn.rollback();
                throw ex;
            } finally {
                conn.setAutoCommit(true);
            }
        }
    }
}

