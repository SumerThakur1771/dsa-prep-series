/**
 * Problem: Best Time to Buy and Sell Stock
 * Link: https://leetcode.com/problems/best-time-to-buy-and-sell-stock/
 * Difficulty: Easy
 *
 * Topic: Arrays
 * Pattern: Kadane's inspired — track minimum so far
 *
 * ─────────────────────────────────────────────
 * APPROACH
 * ─────────────────────────────────────────────
 * 1. Walk through prices array once
 * 2. Keep track of minimum price seen so far
 * 3. For each day calculate profit (prices[i] - min)
 *    and update maxProfit if this profit is better
 *
 * Key Insight: You don't need to find minimum first separately.
 *              As you walk through, track minimum so far and
 *              calculate profit at every step in one pass.
 *
 * ─────────────────────────────────────────────
 * COMPLEXITY
 * ─────────────────────────────────────────────
 * Time:  O(n)  — single pass through prices array
 * Space: O(1)  — only 3 variables, no extra data structure
 *
 * ─────────────────────────────────────────────
 * DRY RUN
 * ─────────────────────────────────────────────
 * Input: prices = [7,1,5,3,6,4]
 *
 * Day 1: price=7, min=7, profit=0, maxProfit=0
 * Day 2: price=1, min=1, profit=0, maxProfit=0
 * Day 3: price=5, min=1, profit=4, maxProfit=4
 * Day 4: price=3, min=1, profit=2, maxProfit=4
 * Day 5: price=6, min=1, profit=5, maxProfit=5 ✅
 * Day 6: price=4, min=1, profit=3, maxProfit=5
 *
 * Output: 5
 */

class BestTimeToBuyAndSellStock {
    public int maxProfit(int[] prices) {
        int min = Integer.MAX_VALUE;
        int profit = 0;
        int maxProfit = 0;

        for (int i = 0; i < prices.length; i++) {
            if (prices[i] < min) {
                min = prices[i];
            }
            profit = prices[i] - min;
            maxProfit = Math.max(profit, maxProfit);
        }

        return maxProfit;
    }
}

/*
 * ─────────────────────────────────────────────
 * ALTERNATIVE APPROACHES
 * ─────────────────────────────────────────────
 * Brute Force:  O(n²) time, O(1) space
 *   → Two loops, check every pair (i, j) where j > i
 *   → maxProfit = max(maxProfit, prices[j] - prices[i])
 *
 * Optimal (above): O(n) time, O(1) space ✅
 *   → Single pass, track minimum so far
 *
 * ─────────────────────────────────────────────
 * COMMON MISTAKES
 * ─────────────────────────────────────────────
 * ❌ Returning inside the loop — only returns after first iteration
 * ❌ Only calculating profit inside the if block — misses most days
 * ❌ Using INT_MAX instead of Integer.MAX_VALUE (Java syntax)
 * ❌ Trying to find global minimum first — doesn't account for
 *    minimum being the last element
 *
 * ─────────────────────────────────────────────
 * SIMILAR PROBLEMS
 * ─────────────────────────────────────────────
 * - #53  Maximum Subarray — Medium (Kadane's)
 * - #122 Best Time to Buy and Sell Stock II — Medium
 * - #123 Best Time to Buy and Sell Stock III — Hard
 */