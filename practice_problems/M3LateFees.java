class FeeAccount {
    String regNo;
    double totalFees;

    FeeAccount(String regNo, double totalFees) {
        this.regNo = regNo;
        this.totalFees = totalFees;
    }

    final double calculateLateFee(int daysLate) {
        return totalFees * daysLate * 0.01;
    }

    final void printSummary(int daysLate) {
        if (daysLate <= 0) {
            System.out.println(regNo + " - On time, no late fee");
        } else {
            System.out.println(regNo + " | Total Fee: Rs " + totalFees +
                    " | Late Fee: Rs " + calculateLateFee(daysLate));
        }
    }
}

public class M3LateFees {
    public static void main(String[] args) {
        String[] regNos = {"RA001", "RA002", "RA003", "RA004"};
        double[] totalFees = {200000, 150000, 180000, 220000};
        int[] daysLate = {10, 0, -2, 5};

        for (int i = 0; i < regNos.length; i++) {
            FeeAccount account = new FeeAccount(regNos[i], totalFees[i]);
            account.printSummary(daysLate[i]);
        }
    }
}
