package Customer;

import java.sql.*;
import java.util.ArrayList;

public class CustomerRepository {

    public static final String URL = "jdbc:sqlite:/Users/BjornSkola/database0/TechGearWebShop.db";

    public ArrayList<Customer> getAll() throws SQLException {

        ArrayList<Customer> customers = new ArrayList<>();

        try (Connection conn = DriverManager.getConnection(URL);
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery("SELECT * FROM customers")) {

            while (rs.next()) {
                Customer customer = new Customer(rs.getInt("customer_id"),
                        rs.getString("name"),
                        rs.getString("email"));
                customers.add(customer);
            }
        }
        return customers;
    }

    public Customer getCustomerById(int customerId) throws SQLException {

        String sql = "SELECT * FROM customers WHERE customer_id = ?" ;


        try (Connection conn = DriverManager.getConnection(URL);
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setInt(1, customerId);

            ResultSet rs = pstmt.executeQuery();

            return new Customer(customerId,
                    rs.getString("name"),
                    rs.getString("email"));

        }
    }

    public void insertCustomer(String name, String email, String password) throws SQLException{
        String sql = "INSERT INTO customers(name,email,password) VALUES (?,?,?)";

        try (Connection conn = DriverManager.getConnection(URL);
             PreparedStatement pstmt = conn.prepareStatement(sql)){

            pstmt.setString(1,name);
            pstmt.setString(2,email);
            pstmt.setString(3,password);

            pstmt.execute();
        }
    }

    public boolean updateEmail(int customerId, String email) throws SQLException{
        String sql = "UPDATE customers SET email = ? WHERE customer_id = ?";

        try (Connection conn = DriverManager.getConnection(URL);
             PreparedStatement pstmt = conn.prepareStatement(sql)){

            pstmt.setString(1,email);
            pstmt.setInt(2,customerId);

            return pstmt.executeUpdate() > 0;
        }
    }

    public boolean deleteCustomer(int customerId) throws SQLException{
        String sql = "DELETE FROM customers WHERE customer_id = ?";

        try (Connection conn = DriverManager.getConnection(URL);
             PreparedStatement pstmt = conn.prepareStatement(sql)){

            pstmt.setInt(1,customerId);

            return pstmt.executeUpdate() > 0;


        }





    }
}