
public class NoticeBoardBubbleSort {
    public static int[] bubbleStats(int[] scores) {
        int passes = 0;
        int swaps = 0;
        int n = scores.length;
        for (int i = 0; i < n - 1; i++) {
            boolean swapped = false;
            passes++;
            for (int j = 0; j < n - 1 - i; j++) {
                if (scores[j] > scores[j + 1]) {
                    int temp = scores[j];
                    scores[j] = scores[j + 1];
                    scores[j + 1] = temp;
                    swaps++;
                    swapped = true;
                }
            }
            if (swapped == false) {
                break;
            }
        }
        return new int[]{passes, swaps};
    }

    public static void main(String[] args) {
        int[] scores = {5, 1, 4, 2, 8};
        int[] result = bubbleStats(scores);
        System.out.print("Sorted Array: ");
        for (int x : scores) {
            System.out.print(x + " ");
        }
        System.out.println();
        System.out.println("Passes: " + result[0]);
        System.out.println("Swaps: " + result[1]);
    }
}
