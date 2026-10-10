import java.util.*;

abstract class Staff {
    String name;
    String type;
    Staff(String name, String type) {
        this.name = name;
        this.type = type;
    }

    abstract int calculatePay();
    public String toString() {
        return "Payslip[name=" + name + ", type=" + type + ", pay=" + calculatePay() + "]";
    }
}

class FullTime extends Staff {
    int salary;
    FullTime(String name, int salary) {
        super(name, "FullTime");
        this.salary = salary;
    }
    int calculatePay() {
        return salary;
    }
}

class PartTime extends Staff {
    int hours, rate;
    PartTime(String name, int hours, int rate) {
        super(name, "PartTime");
        this.hours = hours;
        this.rate = rate;
    }
    int calculatePay() {
        return hours * rate;
    }
}

class Intern extends Staff {
    int stipend;
    Intern(String name, int stipend) {
        super(name, "Intern");
        this.stipend = stipend;
    }
    int calculatePay() {
        return stipend;
    }
}

public class PayrollRegisterSystem {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        Staff[] staff = new Staff[100];
        int count = 0;

        while (sc.hasNext()) {
            String type = sc.next();
            if (type.equalsIgnoreCase("FullTime")) {
                String name = sc.next();
                int salary = sc.nextInt();
                staff[count++] = new FullTime(name, salary);
            } else if (type.equalsIgnoreCase("PartTime")) {
                String name = sc.next();
                int hours = sc.nextInt();
                int rate = sc.nextInt();
                staff[count++] = new PartTime(name, hours, rate);
            } else if (type.equalsIgnoreCase("Intern")) {
                String name = sc.next();
                int stipend = sc.nextInt();
                staff[count++] = new Intern(name, stipend);
            }
        }

        int total = 0;
        int topper = 0;
        for (int i = 0; i < count; i++) {
            System.out.println(staff[i]);
            total += staff[i].calculatePay();

            if (staff[i].calculatePay() > staff[topper].calculatePay()) {
                topper = i;
            }
        }

        System.out.println("Total " + total);
        if (count > 0) {
            System.out.println("Top earner " + staff[topper].name);
        }

        sc.close();
    }
}
