abstract class Vehicle {
    private String name;
    private boolean available;
    Vehicle(String name) {
        this.name = name;
        this.available = true;
    }
    String getName() {
        return name;
    }
    boolean isAvailable() {
        return available;
    }

    void setAvailable(boolean available) {
        this.available = available;
    }
    abstract double calculateCharge(int days);
}

class Sedan extends Vehicle {
    Sedan(String name) {
        super(name);
    }
    double calculateCharge(int days) {
        return days * 50;
    }
}

class SUV extends Vehicle {
    SUV(String name) {
        super(name);
    }
    double calculateCharge(int days) {
        return days * 80;
    }
}

class Truck extends Vehicle {
    Truck(String name) {
        super(name);
    }
    double calculateCharge(int days) {
        return days * 100;
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

class Rental {
    private Vehicle vehicle;
    private Customer customer;
    private int days;
    Rental(Vehicle vehicle, Customer customer, int days) {
        this.vehicle = vehicle;
        this.customer = customer;
        this.days = days;
    }
    void start() {
        if (!vehicle.isAvailable()) {
            System.out.println(vehicle.getName() + " is currently unavailable.");
            return;
        }
        vehicle.setAvailable(false);
        System.out.println(vehicle.getName() + " rented successfully by " + customer.getName() + ".");
        System.out.printf("Rental charge: $%.2f%n", vehicle.calculateCharge(days));
    }
    void returnVehicle() {
        if (!vehicle.isAvailable()) {
            vehicle.setAvailable(true);
            System.out.println(vehicle.getName() + " returned by " + customer.getName() + ".");
        }
    }
}

public class VehicleRentalSystem {
    public static void main(String[] args) {
        Vehicle sedan = new Sedan("Sedan A");
        Vehicle suv = new SUV("SUV B");
        Customer customer1 = new Customer("Customer 1");
        Customer customer2 = new Customer("Customer 2");
        Customer customer3 = new Customer("Customer 3");
        Rental rental1 = new Rental(sedan, customer1, 3);
        rental1.start();
        Rental rental2 = new Rental(sedan, customer2, 2);
        rental2.start();
        rental1.returnVehicle();
        Rental rental3 = new Rental(suv, customer3, 5);
        rental3.start();
    }
}
