/**
 * Problem: [Problem Name]
 * Link: https://leetcode.com/problems/[problem-slug]/
 * Difficulty: Easy / Medium / Hard
 *
 * Topic: Arrays / Strings / Linked Lists / Stacks / Trees / Graphs / DP / etc.
 * Pattern: Two Pointers / Sliding Window / HashMap / BFS / DFS / Recursion / etc.
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
 * Time:  O(?)  — reason
 * Space: O(?)  — reason
 *
 * ─────────────────────────────────────────────
 * DRY RUN  (small example)
 * ─────────────────────────────────────────────
 * Input:  [example input]
 * Step 1: ...
 * Step 2: ...
 * Output: [expected output]
 */

class Solution {

    public ReturnType methodName(ParamType param) {
        // ── Edge cases ────────────────────────────────
        if (param == null) return null;

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
 * Brute Force:  O(?) time, O(?) space
 *   → [brief description]
 *
 * Better:       O(?) time, O(?) space
 *   → [brief description]
 *
 * Optimal (above): O(?) time, O(?) space
 *   → [brief description]
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


ListNode dummy = new ListNode(0);
ListNode curr = dummy;

while (list1 != null && list2 != null) {
    // compare list1.val and list2.val
    if(list1.val<list2.val){
        curr.next = list1;
        list1  = list1.next;
    }else{
        curr.next = list2;
        list2 = list2.next;
    }
    curr = curr.next;
    // attach smaller one to curr.next
    // move that list's pointer forward
    // move curr forward
}
if(list1!=null){
    curr.next = list1;
}
if(list2!=null){
    curr.next = list2;
}
return dummy.next;
