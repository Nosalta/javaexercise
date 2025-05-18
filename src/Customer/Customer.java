package Customer;

public class Customer {

    private int customerId;
    private String name;

    private String email;

    public Customer(int customerId, String name, String email) {
        this.name = name;
        this.customerId = customerId;
        this.email = email;
    }

    public void introduce(){
        System.out.println("Hello my name is " + this.name + ", Customer ID = " + this.customerId);
    }

    public int getCustomerId() {
        return customerId;
    }

    public void setCustomerId(int customerId) {
        this.customerId = customerId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}