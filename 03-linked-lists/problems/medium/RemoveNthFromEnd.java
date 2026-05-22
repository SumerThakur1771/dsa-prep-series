/**
 * Problem: Remove Nth Node From End of List
 * Link: https://leetcode.com/problems/remove-nth-node-from-end-of-list/
 * Difficulty: Medium
 *
 * Companies: Amazon, Google, Facebook, Microsoft, Apple,
 *            Bloomberg, Adobe and more
 *
 * Topic: Linked Lists
 * Pattern: Two Pointers — gap of N
 *
 * ─────────────────────────────────────────────
 * APPROACH
 * ─────────────────────────────────────────────
 * 1. Move fast pointer N steps ahead
 * 2. Edge case: if fast == null → removing head → return head.next
 * 3. Move both slow and fast until fast.next == null
 *    → slow is now at node BEFORE the target
 * 4. slow.next = slow.next.next → remove target node
 * 5. Return head
 *
 * Key Insight: Gap of N between fast and slow means when fast
 *              reaches end, slow is N steps behind = node before target.
 *
 * ─────────────────────────────────────────────
 * COMPLEXITY
 * ─────────────────────────────────────────────
 * Time:  O(n)  — single pass
 * Space: O(1)  — just two pointers
 *
 * ─────────────────────────────────────────────
 * DRY RUN
 * ─────────────────────────────────────────────
 * Input: head = [1,2,3,4,5], n = 2
 *
 * Move fast 2 steps: fast = 3
 * fast != null → not removing head
 *
 * Move both until fast.next == null:
 * fast.next=4 != null → slow=2, fast=4
 * fast.next=5 != null → slow=3, fast=5
 * fast.next=null → STOP
 *
 * slow=3 (node before target 4)
 * slow.next = slow.next.next = 5
 * list = 1→2→3→5→null ✅
 *
 * Edge case: head=[1,2], n=2
 * Move fast 2 steps: fast=null
 * fast==null → return head.next = 2 ✅
 */

class RemoveNthFromEnd {
    public ListNode removeNthFromEnd(ListNode head, int n) {
        ListNode fast = head;
        ListNode slow = head;

        // move fast n steps ahead
        for (int i = 0; i < n; i++) {
            fast = fast.next;
        }

        // edge case: removing head
        if (fast == null) return head.next;

        // move both until fast.next == null
        while (fast.next != null) {
            slow = slow.next;
            fast = fast.next;
        }

        // remove target node
        slow.next = slow.next.next;

        return head;
    }
}

/*
 * ─────────────────────────────────────────────
 * ALTERNATIVE APPROACHES
 * ─────────────────────────────────────────────
 * Brute Force:  O(n) time, O(1) space
 *   → First pass: find length
 *   → Second pass: go to (length - n)th node
 *   → Two passes instead of one
 *
 * Optimal (Two Pointers): O(n) time, O(1) space ✅
 *   → Single pass using gap technique
 *
 * ─────────────────────────────────────────────
 * COMMON MISTAKES
 * ─────────────────────────────────────────────
 * ❌ Using while(fast != null) instead of while(fast.next != null)
 *    → slow moves one step too far → removes wrong node
 * ❌ slow.next = slow.next (just moving slow, not removing!)
 *    → Must do slow.next = slow.next.next
 * ❌ Not handling edge case when fast == null after initial loop
 *    → Removing head node crashes without this check
 *
 * ─────────────────────────────────────────────
 * MY DOUBTS DURING LEARNING (for revision)
 * ─────────────────────────────────────────────
 * Q: Why while(fast.next != null) not while(fast != null)?
 * A: Need slow to stop at node BEFORE target, not AT target.
 *    fast.next==null means fast is at last node → slow is right before target.
 *    fast==null means went one too far → slow AT target, can't remove!
 *
 * Q: Why edge case when fast==null after for loop?
 * A: fast==null means fast went past the end after n steps.
 *    This only happens when n == list length → removing HEAD.
 *    No node before head exists so can't use slow.next trick.
 *    Just return head.next directly.
 *
 * ─────────────────────────────────────────────
 * SIMILAR PROBLEMS
 * ─────────────────────────────────────────────
 * - #876 Middle of Linked List — Easy (Fast/Slow)
 * - #206 Reverse Linked List — Easy
 * - #141 Linked List Cycle — Easy (Fast/Slow)
 */