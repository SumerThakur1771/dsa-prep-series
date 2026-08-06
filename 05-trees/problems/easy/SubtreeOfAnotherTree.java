/**
 * Problem: Subtree of Another Tree
 * Link: https://leetcode.com/problems/subtree-of-another-tree/
 * Difficulty: Easy
 *
 * Companies: Amazon, Meta, Google, Compass, Jump Trading,
 *            Morgan Stanley, eBay and more
 *
 * Topic: Binary Trees
 * Pattern: DFS + reuse isSameTree
 *
 * ─────────────────────────────────────────────
 * APPROACH
 * ─────────────────────────────────────────────
 * For every node in root — check if subtree starting there
 * is identical to subRoot using isSameTree.
 * If any node matches → return true!
 *
 * Key Insight: Reuse isSameTree from #100!
 *              Recursion handles traversal automatically —
 *              no need to manually track left/right side.
 *
 * ─────────────────────────────────────────────
 * COMPLEXITY
 * ─────────────────────────────────────────────
 * Time:  O(m*n) — for each of n nodes in root, isSameTree takes O(m)
 * Space: O(m+n) — recursion stack
 *
 * ─────────────────────────────────────────────
 * DRY RUN
 * ─────────────────────────────────────────────
 * root:           subRoot:
 *     3               4
 *    / \             / \
 *   4   5           1   2
 *  / \
 * 1   2
 *
 * isSubtree(3, subRoot):
 *   isSameTree(3, subRoot)? 3!=4 → false
 *   isSubtree(4, subRoot) || isSubtree(5, subRoot)
 *
 * isSubtree(4, subRoot):
 *   isSameTree(4, subRoot)?
 *     4==4 ✅, isSameTree(1,1)=true, isSameTree(2,2)=true → true ✅
 *   return true → short circuit → overall true ✅
 */

class SubtreeOfAnotherTree {
    public boolean isSubtree(TreeNode root, TreeNode subRoot) {
        if (root == null) return false;
        if (isSameTree(root, subRoot)) return true;
        return isSubtree(root.left, subRoot) || isSubtree(root.right, subRoot);
    }

    public boolean isSameTree(TreeNode p, TreeNode q) {
        if (p == null && q == null) return true;
        if (p == null || q == null) return false;
        if (p.val != q.val) return false;
        return isSameTree(p.left, q.left) && isSameTree(p.right, q.right);
    }
}

/*
 * ─────────────────────────────────────────────
 * COMMON MISTAKES
 * ─────────────────────────────────────────────
 * ❌ Trying to manually traverse to find matching node
 *    → recursion handles traversal automatically!
 * ❌ Not handling root==null → return false (can't find subRoot in empty tree)
 * ❌ Forgetting || between left and right recursive calls
 *    → subRoot can be on either side!
 *
 * ─────────────────────────────────────────────
 * MY DOUBTS DURING LEARNING (for revision)
 * ─────────────────────────────────────────────
 * Q: How does recursion traverse left and right automatically?
 * A: Each call to isSubtree goes one level deeper.
 *    isSubtree(root.left) checks entire left subtree recursively.
 *    isSubtree(root.right) checks entire right subtree recursively.
 *    No manual traversal needed — recursion IS the traversal!
 *
 * Q: Why use || not && between left and right recursive calls?
 * A: subRoot only needs to exist in ONE side — left OR right.
 *    || returns true if EITHER side has subRoot.
 *    && would require subRoot in BOTH sides — too strict!
 *
 * Q: What if subRoot is null?
 * A: Empty tree is always a subtree → return true.
 *    LeetCode constraints guarantee subRoot is non-empty.
 *    But safe to add: if (subRoot == null) return true;
 *
 * Q: Can I reuse functions from other problems?
 * A: YES! Always think "have I solved a similar subproblem?"
 *    This problem reuses isSameTree from #100.
 *    Diameter reuses height function.
 *    Always decompose problems into known subproblems!
 *
 * ─────────────────────────────────────────────
 * SIMILAR PROBLEMS
 * ─────────────────────────────────────────────
 * - #100 Same Tree — Easy (used as helper here!)
 * - #226 Invert Binary Tree — Easy
 * - #104 Maximum Depth — Easy
 */