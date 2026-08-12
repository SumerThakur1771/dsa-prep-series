/**
 * Problem: Binary Tree Level Order Traversal
 * Link: https://leetcode.com/problems/binary-tree-level-order-traversal/
 * Difficulty: Medium
 *
 * Companies: Amazon, Google, Facebook, Microsoft, Bloomberg,
 *            Adobe, LinkedIn, Oracle and more
 *
 * Topic: Binary Trees
 * Pattern: BFS with Queue + size trick for level separation
 *
 * ─────────────────────────────────────────────
 * APPROACH
 * ─────────────────────────────────────────────
 * 1. Add root to queue
 * 2. While queue not empty:
 *    - Capture size = queue.size() (freeze current level count)
 *    - Create new level list
 *    - For loop runs exactly size times (one complete level)
 *      → poll node, add val to level list
 *      → enqueue left and right children
 *    - Add completed level list to result
 * 3. Return result
 *
 * Key Insight: size trick separates levels — captures node count
 *              BEFORE loop so queue growth doesn't affect it!
 *
 * ─────────────────────────────────────────────
 * COMPLEXITY
 * ─────────────────────────────────────────────
 * Time:  O(n)  — visit every node once
 * Space: O(n)  — queue + result list
 *
 * ─────────────────────────────────────────────
 * DRY RUN
 * ─────────────────────────────────────────────
 * Tree:
 *     3
 *    / \
 *   9  20
 *      / \
 *     15   7
 *
 * queue=[3], result=[]
 *
 * Iteration 1: size=1, level=[]
 *   poll 3 → level=[3], enqueue 9,20 → queue=[9,20]
 *   result=[[3]]
 *
 * Iteration 2: size=2, level=[]
 *   poll 9 → level=[9], no children → queue=[20]
 *   poll 20 → level=[9,20], enqueue 15,7 → queue=[15,7]
 *   result=[[3],[9,20]]
 *
 * Iteration 3: size=2, level=[]
 *   poll 15 → level=[15], no children
 *   poll 7 → level=[15,7], no children
 *   result=[[3],[9,20],[15,7]] ✅
 */

class LevelOrderTraversal {
    public List<List<Integer>> levelOrder(TreeNode root) {
        List<List<Integer>> result = new ArrayList<>();
        if (root == null) return result;

        Queue<TreeNode> queue = new LinkedList<>();
        queue.add(root);

        while (!queue.isEmpty()) {
            int size = queue.size();          // freeze level count!
            List<Integer> level = new ArrayList<>();

            for (int i = 0; i < size; i++) {
                TreeNode node = queue.poll();
                level.add(node.val);
                if (node.left != null) queue.add(node.left);
                if (node.right != null) queue.add(node.right);
            }

            result.add(level);  // add completed level to result
        }

        return result;
    }
}

/*
 * ─────────────────────────────────────────────
 * COMMON MISTAKES
 * ─────────────────────────────────────────────
 * ❌ Using return levelOrder(node.left) instead of queue.add(node.left)
 *    → exits entire method after first node! Just enqueue children.
 * ❌ Not creating new level list each iteration
 *    → all nodes end up in same list
 * ❌ Using Queue<Integer> instead of Queue<TreeNode>
 *    → need to store nodes not values to access children!
 * ❌ Not checking root==null → NullPointerException when adding to queue
 *
 * ─────────────────────────────────────────────
 * MY DOUBTS DURING LEARNING (for revision)
 * ─────────────────────────────────────────────
 * Q: Why create level list inside while loop not outside?
 * A: Each level needs its OWN list. If created outside, all nodes
 *    go into same list → can't separate by level!
 *    New list per while iteration = new list per level ✅
 *
 * Q: Why size = queue.size() before for loop not inside?
 * A: Queue grows as children get added inside loop. Capturing size
 *    BEFORE freezes the count at current level. Without freezing,
 *    loop runs too many times and processes next level's nodes too!
 *
 * Q: Why return result not null when root is null?
 * A: Empty tree → empty list [] is correct answer, not null.
 *    Also prevents NullPointerException from queue.add(null)!
 *
 * ─────────────────────────────────────────────
 * SIMILAR PROBLEMS
 * ─────────────────────────────────────────────
 * - #107 Level Order Traversal II — Easy (bottom up)
 * - #103 Zigzag Level Order — Medium
 * - #637 Average of Levels — Easy
 * - #513 Find Bottom Left Value — Medium
 */