# 05 — Binary Trees

> **One-liner:** A tree where each node has at most 2 children (left and right) — like a family tree but each person can have max 2 kids.

---

## 📌 Key Concepts

- **Root** — topmost node, no parent (like head in linked list)
- **Leaf** — node with no children (left==null && right==null)
- **Height** — longest path from root to leaf
- **Binary** — each node has AT MOST 2 children
- **Subtree** — any node and all its descendants form a subtree

---

## 🌳 Visual Structure

```
        1          ← root
       / \
      2   3        ← internal nodes
     / \   \
    4   5   6      ← leaf nodes
```

---

## 💻 Core Implementation

```java
class TreeNode {
    int val;
    TreeNode left;
    TreeNode right;

    TreeNode(int val) {
        this.val = val;
        this.left = null;
        this.right = null;
    }
}
```

**Building a tree:**
```java
TreeNode root = new TreeNode(1);
root.left = new TreeNode(2);
root.right = new TreeNode(3);
root.left.left = new TreeNode(4);
root.left.right = new TreeNode(5);
```

**How to know it's a leaf?**
```java
if (node.left == null && node.right == null) // it's a leaf!
```

---

## ⏱️ Time & Space Complexity

| Operation | Time | Space | Notes |
|-----------|------|-------|-------|
| Traversal | O(n) | O(h) | h = height of tree |
| Search (BST) | O(h) | O(1) | O(log n) balanced |
| Insert (BST) | O(h) | O(1) | O(log n) balanced |

---

## 🔍 Traversals

**Easy way to remember:**
- **Pre**order = root **before** children
- **In**order = root **in between** children
- **Post**order = root **after** children

---

### Inorder — Left → Root → Right
**When to use:** BST inorder gives sorted order!

```java
static void inOrder(TreeNode root) {
    if (root == null) return;
    inOrder(root.left);
    System.out.print(root.val + " ");
    inOrder(root.right);
}
```

**Output for above tree:** `4 2 5 1 3`

---

### Preorder — Root → Left → Right
**When to use:** Copy a tree, serialize a tree

```java
static void preOrder(TreeNode root) {
    if (root == null) return;
    System.out.print(root.val + " ");
    preOrder(root.left);
    preOrder(root.right);
}
```

**Output for above tree:** `1 2 4 5 3`

---

### Postorder — Left → Right → Root
**When to use:** Delete a tree, calculate folder sizes

```java
static void postOrder(TreeNode root) {
    if (root == null) return;
    postOrder(root.left);
    postOrder(root.right);
    System.out.print(root.val + " ");
}
```

**Output for above tree:** `4 5 2 3 1`

---

### Level Order — BFS (Level by Level)
**When to use:** Find nodes at each level, shortest path, level-wise processing

Uses **Queue** — FIFO ensures level by level processing.

```java
static void levelOrder(TreeNode root) {
    if (root == null) return;
    Queue<TreeNode> queue = new LinkedList<>();
    queue.add(root);

    while (!queue.isEmpty()) {
        TreeNode node = queue.poll();
        System.out.print(node.val + " ");
        if (node.left != null) queue.add(node.left);
        if (node.right != null) queue.add(node.right);
    }
}
```

**Dry Run for tree above:**
```
queue=[1]
dequeue 1 → print 1, enqueue 2,3 → queue=[2,3]
dequeue 2 → print 2, enqueue 4,5 → queue=[3,4,5]
dequeue 3 → print 3, no children → queue=[4,5]
dequeue 4 → print 4, no children → queue=[5]
dequeue 5 → print 5, no children → queue=[]

Output: 1 2 3 4 5 ✅
```

**My doubt:** Why Queue and not Stack for level order?
**Answer:** Queue is FIFO — processes nodes in order they were discovered. Stack would give DFS (depth first) not BFS (breadth first). Level order needs to finish one level before going deeper = FIFO.

---

## 📐 Tree Properties

### Height of Tree — O(n)
Height = longest path from root to leaf (counting nodes).

**Formula:** `height = 1 + max(height(left), height(right))`
**Base case:** `height(null) = 0`

```java
static int height(TreeNode root) {
    if (root == null) return 0;
    return 1 + Math.max(height(root.left), height(root.right));
}
```

**Dry Run:**
```
height(4) = 1 + max(0,0) = 1
height(5) = 1 + max(0,0) = 1
height(2) = 1 + max(1,1) = 2
height(3) = 1 + max(0,0) = 1
height(1) = 1 + max(2,1) = 3 ✅
```

---

### Count Nodes — O(n)
Count total nodes in tree.

**Formula:** `count = 1 + count(left) + count(right)`
**Base case:** `count(null) = 0`

```java
static int countNodes(TreeNode root) {
    if (root == null) return 0;
    return 1 + countNodes(root.left) + countNodes(root.right);
}
```

**Notice the pattern:**
```
Height: 1 + MAX(left, right)  → picks longer path
Count:  1 + left + right       → adds both sides
```

Same recursion structure, different operation!

**My doubt:** Why does `root` parameter name not always mean the actual root?
**Answer:** `root` is just a parameter name — it refers to "current node being processed". In recursive calls it changes to left child, right child etc. Could be named `node` for clarity — both work the same.

---

## 🔄 How Recursion Works for Traversals

Inorder trace for tree above:

```
inOrder(1) →
    inOrder(2) →
        inOrder(4) →
            inOrder(null) → return
            print 4
            inOrder(null) → return
        print 2
        inOrder(5) →
            inOrder(null) → return
            print 5
            inOrder(null) → return
    print 1
    inOrder(3) →
        inOrder(null) → return
        print 3
        inOrder(null) → return

Output: 4 2 5 1 3 ✅
```

Key insight: Recursion goes all the way DOWN to null before printing. Like unwinding a spring — go deep first, print on the way back!

---

### Diameter of Binary Tree — O(n)
Diameter = longest path between any two nodes (measured in edges).

**Key insight:** For each node, longest path THROUGH it = height(left) + height(right)
Track maximum across ALL nodes using global variable.

**Why edges not nodes?** Diameter = edges. Height returns nodes. `left + right` = edges on both sides = diameter in edges ✅

```java
int maxDiameter = 0;

int height(TreeNode root) {
    if (root == null) return 0;
    int left = height(root.left);
    int right = height(root.right);
    maxDiameter = Math.max(maxDiameter, left + right);  // update diameter
    return 1 + Math.max(left, right);  // return height
}

int diameterOfBinaryTree(TreeNode root) {
    height(root);  // triggers recursion, updates maxDiameter as side effect
    return maxDiameter;
}
```

**My doubt:** Why call height(root) if we return maxDiameter?
**Answer:** height(root) triggers full recursion through tree, updating maxDiameter at every node as side effect. We don't use the return value (height) — we just need the side effect (maxDiameter updates).

**My doubt:** Why maxDiameter is class-level variable?
**Answer:** Class-level variables are shared across all methods in the class. Both height() and diameterOfBinaryTree() can access and modify the same maxDiameter — like a shared whiteboard in a room.

**My doubt:** Height uses `1 + max(left, right)` but diameter uses `left + right` — why no +1?
**Answer:** Height counts NODES, diameter counts EDGES. Edges = nodes - 1. So diameter = left_nodes + right_nodes - 1... but since height already returns nodes, left+right gives edges on both sides directly. No +1 needed.

---

### Left View — O(n)
Nodes visible from left side = first node at each level.

Uses level order with `size` trick to track level boundaries.

```java
static void leftView(TreeNode root) {
    if (root == null) return;
    Queue<TreeNode> queue = new LinkedList<>();
    queue.add(root);

    while (!queue.isEmpty()) {
        int size = queue.size();  // nodes at current level

        for (int i = 0; i < size; i++) {
            TreeNode node = queue.poll();
            if (i == 0) System.out.print(node.val + " ");  // first node
            if (node.left != null) queue.add(node.left);
            if (node.right != null) queue.add(node.right);
        }
    }
}
```

---

### Right View — O(n)
Nodes visible from right side = last node at each level.

```java
static void rightView(TreeNode root) {
    if (root == null) return;
    Queue<TreeNode> queue = new LinkedList<>();
    queue.add(root);

    while (!queue.isEmpty()) {
        int size = queue.size();

        for (int i = 0; i < size; i++) {
            TreeNode node = queue.poll();
            if (i == size - 1) System.out.print(node.val + " ");  // last node
            if (node.left != null) queue.add(node.left);
            if (node.right != null) queue.add(node.right);
        }
    }
}
```

**My doubt:** Why size = queue.size() before the for loop?
**Answer:** queue.size() changes as we add children inside the loop. Capturing size BEFORE the loop freezes the count at current level.

Example — Iteration 2 with queue=[2,3]:
```
size = 2  ← frozen here before loop

i=0: poll 2, enqueue 4,5 → queue=[3,4,5]  (size still = 2!)
i=1: poll 3, enqueue 6   → queue=[4,5,6]  (size still = 2!)
loop ends after 2 iterations ✅
```

If we used queue.size() inside loop condition → after i=0, size becomes 3 → loop runs 3 times → processes nodes from next level too! ❌

**My doubt:** Why i == size-1 for right view not i == 1?
**Answer:** Last node index depends on level size. Level with 3 nodes → last is index 2 = size-1. Level with 1 node → last is index 0 = size-1. Always size-1, never fixed index.

**My doubt:** Why add left before right when enqueuing children?
**Answer:** Standard tree processing is left to right. Adding right before left reverses the order — "last" node at each level would be leftmost giving wrong right view.

---

## 🌲 Binary Search Tree (BST)

> **One-liner:** Binary Tree with ordering rule — left < parent < right for every node.

### Key Property
```
        5
       / \
      3   7
     / \ / \
    2  4 6  8
```
- Every node in LEFT subtree < current node
- Every node in RIGHT subtree > current node
- Applies recursively to ALL nodes!

**Why useful?** Search is O(log n) — at each node eliminate half the tree!

---

### Search in BST — O(log n)
```java
public boolean search(TreeNode root, int target) {
    if (root == null) return false;
    if (root.val == target) return true;
    if (target < root.val) return search(root.left, target);
    return search(root.right, target);
}
```

**My doubt:** Why check root==null BEFORE root.val==target?
**Answer:** If root is null, accessing root.val crashes! Always null check first — this is the base case that stops recursion.

---

### Insert in BST — O(log n)
Find the correct null spot and insert. Return updated tree.

```java
public TreeNode insert(TreeNode root, int val) {
    if (root == null) return new TreeNode(val);  // found the spot!
    if (val < root.val)
        root.left = insert(root.left, val);   // go left
    else
        root.right = insert(root.right, val); // go right
    return root;
}
```

**My doubt:** Why store result in root.left = insert(...)?
**Answer:** When recursion hits null and returns new TreeNode, that node needs to be ATTACHED to the tree. Without `root.left = insert(...)`, the new node is created but never connected — it disappears!

**My doubt:** Why return root at the end?
**Answer:** Parent call needs to reconnect the subtree. `root.left = insert(root.left, val)` uses this return value to maintain the tree structure.

---

### BST Delete — O(log n)
Three cases based on number of children.

**Case 1:** No children (leaf) → return null (parent pointer becomes null)
**Case 2:** One child → return that child (parent pointer skips deleted node)
**Case 3:** Two children → replace with inorder successor (smallest in right subtree), delete successor

**Why inorder successor?** It's greater than everything in left subtree AND smallest in right subtree → maintains BST property perfectly. Inorder predecessor (largest in left) also works!

**Why return null removes node?** Parent does `root.left = delete(...)`. If delete returns null → `root.left = null` → node gone!

```java
public TreeNode delete(TreeNode root, int val) {
    if (root == null) return null;
    if (root.val > val) {
        root.left = delete(root.left, val);
    } else if (root.val < val) {
        root.right = delete(root.right, val);
    } else {  // found node to delete
        if (root.left == null && root.right == null) return null;  // leaf
        if (root.left == null) return root.right;   // one child
        if (root.right == null) return root.left;   // one child
        // two children — find inorder successor
        TreeNode successor = root.right;
        while (successor.left != null) successor = successor.left;
        root.val = successor.val;
        root.right = delete(root.right, successor.val);
    }
    return root;
}
```

**My doubt:** How does returning null/root.right remove the node?
**Answer:** Parent call stores: `root.left = delete(root.left, val)`. Whatever delete returns gets stored in parent's pointer. Return null → parent.left=null → deleted. Return root.right → parent.left=right child → node skipped.

---

### LCA of BST — O(log n)
Lowest Common Ancestor = deepest node that has both p and q as descendants.

**Key insight — use BST property:**
- Both p,q < root → LCA in left subtree
- Both p,q > root → LCA in right subtree
- Otherwise → root IS the LCA (one on each side, or one equals root)

```java
public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
    if (root == null) return null;
    if (root.val > p.val && root.val > q.val)
        return lowestCommonAncestor(root.left, p, q);
    if (root.val < p.val && root.val < q.val)
        return lowestCommonAncestor(root.right, p, q);
    return root;  // root is LCA!
}
```

**My doubt:** Why return root when neither condition matches?
**Answer:** If root is not greater than both AND not less than both → one of p,q is on each side (or one equals root). That means root is the DEEPEST node where paths to p and q diverge → LCA!

---
Swap left and right children at every node recursively.

```java
public TreeNode invertTree(TreeNode root) {
    if (root == null) return null;
    TreeNode temp = root.right;
    root.right = root.left;
    root.left = temp;
    invertTree(root.left);
    invertTree(root.right);
    return root;
}
```

**My doubt:** Why swap first then recurse?
**Answer:** Swap current node's children first, then recursively invert each subtree. Order doesn't actually matter here — both work. But swapping first is more intuitive.

---

- ❌ Forgetting base case `if (root == null) return` → infinite recursion/crash
- ❌ Wrong order of recursive calls — determines which traversal you get
- ❌ Confusing Binary Tree with Binary Search Tree — BT has no ordering rule
- ❌ In BST insert — not storing result: `root.left = insert(root.left, val)` → new node never attached

---

## 💡 Interview Tips

- ✅ Always start with base case — null check first
- ✅ Draw the tree before coding
- ✅ Inorder of BST = sorted array — very useful property
- ✅ Most tree problems use recursion — think in terms of subtrees

---

## 📎 LeetCode Problems

| # | Problem | Difficulty | Pattern | Status |
|---|---------|------------|---------|--------|
| 104 | Maximum Depth of Binary Tree | Easy | DFS recursion | ⬜ |
| 100 | Same Tree | Easy | DFS recursion | ⬜ |
| 226 | Invert Binary Tree | Easy | DFS recursion | ⬜ |
| 572 | Subtree of Another Tree | Easy | DFS recursion | ⬜ |
| 102 | Binary Tree Level Order Traversal | Medium | BFS | ⬜ |
| 98 | Validate BST | Medium | Inorder | ⬜ |
| 105 | Construct BT from Preorder/Inorder | Medium | Recursion | ⬜ |
| 124 | Binary Tree Maximum Path Sum | Hard | DFS | ⬜ |

---

## 🔗 Related Topics

- **Linked Lists** — TreeNode is like ListNode but with two pointers
- **Recursion** — most tree problems use recursion
- **Stacks** — DFS uses stack internally
- **Queues** — BFS (level order) uses queue
- **BST** — special binary tree with ordering property (Week 4)