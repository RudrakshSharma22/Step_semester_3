import java.util.*;

interface Chargeable {
    void charge();
}

class Vehicle {
    String passNumber, owner, type;
    int fee;
    Vehicle(String passNumber, String owner, String type, int fee) {
        this.passNumber = passNumber;
        this.owner = owner;
        this.type = type;
        this.fee = fee;
    }

    void displayPass() {
        System.out.println(passNumber + " (" + type + ") pass fee " + fee);
    }
}

class Bike extends Vehicle {
    Bike(String id, String owner) {
        super(id, owner, "Bike", 300);
    }
}
class Car extends Vehicle {
    Car(String id, String owner) {
        super(id, owner, "Car", 1000);
    }
}

class EBike extends Vehicle implements Chargeable {
    EBike(String id, String owner) {
        super(id, owner, "E-Bike", 300);
    }
    public void charge() {
        System.out.println(passNumber + " charging bay allotted");
    }
}

class ECar extends Vehicle implements Chargeable {
    ECar(String id, String owner) {
        super(id, owner, "E-Car", 1000);
    }
    public void charge() {
        System.out.println(passNumber + " charging bay allotted");
    }
}

public class CampusVehiclePassSystem {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        Map<String, Vehicle> vehicles = new HashMap<>();

        while (sc.hasNext()) {
            String operation = sc.next();

            if (operation.equals("PASS")) {
                String type = sc.next();
                String id = sc.next();
                String owner = sc.next();
                Vehicle v = null;

                switch (type.toUpperCase()) {
                    case "BIKE":
                        v = new Bike(id, owner);
                        break;
                    case "CAR":
                        v = new Car(id, owner);
                        break;
                    case "E-BIKE":
                    case "EBIKE":
                        v = new EBike(id, owner);
                        break;
                    case "E-CAR":
                    case "ECAR":
                        v = new ECar(id, owner);
                        break;
                }

                if (v != null) {
                    vehicles.put(id, v);
                    v.displayPass();
                }
            } else if (operation.equals("CHARGE")) {
                String id = sc.next();
                Vehicle v = vehicles.get(id);
                if (v == null) {
                    System.out.println(id + " rejected: vehicle not found");
                } else if (v instanceof Chargeable) {
                    ((Chargeable) v).charge();
                } else {
                    System.out.println(id + " rejected: charging unsupported");
                }
            }
        }

        sc.close();
    }
}
