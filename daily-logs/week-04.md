# Week 4 Log (April 14 – April 20, 2026)

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
- Validate BST — wrong approach: check just left < root < right. Correct: pass valid range (min, max) down. Going left → max becomes parent.val. Going right → min becomes parent.val.
- Use Long not int for boundaries — node values can be Integer.MIN/MAX_VALUE, long prevents false failures.
- Subtree of Another Tree — reuse isSameTree! At each node check if subtree matches subRoot. Recursion handles traversal automatically — no manual left/right tracking needed.
- Key pattern: always think "can I reuse an existing function?" — decompose into known subproblems.

**Code written:** `ValidateBST.java`, `SubtreeOfAnotherTree.java`

**Key insight today:** Recursion IS the traversal — you just define what to do at each node, recursion handles getting there. Same insight applies to isSubtree, diameter, invertTree etc.

**Struggles:** Understanding why max becomes 5 for node 6 in tricky BST case — clicked after tracing how max gets passed down through left child calls.

---

## Day 4 — April 17
*(to be filled)*

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

## 📊 Problems Solved This Week

| Problem | Difficulty | Companies | Key Takeaway |
|---------|------------|-----------|--------------|
| #226 Invert Binary Tree | Easy | Amazon, Google, Facebook | Swap left/right at every node |
| #100 Same Tree | Easy | Amazon, Google, Bloomberg | Both null=true, one null=false |
| #98 Validate BST | Medium | Amazon, Google, Facebook, Bloomberg + many more | Min/max range approach, use Long |
| #572 Subtree of Another Tree | Easy | Amazon, Meta, Google | Reuse isSameTree, recursion handles traversal |

**Total: 4 / 5**

---

## 🎯 Next Session
- #230 Kth Smallest in BST
- #235 LCA of BST
- #102 Binary Tree Level Order Traversal