import java.util.*;

interface PaymentMethod {
    boolean processPayment(double amount);
    String getName();
}
class CreditCardPayment implements PaymentMethod {
    public boolean processPayment(double amount) {
        return true;
    }
    public String getName() {
        return "Credit Card";
    }
}

class PayPalPayment implements PaymentMethod {
    public boolean processPayment(double amount) {
        return false;
    }
    public String getName() {
        return "PayPal";
    }
}

class BankTransferPayment implements PaymentMethod {
    public boolean processPayment(double amount) {
        return true;
    }
    public String getName() {
        return "Bank Transfer";
    }
}

class Product {
    private String name;
    private double price;
    Product(String name, double price) {
        this.name = name;
        this.price = price;
    }
    double getPrice() {
        return price;
    }
    String getName() {
        return name;
    }
}

class OrderItem {
    private Product product;
    private int quantity;
    OrderItem(Product product, int quantity) {
        this.product = product;
        this.quantity = quantity;
    }
    double getTotal() {
        return product.getPrice() * quantity;
    }
}

class Customer {
    private String name;
    Customer(String name) {
        this.name = name;
    }
    String getName() {
        return name;
    }
}
enum OrderStatus {
    Pending, Paid
}

class Order {
    private Customer customer;
    private List<OrderItem> items;
    private OrderStatus status;
    Order(Customer customer) {
        this.customer = customer;
        items = new ArrayList<>();
        status = OrderStatus.Pending;
    }

    void addProduct(Product product, int quantity) {
        if (quantity > 0) {
            items.add(new OrderItem(product, quantity));
        }
    }

    double getTotal() {
        double total = 0;
        for (OrderItem item : items) {
            total += item.getTotal();
        }
        return total;
    }

    void pay(PaymentMethod paymentMethod) {
        if (items.isEmpty()) {
            System.out.println("Cannot process payment for an empty order.");
            return;
        }
        System.out.println("Payment initiated via " +
                paymentMethod.getName() + " for " +
                customer.getName() + ".");
        boolean success = paymentMethod.processPayment(getTotal());
        if (success) {
            status = OrderStatus.Paid;
            System.out.println("Payment for " + customer.getName() +
                    "'s order successful.");
        } else {
            System.out.println("Payment for " + customer.getName() +
                    "'s order failed.");
        }
        System.out.println("Order status: " + status);
    }
}

public class PaymentProcessingShoppingSystem {
    public static void main(String[] args) {
        Customer customerX = new Customer("Customer X");
        Product productA = new Product("Product A", 100);
        Product productB = new Product("Product B", 200);
        Order orderX = new Order(customerX);
        orderX.addProduct(productA, 2);
        orderX.addProduct(productB, 1);
        System.out.println("Order created for Customer X.");
        orderX.pay(new CreditCardPayment());
        Customer customerY = new Customer("Customer Y");
        Order orderY = new Order(customerY);
        System.out.println("Order created for Customer Y.");
        orderY.pay(new CreditCardPayment());
        Customer customerZ = new Customer("Customer Z");
        Product productC = new Product("Product C", 300);
        Order orderZ = new Order(customerZ);
        orderZ.addProduct(productC, 1);
        System.out.println("Order created for Customer Z.");
        orderZ.pay(new PayPalPayment());
    }
}
