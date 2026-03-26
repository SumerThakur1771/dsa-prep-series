# Week 1 Log (March 25 – March 31, 2026)

**Theme:** Setup + Linked Lists + Arrays LeetCode

---

## Day 1 — March 25

**Topics Covered:** Linked Lists intro

**What I learned:**
- What a node is — a box with `val` and `next`
- How to connect nodes using `.next`
- Traversal using a `curr` pointer instead of moving head
- addFront — O(1), connect new node first THEN update head
- addEnd — O(n), walk until `curr.next == null` then connect
- remove — stop at node BEFORE target, do `curr.next = curr.next.next`

**Code written:** `ListNode.java` with traversal, addFront, addEnd, remove

**Key insight today:** Never move `head` directly during traversal — always use a separate `curr` pointer or you lose the entire list.

**Struggles:** Understanding why order matters in addFront — solved by drawing it out visually step by step.

---

## Day 2 — March 26
*(to be filled)*

---

## Day 3 — March 27
*(to be filled)*

---

## Day 4 — March 28
*(to be filled)*

---

## Day 5 — March 29
*(to be filled)*

---

## Day 6 — March 30 (Saturday)
*(to be filled)*

---

## Day 7 — March 31 (Sunday)
*(to be filled)*

---

## 📊 Problems Solved This Week

| Problem | Difficulty | Time Taken | Attempts | Key Takeaway |
|---------|------------|------------|----------|--------------|
| | | | | |

**Total: 0 / 5**

---

## 🎯 Next Session (Day 2 — March 26)
- LL operations: reverse LL, Nth from end
- LeetCode: #1 Two Sum, #121 Best Time to Buy and Sell Stock