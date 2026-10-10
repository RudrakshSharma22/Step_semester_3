import java.util.*;

public class ClassMarksGridAnalysis {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        List<String> names = new ArrayList<>();
        List<int[]> marksList = new ArrayList<>();

        while (sc.hasNext()) {
            String name = sc.next();
            int[] marks = new int[3];
            for (int i = 0; i < 3; i++) {
                marks[i] = sc.nextInt();
            }
            names.add(name);
            marksList.add(marks);
        }

        int n = names.size();
        int[] totals = new int[n];
        double[] averages = new double[3];
        int topper = 0;

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < 3; j++) {
                totals[i] += marksList.get(i)[j];
                averages[j] += marksList.get(i)[j];
            }
            if (totals[i] > totals[topper]) {
                topper = i;
            }
        }
      
        for (int i = 0; i < n; i++) {
            System.out.println("Total " + names.get(i) + " " + totals[i]);
        }
        for (int j = 0; j < 3; j++) {
            averages[j] /= n;
        }

        System.out.printf(Locale.US, "Averages %.2f, %.2f, %.2f%n",
                averages[0], averages[1], averages[2]);
        System.out.println("Topper " + names.get(topper) + " (" + totals[topper] + ")");
        sc.close();
    }
}
