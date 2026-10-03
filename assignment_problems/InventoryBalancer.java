class InventoryBalancer {
    static void analyzeInventory(int[] sectionA, int[] sectionB) {
        int totalA = 0;
        int totalB = 0;
        int highest = sectionA[0];
        String section = "Section A";
        int index = 0;

        for (int i = 0; i < sectionA.length; i++) {
            totalA += sectionA[i];
            totalB += sectionB[i];

            if (sectionA[i] > highest) {
                highest = sectionA[i];
                section = "Section A";
                index = i;
            }

            if (sectionB[i] > highest) {
                highest = sectionB[i];
                section = "Section B";
                index = i;
            }
        }

        System.out.print("Section A Total: " + totalA);
        System.out.print(" | Section B Total: " + totalB);

        if (totalA == totalB)
            System.out.print(" | Status: Balanced");
        else
            System.out.print(" | Status: Not Balanced");

        System.out.println(" | Highest Quantity: " + highest +
                " (" + section + ", Item " + (index + 1) + ")");
    }

    public static void main(String[] args) {
        int[] sectionA = {20, 15, 30};
        int[] sectionB = {25, 10, 30};

        analyzeInventory(sectionA, sectionB);
    }
}
