/**
 * Problem: Reverse Linked List
 * Link: https://leetcode.com/problems/reverse-linked-list/
 * Difficulty: Easy
 *
 * Companies: Amazon, Google, Facebook, Microsoft, Apple, Bloomberg,
 *            Adobe, Uber, Oracle and more
 *
 * Topic: Linked Lists
 * Pattern: Iterative — 3 pointers (prev, curr, next)
 *
 * ─────────────────────────────────────────────
 * APPROACH
 * ─────────────────────────────────────────────
 * 1. Initialize prev=null, curr=head
 * 2. For each node:
 *    - Save next before losing it
 *    - Reverse curr.next to point to prev
 *    - Move prev forward to curr
 *    - Move curr forward to saved next
 * 3. Return prev — it's the new head
 *
 * Key Insight: Save next BEFORE reversing pointer — otherwise
 *              you lose the rest of the list forever.
 *              Order of steps matters: save → reverse → move prev → move curr
 *
 * ─────────────────────────────────────────────
 * COMPLEXITY
 * ─────────────────────────────────────────────
 * Time:  O(n)  — single pass through the list
 * Space: O(1)  — just 3 pointers, no extra space
 *
 * ─────────────────────────────────────────────
 * DRY RUN
 * ─────────────────────────────────────────────
 * Input: 1 ──▶ 2 ──▶ 3 ──▶ null
 *
 * Start: prev=null, curr=1
 *
 * Iter 1: next=2, 1.next=null, prev=1, curr=2
 *         null ◀── 1    2 ──▶ 3
 *
 * Iter 2: next=3, 2.next=1, prev=2, curr=3
 *         null ◀── 1 ◀── 2    3
 *
 * Iter 3: next=null, 3.next=2, prev=3, curr=null
 *         null ◀── 1 ◀── 2 ◀── 3
 *
 * curr=null → loop ends → return prev=3 ✅
 * Output: 3 ──▶ 2 ──▶ 1 ──▶ null
 */

class ReverseLinkedList {
    public ListNode reverseList(ListNode head) {
        ListNode prev = null;
        ListNode curr = head;

        while (curr != null) {
            ListNode next = curr.next;  // step 1: save next
            curr.next = prev;           // step 2: reverse pointer
            prev = curr;               // step 3: move prev forward
            curr = next;               // step 4: move curr forward
        }

        return prev;  // prev is new head
    }
}

/*
 * ─────────────────────────────────────────────
 * ALTERNATIVE APPROACHES
 * ─────────────────────────────────────────────
 * Recursive:  O(n) time, O(n) space (call stack)
 *   → Recurse to end, then reverse on way back
 *   → More elegant but uses extra space
 *
 * Iterative (above): O(n) time, O(1) space ✅
 *   → 3 pointers, single pass
 *
 * ─────────────────────────────────────────────
 * COMMON MISTAKES
 * ─────────────────────────────────────────────
 * ❌ Not saving next before reversing — loses rest of list
 * ❌ Wrong order: curr=next before prev=curr
 *    → prev ends up pointing to already-moved curr
 * ❌ Using while(curr.next != null) — misses reversing last node
 * ❌ Returning head instead of prev — head is now the tail!
 *
 * ─────────────────────────────────────────────
 * MY DOUBTS DURING LEARNING (for revision)
 * ─────────────────────────────────────────────
 * Q: Why return prev not head?
 * A: After loop, head still points to original first node which
 *    is now the TAIL. prev has walked to new head (last node).
 *    Returning head gives you just the tail. ❌
 *
 * Q: Why prev = curr before curr = next?
 * A: If you do curr=next first, then prev=curr would point to
 *    already-moved curr — wrong node! Always prev=curr first.
 *
 * Q: Why curr != null not curr.next != null?
 * A: curr.next != null stops at last node — last node never gets
 *    reversed! curr != null processes every node including last. ✅
 *
 * Q: Why does curr = curr.next not work after reversing?
 * A: curr.next was already changed to prev in step 2!
 *    Must use saved next variable instead.
 *
 * ─────────────────────────────────────────────
 * SIMILAR PROBLEMS
 * ─────────────────────────────────────────────
 * - #92  Reverse Linked List II — Medium (reverse between positions)
 * - #143 Reorder List — Medium (reverse second half)
 * - #234 Palindrome Linked List — Easy (reverse second half + compare)
 */