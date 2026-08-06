import java.util.LinkedList;
import java.util.Queue;

/**
 * Problem: [Problem Name]
 * Link: https://leetcode.com/problems/[problem-slug]/
 * Difficulty: Easy / Medium / Hard
 *
 * Topic: Arrays / Strings / Linked Lists / Stacks / Trees / Graphs / DP / etc.
 * Pattern: Two Pointers / Sliding Window / HashMap / BFS / DFS / Recursion /
 * etc.
 *
 * ─────────────────────────────────────────────
 * APPROACH
 * ─────────────────────────────────────────────
 * 1. [Step 1 — what you observe / set up]
 * 2. [Step 2 — core logic]
 * 3. [Step 3 — edge cases / return]
 *
 * Key Insight: [The "aha" moment — what made this click?]
 *
 * ─────────────────────────────────────────────
 * COMPLEXITY
 * ─────────────────────────────────────────────
 * Time: O(?) — reason
 * Space: O(?) — reason
 *
 * ─────────────────────────────────────────────
 * DRY RUN (small example)
 * ─────────────────────────────────────────────
 * Input: [example input]
 * Step 1: ...
 * Step 2: ...
 * Output: [expected output]
 */

class Solution {

    public ReturnType methodName(ParamType param) {
        // ── Edge cases ────────────────────────────────
        if (param == null)
            return null;

        // ── Core logic ────────────────────────────────

        // ── Return ────────────────────────────────────
        return null;
    }

    // ── Helper methods (if any) ──────────────────────

}

/*
 * ─────────────────────────────────────────────
 * ALTERNATIVE APPROACHES
 * ─────────────────────────────────────────────
 * Brute Force: O(?) time, O(?) space
 * → [brief description]
 *
 * Better: O(?) time, O(?) space
 * → [brief description]
 *
 * Optimal (above): O(?) time, O(?) space
 * → [brief description]
 *
 * ─────────────────────────────────────────────
 * COMMON MISTAKES
 * ─────────────────────────────────────────────
 * ❌ [Mistake 1 — e.g. off-by-one on loop bounds]
 * ❌ [Mistake 2 — e.g. forgetting null check on head]
 *
 * ─────────────────────────────────────────────
 * SIMILAR PROBLEMS
 * ─────────────────────────────────────────────
 * - #[num] [Problem Name] — [Difficulty]
 * - #[num] [Problem Name] — [Difficulty]
 */


public TreeNode insert(TreeNode root, int val) {
    if (root == null) return new TreeNode(val);  // found spot!
    if (val < root.val) 
        root.left = insert(root.left, val);   // go left
    else 
        root.right = insert(root.right, val); // go right
    return root;
}

public TreeNode search(TreeNode root, int target) {
    if (root == null) return root.pare;      // base case first!
    if (root.val == target) return true;
    if (target < root.val) return search(root.left, target);
    return search(root.right, target);
}

public TreeNode delete(TreeNode root, int val){
    if(root == null) return null;
    if(root.val > val){
        root.left = delete(root.left, val);
    }else if(root.val < val){
        root.right = delete(root.right, val);
    }else{
    if(root.right == null && root.left == null) return null;
    if(root.left == null)return root.right;
    if(root.right == null)return root.left;

    TreeNode successor = root.right;
    while(successor.left != null) successor = successor.left;
    root.val = successor.val;
    root.right = delete(root.right, successor.val);
    return root;
    }
}

public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q){
    if(root == null) return null;
    if(root.val > p.val && root.val > q.val) return lowestCommonAncestor(root.left, p, q);
    if(root.val < p.val && root.val < q.val) return lowestCommonAncestor(root.right, p, q);
    return root;
    
}

    if (p == null && q == null) return true;
if (p == null || q == null) return false;  // one null other not
if (p.val != q.val) return false;          // values differ
return isSameTree(p.left, q.left) && isSameTree(p.right, q.right);

int count = 0;
int result = 0;
public void kthsmallest (TeeNode root, int k){
    if(root == null) return;
    kthsmallest(root.left, k);
    count++;
    if(count == k){
        result = root.val;
        return;
    }
    kthsmallest(root.right, k);
}