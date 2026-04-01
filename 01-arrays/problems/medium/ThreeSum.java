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
 * Space: O(1)  — no extra space apart from result list
 *
 * ─────────────────────────────────────────────
 * DRY RUN
 * ─────────────────────────────────────────────
 * Input: nums = [-1, 0, 1, 2, -1, -4]
 * After sort: [-4, -1, -1, 0, 1, 2]
 *
 * i=0 (val=-4): j=1, k=5 → sum=-4+(-1)+2=-3 < 0 → j++
 *               j=2, k=5 → sum=-4+(-1)+2=-3 < 0 → j++
 *               j=3, k=5 → sum=-4+0+2=-2 < 0 → j++
 *               j=4, k=5 → sum=-4+1+2=-1 < 0 → j++
 *               j=5, k=5 → j not < k → stop
 *
 * i=1 (val=-1): j=2, k=5 → sum=-1+(-1)+2=0 → add [-1,-1,2] ✅
 *               j=3, k=4 → sum=-1+0+1=0 → add [-1,0,1] ✅
 *
 * i=2 (val=-1): nums[2]==nums[1] → skip (duplicate)
 *
 * i=3 (val=0):  j=4, k=5 → sum=0+1+2=3 > 0 → k--
 *               j=4, k=4 → j not < k → stop
 *
 * Output: [[-1,-1,2], [-1,0,1]]
 */

import java.util.*;

class ThreeSum {
    public List<List<Integer>> threeSum(int[] nums) {
        List<List<Integer>> result = new ArrayList<>();
        Arrays.sort(nums);

        for (int i = 0; i < nums.length - 2; i++) {
            if (i > 0 && nums[i] == nums[i-1]) continue; // skip duplicates for i

            int j = i + 1, k = nums.length - 1;
            while (j < k) {
                int sum = nums[i] + nums[j] + nums[k];
                if (sum == 0) {
                    result.add(Arrays.asList(nums[i], nums[j], nums[k]));
                    while (j < k && nums[j] == nums[j+1]) j++; // skip duplicates
                    while (j < k && nums[k] == nums[k-1]) k--; // skip duplicates
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
 *   → Fix one, two pointers for the rest
 *   → O(n²) is the best known solution for 3Sum
 *
 * ─────────────────────────────────────────────
 * COMMON MISTAKES
 * ─────────────────────────────────────────────
 * ❌ Skipping duplicates BEFORE adding triplet — misses valid combinations
 * ❌ Not sorting first — two pointer logic breaks without sorted array
 * ❌ Forgetting extra j++ and k-- after while duplicate loops
 *    → while loops stop at last duplicate, need one more step to fresh value
 * ❌ Using nums[i] == nums[i+1] for i duplicate check instead of nums[i-1]
 *    → need to check if current value was already processed before
 *
 * ─────────────────────────────────────────────
 * SIMILAR PROBLEMS
 * ─────────────────────────────────────────────
 * - #1   Two Sum — Easy (HashMap)
 * - #167 Two Sum II — Medium (Two Pointers)
 * - #18  4Sum — Medium (Sort + Two Pointers)
 * - #16  3Sum Closest — Medium (Sort + Two Pointers)
 */