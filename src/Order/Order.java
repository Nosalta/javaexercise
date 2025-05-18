package Order;

import java.util.Date;



public class Order {
    private int orderId;
    private final int customerId;
    private final Date orderDate;
    private double totalAmount;

    public Order(int orderId, int customerId, Date orderDate, double totalAmount) {
        this.orderId = orderId;
        this.customerId = customerId;
        this.orderDate = orderDate;
        this.totalAmount = totalAmount;
    }

    // skapa ny order
    public Order(int customerId) {
        this.customerId = customerId;
        this.orderDate = new Date();
    }

    public int getCustomerId() {
        return customerId;
    }

    public int getOrderId() {

        return orderId;
    }


    public Date getOrderDate() {
        return orderDate;
    }

    @Override
    public String toString() {
        return  "-------------------------------------\n" +
                "Order ID: " + orderId + "\n" +
                "Kund ID: " + customerId + "\n" +
                "Order Datum: " + orderDate + "\n" +
                "Total Summa: Kr" + String.format("%.2f", totalAmount) + "\n" +
                "-------------------------------------";
    }
}
