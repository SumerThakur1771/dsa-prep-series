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

## ⚠️ Common Mistakes

- ❌ Forgetting base case `if (root == null) return` → infinite recursion/crash
- ❌ Wrong order of recursive calls — determines which traversal you get
- ❌ Confusing Binary Tree with Binary Search Tree — BT has no ordering rule

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