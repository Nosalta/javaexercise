package Customer;

import java.sql.SQLException;
import java.util.ArrayList;

public class CustomerService {

   CustomerRepository customerRepository = new CustomerRepository();


    public ArrayList<Customer> getAllCustomers() throws SQLException {
        System.out.println("Välkommen till din databas!");
        System.out.println("Völj åtgärd i menyn.");
        return customerRepository.getAll();
    }

    public void insertUser(String name, String email, String password) throws SQLException{
        System.out.println("service lager skickar vidare name");
        customerRepository.insertCustomer(name, email, password);
    }

    public boolean updateEmail(int customer_id, String email) throws SQLException{
        System.out.println("service skickar vidare update");
        return customerRepository.updateEmail(customer_id,email);
    }

    public boolean deleteCustomer(int id) throws SQLException{
        System.out.println("service skickar vidare id");
        return customerRepository.deleteCustomer(id);
    }

    public Customer getCustomerById(int id) throws SQLException {
        return customerRepository.getCustomerById(id);
    }

}