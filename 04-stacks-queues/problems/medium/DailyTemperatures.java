/**
 * Problem: Daily Temperatures
 * Link: https://leetcode.com/problems/daily-temperatures/
 * Difficulty: Medium
 *
 * Companies: Amazon, Google, Facebook, Microsoft, Bloomberg,
 *            Adobe, Uber and more
 *
 * Topic: Stacks
 * Pattern: NGE (Next Greater Element) — stack stores indices
 *
 * ─────────────────────────────────────────────
 * APPROACH
 * ─────────────────────────────────────────────
 * Same as NGE pattern but:
 * - Stack stores INDICES (not values) — to calculate distance
 * - Process right to left
 * - When current temp > stack top temp → answer = stack.peek() - i
 * - If stack empty → no warmer day → answer = 0
 *
 * Key Insight: This is exactly NGE but answer is distance to next
 *              greater element, not the element itself.
 *
 * ─────────────────────────────────────────────
 * COMPLEXITY
 * ─────────────────────────────────────────────
 * Time:  O(n)  — each element pushed and popped at most once
 * Space: O(n)  — stack stores indices
 *
 * ─────────────────────────────────────────────
 * DRY RUN
 * ─────────────────────────────────────────────
 * Input: temperatures = [73, 74, 75, 71, 69, 72, 76, 73]
 *
 * Process right to left:
 * i=7: temp=73, stack=[] → answer[7]=0, push 7 → stack=[7]
 * i=6: temp=76, stack=[7] → 73<=76 pop → stack=[] → answer[6]=0, push 6 → stack=[6]
 * i=5: temp=72, stack=[6] → 76>72 stop → answer[5]=6-5=1, push 5 → stack=[6,5]
 * i=4: temp=69, stack=[6,5] → 72>69 stop → answer[4]=5-4=1, push 4 → stack=[6,5,4]
 * i=3: temp=71, stack=[6,5,4] → 69<=71 pop, 72>71 stop → answer[3]=5-3=2, push 3
 * i=2: temp=75, stack=[6,5,3] → 71<=75 pop, 72<=75 pop, 76>75 stop → answer[2]=6-2=4
 * i=1: temp=74, stack=[6,2] → 75>74 stop → answer[1]=2-1=1, push 1
 * i=0: temp=73, stack=[6,2,1] → 74>73 stop → answer[0]=1-0=1, push 0
 *
 * Output: [1, 1, 4, 2, 1, 1, 0, 0] ✅
 */

import java.util.Stack;

class DailyTemperatures {
    public int[] dailyTemperatures(int[] temperatures) {
        int[] answer = new int[temperatures.length];
        Stack<Integer> stack = new Stack<>();

        for (int i = temperatures.length - 1; i >= 0; i--) {
            while (!stack.isEmpty() && temperatures[stack.peek()] <= temperatures[i]) {
                stack.pop();
            }
            answer[i] = stack.isEmpty() ? 0 : stack.peek() - i;
            stack.push(i);
        }

        return answer;
    }
}

/*
 * ─────────────────────────────────────────────
 * ALTERNATIVE APPROACHES
 * ─────────────────────────────────────────────
 * Brute Force:  O(n²) time, O(1) space
 *   → Two loops — for each day scan right to find warmer day
 *   → Gets Time Limit Exceeded on LeetCode
 *
 * Optimal (Stack): O(n) time, O(n) space ✅
 *
 * ─────────────────────────────────────────────
 * COMMON MISTAKES
 * ─────────────────────────────────────────────
 * ❌ Comparing stack.peek() with temperatures[i] directly
 *    → stack stores indices not values!
 *    → Must use temperatures[stack.peek()]
 * ❌ Using i - stack.peek() instead of stack.peek() - i
 *    → Stack top index is to the RIGHT (greater than i)
 *    → Distance = right index - left index = stack.peek() - i
 *
 * ─────────────────────────────────────────────
 * MY DOUBTS DURING LEARNING (for revision)
 * ─────────────────────────────────────────────
 * Q: Why store indices not temperatures in stack?
 * A: Need to calculate DISTANCE (days to wait) not just find the value.
 *    Distance = stack.peek() - i. Can't do this without indices.
 *
 * Q: Why stack.peek() - i and not i - stack.peek()?
 * A: Stack top is always to the RIGHT of current i (we process right to left).
 *    Right index > left index, so stack.peek() > i always.
 *    stack.peek() - i gives positive distance. ✅
 *
 * ─────────────────────────────────────────────
 * SIMILAR PROBLEMS
 * ─────────────────────────────────────────────
 * - #496 Next Greater Element I — Easy (NGE)
 * - #503 Next Greater Element II — Medium (Circular NGE)
 * - #84  Largest Rectangle in Histogram — Hard (Stack)
 * - #901 Online Stock Span — Medium (Stock Span)
 */