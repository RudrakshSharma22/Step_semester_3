
public class CircularLinkedListEndInsertion {
    static class Node {
        int data;
        Node next;
        Node(int data) {
            this.data = data;
            this.next = null;
        }
    }
    static Node insertAtEndCircular(Node head, int value) {
        Node newNode = new Node(value);
        if (head == null) {
            newNode.next = newNode;
            return newNode;
        }
        Node current = head;
        while (current.next != head) {
            current = current.next;
        }
        current.next = newNode;
        newNode.next = head;
        return head;
    }

    static void display(Node head) {
        if (head == null) {
            System.out.println("List is empty");
            return;
        }
        Node current = head;
        do {
            System.out.print(current.data + " ");
            current = current.next;
        } while (current != head);

        System.out.println("(back to head)");
    }

    public static void main(String[] args) {
        Node head = new Node(1);
        Node second = new Node(2);
        Node third = new Node(3);
        head.next = second;
        second.next = third;
        third.next = head;

        head = insertAtEndCircular(head, 4);
        display(head);
        Node emptyHead = null;
        emptyHead = insertAtEndCircular(emptyHead, 9);
        display(emptyHead);
    }
}
