/**
 * Problem: Lowest Common Ancestor of a Binary Search Tree
 * Link: https://leetcode.com/problems/lowest-common-ancestor-of-a-binary-search-tree/
 * Difficulty: Medium
 *
 * Companies: Amazon, Google, Facebook, Microsoft, Bloomberg,
 *            LinkedIn, Adobe and more
 *
 * Topic: BST
 * Pattern: BST property — go left/right based on values
 *
 * ─────────────────────────────────────────────
 * APPROACH
 * ─────────────────────────────────────────────
 * Use BST property to navigate — no need to check all nodes!
 *
 * 3 conditions:
 * 1. Both p,q < root → LCA must be in LEFT subtree → go left
 * 2. Both p,q > root → LCA must be in RIGHT subtree → go right
 * 3. Otherwise → root IS the LCA (split point)
 *
 * "Otherwise" covers:
 * - One on each side of root
 * - One of p,q equals root
 *
 * ─────────────────────────────────────────────
 * COMPLEXITY
 * ─────────────────────────────────────────────
 * Time:  O(h)  — h = height, O(log n) balanced
 * Space: O(h)  — recursion stack
 *
 * ─────────────────────────────────────────────
 * DRY RUN (all 3 conditions)
 * ─────────────────────────────────────────────
 * Tree:
 *         6
 *        / \
 *       2   8
 *      / \ / \
 *     0  4 7  9
 *
 * LCA(0, 4): both < 6 → go left to 2
 *            0 < 2, 4 > 2 → split! return 2 ✅
 *
 * LCA(7, 9): both > 6 → go right to 8
 *            7 < 8, 9 > 8 → split! return 8 ✅
 *
 * LCA(0, 8): 0 < 6, 8 > 6 → split at root! return 6 ✅
 *
 * LCA(6, 4): 6 == root → return root (6) ✅
 */

class LCAofBST {
    public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
        if (root == null) return null;
        if (p.val < root.val && q.val < root.val)
            return lowestCommonAncestor(root.left, p, q);
        if (p.val > root.val && q.val > root.val)
            return lowestCommonAncestor(root.right, p, q);
        return root;  // split point = LCA!
    }
}

/*
 * ─────────────────────────────────────────────
 * COMMON MISTAKES
 * ─────────────────────────────────────────────
 * ❌ Not returning result of recursive calls
 *    → must be: return lowestCommonAncestor(root.left, p, q)
 * ❌ Using this approach for regular Binary Tree (not BST)
 *    → BST property required! For regular BT use #236 approach
 *
 * ─────────────────────────────────────────────
 * MY DOUBTS DURING LEARNING (for revision)
 * ─────────────────────────────────────────────
 * Q: Why does returning root work when neither condition matches?
 * A: If not both < root AND not both > root → one is on each side
 *    (or one equals root). Root is the DEEPEST node where paths
 *    to p and q diverge = LCA by definition!
 *
 * Q: What if one of p or q IS the root?
 * A: Neither condition triggers (not both < root, not both > root)
 *    → return root immediately ✅ correct! A node is its own ancestor.
 *
 * ─────────────────────────────────────────────
 * SIMILAR PROBLEMS
 * ─────────────────────────────────────────────
 * - #236 LCA of Binary Tree — Medium (harder, no BST property)
 * - #98  Validate BST — Medium
 * - #230 Kth Smallest in BST — Medium
 */