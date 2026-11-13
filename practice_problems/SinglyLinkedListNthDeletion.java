
class Node {
    int data;
    Node next;
    Node(int data) {
        this.data = data;
        this.next = null;
    }
}

public class SinglyLinkedListNthDeletion {
    static Node deleteNthNode(Node head, int n) {
        if (n == 1) {
            return head.next;
        }
        Node current = head;

        for (int i = 0; i < n - 2; i++) {
            current = current.next;
        }
        current.next = current.next.next;
        return head;
    }

    static void display(Node head) {
        Node current = head;
        while (current != null) {
            System.out.print(current.data);

            if (current.next != null) {
                System.out.print(" -> ");
            }
            current = current.next;
        }
        System.out.println();
    }

    public static void main(String[] args) {

        Node head = new Node(10);
        head.next = new Node(20);
        head.next.next = new Node(30);
        head.next.next.next = new Node(40);

        System.out.println("Original List:");
        display(head);
        head = deleteNthNode(head, 3);
        System.out.println("After deleting 3rd node:");
        display(head);
        head = deleteNthNode(head, 1);
        System.out.println("After deleting 1st node:");
        display(head);
    }
}
