
public class ContestLeaderboardSorter {
    static class Entry {
        String name;
        int marks;
        Entry(String name, int marks) {
            this.name = name;
            this.marks = marks;
        }
    }
    public static Entry[] leaderboard(Entry[] entries) {
        int n = entries.length;
        int[] count = new int[101];
        Entry[] result = new Entry[n];

      for (int i = 0; i < n; i++) {
            int key = 100 - entries[i].marks;
            count[key]++;
        }

        for (int i = 1; i < 101; i++) {
            count[i] += count[i - 1];
        }

        for (int i = n - 1; i >= 0; i--) {
            int key = 100 - entries[i].marks;
            result[--count[key]] = entries[i];
        }
        return result;
    }

    public static void main(String[] args) {
        Entry[] entries = {
            new Entry("Asha", 90),
            new Entry("Ravi", 75),
            new Entry("Neha", 98),
            new Entry("Kiran", 100),
            new Entry("Meera", 75)
        };
        Entry[] sorted = leaderboard(entries);
        System.out.println("Contest Leaderboard:");
        for (Entry e : sorted) {
            System.out.println(e.name + " " + e.marks);
        }
    }
}
