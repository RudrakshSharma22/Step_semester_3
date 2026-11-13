public class SinglyLinkedListEmptyChecker {
    static class Node {
        int data;
        Node next;
        Node(int data) {
            this.data = data;
            this.next = null;
        }
    }

    static boolean checkIfEmpty(Node head) {
        return head == null;
    }
    public static void main(String[] args) {
        Node head1 = null;
        Node head2 = new Node(8);
        System.out.println(checkIfEmpty(head1));
        System.out.println(checkIfEmpty(head2));
    }
}
