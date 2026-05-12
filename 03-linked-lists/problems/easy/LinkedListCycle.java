import java.util.ArrayDeque;
import java.util.Deque;
import java.util.HashMap;

/**
 * Problem: Linked List Cycle
 * Link: https://leetcode.com/problems/linked-list-cycle/
 * Difficulty: Easy
 *
 * Companies: Amazon, Google, Facebook, Microsoft, Apple,
 *            Bloomberg, Adobe, Yahoo and more
 *
 * Topic: Linked Lists
 * Pattern: Fast & Slow Pointers (Floyd's Cycle Detection)
 *
 * ─────────────────────────────────────────────
 * APPROACH
 * ─────────────────────────────────────────────
 * 1. Initialize slow = head, fast = head
 * 2. Move slow 1 step, fast 2 steps
 * 3. If they meet → cycle exists
 * 4. If fast hits null → no cycle
 *
 * Key Insight: If cycle exists, fast will eventually lap slow
 *              and they'll meet. Like two runners on circular track —
 *              faster one always catches slower one.
 *
 * ─────────────────────────────────────────────
 * COMPLEXITY
 * ─────────────────────────────────────────────
 * Time:  O(n)  — at most n steps to detect cycle
 * Space: O(1)  — just two pointers
 *
 * ─────────────────────────────────────────────
 * DRY RUN
 * ─────────────────────────────────────────────
 * Input: 1 → 2 → 3 → 4 → 2 (cycle back to node 2)
 *
 * Start: slow=1, fast=1
 * Step 1: slow=2, fast=3
 * Step 2: slow=3, fast=2 (4→2)
 * Step 3: slow=4, fast=4 (2→3→4)
 * slow==fast → CYCLE DETECTED ✅
 */

public class LinkedListCycle {
    public boolean hasCycle(ListNode head) {
        ListNode slow = head;
        ListNode fast = head;

        while (fast != null && fast.next != null) {
            slow = slow.next;
            fast = fast.next.next;
            if (slow == fast) return true;
        }

        return false;
    }
}

/*
 * ─────────────────────────────────────────────
 * ALTERNATIVE APPROACHES
 * ─────────────────────────────────────────────
 * HashMap:  O(n) time, O(n) space
 *   → Store visited nodes in HashSet
 *   → If node seen again → cycle
 *   → Uses extra space
 *
 * Floyd's (above): O(n) time, O(1) space ✅
 *
 * ─────────────────────────────────────────────
 * COMMON MISTAKES
 * ─────────────────────────────────────────────
 * ❌ Using || instead of && in while condition
 *    → crashes with NullPointerException on lists without cycle
 * ❌ Comparing slow.val == fast.val instead of slow == fast
 *    → two different nodes can have same value
 * ❌ Checking fast.next == null instead of fast != null && fast.next != null
 *    → fast itself could be null causing crash
 *
 * ─────────────────────────────────────────────
 * MY DOUBTS DURING LEARNING (for revision)
 * ─────────────────────────────────────────────
 * Q: Can fast skip over slow and never meet?
 * A: No! Gap reduces by 1 every step → always reaches 0.
 *    Works because fast moves exactly 2 steps (not 3).
 *
 * Q: Why && not || in while condition?
 * A: Doing fast.next.next inside loop needs BOTH fast and
 *    fast.next to be non-null. && ensures both checked before entering.
 *
 * Q: Why compare references not values?
 * A: Want to check same node in memory, not same value.
 *    Two different nodes could have same value but not be same node.
 *
 * ─────────────────────────────────────────────
 * SIMILAR PROBLEMS
 * ─────────────────────────────────────────────
 * - #142 Linked List Cycle II — Medium (find entry point)
 * - #287 Find Duplicate Number — Medium (Floyd's on array)
 * - #876 Middle of Linked List — Easy (Fast/Slow)
 */