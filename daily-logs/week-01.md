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

**Topics Covered:** Reverse LL, Nth from end, Two Sum, Best Time to Buy and Sell Stock

**What I learned:**
- Reverse LL — 3 pointers (prev, curr, next). Save next BEFORE reversing pointer. Return prev not head because head stays at old position.
- Nth from end — two pointer gap technique. Move fast N steps ahead, then move both together. When fast hits null, slow is at Nth from end.
- Two Sum — HashMap to store complement. Check BEFORE putting in map otherwise same element matches itself.
- Best Time to Buy and Sell Stock — track minimum so far as you walk. Calculate profit at every step. One pass O(n).

**Code written:** Reverse LL and Nth from end added to `ListNode.java`

**Key insight today:** Order of pointer updates always matters in linked list problems — wrong order gives null or wrong output. Debugged reverse LL pointer order bug myself.

**Struggles:** Understanding why we check complement BEFORE putting in HashMap for Two Sum — solved by tracing through `nums=[3,3]` edge case.

---

## Day 3 — March 27

**Topics Covered:** Floyd's Cycle Detection, Container With Most Water

**What I learned:**
- Floyd's Cycle Detection — slow moves 1 step, fast moves 2 steps. If they meet → cycle exists. Use `&&` not `||` in while condition to avoid NullPointerException on lists without cycle. Compare references `slow == fast` not values.
- Container With Most Water — two pointers from both ends. Always move the shorter wall inward because taller wall can never improve area. Use `while(i < j)` not `i < length`.

**Code written:** `floydCycle` added to `ListNode.java`

**Key insight today:** Fast pointer needs BOTH `fast != null` AND `fast.next != null` before doing `fast.next.next` — use `&&` to check both or you get NullPointerException on lists without a cycle.

**Struggles:** Understanding why we always move the shorter pointer in Container With Most Water — clicked after tracing through the example step by step.

---

## Day 4 — March 28

**Topics Covered:** Remove Cycle, Palindrome Linked List, 3Sum

**What I learned:**
- Remove Cycle — after Floyd's detection, reset slow to head, move both one step → they meet at cycle entry point. Then walk from entry until node whose next == entry, set next = null.
- Palindrome LL — find middle with slow/fast, reverse second half, compare both halves node by node. Compare values not references (`slow.val != fast.val`).
- 3Sum — sort first, fix i in outer loop, two pointers j and k inside. Skip duplicates for i using `nums[i] == nums[i-1]`. Skip duplicates for j and k AFTER adding triplet, then do extra j++ and k-- to move past last duplicate.

**Code written:** `removeCycle` and `isPalindrome` added to `ListNode.java`

**Key insight today:** Duplicate skip in 3Sum must happen AFTER adding triplet — skipping before misses valid combinations. Extra j++ and k-- needed after while loops because loops stop AT last duplicate not past it.

**Struggles:** Understanding why Floyd's entry point trick works mathematically — accepted it as a pattern for now, proof involves modular arithmetic.

---

## Day 5 — March 29

**Topics Covered:** Valid Palindrome (string)

**What I learned:**
- Valid Palindrome — two pointers, skip non-alphanumeric with inner while loops, lowercase both chars before comparing.
- Always add `i < j` guard inside inner while loops — without it, pointer goes out of bounds on strings with no alphanumeric chars.
- `Character.toLowerCase()` returns a value — must store it, doesn't modify in place.

**Code written:** `ValidPalindrome.java` in `02-strings/problems/easy/`

**Key insight today:** Nested while loops don't automatically mean O(n²). Since i and j never restart — total moves across all loops = n. O(n²) only happens when inner loop restarts from scratch each outer iteration.

**Struggles:** Runtime error from missing `i < j` guard in inner while — fixed by adding bounds check to both inner loops.

---

## Day 6 — March 30 (Saturday)

**Topics Covered:** Merge Sort on Linked Lists + Mixed revision

**What I learned:**
- Merge Sort on LL — same idea as array merge sort but use slow/fast pointers to find middle and split. Start fast at `head.next` not `head` to get even split.
- Dummy head in merge — fake node gives curr a starting point so every node including first attaches the same way. Return dummy.next.
- Remaining nodes in merge — attach entire remaining chain with one if statement, no while loop needed because nodes are already connected.
- Updated `03-linked-lists/README.md` with all Week 1 concepts and doubts.

**Code written:** `mergeSort` and `merge` added to `ListNode.java`

**Key insight today:** Starting fast at `head.next` instead of `head` prevents infinite recursion on 2-node lists — ensures slow stops at END of first half giving even split every time.

**Struggles:** File naming error — Java requires filename to exactly match class name (case sensitive). `listNode.java` vs `ListNode.java` caused NoClassDefFoundError.

---

## Day 7 — March 31 (Sunday)

**Topics Covered:** Week 1 revision

**What I did:**
- Revised all Linked List operations from README
- Re-solved #15 3Sum from scratch — got it completely on my own ✅
- Re-solved #11 Container With Most Water from scratch — got it on my own ✅
- Code was cleaner on second attempt — combined area calculation with pointer movement

**Key insight today:** Revision works. Second attempt at both problems was faster and cleaner than first attempt. Pattern recognition is building.

---

## 📊 Problems Solved This Week

| Problem | Difficulty | Time Taken | Attempts | Key Takeaway |
|---------|------------|------------|----------|--------------|
| #1 Two Sum | Easy | 20 min | 3 | Check before put in HashMap |
| #121 Best Time to Buy & Sell | Easy | 15 min | 3 | Track min so far in one pass |
| #11 Container With Most Water | Medium | 20 min | 2 | Move shorter pointer inward |
| #15 3Sum | Medium | 30 min | 3 | Sort + fix one + two pointers, skip dupes after adding |
| #125 Valid Palindrome | Easy | 20 min | 3 | Skip non-alphanumeric with inner while + i<j guard |

**Total: 5 / 5** ✅

---

## 🏆 Week 1 Summary

- **Concepts learned:** Node, traversal, addFront, addEnd, remove, reverse, Nth from end, Floyd's cycle detection, remove cycle, palindrome LL, merge sort on LL
- **Problems solved:** 5/5 Blind 75 problems
- **Biggest win:** Debugged reverse LL pointer order bug independently. Re-solved 3Sum and Container With Most Water from scratch on Day 7.
- **Patterns learned:** Two pointers, fast/slow pointers, HashMap lookup, track min/max so far

---

## 🎯 Week 2 Plan (April 1–7)

```
Day 1 → Doubly LL, Circular LL | #3 Longest Substring Without Repeating
Day 2 → Stack intro | #49 Group Anagrams
Day 3 → Stack problems (NGE, Stock Span) | #206 Reverse LL
Day 4 → Valid Parentheses, Duplicate Parens | #21 Merge Two Sorted Lists
Day 5 → Max Area Histogram | #141 Linked List Cycle
Day 6 → Queue intro + implementations | Revision
Day 7 → Week 2 revision + notes
```