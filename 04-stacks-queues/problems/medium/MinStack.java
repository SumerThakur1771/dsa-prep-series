/**
 * Problem: Min Stack
 * Link: https://leetcode.com/problems/min-stack/
 * Difficulty: Medium
 *
 * Companies: Amazon, Google, Facebook, Microsoft, Apple,
 *            Bloomberg, Adobe, Oracle and more
 *
 * Topic: Stacks
 * Pattern: Two Stacks — parallel min tracking
 *
 * ─────────────────────────────────────────────
 * APPROACH
 * ─────────────────────────────────────────────
 * Use two stacks:
 * - stack1 → normal stack (stores all elements)
 * - stack2 → min stack (stores current minimum at each state)
 *
 * For every push to stack1, push min(val, stack2.peek()) to stack2.
 * stack2 top always = minimum of ALL elements currently in stack1.
 * Pop both stacks together to keep them in sync.
 *
 * Key Insight: stack2 mirrors stack1's state. For each element in
 *              stack1, stack2 stores what the minimum WAS at that point.
 *              When you pop, the previous minimum is restored automatically!
 *
 * ─────────────────────────────────────────────
 * COMPLEXITY
 * ─────────────────────────────────────────────
 * Time:  O(1)  — all operations constant time
 * Space: O(n)  — two stacks storing n elements
 *
 * ─────────────────────────────────────────────
 * DRY RUN
 * ─────────────────────────────────────────────
 * push(5): stack1=[5],     stack2=[5]     getMin=5
 * push(3): stack1=[5,3],   stack2=[5,3]   getMin=3
 * push(7): stack1=[5,3,7], stack2=[5,3,3] getMin=3
 * push(2): stack1=[5,3,7,2],stack2=[5,3,3,2] getMin=2
 * pop():   stack1=[5,3,7], stack2=[5,3,3] getMin=3 ✅ (restored!)
 * top():   returns 7
 * getMin():returns 3
 */

class MinStack {
    Stack<Integer> stack1;
    Stack<Integer> stack2;

    public MinStack() {
        stack1 = new Stack<>();
        stack2 = new Stack<>();
    }

    public void push(int val) {
        stack1.push(val);
        stack2.push(stack2.isEmpty() ? val : Math.min(val, stack2.peek()));
    }

    public void pop() {
        stack1.pop();
        stack2.pop();  // must pop both to stay in sync!
    }

    public int top() {
        return stack1.peek();
    }

    public int getMin() {
        return stack2.peek();  // always O(1)!
    }
}

/*
 * ─────────────────────────────────────────────
 * ALTERNATIVE APPROACHES
 * ─────────────────────────────────────────────
 * Brute Force: O(n) getMin
 *   → Loop through stack to find min each time
 *
 * Two Stacks (above): O(1) all operations ✅
 *
 * ─────────────────────────────────────────────
 * COMMON MISTAKES
 * ─────────────────────────────────────────────
 * ❌ Not popping from stack2 when popping from stack1
 *    → Stacks go out of sync → wrong getMin after pop
 * ❌ Not handling stack2.isEmpty() on first push
 *    → stack2.peek() crashes on empty stack
 * ❌ Storing global min instead of min at each state
 *    → After popping the minimum, you lose track of previous min
 *
 * ─────────────────────────────────────────────
 * MY DOUBTS DURING LEARNING (for revision)
 * ─────────────────────────────────────────────
 * Q: Why pop from stack2 too?
 * A: stack2 mirrors stack1 — one entry per element. If you only pop
 *    stack1, they go out of sync and getMin returns wrong value.
 *    e.g. push 5,3 then pop → stack1=[5], stack2=[5,3] → getMin=3 ❌
 *
 * Q: Why not just track one global minimum variable?
 * A: When you pop the minimum element, you need to know what the
 *    PREVIOUS minimum was. Stack2 stores historical minimums at each
 *    state — popping it automatically restores the previous minimum!
 *
 * Q: Why does pop() return void not int?
 * A: LeetCode's interface defines it as void. top() is used to get
 *    the value. Different from Java's built-in Stack.pop() which returns value.
 *
 * ─────────────────────────────────────────────
 * SIMILAR PROBLEMS
 * ─────────────────────────────────────────────
 * - #232 Implement Queue using Stacks — Medium
 * - #716 Max Stack — Hard
 * - #239 Sliding Window Maximum — Hard
 */