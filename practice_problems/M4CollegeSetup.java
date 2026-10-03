class SrmStudent {
    static String collegeName;
    static String academicYear;

    static {
        collegeName = "SRM";
        academicYear = "2026-27";
        System.out.println("College info loaded");
    }

    String name;

    SrmStudent(String name) {
        this.name = name;
    }

    void printConfirmation() {
        System.out.println("Student record created: " + name);
    }
}

public class M4CollegeSetup {
    public static void main(String[] args) {
        String[] names = {"Ravi", "Meera", "Karthik", "Divya", "Anitha"};

        for (String name : names) {
            SrmStudent student = new SrmStudent(name);
            student.printConfirmation();
        }
    }
}
