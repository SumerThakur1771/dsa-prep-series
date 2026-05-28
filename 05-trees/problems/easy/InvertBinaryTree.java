/**
 * Problem: Invert Binary Tree
 * Link: https://leetcode.com/problems/invert-binary-tree/
 * Difficulty: Easy
 *
 * Companies: Amazon, Google, Facebook, Microsoft, Apple,
 *            Bloomberg, Adobe and more
 *
 * Topic: Binary Trees
 * Pattern: DFS Recursion — swap at every node
 *
 * ─────────────────────────────────────────────
 * APPROACH
 * ─────────────────────────────────────────────
 * 1. Base case: if root is null → return null
 * 2. Swap left and right children
 * 3. Recursively invert left subtree
 * 4. Recursively invert right subtree
 * 5. Return root
 *
 * Key Insight: Just swap left and right at EVERY node.
 *              Recursion handles the rest automatically.
 *
 * ─────────────────────────────────────────────
 * COMPLEXITY
 * ─────────────────────────────────────────────
 * Time:  O(n)  — visit every node once
 * Space: O(h)  — recursion stack, h = height of tree
 *
 * ─────────────────────────────────────────────
 * DRY RUN
 * ─────────────────────────────────────────────
 * Input:
 *       4
 *      / \
 *     2   7
 *    / \ / \
 *   1  3 6  9
 *
 * invertTree(4): swap 2↔7
 *       4
 *      / \
 *     7   2
 *    / \ / \
 *   6  9 1  3  ← children also inverted recursively ✅
 *
 * Output:
 *       4
 *      / \
 *     7   2
 *    / \ / \
 *   9  6 3  1
 */

class InvertBinaryTree {
    public TreeNode invertTree(TreeNode root) {
        if (root == null) return null;

        TreeNode temp = root.right;
        root.right = root.left;
        root.left = temp;

        invertTree(root.left);
        invertTree(root.right);

        return root;
    }
}

/*
 * ─────────────────────────────────────────────
 * ALTERNATIVE APPROACHES
 * ─────────────────────────────────────────────
 * BFS (Level Order):  O(n) time, O(n) space
 *   → Use queue, swap children of each dequeued node
 *
 * DFS Recursive (above): O(n) time, O(h) space ✅
 *   → Cleaner, more intuitive
 *
 * ─────────────────────────────────────────────
 * COMMON MISTAKES
 * ─────────────────────────────────────────────
 * ❌ return; instead of return null → method returns TreeNode not void
 * ❌ new TreeNode(null) for temp → TreeNode takes int not null
 *    Just use: TreeNode temp = root.right
 * ❌ Forgetting return root at end
 *
 * ─────────────────────────────────────────────
 * MY DOUBTS DURING LEARNING (for revision)
 * ─────────────────────────────────────────────
 * Q: Why swap first then recurse into children?
 * A: Order doesn't actually matter here — both work.
 *    Swapping first is more intuitive (swap current, then handle subtrees).
 *
 * ─────────────────────────────────────────────
 * SIMILAR PROBLEMS
 * ─────────────────────────────────────────────
 * - #100 Same Tree — Easy (DFS comparison)
 * - #572 Subtree of Another Tree — Easy (DFS)
 * - #104 Maximum Depth — Easy (DFS)
 */