# Linked Lists

> **One-liner:** A chain of nodes where each node holds a value and points to the next node — like a treasure hunt where each clue tells you where the next one is.

---

## 📌 Key Concepts

- **Node:** A box with two compartments — `val` (the data) and `next` (arrow to next node)
- **Head:** The first node — your entry point into the list. Never lose this.
- **Tail:** The last node — its `next` points to `null`
- **No random access:** You can't do `list[2]` like arrays. You have to walk from head every time.

---

## ⏱️ Time & Space Complexity

| Operation | Time | Space | Notes |
|-----------|------|-------|-------|
| Access by index | O(n) | O(1) | Must traverse from head |
| Search | O(n) | O(1) | Linear scan |
| Insert at front | O(1) | O(1) | Just rewire pointers |
| Insert at end | O(n) | O(1) | Must walk to tail first |
| Delete at front | O(1) | O(1) | Return head.next |
| Delete by value | O(n) | O(1) | Traverse to find node |

---

## 💻 Core Implementation

```java
class ListNode {
    int val;
    ListNode next;

    ListNode(int val) {
        this.val = val;
        this.next = null;
    }
}
```

**What this means:**
- `ListNode` is a custom type (class) I defined — just like `int` or `String` but made by me
- `val` stores the data
- `next` stores the reference to the next node
- Constructor sets `val` and defaults `next` to null

---

## 🔍 Operations

### Traversal
Use a `curr` pointer to walk — never move `head` directly or you lose the list.

```java
static void print(ListNode head) {
    ListNode curr = head;
    while (curr != null) {
        System.out.print(curr.val + " -> ");
        curr = curr.next;
    }
    System.out.println("null");
}
```

---

### addFront — O(1)
**Key insight:** Connect new node to head FIRST, then update head. Never the other way — you'll lose the list.

```java
static ListNode addFront(ListNode head, int val) {
    ListNode newNode = new ListNode(val);
    newNode.next = head;  // connect first
    return newNode;       // new node is new head
}
```

---

### addEnd — O(n)
**Key insight:** Walk until `curr.next == null` — that's your last node. Then connect.

```java
static ListNode addEnd(ListNode head, int val) {
    ListNode newNode = new ListNode(val);
    if (head == null) return newNode;
    ListNode curr = head;
    while (curr.next != null) {
        curr = curr.next;
    }
    curr.next = newNode;
    return head;  // head didn't change
}
```

---

### remove — O(n)
**Key insight:** Stop at the node BEFORE the target. Then do `curr.next = curr.next.next` to skip over it.

```java
static ListNode remove(ListNode head, int val) {
    if (head == null) return null;
    if (head.val == val) return head.next;  // edge case: removing head
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
```

---

### Reverse — O(n)
**Key insight:** 3 pointers — prev, curr, next. Save next BEFORE reversing pointer. Return prev not head.

```java
static ListNode reverse(ListNode head) {
    ListNode prev = null;
    ListNode curr = head;
    while (curr != null) {
        ListNode next = curr.next;  // save next FIRST
        curr.next = prev;           // reverse pointer
        prev = curr;                // move prev forward
        curr = next;                // move curr forward
    }
    return prev;
}
```

**My doubt:** Why return prev not head?
**Answer:** After loop, head is still at the original first node which is now the TAIL. prev has walked to the new head. Returning head gives you just the tail.

**My doubt:** Why does order of prev=curr and curr=next matter?
**Answer:** If you do curr=next first, then prev=curr would point to already-moved curr. Always prev=curr first, then curr=next. Wrong order = null output.

---

### Nth from End — O(n)
**Key insight:** Move fast N steps ahead, then move both together. When fast hits null, slow is N steps from end.

```java
static ListNode nthFromEnd(ListNode head, int n) {
    ListNode fast = head, slow = head;
    for (int i = 0; i < n; i++) fast = fast.next;
    while (fast != null) {
        fast = fast.next;
        slow = slow.next;
    }
    return slow;
}
```

**Why it works:** Fast is always N steps ahead. When fast falls off end, slow is N behind end. Gap never changes as they walk.

---

### Floyd's Cycle Detection — O(n)
**Key insight:** slow moves 1 step, fast moves 2 steps. Meet → cycle. fast hits null → no cycle.

```java
static boolean floydCycle(ListNode head) {
    ListNode slow = head, fast = head;
    while (fast != null && fast.next != null) {
        fast = fast.next.next;
        slow = slow.next;
        if (slow == fast) return true;
    }
    return false;
}
```

**My doubt:** Why && not || in while condition?
**Answer:** You do fast.next.next inside loop. If fast.next is null and you try fast.next.next → NullPointerException. Need BOTH to be non-null before entering.

**My doubt:** Why slow == fast (references) not slow.val == fast.val?
**Answer:** Two different nodes can have same value. Want to check same node in memory, not same value.

**My doubt:** Can fast skip over slow and never meet?
**Answer:** No. Gap reduces by 1 every step → always reaches 0. Works because fast moves exactly 2 steps (not 3 — that could skip).

---

### Remove Cycle — O(n)
**Key insight:** Detect → reset slow to head → move both 1 step → meet at entry point → walk from entry to find last node → set last.next = null.

```java
static ListNode removeCycle(ListNode head) {
    ListNode slow = head, fast = head;

    while (fast != null && fast.next != null) {
        fast = fast.next.next;
        slow = slow.next;
        if (slow == fast) { slow = head; break; }
    }

    if (fast == null || fast.next == null) return head;

    while (slow != fast) {
        slow = slow.next;
        fast = fast.next;
    }

    ListNode curr = slow;
    while (curr.next != slow) curr = curr.next;
    curr.next = null;
    return head;
}
```

**My doubt:** Do slow and fast meet AT cycle entry after detection?
**Answer:** No! They meet somewhere INSIDE cycle. Phase 2 (reset slow to head, move both 1 step) finds actual entry point.

**My doubt:** Why curr.next = null and not entry.next = null?
**Answer:** entry.next = null cuts the list short. Need last node of cycle (one pointing BACK to entry) and cut that connection.

---

### Palindrome Linked List — O(n)
**Key insight:** Find middle → reverse second half → compare both halves.

```java
static boolean isPalindrome(ListNode head) {
    ListNode slow = head, fast = head;
    while (fast != null && fast.next != null) {
        slow = slow.next;
        fast = fast.next.next;
    }
    ListNode prev = null, curr = slow;
    while (curr != null) {
        ListNode next = curr.next;
        curr.next = prev;
        prev = curr;
        curr = next;
    }
    ListNode left = head, right = prev;
    while (right != null) {
        if (left.val != right.val) return false;
        left = left.next;
        right = right.next;
    }
    return true;
}
```

**My doubt:** Compare references or values here?
**Answer:** VALUES — left.val != right.val. You're checking if corresponding nodes have same value, not if they're the same node.

---

### Merge Sort — O(n log n)
**Key insight:** Split using slow/fast, recursively sort each half, merge with dummy head. Start fast at head.next for correct split.

```java
static ListNode mergeSort(ListNode head) {
    if (head == null || head.next == null) return head;
    ListNode slow = head, fast = head.next;  // fast starts at head.next!
    while (fast != null && fast.next != null) {
        slow = slow.next;
        fast = fast.next.next;
    }
    ListNode secondHalf = slow.next;
    slow.next = null;
    ListNode left = mergeSort(head);
    ListNode right = mergeSort(secondHalf);
    return merge(left, right);
}

static ListNode merge(ListNode left, ListNode right) {
    ListNode dummy = new ListNode(0);
    ListNode curr = dummy;
    while (left != null && right != null) {
        if (left.val < right.val) { curr.next = left; left = left.next; }
        else { curr.next = right; right = right.next; }
        curr = curr.next;
    }
    if (left != null) curr.next = left;
    if (right != null) curr.next = right;
    return dummy.next;
}
```

**My doubt:** Why fast = head.next not fast = head?
**Answer:** With fast=head on 2-node list, slow ends at second node → first half = full list → infinite recursion. fast=head.next ensures slow stops at END of first half giving even split.

**My doubt:** What is dummy = new ListNode(0)?
**Answer:** Fake temporary node so curr has a starting point. Without it you need special case for first node. With dummy every node attaches same way. Return dummy.next = real first node. Value 0 is just placeholder.

**My doubt:** Why no while loop for remaining nodes at end of merge?
**Answer:** Remaining nodes are already sorted AND connected to each other. Attaching head of remaining chain brings all nodes at once — no need to attach one by one.

---

## ⚠️ Common Mistakes

- ❌ Moving `head` during traversal — always use `curr`
- ❌ Wrong order in reverse — prev=curr must come BEFORE curr=next
- ❌ Using `||` instead of `&&` in Floyd's — crashes on lists without cycle
- ❌ Comparing `slow.val == fast.val` in Floyd's — use references `slow == fast`
- ❌ Starting `fast = head` in merge sort split — infinite recursion on 2-node lists
- ❌ Not handling empty list or single node

---

## 💡 Interview Tips

- ✅ Always draw the list with arrows before coding
- ✅ State null checks out loud — interviewers notice
- ✅ Test with: empty list, single node, two nodes, cycle at tail
- ✅ When stuck — can slow/fast pointers help?
- ✅ Dummy head simplifies any merge/insert problem

---

## 📎 LeetCode Problems

| # | Problem | Difficulty | Pattern | Status |
|---|---------|------------|---------|--------|
| 206 | Reverse Linked List | Easy | Iterative | ⬜ |
| 21 | Merge Two Sorted Lists | Easy | Two Pointers + Dummy | ⬜ |
| 141 | Linked List Cycle | Easy | Fast/Slow | ⬜ |
| 19 | Remove Nth Node From End | Medium | Two Pointers Gap | ⬜ |
| 143 | Reorder List | Medium | Fast/Slow + Reverse | ⬜ |
| 23 | Merge K Sorted Lists | Hard | Heap | ⬜ |

---

## 🔗 Related Topics

- **Arrays** — LL solves dynamic insert/delete that arrays can't do cheaply
- **Stacks/Queues** — often implemented using linked lists
- **Trees** — same idea of nodes with pointers, just with two children instead of one