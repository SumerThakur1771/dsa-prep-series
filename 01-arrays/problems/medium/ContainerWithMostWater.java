/**
 * Problem: Container With Most Water
 * Link: https://leetcode.com/problems/container-with-most-water/
 * Difficulty: Medium
 *
 * Topic: Arrays
 * Pattern: Two Pointers
 *
 * ─────────────────────────────────────────────
 * APPROACH
 * ─────────────────────────────────────────────
 * 1. Start with two pointers — left at start, right at end
 * 2. Calculate area = width × min(height[left], height[right])
 * 3. Move the shorter wall inward — taller wall stays
 *    because moving taller wall can never improve height
 * 4. Track maximum area throughout
 *
 * Key Insight: Always move the shorter pointer inward.
 *              Moving the taller one can never improve area
 *              since height is limited by the shorter wall anyway.
 *
 * ─────────────────────────────────────────────
 * COMPLEXITY
 * ─────────────────────────────────────────────
 * Time:  O(n)  — single pass, two pointers moving inward
 * Space: O(1)  — just a few variables
 *
 * ─────────────────────────────────────────────
 * DRY RUN
 * ─────────────────────────────────────────────
 * Input: height = [1,8,6,2,5,4,8,3,7]
 *
 * left=0(h=1), right=8(h=7) → area = 8×1 = 8  → move left (shorter)
 * left=1(h=8), right=8(h=7) → area = 7×7 = 49 → move right (shorter)
 * left=1(h=8), right=7(h=3) → area = 6×3 = 18 → move right
 * left=1(h=8), right=6(h=8) → area = 5×8 = 40 → move right
 * ...
 *
 * Output: 49
 */

class ContainerWithMostWater {
    public int maxArea(int[] height) {
        int maxArea = 0;
        int i = 0, j = height.length - 1;

        while (i < j) {
            int width = j - i;
            int area = width * Math.min(height[i], height[j]);
            maxArea = Math.max(area, maxArea);

            if (height[i] < height[j]) {
                i++;
            } else {
                j--;
            }
        }

        return maxArea;
    }
}

/*
 * ─────────────────────────────────────────────
 * ALTERNATIVE APPROACHES
 * ─────────────────────────────────────────────
 * Brute Force:  O(n²) time, O(1) space
 *   → Two loops, check every pair (i, j)
 *   → area = (j-i) × min(height[i], height[j])
 *
 * Optimal (Two Pointers): O(n) time, O(1) space ✅
 *   → Start from both ends, always move shorter wall inward
 *
 * ─────────────────────────────────────────────
 * COMMON MISTAKES
 * ─────────────────────────────────────────────
 * ❌ Using while(i < height.length && j >= 0) — pointers can cross
 *    Always use while(i < j)
 * ❌ Moving the taller wall instead of shorter — area can never improve
 * ❌ Confusing this with Trapping Rain Water (#42) — different problem!
 *    This picks two walls only, not all bars
 *
 * ─────────────────────────────────────────────
 * SIMILAR PROBLEMS
 * ─────────────────────────────────────────────
 * - #42  Trapping Rain Water — Hard (different, uses leftMax/rightMax)
 * - #15  3Sum — Medium (Two Pointers)
 */