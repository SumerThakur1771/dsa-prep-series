# Week 2 Log (April 1 – April 7, 2026)

**Theme:** Linked Lists cont. + Stacks + Strings LeetCode

---

## Day 1 — April 1

**Topics Covered:** Doubly Linked List, Circular Linked List, Longest Substring Without Repeating Characters

**What I learned:**
- Doubly LL — node has prev + val + next. Must set BOTH next and prev when connecting nodes. Can traverse forward and backward.
- Circular LL — last node points back to head. To print without infinite loop, stop when curr == head. Used in round robin scheduling, music player loop.
- Longest Substring — sliding window + HashMap. Store char → last seen index. Only treat as repeat if previous index >= left (inside current window). maxLength updates every step not just on repeat.

**Code written:** `DoublyListNode.java` with forward/backward print, addFront, circular print

**Key insight today:** `map.get(char) >= left` check is critical — a character might be in the map from a previous window. If its index is less than left, it's outside current window and not a real repeat.

**Struggles:** Understanding why >= left needed — clicked after dry running "tmmzuxt" example where first 't' was outside current window.

---

## Day 2 — April 2

**Topics Covered:** Stack intro, ArrayList intro, Group Anagrams

**What I learned:**
- Stack — LIFO (Last In First Out). push adds to top, pop removes from top, peek looks at top. Built-in Java Stack class available.
- ArrayList — dynamic array. Grows/shrinks automatically. add(), get(), size(), remove() are main methods. Inside <> always use wrapper class (Integer not int, String is always String).
- Group Anagrams — sort each string to get key. All anagrams produce same sorted string. Use HashMap<String, List<String>> to group. putIfAbsent creates empty list if key new. Add ORIGINAL string not sorted version.
- Wrapper classes — inside <> use Integer not int, Character not char. Java autoboxes between them automatically.

**Code written:** `StackImpl.java` in `04-stacks-queues/`

**Key insight today:** HashMap stores whatever YOU put as value — not always an index. In Two Sum value=index, in Group Anagrams value=ArrayList of strings. map.get() just returns whatever was stored.

**Struggles:** Confusion between primitives and wrapper classes — rule: always use capital version inside <>. Confusion about map.get().add() — map.get() returns the ArrayList stored at that key, then .add() adds to it.

---

## Day 3 — April 3

**Topics Covered:** NGE (Next Greater Element), Stock Span, Reverse Linked List

**What I learned:**
- NGE — process right to left. Stack stores VALUES. Pop elements smaller than current (they can never be NGE). Stack top after popping = NGE. If stack empty = -1.
- Stock Span — process left to right. Stack stores INDICES not values. Pop indices where price <= current. If stack empty → span = i+1. If not → span = i - stack.peek(). Push current index.
- Reverse Linked List — 3 pointers (prev, curr, next). Save next BEFORE reversing. Order: save next → reverse pointer → move prev → move curr. Return prev not head.

**Code written:** NGE and Stock Span in `04-stacks-queues/README.md`

**Key insight today:** Stock Span uses index subtraction instead of counting one by one — everything between blocker index and current index is automatically smaller. Index difference gives span directly without visiting each element.

**Struggles:** Coming back after a week break made everything feel harder. NGE and Stock Span stack logic was confusing — clicked after tracing step by step.

---

## Day 4 — April 4

**Topics Covered:** Valid Parentheses, Duplicate Parentheses, Merge Two Sorted Lists

**What I learned:**
- Valid Parentheses — push opening brackets. On closing bracket, pop and check match. Return stack.isEmpty() at end. If stack top doesn't match closing bracket → always invalid (brackets are crossed).
- Duplicate Parentheses — push everything except ')'. On ')' — pop until '(' and count. If count==0 → duplicate brackets (nothing meaningful between them). Brackets themselves don't count because inner brackets were already processed.
- Merge Two Sorted Lists — dummy head + two pointers. Compare vals, attach smaller, move pointer. Attach remaining with if (not while). Return dummy.next.

**Code written:** Valid Parentheses and Duplicate Parentheses added to `04-stacks-queues/README.md`

**Key insight today:** Speaking out your approach before coding helps organize thoughts. Remembered merge logic from Week 1 Merge Sort — brain making connections across topics naturally.

**Struggles:** Understanding Duplicate Parentheses — why count==0 means duplicate. Clicked after tracing "((a+b))" step by step and seeing inner brackets get popped first leaving nothing for outer.

---

## Day 5 — April 5

**Topics Covered:** Max Area Histogram, Linked List Cycle

**What I learned:**
- Max Area Histogram — height of rectangle = shortest bar in range (limiting factor). Brute force O(n²): fix start with outer loop, expand right with inner loop, track minHeight as you go.
- Stack approach O(n) — for each bar find left smaller and right smaller using two separate stack passes. Width = right[i] - left[i] - 1. No left boundary → -1, no right boundary → n (virtual boundaries outside array).
- Linked List Cycle — Floyd's algorithm: slow moves 1 step, fast moves 2 steps. If they meet → cycle. && not || in while condition.

**Code written:** Max Area Histogram added to `04-stacks-queues/README.md`, `LinkedListCycle.java`

**Key insight today:** -1 and n are virtual boundaries one step OUTSIDE the array. Makes width formula work correctly for bars that extend to the edges of the histogram.

**Struggles:** Max Area Histogram stack approach initially confusing — understood after watching YouTube video and doing full dry run with code in parallel. Hard level problem.

---

## Day 6 — April 6 (Saturday)

**Topics Covered:** Queue intro + Week 2 revision

**What I learned:**
- Queue — FIFO (First In First Out). Add to BACK (enqueue), remove from FRONT (dequeue). In Java: `Queue<Integer> queue = new LinkedList<>()`. Methods: add(), poll(), peek(), isEmpty().
- Queue vs Stack: Stack=LIFO (plates), Queue=FIFO (coffee line)

**Week 2 Revision Results:**
- ✅ Doubly LL — connect first then update head, prev+val+next
- ✅ Circular LL — stop when curr == head
- ✅ Longest Substring — HashMap char→index, >= left check
- ✅ Group Anagrams — sorted string as key, original as value
- ✅ NGE — right to left, pop smaller, stack top = NGE
- ✅ Stock Span — left to right, stack of indices, index subtraction
- ✅ Valid Parentheses — pop and match, return stack.isEmpty()
- ✅ Merge Two Sorted Lists — dummy head, if not while for remaining
- ✅ Linked List Cycle — slow/fast, meet = cycle
- ✅ Max Area Histogram — remembered full optimal approach independently!

**Key insight today:** Max Area Histogram optimal approach fully clicked — for each bar find left smaller and right smaller, width = right - left - 1, default boundaries are -1 and n.

---

## Day 7 — April 7 (Sunday)
*(to be filled)*

---

## 📊 Problems Solved This Week

| Problem | Difficulty | Time Taken | Attempts | Key Takeaway |
|---------|------------|------------|----------|--------------|
| #3 Longest Substring Without Repeating | Medium | 25 min | 3 | Sliding window + >= left check |
| #49 Group Anagrams | Medium | 30 min | 2 | Sort each string as key, add original to list |
| #206 Reverse Linked List | Easy | 15 min | 2 | Save next before reversing, return prev not head |
| #21 Merge Two Sorted Lists | Easy | 20 min | 1 | Dummy head + if not while for remaining |
| #141 Linked List Cycle | Easy | 10 min | 1 | Floyd's — slow/fast pointers, && not || |

**Total: 5 / 5** ✅

---

## 🎯 Next Session (Day 7 — April 7)
- Week 2 full revision + notes cleanup
- Plan Week 3