/**
 * Problem: 3Sum
 * Link: https://leetcode.com/problems/3sum/
 * Difficulty: Medium
 *
 * Topic: Arrays
 * Pattern: Sort + Two Pointers
 *
 * ─────────────────────────────────────────────
 * APPROACH
 * ─────────────────────────────────────────────
 * 1. Sort the array first — enables two pointer technique
 * 2. Fix one number with outer loop (i)
 * 3. Use two pointers (j = i+1, k = end) to find pair summing to -nums[i]
 *    - sum == 0 → add triplet, skip duplicates, move both pointers
 *    - sum > 0  → move k left (need smaller value)
 *    - sum < 0  → move j right (need larger value)
 * 4. Skip duplicates for i to avoid duplicate triplets in result
 *
 * Key Insight: Fix one number, reduce to Two Sum with two pointers.
 *              Sorting is essential — lets you make decisions on pointer movement.
 *              Skip duplicates AFTER adding triplet, not before.
 *
 * ─────────────────────────────────────────────
 * COMPLEXITY
 * ─────────────────────────────────────────────
 * Time:  O(n²) — O(n log n) sort + O(n²) two pointer = O(n²)
 *        O(n²) is the BEST POSSIBLE for 3Sum — cannot do better
 * Space: O(1)  — no extra space apart from result list
 *
 * ─────────────────────────────────────────────
 * DRY RUN
 * ─────────────────────────────────────────────
 * Input: nums = [-1, 0, 1, 2, -1, -4]
 * After sort: [-4, -1, -1, 0, 1, 2]
 *
 * i=0 (val=-4): j=1, k=5 → sum=-3 < 0 → j++
 *               j=2, k=5 → sum=-3 < 0 → j++
 *               j=3, k=5 → sum=-2 < 0 → j++
 *               j=4, k=5 → sum=-1 < 0 → j++
 *               j=5 → j not < k → stop
 *
 * i=1 (val=-1): j=2, k=5 → sum=0 → add [-1,-1,2] ✅ → j++, k--
 *               j=3, k=4 → sum=0 → add [-1,0,1] ✅ → j++, k--
 *               j=4 → j not < k → stop
 *
 * i=2 (val=-1): nums[2]==nums[1] → duplicate → skip ✅
 *
 * Output: [[-1,-1,2], [-1,0,1]]
 */

import java.util.*;

class ThreeSum {
    public List<List<Integer>> threeSum(int[] nums) {
        List<List<Integer>> result = new ArrayList<>();
        Arrays.sort(nums);

        for (int i = 0; i < nums.length - 2; i++) {
            if (i > 0 && nums[i] == nums[i-1]) continue;

            int j = i + 1, k = nums.length - 1;
            while (j < k) {
                int sum = nums[i] + nums[j] + nums[k];
                if (sum == 0) {
                    result.add(Arrays.asList(nums[i], nums[j], nums[k]));
                    while (j < k && nums[j] == nums[j+1]) j++;
                    while (j < k && nums[k] == nums[k-1]) k--;
                    j++;
                    k--;
                } else if (sum > 0) {
                    k--;
                } else {
                    j++;
                }
            }
        }
        return result;
    }
}

/*
 * ─────────────────────────────────────────────
 * ALTERNATIVE APPROACHES
 * ─────────────────────────────────────────────
 * Brute Force:  O(n³) time, O(1) space
 *   → Three loops i, j=i+1, k=j+1
 *   → Check every triplet, hard to handle duplicates
 *
 * Optimal (Sort + Two Pointers): O(n²) time, O(1) space ✅
 *
 * ─────────────────────────────────────────────
 * COMMON MISTAKES
 * ─────────────────────────────────────────────
 * ❌ Skipping duplicates BEFORE adding triplet — misses valid combinations
 * ❌ Not sorting first — two pointer logic breaks without sorted array
 * ❌ Forgetting extra j++ and k-- after while duplicate loops
 *    → while loops stop AT last duplicate, need one more step to reach fresh value
 * ❌ Using nums[i] == nums[i+1] for i duplicate check instead of nums[i-1]
 *    → need to check if current value was already processed BEFORE, not ahead
 *
 * ─────────────────────────────────────────────
 * MY DOUBTS DURING LEARNING (for revision)
 * ─────────────────────────────────────────────
 * Q: How do duplicates occur if indices are always different?
 * A: Duplicates are about VALUES not indices. e.g. [-2,0,0,2,2]
 *    j=1(val=0) and j=2(val=0) produce the same triplet even though
 *    indices are different. We skip based on value equality, not index.
 *
 * Q: Why while loop for j/k duplicate skip but if for i?
 * A: i is in a for loop which moves automatically — just continue skips it.
 *    j and k are in a while loop with manual movement so we need while to
 *    keep skipping until we hit a fresh value.
 *
 * Q: Why nums[i] == nums[i-1] and not nums[i+1] for i duplicate check?
 * A: We check backwards (i-1) to see if this value was already processed
 *    in a previous iteration. Checking forward (i+1) tells you nothing
 *    about what already happened. i>0 guard prevents crash at i=0.
 *
 * Q: Why extra j++ and k-- after the while duplicate loops?
 * A: The while loops stop when nums[j] != nums[j+1] — but j is still
 *    pointing at the last duplicate value. Need one more step to reach
 *    the first fresh new value.
 *    Example: [0,0,2] → while stops at j pointing at second 0,
 *    extra j++ moves to 2 (fresh value) ✅
 *
 * Q: Can we use a for loop instead of while for j and k?
 * A: Yes! for(int j=i+1, k=end; j<k;) works exactly the same.
 *    It's a while loop in disguise — increment happens manually inside.
 *
 * Q: Is O(n²) really the best for 3Sum?
 * A: Yes — you must fix at least one number (O(n)) and find two others
 *    which is minimum O(n) with two pointers. So O(n²) is optimal.
 *    Nobody has found a better solution.
 *
 * ─────────────────────────────────────────────
 * SIMILAR PROBLEMS
 * ─────────────────────────────────────────────
 * - #1   Two Sum — Easy (HashMap)
 * - #167 Two Sum II — Medium (Two Pointers)
 * - #18  4Sum — Medium (Sort + Two Pointers)
 * - #16  3Sum Closest — Medium (Sort + Two Pointers)
 */