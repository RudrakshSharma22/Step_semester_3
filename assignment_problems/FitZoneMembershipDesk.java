interface MembershipPlan {
    double calculateFee();
    String getName();
}

class MonthlyPlan implements MembershipPlan {
    public double calculateFee() {
        return 1000;
    }

    public String getName() {
        return "Monthly";
    }
}

class QuarterlyPlan implements MembershipPlan {
    public double calculateFee() {
        return 1000 * 3 * 0.90;
    }

    public String getName() {
        return "Quarterly";
    }
}

class AnnualPlan implements MembershipPlan {
    public double calculateFee() {
        return 1000 * 12 * 0.75;
    }
    public String getName() {
        return "Annual";
    }
}

class Member {
    private String name;
    Member(String name) {
        this.name = name;
    }
    String getName() {
        return name;
    }
}
enum MembershipStatus {
    Active, Frozen, Expired
}

class Membership {
    private Member member;
    private MembershipPlan plan;
    private MembershipStatus status;
    Membership(Member member, MembershipPlan plan) {
        this.member = member;
        this.plan = plan;
        this.status = MembershipStatus.Active;
    }
    void displayDetails() {
        System.out.println(plan.getName() + " membership created for " + member.getName() + ".");
        System.out.printf("Fee: ₹%.2f%n", plan.calculateFee());
        System.out.println("Status: " + status);
    }
    void checkIn() {
        if (status == MembershipStatus.Active) {
            System.out.println(member.getName() + " checked in successfully.");
        } else {
            System.out.println("Check-in denied: " + member.getName() +
                    "'s membership is " + status + ".");
        }
    }
    void freeze() {
        if (status == MembershipStatus.Active) {
            status = MembershipStatus.Frozen;
            System.out.println(member.getName() + "'s membership frozen.");
            System.out.println("Status: " + status);
        } else if (status == MembershipStatus.Expired) {
            System.out.println("Cannot freeze an Expired membership.");
        } else {
            System.out.println("Membership is already Frozen.");
        }
    }
    void unfreeze() {
        if (status == MembershipStatus.Frozen) {
            status = MembershipStatus.Active;
            System.out.println(member.getName() + "'s membership unfrozen.");
            System.out.println("Status: " + status);
        } else if (status == MembershipStatus.Expired) {
            System.out.println("Cannot unfreeze an Expired membership.");
        } else {
            System.out.println("Membership is already Active.");
        }
    }
    void expire() {
        if (status != MembershipStatus.Expired) {
            status = MembershipStatus.Expired;
            System.out.println(member.getName() + "'s membership expired.");
            System.out.println("Status: " + status);
        }
    }
}

public class FitZoneMembershipDesk {
    public static void main(String[] args) {
        Member asha = new Member("Asha");
        Member ravi = new Member("Ravi");
        Membership ashaMembership =
                new Membership(asha, new QuarterlyPlan());
        Membership raviMembership =
                new Membership(ravi, new MonthlyPlan());
        ashaMembership.displayDetails();
        raviMembership.displayDetails();
        ashaMembership.checkIn();
        ashaMembership.freeze();
        ashaMembership.checkIn();
        raviMembership.expire();
        raviMembership.freeze();
    }
}
