abstract class WashType {
    abstract int getDuration();
    abstract double getCharge();
    abstract String getName();
}

class QuickWash extends WashType {
    int getDuration() {
        return 30;
    }

    double getCharge() {
        return 20;
    }

    String getName() {
        return "Quick";
    }
}

class NormalWash extends WashType {
    int getDuration() {
        return 45;
    }

    double getCharge() {
        return 30;
    }

    String getName() {
        return "Normal";
    }
}

class HeavyWash extends WashType {
    int getDuration() {
        return 60;
    }

    double getCharge() {
        return 45;
    }

    String getName() {
        return "Heavy";
    }
}

class Student {
    private String name;

    Student(String name) {
        this.name = name;
    }

    String getName() {
        return name;
    }
}

class WashCycle {
    private Student student;
    private WashingMachine machine;
    private WashType washType;

    WashCycle(Student student, WashingMachine machine, WashType washType) {
        this.student = student;
        this.machine = machine;
        this.washType = washType;
    }

    void start() {
        System.out.println(washType.getName() + " wash started on " + machine.getId() + " for " + student.getName() + " (" + washType.getDuration() + " min).");
        System.out.printf("Charge: ₹%.2f%n", washType.getCharge());
    }
}

class WashingMachine {
    private String id;
    private boolean busy;
    private WashCycle currentCycle;

    WashingMachine(String id) {
        this.id = id;
        busy = false;
    }

    String getId() {
        return id;
    }

    boolean isBusy() {
        return busy;
    }

    void startWash(Student student, WashType washType) {
        if (busy) {
            System.out.println("Machine " + id + " is currently busy.");
            return;
        }

        currentCycle = new WashCycle(student, this, washType);
        busy = true;
        currentCycle.start();
    }

    void completeWash() {
        if (busy) {
            System.out.println(id + " cycle completed.");
            busy = false;
            currentCycle = null;
            System.out.println(id + " is now free.");
        }
    }
}

public class HostelLaundryQueue {
    public static void main(String[] args) {
        Student asha = new Student("Asha");
        Student ravi = new Student("Ravi");
        Student neha = new Student("Neha");

        WashingMachine m1 = new WashingMachine("M1");
        WashingMachine m2 = new WashingMachine("M2");

        m1.startWash(asha, new QuickWash());
        m1.startWash(ravi, new HeavyWash());
        m2.startWash(ravi, new HeavyWash());

        m1.completeWash();

        m1.startWash(neha, new NormalWash());
    }
}
