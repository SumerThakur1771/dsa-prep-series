/**
 * Problem: Merge Two Sorted Lists
 * Link: https://leetcode.com/problems/merge-two-sorted-lists/
 * Difficulty: Easy
 *
 * Companies: Amazon, Google, Facebook, Microsoft, Apple,
 *            Bloomberg, Adobe, Uber, Oracle and more
 *
 * Topic: Linked Lists
 * Pattern: Two Pointers + Dummy Head
 *
 * ─────────────────────────────────────────────
 * APPROACH
 * ─────────────────────────────────────────────
 * 1. Create dummy head node — avoids special case for first node
 * 2. Compare list1.val and list2.val
 *    - Attach smaller one to curr.next
 *    - Move that list's pointer forward
 *    - Move curr forward
 * 3. When one list exhausts — attach remaining directly (already sorted)
 * 4. Return dummy.next — real head of merged list
 *
 * Key Insight: Dummy head makes it clean — every node including
 *              first one attaches the same way. No special cases.
 *              Remaining nodes attach in ONE step — already connected!
 *
 * ─────────────────────────────────────────────
 * COMPLEXITY
 * ─────────────────────────────────────────────
 * Time:  O(n+m) — traverse both lists once
 * Space: O(1)   — just pointers, no extra space
 *
 * ─────────────────────────────────────────────
 * DRY RUN
 * ─────────────────────────────────────────────
 * Input: list1 = 1→3→5, list2 = 2→4→6
 *
 * dummy→null, curr=dummy
 *
 * 1<2 → attach 1, list1=3 → curr=1 → dummy→1
 * 2<3 → attach 2, list2=4 → curr=2 → dummy→1→2
 * 3<4 → attach 3, list1=5 → curr=3 → dummy→1→2→3
 * 4<5 → attach 4, list2=6 → curr=4 → dummy→1→2→3→4
 * 5<6 → attach 5, list1=null → curr=5 → dummy→1→2→3→4→5
 * list1=null → loop ends
 * list2=6→null → attach remaining → dummy→1→2→3→4→5→6
 *
 * return dummy.next = 1 ✅
 */

class MergeTwoSortedLists {
    public ListNode mergeTwoLists(ListNode list1, ListNode list2) {
        ListNode dummy = new ListNode(0);
        ListNode curr = dummy;

        while (list1 != null && list2 != null) {
            if (list1.val < list2.val) {
                curr.next = list1;
                list1 = list1.next;
            } else {
                curr.next = list2;
                list2 = list2.next;
            }
            curr = curr.next;
        }

        if (list1 != null) curr.next = list1;
        if (list2 != null) curr.next = list2;

        return dummy.next;
    }
}

/*
 * ─────────────────────────────────────────────
 * ALTERNATIVE APPROACHES
 * ─────────────────────────────────────────────
 * Recursive:  O(n+m) time, O(n+m) space (call stack)
 *   → Elegant but uses extra space
 *
 * Iterative (above): O(n+m) time, O(1) space ✅
 *
 * ─────────────────────────────────────────────
 * COMMON MISTAKES
 * ─────────────────────────────────────────────
 * ❌ Using while instead of if for remaining nodes
 *    → Infinite loop since pointer never moves forward
 * ❌ Forgetting curr = curr.next inside loop
 *    → All nodes attach to same position
 * ❌ Returning dummy instead of dummy.next
 *    → dummy is fake node with value 0, not real head
 *
 * ─────────────────────────────────────────────
 * MY DOUBTS DURING LEARNING (for revision)
 * ─────────────────────────────────────────────
 * Q: Why if not while for attaching remaining nodes?
 * A: Remaining nodes are already sorted AND connected to each other.
 *    Attaching head of remaining chain brings ALL nodes at once.
 *    while loop would be infinite since we never move the pointer.
 *
 * Q: Why dummy head?
 * A: Without dummy, first node needs special handling.
 *    With dummy, every node including first attaches same way.
 *    Return dummy.next = real first node of merged list.
 *
 * ─────────────────────────────────────────────
 * SIMILAR PROBLEMS
 * ─────────────────────────────────────────────
 * - #23  Merge K Sorted Lists — Hard (Heap / Divide & Conquer)
 * - #88  Merge Sorted Array — Easy (Two Pointers)
 * - #148 Sort List — Medium (Merge Sort on LL)
 */