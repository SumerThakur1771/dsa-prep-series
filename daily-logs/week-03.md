# Week 3 Log (April 7 – April 13, 2026)

**Theme:** Queues + Binary Trees

---

## Day 1 — April 7

**Topics Covered:** Queue using Stacks, Circular Queue, Valid Parentheses (#20)

**What I learned:**
- Queue using Two Stacks — Stack1=inbox, Stack2=outbox. Pour stack1 into stack2 ONLY when stack2 is empty. Pouring reverses order → LIFO becomes FIFO.
- Circular Queue — fixed size array with front/rear pointers. Modulo wraps index back to start. Track size separately to distinguish full vs empty.
- Valid Parentheses — already solved in Week 2, just submitted existing solution.

**Code written:** `CircularQueue.java`, `MyQueue.java` in `04-stacks-queues/`

**Key insight today:** Pouring stack1 into stack2 reverses order — stack1=[1,2,3] (3 on top) becomes stack2=[3,2,1] (1 on top). Turns LIFO into FIFO naturally!

**Struggles:** Constructor concept still somewhat fuzzy — will click better after OOP revision.

---

## Day 2 — April 8

**Topics Covered:** Deque, First Non-Repeating Character in Stream, Min Stack (#155)

**What I learned:**
- Deque — Double Ended Queue. Add/remove from BOTH ends. Use ArrayDeque in Java. Methods: addFirst, addLast, removeFirst, removeLast, peekFirst, peekLast.
- First Non-Repeating — HashMap for frequency + Deque for order. Add to back, remove from front LAZILY (only when front has count>1). Use while not if.
- Min Stack — two stacks. stack2 always stores current minimum. Push min(val, stack2.peek()) to stack2. Pop both stacks together to stay in sync.

**Code written:** First Non-Repeating in `04-stacks-queues/README.md`, `MinStack.java`

**Key insight today:** In Min Stack, stack2 stores historical minimums — when you pop, previous minimum is automatically restored.

**Struggles:** Understanding why repeated chars in middle of deque don't need immediate removal — lazy cleanup works because we only care about front.

---

## Day 3 — April 9

**Topics Covered:** Binary Trees intro + Traversals, Daily Temperatures (#739)

**What I learned:**
- Binary Tree — each node has val, left, right. Root = topmost node. Leaf = both children null.
- Inorder: left → root → right | Preorder: root → left → right | Postorder: left → right → root
- All traversals use recursion with base case `if (root == null) return`
- Daily Temperatures — NGE pattern with indices. Answer = stack.peek() - i.

**Code written:** `TreeNode.java` in `05-trees/` with all three traversals

**Key insight today:** Recursion goes ALL THE WAY DOWN to null before printing. Like unwinding a spring!

**Struggles:** Understanding why Daily Temperatures uses stack.peek() - i not i - stack.peek().

---

## Day 4 — April 10

**Topics Covered:** Level Order Traversal, Height of Tree, Count Nodes, Remove Nth Node (#19)

**What I learned:**
- Level Order — uses Queue (FIFO). Dequeue node → print → enqueue left and right children.
- Height — recursion: `1 + max(height(left), height(right))`. Base case: null = 0.
- Count Nodes — recursion: `1 + count(left) + count(right)`. Same pattern as height but adds.
- Remove Nth From End — two pointer gap. Edge case: fast==null means remove head.

**Code written:** Level Order, Height, Count Nodes added to `TreeNode.java`

**Key insight today:** Height and Count Nodes have same recursion structure — `1 + operation(left, right)`. Height takes max, Count adds.

**Struggles:** Remove Nth — confused about while(fast!=null) vs while(fast.next!=null).

---

## Day 5 — April 11

**Topics Covered:** Diameter of Binary Tree, Left View, Right View, Max Depth (#104)

**What I learned:**
- Diameter — longest path between any two nodes (edges). For each node: diameter = height(left) + height(right). Track max using class-level variable updated as side effect of height().
- Left View — first node at each level. Use level order with size trick. `if (i == 0)` prints first node.
- Right View — last node at each level. `if (i == size-1)` prints last node.
- Max Depth (#104) — same as height! `1 + max(left, right)`.
- Key difference: Height counts NODES (+1), Diameter counts EDGES (no +1).

**Code written:** Diameter, Left View, Right View added to `TreeNode.java` in `05-trees/`

**Key insight today:** `size = queue.size()` captured BEFORE the for loop freezes level count. Without this, size grows as children are enqueued and we'd process too many nodes per level.

**Struggles:** Understanding why height(root) is called in diameterOfBinaryTree just for side effect — maxDiameter is class-level shared variable updated during recursion.

---

## Day 6 — April 12 (Saturday)

**Topics Covered:** Week 3 revision

**Revision Q&A Results:**
- ✅ Level Order — Queue FIFO, size trick freezes level count
- ✅ Diameter — height(left)+height(right), edges not nodes, no +1
- ✅ Left/Right View — i==0 vs i==size-1, only difference
- ✅ Min Stack — pop both stacks, one entry per element always in sync
- ✅ First Non-Repeating — while not if, lazy cleanup from front only
- ✅ Queue using Stacks — pour only when stack2 empty
- ✅ Traversals — pre/in/post order and use cases
- ✅ Circular Queue — modulo wraps index back to 0

**Problems re-solved from memory:**
- ✅ #155 Min Stack — solved with minor syntax fixes
- ✅ #739 Daily Temperatures — solved, fixed return inside loop bug

**Key insight today:** Min Stack — stack2 has one entry per stack1 entry always. For every push → push min. For every pop → pop both. Permanently in sync.

---

## Day 7 — April 13 (Sunday)

**Topics Covered:** Week 3 full revision

**Revision Q&A Results:**
- ✅ Traversals — inorder=left/root/right, preorder=root/left/right, postorder=left/right/root
- ✅ Height — `1 + Math.max(height(left), height(right))`, base case null=0
- ✅ Level Order size trick — freezes level count before loop so queue growth doesn't affect it
- ✅ Diameter — edges not nodes so `left + right` no +1
- ✅ Queue using Stacks — pouring when stack2 not empty destroys FIFO order
- ✅ Right View — last node at index `size-1` e.g. 4 nodes → index 3

**Key insight today:** Diameter uses `left + right` (no +1) because it counts EDGES. Height uses `1 + max(left, right)` because it counts NODES. One formula difference, completely different meaning!

---

## 📊 Problems Solved This Week

| Problem | Difficulty | Time Taken | Attempts | Key Takeaway |
|---------|------------|------------|----------|--------------|
| #20 Valid Parentheses | Easy | — | — | Already solved Week 2 |
| #232 Implement Queue using Stacks | Medium | 20 min | 2 | Pour stack1→stack2 only when stack2 empty |
| #155 Min Stack | Medium | 20 min | 2 | Two stacks — pop both together to stay in sync |
| #739 Daily Temperatures | Medium | 15 min | 2 | NGE with indices, answer = stack.peek() - i |
| #19 Remove Nth Node From End | Medium | 20 min | 2 | Gap technique, edge case when fast==null |
| #104 Max Depth of Binary Tree | Easy | 5 min | 1 | Same as height — 1 + max(left, right) |

**Total: 5 / 5** ✅

---

## 🏆 Week 3 Summary

- **Concepts learned:** Queue using Stacks, Circular Queue, Deque, First Non-Repeating, Min Stack, Binary Trees (all traversals), Level Order, Height, Count, Diameter, Left/Right Views
- **Problems solved:** 5/5 Blind 75 problems
- **Biggest win:** Binary Trees clicked quickly — traversals, height, diameter all understood in one session
- **Patterns learned:** Level Order (BFS), Tree recursion, Size trick for level separation

---

## 🎯 Week 4 Plan (April 14–20)

```
Day 1 → BST intro, insert, search | #226 Invert Binary Tree
Day 2 → BST delete, LCA | #100 Same Tree
Day 3 → BST problems | #572 Subtree of Another Tree
Day 4 → BST to sorted array | #102 Level Order Traversal
Day 5 → Revision: Arrays, Strings, LL | Mixed LC
Day 6 → Revision: Stacks, Queues, Trees | Mixed LC
Day 7 → Week 4 revision + notes
```