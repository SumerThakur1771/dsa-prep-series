/**
 * Problem: Validate Binary Search Tree
 * Link: https://leetcode.com/problems/validate-binary-search-tree/
 * Difficulty: Medium
 *
 * Companies: Adobe, Amazon, Apple, Asana, Bloomberg, Capital One,
 *            Facebook, Goldman Sachs, Google, LinkedIn, Microsoft,
 *            Oracle, Salesforce, Uber, Visa, Walmart Labs and more
 *
 * Topic: Binary Trees / BST
 * Pattern: DFS with valid range (min, max) passed down
 *
 * ─────────────────────────────────────────────
 * APPROACH
 * ─────────────────────────────────────────────
 * Wrong approach: just check left < root < right at each node
 * → fails for nodes that violate ancestor constraints!
 *
 * Correct approach: pass valid range (min, max) for each node
 * - Root → no restriction → (-∞, +∞)
 * - Left child → must be < parent → (min, parent.val)
 * - Right child → must be > parent → (parent.val, max)
 *
 * Key Insight: Every node must satisfy constraints from ALL ancestors,
 *              not just its immediate parent!
 *
 * ─────────────────────────────────────────────
 * COMPLEXITY
 * ─────────────────────────────────────────────
 * Time:  O(n)  — visit every node once
 * Space: O(h)  — recursion stack, h = height
 *
 * ─────────────────────────────────────────────
 * DRY RUN
 * ─────────────────────────────────────────────
 * Valid BST:          Invalid BST:
 *     2                   5
 *    / \                 / \
 *   1   3               1   4
 *                          / \
 *                         3   6
 *
 * Valid: isValid(2,-∞,+∞) → isValid(1,-∞,2) → true
 *                         → isValid(3,2,+∞) → true
 *        return true ✅
 *
 * Invalid: isValid(5,-∞,+∞) → isValid(4,5,+∞)
 *          4 <= 5? YES → return false ❌
 *
 * Tricky case:
 *     5
 *    /
 *   3
 *    \
 *     6   ← 6 > 3 locally but violates ancestor constraint (must be < 5)!
 *
 * isValid(3,-∞,5) → isValid(6,3,5)
 * 6 >= 5? YES → return false ❌ caught by max boundary!
 */

class ValidateBST {
    public boolean isValidBST(TreeNode root) {
        return isValid(root, Long.MIN_VALUE, Long.MAX_VALUE);
    }

    public boolean isValid(TreeNode root, long min, long max) {
        if (root == null) return true;
        if (root.val <= min || root.val >= max) return false;
        return isValid(root.left, min, root.val) &&
               isValid(root.right, root.val, max);
    }
}

/*
 * ─────────────────────────────────────────────
 * COMMON MISTAKES
 * ─────────────────────────────────────────────
 * ❌ Only checking immediate children — misses ancestor constraint violations
 * ❌ Using int instead of long for min/max
 *    → node value could equal Integer.MIN_VALUE or MAX_VALUE → false result!
 * ❌ root.val <= min should use root.val not root
 *    → comparing TreeNode object not its value
 *
 * ─────────────────────────────────────────────
 * MY DOUBTS DURING LEARNING (for revision)
 * ─────────────────────────────────────────────
 * Q: Why use Long not int for min/max?
 * A: Node values can be Integer.MIN_VALUE or MAX_VALUE. If we use int,
 *    a node with value Integer.MIN_VALUE would fail `root.val <= min`
 *    check incorrectly. Long boundaries are always outside int range → safe!
 *
 * Q: How does max become 5 when checking node 6 in tricky case?
 * A: Going LEFT from node 5: isValid(root.left, min, root.val)
 *    root.val=5 becomes max for entire left subtree.
 *    This max travels down through all left descendants — enforcing
 *    that everything in left subtree is < 5!
 *
 * Q: Why can't we just check left.val < root.val < right.val?
 * A: Only checks immediate children. A deeper node could violate
 *    ancestor constraints — e.g. node 6 in right subtree of node 3
 *    which is in LEFT subtree of node 5. 6 > 5 but we'd miss it!
 *
 * Q: Are negative values allowed in BST?
 * A: Yes! BST rule is just about ordering (left < parent < right).
 *    Values can be anything — positive, negative, zero!
 *
 * ─────────────────────────────────────────────
 * SIMILAR PROBLEMS
 * ─────────────────────────────────────────────
 * - #700 Search in BST — Easy
 * - #230 Kth Smallest in BST — Medium
 * - #235 LCA of BST — Medium
 * - #450 Delete Node in BST — Medium
 */