import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

abstract class Employee {
    private String name;
    Employee(String name) {
        this.name = name;
    }
    String getName() {
        return name;
    }
    abstract boolean canTakeLeave(long days);
}

class FullTimeEmployee extends Employee {
    FullTimeEmployee(String name) {
        super(name);
    }
    boolean canTakeLeave(long days) {
        return days <= 30;
    }
}

class PartTimeEmployee extends Employee {
    PartTimeEmployee(String name) {
        super(name);
    }
    boolean canTakeLeave(long days) {
        return days <= 15;
    }
}

class Contractor extends Employee {
    Contractor(String name) {
        super(name);
    }
    boolean canTakeLeave(long days) {
        return days <= 10;
    }
}
enum LeaveStatus {
    Pending, Approved, Rejected
}

class LeaveRequest {
    private Employee employee;
    private LocalDate startDate;
    private LocalDate endDate;
    private LeaveStatus status;
    LeaveRequest(Employee employee, LocalDate startDate, LocalDate endDate) {
        this.employee = employee;
        this.startDate = startDate;
        this.endDate = endDate;
        this.status = LeaveStatus.Pending;
    }
    void submit() {
        long days = ChronoUnit.DAYS.between(startDate, endDate) + 1;
        if (!employee.canTakeLeave(days)) {
            System.out.println("Leave request rejected due to leave policy.");
            status = LeaveStatus.Rejected;
            return;
        }
        System.out.println("Leave request submitted for " + employee.getName() +
                " (" + startDate + "-" + endDate + ").");
        System.out.println("Status: " + status);
    }

    void approve(String reviewer) {
        if (status != LeaveStatus.Pending) {
            System.out.println("Cannot approve request. Current status: " + status);
            return;
        }
        status = LeaveStatus.Approved;
        System.out.println(employee.getName() + "'s leave request (" +
                startDate + "-" + endDate + ") approved.");
        System.out.println("Status: " + status);
    }

    void reject(String reviewer) {
        if (status != LeaveStatus.Pending) {
            System.out.println("Cannot reject request. Current status: " + status);
            return;
        }
        status = LeaveStatus.Rejected;
        System.out.println(employee.getName() + "'s leave request (" +
                startDate + "-" + endDate + ") rejected.");
        System.out.println("Status: " + status);
    }

    void changeToPending() {
        if (status == LeaveStatus.Approved || status == LeaveStatus.Rejected) {
            System.out.println("Cannot change leave request status from " +
                    status + " to Pending.");
        }
    }
}

public class EmployeeLeaveRequestWorkflow {
    public static void main(String[] args) {
        Employee john = new FullTimeEmployee("John");
        Employee jane = new PartTimeEmployee("Jane");
        LeaveRequest johnRequest = new LeaveRequest(
                john,
                LocalDate.of(2026, 1, 1),
                LocalDate.of(2026, 1, 5)
        )
        johnRequest.submit();
        johnRequest.approve("Alice");
        LeaveRequest janeRequest = new LeaveRequest(
                jane,
                LocalDate.of(2026, 2, 10),
                LocalDate.of(2026, 2, 11)
        );
        janeRequest.submit();
        janeRequest.reject("Bob");
        johnRequest.changeToPending();
    }
}
