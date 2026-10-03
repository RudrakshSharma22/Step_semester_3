class CustomerNameReverser {
    static String reverseCustomerName(String customerName) {
        char[] characters = customerName.toCharArray();

        for (int i = 0, j = characters.length - 1; i < j; i++, j--) {
            char temp = characters[i];
            characters[i] = characters[j];
            characters[j] = temp;
        }

        return new String(characters);
    }

    public static void main(String[] args) {
        String customerName = "Sunil";

        System.out.println("Original Name: " + customerName);
        System.out.println("Reversed Name: " + reverseCustomerName(customerName));
    }
}
