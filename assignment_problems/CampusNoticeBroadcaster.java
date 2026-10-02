import java.util.*;

interface NotificationChannel {
    void send(Student student, String message);
}
class EmailChannel implements NotificationChannel {
    public void send(Student student, String message) {
        System.out.println("[Email → " + student.getName() + "] " + message);
    }
}
class SmsChannel implements NotificationChannel {
    public void send(Student student, String message) {
        System.out.println("[SMS → " + student.getName() + "] " + message);
    }
}
class AppChannel implements NotificationChannel {
    public void send(Student student, String message) {
        System.out.println("[App → " + student.getName() + "] " + message);
    }
}

class Student {
    private String name;
    private String department;
    private List<NotificationChannel> channels;
    Student(String name, String department) {
        this.name = name;
        this.department = department;
        channels = new ArrayList<>();
    }
    String getName() {
        return name;
    }
    String getDepartment() {
        return department;
    }
    void addChannel(NotificationChannel channel) {
        channels.add(channel);
    }
    List<NotificationChannel> getChannels() {
        return channels;
    }
}
class Notice {
    private String title;
    private Set<String> departments;
    Notice(String title, Set<String> departments) {
        this.title = title;
        this.departments = departments;
    }
    boolean isValid() {
        return title != null && !title.trim().isEmpty()
                && departments != null && !departments.isEmpty();
    }
    String getTitle() {
        return title;
    }
    Set<String> getDepartments() {
        return departments;
    }
}

class NoticeBoard {
    private List<Student> students;
    NoticeBoard() {
        students = new ArrayList<>();
    }
    void addStudent(Student student) {
        students.add(student);
    }
    void postNotice(Notice notice) {
        if (!notice.isValid()) {
            System.out.println("Cannot post notice: At least one target department is required.");
            return;
        }
        System.out.print("Notice '" + notice.getTitle() + "' posted to ");
        int count = 0;
        for (String department : notice.getDepartments()) {
            System.out.print(department);
            count++;
            if (count < notice.getDepartments().size()) {
                System.out.print(", ");
            }
        }
        System.out.println(".");
        for (Student student : students) {
            if (notice.getDepartments().contains(student.getDepartment())) {
                for (NotificationChannel channel : student.getChannels()) {
                    channel.send(student, notice.getTitle());
                }
            }
        }
    }
}

public class CampusNoticeBroadcaster {
    public static void main(String[] args) {
        NoticeBoard board = new NoticeBoard();
        Student asha = new Student("Asha", "CSE");
        Student ravi = new Student("Ravi", "ECE");
        asha.addChannel(new EmailChannel());
        asha.addChannel(new AppChannel());
        ravi.addChannel(new SmsChannel());
        board.addStudent(asha);
        board.addStudent(ravi);
        Set<String> cse = new LinkedHashSet<>();
        cse.add("CSE");
        Notice notice1 = new Notice("Lab Closed Tomorrow", cse);
        board.postNotice(notice1);
        Set<String> cseEce = new LinkedHashSet<>();
        cseEce.add("CSE");
        cseEce.add("ECE");
        Notice notice2 = new Notice("Fee Deadline Extended", cseEce);
        board.postNotice(notice2);
        Set<String> noDepartment = new LinkedHashSet<>();
        Notice notice3 = new Notice("Sports Day", noDepartment);
        board.postNotice(notice3);
    }
}
