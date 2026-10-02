import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

abstract class Assignment {
    private String title;
    private int maxMarks;
    private LocalDate dueDate;

    Assignment(String title, int maxMarks, LocalDate dueDate) {
        this.title = title;
        this.maxMarks = maxMarks;
        this.dueDate = dueDate;
    }
    String getTitle() {
        return title;
    }
    int getMaxMarks() {
        return maxMarks;
    }
    LocalDate getDueDate() {
        return dueDate;
    }
    abstract double applyPenalty(double marks, long lateDays);
}
class CodingAssignment extends Assignment {
    CodingAssignment(String title, int maxMarks, LocalDate dueDate) {
        super(title, maxMarks, dueDate);
    }
    double applyPenalty(double marks, long lateDays) {
        double penalty = lateDays * 0.10;
        return marks * (1 - penalty);
    }
}
class WrittenAssignment extends Assignment {
    WrittenAssignment(String title, int maxMarks, LocalDate dueDate) {
        super(title, maxMarks, dueDate);
    }
    double applyPenalty(double marks, long lateDays) {
        double penalty = lateDays * 0.20;
        return marks * (1 - penalty);
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
enum SubmissionStatus {
    Submitted, Graded
}
class Submission {
    private Student student;
    private Assignment assignment;
    private LocalDate submissionDate;
    private SubmissionStatus status;
    private double finalMarks;
    Submission(Student student, Assignment assignment, LocalDate submissionDate) {
        this.student = student;
        this.assignment = assignment;
        this.submissionDate = submissionDate;
        this.status = SubmissionStatus.Submitted;
    }
    void submit() {
        if (status == SubmissionStatus.Graded) {
            System.out.println("Cannot resubmit: '" + assignment.getTitle() + "' has already been graded.");
            return;
        }
        long lateDays = Math.max(0, ChronoUnit.DAYS.between(assignment.getDueDate(), submissionDate));
        System.out.println(student.getName() + "'s submission for '" + assignment.getTitle() + "' received (" +
                (lateDays == 0 ? "on time" : lateDays + " days late") + ").");
        System.out.println("Status: " + status);
    }
    void grade(double marks) {
        if (status != SubmissionStatus.Submitted) {
            System.out.println("Cannot grade this submission.");
            return;
        }
        long lateDays = Math.max(0, ChronoUnit.DAYS.between(assignment.getDueDate(), submissionDate));
        finalMarks = assignment.applyPenalty(marks, lateDays);
        status = SubmissionStatus.Graded;
        if (lateDays == 0) {
            System.out.printf("%s graded: %.0f/%d.%n", student.getName(), finalMarks, assignment.getMaxMarks());
        } else {
            System.out.printf("%s graded: %.0f/%d after %.0f%% late penalty. Status: %s.%n",
                    student.getName(), finalMarks, assignment.getMaxMarks(),
                    lateDays * getPenaltyRate() * 100, status);
        }
    }
    private double getPenaltyRate() {
        if (assignment instanceof CodingAssignment) {
            return 0.10;
        }
        return 0.20;
    }
}
public class AssignmentSubmissionPortal{
    public static void main(String[] args) {
        Assignment coding = new CodingAssignment(
                "Linked List Lab", 50, LocalDate.of(2026, 3, 10));
        Assignment written = new WrittenAssignment(
                "Design Essay", 50, LocalDate.of(2026, 3, 12));
        Student asha = new Student("Asha");
        Student ravi = new Student("Ravi");
        Submission s1 = new Submission(
                asha, coding, LocalDate.of(2026, 3, 10));
        Submission s2 = new Submission(
                ravi, written, LocalDate.of(2026, 3, 14));
        s1.submit();
        s2.submit();

        s1.grade(45);
        s2.grade(40);

        s1.submit();
    }
}
