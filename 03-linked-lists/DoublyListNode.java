public class DoublyListNode {
    DoublyListNode prev;
    int val;
    DoublyListNode next;

    DoublyListNode(int val) {
        this.prev = null;
        this.val = val;
        this.next = null;
    }

    public static void print(DoublyListNode head) {
        DoublyListNode curr = head;
        while (curr != null) {
            System.out.print(curr.val + "->");
            curr = curr.next;
        }
        System.out.println("null");
        DoublyListNode curr2 = head;
        while (curr2.next != null) {
            curr2 = curr2.next;
        }
        while (curr2 != null) {
            System.out.print(curr2.val + "->");
            curr2 = curr2.prev;
        }
        System.out.println("null");
    }

    public static DoublyListNode addnew(DoublyListNode head, int val) {
        DoublyListNode newNode = new DoublyListNode(val);
        newNode.next = head;
        head.prev = newNode;
        return newNode;
    }

    public static void printCircular(DoublyListNode head) {
        if (head == null)
            return;
        System.out.print(head.val + "->");
        DoublyListNode curr = head.next;
        while (curr != head) {
            System.out.print(curr.val + "->");
            curr = curr.next;
        }
        System.out.println("(back to head)");
    }

    public static void main(String[] args) {
        DoublyListNode node1 = new DoublyListNode(1);
        DoublyListNode node2 = new DoublyListNode(2);
        DoublyListNode node3 = new DoublyListNode(3);

        node1.next = node2;
        node2.prev = node1;
        node2.next = node3;
        node3.prev = node2;
        node3.next = node1;
        // print(node1);
        // node1 = addnew(node1, 7);
        // print(node1);
        printCircular(node1);
    }
}
