
class Node {
    int data;
    Node next;

    Node(int data) {
        this.data = data;
        this.next = null;
    }
}

public class SinglyLinkedListKNodeRemoval {
    static Node removeFirstKNodes(Node head, int k) {
        if (head == null || k == 0) {
            return head;
        }
        for (int i = 0; i < k; i++) {
            if (head == null) {
                break;
            }
            head = head.next;
        }
        return head;
    }

    static void display(Node head) {
        Node current = head;
        while (current != null) {
            System.out.print(current.data + " ");
            current = current.next;
        }
        System.out.println();
    }
    public static void main(String[] args) {

        Node head = new Node(1);
        head.next = new Node(2);
        head.next.next = new Node(3);
        head.next.next.next = new Node(4);
        head.next.next.next.next = new Node(5);

        System.out.println("Original List:");
        display(head);
        head = removeFirstKNodes(head, 2);
        System.out.println("After Removing 2 Nodes:");
        display(head);
        head = removeFirstKNodes(head, 5);
        System.out.println("After Removing 5 Nodes:");
        display(head);
    }
}
