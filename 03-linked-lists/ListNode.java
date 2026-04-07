class ListNode {
    int val;
    ListNode next;

    ListNode(int val) {
        this.val = val;
        this.next = null;
    }

    static void print(ListNode head) {
        ListNode curr = head;
        while (curr != null) {
            System.out.print(curr.val + "->");
            curr = curr.next;
        }
        System.out.println("null");
    }

    static ListNode addFront(ListNode head, int val) {
        ListNode newNode = new ListNode(val);
        newNode.next = head;
        return newNode;
    }

    static ListNode addLast(ListNode head, int val) {
        ListNode newNode = new ListNode(val);
        if (head == null) {
            return newNode;
        }
        ListNode curr = head;
        while (curr.next != null) {
            curr = curr.next;
        }
        curr.next = newNode;

        return head;
    }

    static ListNode remove(ListNode head, int val) {
        if (head == null)
            return null;
        if (head.val == val) {
            return head.next;
        }
        ListNode curr = head;
        while (curr.next != null) {
            if (curr.next.val == val) {
                curr.next = curr.next.next;
                return head;
            }
            curr = curr.next;
        }
        return head;
    }

    static ListNode reverse(ListNode head) {
        ListNode prev = null;
        ListNode curr = head;
        while (curr != null) {
            ListNode next = curr.next;
            curr.next = prev;
            prev = curr;
            curr = next;
        }
        return prev;
    }

    static ListNode nthFromEnd(ListNode head, int n) {
        ListNode fast = head;
        ListNode slow = head;

        for (int i = 0; i < n; i++) {
            fast = fast.next;
        }

        while (fast != null) {
            fast = fast.next;
            slow = slow.next;
        }
        return slow;
    }

    static boolean floydCycle(ListNode head) {
        ListNode slow = head;
        ListNode fast = head;
        while (fast != null && fast.next != null) {
            fast = fast.next.next;
            slow = slow.next;
            if (slow == fast) {
                return true;
            }
        }
        return false;
    }

    static ListNode removeFloyde(ListNode head) {
        ListNode slow = head;
        ListNode fast = head;
        while (fast != null && fast.next != null) {
            fast = fast.next.next;
            slow = slow.next;
            if (slow == fast) {
                slow = head;
                break;
            }
        }

        if (fast == null || fast.next == null) {
            return head;
        }

        while (slow != fast) {
            slow = slow.next;
            fast = fast.next;
        }
        if (slow == fast) {
            ListNode curr = slow;
            while (curr.next != slow) {
                curr = curr.next;
            }
            curr.next = null;
        }
        return head;
    }

    static boolean isPalindrom(ListNode head) {
        ListNode slow = head;
        ListNode fast = head;
        while (fast != null && fast.next != null) {
            slow = slow.next;
            fast = fast.next.next;
        }
        fast = slow;
        slow = head;
        fast = reverse(fast);
        boolean isPalindrom = true;
        while (fast != null) {
            if (slow.val != fast.val) {
                isPalindrom = false;
            }
            slow = slow.next;
            fast = fast.next;
        }
        return isPalindrom;
    }

    static ListNode MergeSort(ListNode head) {
        if (head == null || head.next == null) {
            return head;
        }
        ListNode slow = head;
        ListNode fast = head.next;
        while (fast != null && fast.next != null) {
            slow = slow.next;
            fast = fast.next.next;
        }
        ListNode secondHalf = slow.next;
        slow.next = null;
        ListNode Left = MergeSort(head);
        ListNode Right = MergeSort(secondHalf);
        return merge(Left, Right);

    }

    static ListNode merge(ListNode left, ListNode right) {
        ListNode dummy = new ListNode(0);
        ListNode curr = dummy;
        while (left != null && right != null) {
            if (left.val < right.val) {
                curr.next = left;
                left = left.next;
            } else {
                curr.next = right;
                right = right.next;
            }
            curr = curr.next;
        }
        if (right != null)
            curr.next = right;
        if (left != null)
            curr.next = left;
        return dummy.next;
    }

    public static void main(String[] args) {
        ListNode node1 = new ListNode(4);
        ListNode node2 = new ListNode(2);
        ListNode node3 = new ListNode(1);
        ListNode node4 = new ListNode(3);

        node1.next = node2;
        node2.next = node3;
        node3.next = node4;

        // System.out.println(node1.val);
        // System.out.println(node1.next.val);

        // print(node1);

        // node1 = addFront(node1, 0);
        // print(node1);
        // node1 = addLast(node1,4);
        // print(node1);
        // node1 = remove(node1,2);
        // print(node1);
        // node1 = reverse(node1);
        // print(node1);

        // ListNode nthNode = nthFromEnd(node1, 2);
        // System.out.println("nth node from end is "+ nthNode.val);
        // boolean isFloyd = floydCycle(node1);
        // System.out.println(isFloyd);
        // node1 = removeFloyde(node1);
        // print(node1);
        // System.out.println(isPalindrom(node1));
        node1 = MergeSort(node1);
        print(node1);
    }
}
