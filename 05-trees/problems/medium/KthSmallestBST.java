/**
 * Problem: Kth Smallest Element in a BST
 * Link: https://leetcode.com/problems/kth-smallest-element-in-a-bst/
 * Difficulty: Medium
 *
 * Companies: Amazon, Google, Facebook, Microsoft, Bloomberg,
 *            Adobe, LinkedIn and more
 *
 * Topic: BST
 * Pattern: Inorder Traversal + Counter
 *
 * ─────────────────────────────────────────────
 * APPROACH
 * ─────────────────────────────────────────────
 * Key insight: Inorder traversal of BST gives nodes in SORTED ORDER!
 * So Kth smallest = Kth node visited in inorder traversal.
 *
 * 1. Do inorder traversal (left → root → right)
 * 2. Increment counter at each node
 * 3. When counter == k → store result and return
 *
 * ─────────────────────────────────────────────
 * COMPLEXITY
 * ─────────────────────────────────────────────
 * Time:  O(n)  — worst case visit all nodes
 * Space: O(h)  — recursion stack
 *
 * ─────────────────────────────────────────────
 * DRY RUN
 * ─────────────────────────────────────────────
 * Tree:        k=3
 *     3
 *    / \
 *   1   4
 *    \
 *     2
 *
 * Inorder: 1, 2, 3, 4
 * count=1 (val=1), count=2 (val=2), count=3 (val=3) → result=3 ✅
 */

class KthSmallestBST {
    int count = 0;
    int result = 0;

    public int kthSmallest(TreeNode root, int k) {
        inOrder(root, k);
        return result;
    }

    void inOrder(TreeNode root, int k) {
        if (root == null) return;
        inOrder(root.left, k);   // go left first
        count++;                  // visit current node
        if (count == k) {
            result = root.val;   // found kth smallest!
            return;
        }
        inOrder(root.right, k);  // go right
    }
}

/*
 * ─────────────────────────────────────────────
 * COMMON MISTAKES
 * ─────────────────────────────────────────────
 * ❌ return null in void method — use just return;
 * ❌ Inconsistent method name capitalization (inorder vs inOrder)
 * ❌ Forgetting return result in main method
 * ❌ Not using class-level variables for count and result
 *    → local variables reset on each recursive call!
 *
 * ─────────────────────────────────────────────
 * MY DOUBTS DURING LEARNING (for revision)
 * ─────────────────────────────────────────────
 * Q: Why use class-level variables for count and result?
 * A: Local variables are reset on each recursive call.
 *    Class-level variables persist across all recursive calls —
 *    count keeps incrementing correctly through entire traversal!
 *
 * Q: Why inorder gives sorted order for BST?
 * A: BST property: left < root < right at every node.
 *    Inorder visits left subtree first (all smaller values),
 *    then root, then right subtree (all larger values).
 *    Applied recursively → ascending order guaranteed!
 *
 * ─────────────────────────────────────────────
 * SIMILAR PROBLEMS
 * ─────────────────────────────────────────────
 * - #98  Validate BST — Medium
 * - #235 LCA of BST — Medium
 * - #700 Search in BST — Easy
 */