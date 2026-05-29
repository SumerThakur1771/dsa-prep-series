/**
 * Problem: Same Tree
 * Link: https://leetcode.com/problems/same-tree/
 * Difficulty: Easy
 *
 * Companies: Amazon, Google, Facebook, Bloomberg, Adobe and more
 *
 * Topic: Binary Trees
 * Pattern: DFS Recursion — compare structure and values
 *
 * ─────────────────────────────────────────────
 * APPROACH
 * ─────────────────────────────────────────────
 * 1. Both null → same ✅ return true
 * 2. One null other not → different ❌ return false
 * 3. Values differ → different ❌ return false
 * 4. Recursively check left AND right subtrees
 *
 * Key Insight: Two trees are same only if BOTH structure
 *              AND values match at every node.
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
 * p = [1,2,3], q = [1,2,3]
 *
 * isSameTree(1,1): vals match → check subtrees
 *   isSameTree(2,2): vals match → check subtrees
 *     isSameTree(null,null): both null → true ✅
 *     isSameTree(null,null): both null → true ✅
 *   returns true && true = true
 *   isSameTree(3,3): vals match → check subtrees
 *     both null → true ✅
 *   returns true
 * returns true ✅
 *
 * p = [1,2], q = [1,null,2]
 * isSameTree(1,1): vals match → check subtrees
 *   isSameTree(2,null): one null other not → false ❌
 * returns false ✅
 */

class SameTree {
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
 * ❌ Using & instead of && — & doesn't short circuit, can crash
 * ❌ Not handling case where ONE is null and other is not
 * ❌ Forgetting return on recursive calls — results thrown away
 * ❌ Checking subtrees without combining with && — both must be true
 *
 * ─────────────────────────────────────────────
 * MY DOUBTS DURING LEARNING (for revision)
 * ─────────────────────────────────────────────
 * Q: Why check p==null || q==null after p==null && q==null?
 * A: First check handles BOTH null (same). Second check handles
 *    ONE null (different). Order matters — if both null we return
 *    true before reaching the OR check.
 *
 * ─────────────────────────────────────────────
 * SIMILAR PROBLEMS
 * ─────────────────────────────────────────────
 * - #572 Subtree of Another Tree — Easy
 * - #226 Invert Binary Tree — Easy
 * - #104 Maximum Depth — Easy
 */