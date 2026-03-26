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
            curr.next = curr.next.next;     // skip over target
            return head;
        }
        curr = curr.next;
    }
    return head;
}
```

---

## ⚠️ Common Mistakes

- ❌ Moving `head` during traversal — always use `curr` instead
- ❌ Updating head before connecting in addFront — you'll lose the original list
- ❌ Not handling empty list (`head == null`)
- ❌ Stopping AT the node to remove — always stop at the node BEFORE it

---

## 💡 Interview Tips

- ✅ Always draw the list with arrows before coding
- ✅ State null checks out loud — interviewers notice this
- ✅ Always test with: empty list, single node, removing the head

---

## 📎 LeetCode Problems

| # | Problem | Difficulty | Pattern | Status |
|---|---------|------------|---------|--------|
| 206 | Reverse Linked List | Easy | Iterative | ⬜ |
| 21 | Merge Two Sorted Lists | Easy | Two Pointers | ⬜ |
| 141 | Linked List Cycle | Easy | Fast/Slow | ⬜ |
| 19 | Remove Nth Node From End | Medium | Two Pointers | ⬜ |
| 143 | Reorder List | Medium | Fast/Slow + Reverse | ⬜ |
| 23 | Merge K Sorted Lists | Hard | Heap | ⬜ |

---

## 🔗 Related Topics

- **Arrays** — LL solves dynamic insert/delete that arrays can't do cheaply
- **Stacks/Queues** — often implemented using linked lists
- **Trees** — same idea of nodes with pointers, just with two children instead of one