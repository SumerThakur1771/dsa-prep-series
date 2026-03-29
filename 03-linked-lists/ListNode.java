class ListNode{
    int val;
    ListNode next;

    ListNode(int val){
        this.val = val;
        this.next = null;
    }

    static void print(ListNode head){
        ListNode curr = head;
        while(curr != null){
            System.out.print(curr.val + "->");
            curr = curr.next;
        }
        System.out.println("null");
    }

    static ListNode addFront(ListNode head, int val){
        ListNode newNode = new ListNode(val);
        newNode.next = head;
        return newNode;
    }

    static ListNode addLast(ListNode head, int val){
        ListNode newNode = new ListNode(val);
        if(head == null){
            return newNode;
        }
        ListNode curr = head;
        while(curr.next!=null){
            curr = curr.next;
        }
        curr.next = newNode;

        return head;
    }

    static ListNode remove(ListNode head, int val){
        if (head == null) return null;
        if(head.val == val){
            return head.next;
        }
        ListNode curr = head;
        while(curr.next != null){
            if(curr.next.val == val){
                curr.next = curr.next.next;
                return head;
            }
            curr = curr.next;
        }
        return head;
    }

    static ListNode reverse(ListNode head){
        ListNode prev = null;
        ListNode curr = head;
        while(curr !=null){
            ListNode next = curr.next;
            curr.next = prev;
            prev = curr;
            curr = next;
        }
        return prev;
    }

    static ListNode nthFromEnd(ListNode head, int n){
        ListNode fast = head;
        ListNode slow = head;

        for(int i = 0; i < n; i++){
            fast = fast.next;
        }

        while(fast != null){
            fast = fast.next;
            slow = slow.next;
        }
        return slow;
    }

public static void main(String[] args) {
    ListNode node1 = new ListNode(1);
    ListNode node2 = new ListNode(2);
    ListNode node3 = new ListNode(3);

    node1.next = node2;
    node2.next = node3;

    System.out.println(node1.val);
    System.out.println(node1.next.val);
    
    print(node1);

    node1 = addFront(node1, 0);
    print(node1);
    node1 = addLast(node1,4);
    print(node1);
    node1 = remove(node1,2);
    print(node1);
    node1 = reverse(node1);
    print(node1);

    ListNode nthNode = nthFromEnd(node1, 2);
    System.out.println("nth node from end is "+ nthNode.val);

}
}

