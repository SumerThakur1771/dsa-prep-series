# Week 4 Log (April 14 – April 20 / Resumed August 2026)

**Theme:** BST + Revision

---

## Day 1 — April 14

**Topics Covered:** BST intro, Search, Insert, Invert Binary Tree (#226)

**What I learned:**
- BST — Binary Tree with ordering rule: left < parent < right for every node. Search is O(log n).
- BST Search — null check FIRST, then compare val, go left or right.
- BST Insert — find null spot recursively. `root.left = insert(root.left, val)` attaches new node.
- Invert Binary Tree — swap left/right at every node. Return TreeNode not void.

**Key insight today:** In BST insert, `root.left = insert(...)` is critical — recursive call returns new node that must be connected back to tree.

---

## Day 2 — April 15

**Topics Covered:** BST Delete, LCA of BST, Same Tree (#100)

**What I learned:**
- BST Delete — 3 cases: leaf→return null, one child→return that child, two children→replace with inorder successor.
- Why returning null/child works — parent stores `root.left = delete(...)`. Return value reconnects tree.
- LCA of BST — both < root → go left, both > root → go right, otherwise root IS LCA.
- Same Tree — both null=true, one null=false, vals differ=false, check both subtrees with &&.

**Key insight today:** LCA of BST is elegant — BST property tells you exactly which direction to go. Split point = LCA!

---

## Day 3 — August 2 (resumed after break)

**Topics Covered:** Validate BST (#98), Subtree of Another Tree (#572)

**What I learned:**
- Validate BST — pass valid range (min, max) down. Going left → max becomes parent.val. Going right → min becomes parent.val. Use Long not int for boundaries.
- Subtree of Another Tree — reuse isSameTree! Recursion handles traversal automatically.
- Key pattern: always think "can I reuse an existing function?"

**Key insight today:** Recursion IS the traversal — define what to do at each node, recursion handles getting there.

---

## Day 4 — August 3

**Topics Covered:** Kth Smallest in BST (#230), LCA of BST (#235), Level Order Traversal (#102)

**What I learned:**
- Kth Smallest — inorder of BST gives sorted order! Count to k using class-level variables (not local — reset on each call).
- LCA of BST — 3 conditions: both < root → left, both > root → right, else root IS LCA. O(h) not O(n)!
- Level Order — BFS with Queue. size trick freezes level count before for loop. Create new level list each iteration. Queue stores TreeNode not Integer.

**Key insight today:** Level Order needs TWO lists — outer `result` for all levels, inner `level` for current level. Each while iteration = one complete level processed.

**Struggles:** Level order — initially used `return levelOrder(node.left)` instead of `queue.add(node.left)`. Clicked after understanding queue.add is what enables BFS, not recursion!

---

## Day 5 — April 18
*(to be filled)*

---

## Day 6 — April 19 (Saturday)
*(to be filled)*

---

## Day 7 — April 20 (Sunday)
*(to be filled)*

---

## 📊 Problems Solved

| Problem | Difficulty | Companies | Key Takeaway |
|---------|------------|-----------|--------------|
| #226 Invert Binary Tree | Easy | Amazon, Google, Facebook | Swap left/right at every node |
| #100 Same Tree | Easy | Amazon, Google, Bloomberg | Both null=true, one null=false |
| #98 Validate BST | Medium | Amazon, Google, Facebook + many | Min/max range, use Long |
| #572 Subtree of Another Tree | Easy | Amazon, Meta, Google | Reuse isSameTree, recursion = traversal |
| #230 Kth Smallest in BST | Medium | Amazon, Google, Facebook | Inorder = sorted, count to k |
| #235 LCA of BST | Medium | Amazon, Google, Facebook | 3 BST conditions, O(h) not O(n) |
| #102 Level Order Traversal | Medium | Amazon, Google, Facebook | BFS + size trick + two lists |

**Total: 7 problems** ✅

---

## 🎯 Next Session
- #236 LCA of Binary Tree (harder — no BST property)
- #105 Construct BT from Preorder/Inorder
- Heaps intro