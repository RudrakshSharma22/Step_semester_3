public class DoublyLinkedListLastDeletion {
    static class DNode {
        int data;
        DNode prev;
        DNode next;
        DNode(int data) {
            this.data = data;
            this.prev = null;
            this.next = null;
        }
    }
    static DNode deleteLastNodeDoubly(DNode head) {
        if (head == null) {
            return null;
        }
        if (head.next == null) {
            return null;
        }
        DNode current = head;
        while (current.next != null) {
            current = current.next;
        }
        current.prev.next = null;
        current.prev = null;
        return head;
    }
    static void display(DNode head) {
        DNode current = head;
        if (current == null) {
            System.out.println("Empty list");
            return;
        }
        while (current != null) {
            System.out.print(current.data);
            if (current.next != null) {
                System.out.print(" <-> ");
            }
            current = current.next;
        }
        System.out.println();
    }
    public static void main(String[] args) {
        DNode head = new DNode(1);
        DNode second = new DNode(2);
        DNode third = new DNode(3);
        head.next = second;
        second.prev = head;
        second.next = third;
        third.prev = second;
        head = deleteLastNodeDoubly(head);
        display(head);
        DNode single = new DNode(5);
        single = deleteLastNodeDoubly(single);
        display(single);
    }
}
