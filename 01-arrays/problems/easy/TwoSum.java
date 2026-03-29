/**
 * Problem: Two Sum
 * Link: https://leetcode.com/problems/two-sum/
 * Difficulty: Easy
 *
 * Topic: Arrays
 * Pattern: HashMap
 *
 * ─────────────────────────────────────────────
 * APPROACH
 * ─────────────────────────────────────────────
 * 1. For each number, calculate its complement (target - nums[i])
 * 2. Check if complement already exists in the HashMap
 *    - If yes → found the pair, return [map.get(complement), i]
 *    - If no  → store current number and its index in map, continue
 * 3. Check BEFORE putting — otherwise same element could match itself
 *
 * Key Insight: Instead of searching the whole array for complement (brute O(n²)),
 *              store what we've already seen in a HashMap so lookup is O(1).
 *              When we reach the second number of a pair, the first is already in the map.
 *
 * ─────────────────────────────────────────────
 * COMPLEXITY
 * ─────────────────────────────────────────────
 * Time:  O(n)  — single pass through the array
 * Space: O(n)  — HashMap stores up to n elements
 *
 * ─────────────────────────────────────────────
 * DRY RUN
 * ─────────────────────────────────────────────
 * Input: nums = [2, 7, 11, 15], target = 9
 *
 * i=0: complement = 9-2 = 7 → map empty, not found → store {2:0}
 * i=1: complement = 9-7 = 2 → found in map at index 0! → return [1, 0] ✅
 *
 * Output: [0, 1]
 */

import java.util.HashMap;

class TwoSum {
    public int[] twoSum(int[] nums, int target) {
        HashMap<Integer, Integer> map = new HashMap<>();

        for (int i = 0; i < nums.length; i++) {
            if (map.containsKey(target - nums[i])) {
                return new int[]{map.get(target - nums[i]), i};
            }
            map.put(nums[i], i);
        }

        return new int[]{};
    }
}

/*
 * ─────────────────────────────────────────────
 * ALTERNATIVE APPROACHES
 * ─────────────────────────────────────────────
 * Brute Force:  O(n²) time, O(1) space
 *   → Two loops, check every pair (i, j) where j = i+1
 *   → if nums[i] + nums[j] == target → return [i, j]
 *
 * Optimal (HashMap): O(n) time, O(n) space ✅
 *   → Single pass, store complement as we go
 *
 * ─────────────────────────────────────────────
 * COMMON MISTAKES
 * ─────────────────────────────────────────────
 * ❌ Putting in map BEFORE checking → same element matches itself
 *    e.g. nums=[3,3], target=6 → would return [0,0] instead of [0,1]
 * ❌ Using i+j instead of nums[i]+nums[j] — adds indices not values
 * ❌ Wrong return syntax — Java needs new int[]{i,j} not [i,j]
 *
 * ─────────────────────────────────────────────
 * SIMILAR PROBLEMS
 * ─────────────────────────────────────────────
 * - #167 Two Sum II (sorted array) — Medium (Two Pointers)
 * - #15  3Sum — Medium (Sort + Two Pointers)
 * - #560 Subarray Sum Equals K — Medium (Prefix Sum + HashMap)
 */