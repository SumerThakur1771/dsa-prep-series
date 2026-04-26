# 04 — Stacks & Queues

> **One-liner:** Stack is LIFO (Last In First Out) — like a stack of plates. Queue is FIFO (First In First Out) — like a line of people.

---

## 📌 Key Concepts

- **Stack:** Add and remove from TOP only. Last in = first out.
- **Queue:** Add to BACK, remove from FRONT. First in = first out.
- **Push:** Add to stack
- **Pop:** Remove from stack
- **Peek:** Look at top without removing
- **isEmpty:** Check if stack has no elements

---

## ⏱️ Time & Space Complexity

| Operation | Stack | Queue |
|-----------|-------|-------|
| Push/Enqueue | O(1) | O(1) |
| Pop/Dequeue | O(1) | O(1) |
| Peek | O(1) | O(1) |
| Search | O(n) | O(n) |

---

## 💻 Core Implementation

```java
import java.util.Stack;

Stack<Integer> stack = new Stack<>();
stack.push(1);       // add to top
stack.pop();         // remove from top and return
stack.peek();        // look at top without removing
stack.isEmpty();     // true if no elements
stack.size();        // number of elements
```

---

## 🧩 Common Patterns

### Pattern 1: Next Greater Element (NGE)
**When to use:** Find first greater element to the RIGHT of each element.

**Approach:** Process right to left. Stack stores potential NGE candidates.
- Pop elements smaller than current — they can never be NGE for anything to the left
- Stack top after popping = NGE (if exists)
- Push current element

**Time:** O(n) | **Space:** O(n)

```java
static int[] nextGreaterElement(int[] arr) {
    int[] result = new int[arr.length];
    Arrays.fill(result, -1);
    Stack<Integer> stack = new Stack<>();

    for (int i = arr.length - 1; i >= 0; i--) {
        // pop elements smaller than current — useless as NGE
        while (!stack.isEmpty() && stack.peek() <= arr[i]) {
            stack.pop();
        }
        // top of stack is NGE if exists
        if (!stack.isEmpty()) {
            result[i] = stack.peek();
        }
        stack.push(arr[i]);
    }

    return result;
}
```

**Dry Run:** `arr = [4, 5, 2, 10, 8]`
```
i=4: val=8,  stack=[]   → -1,  push 8  → stack=[8]
i=3: val=10, stack=[8]  → pop 8, empty → -1, push 10 → stack=[10]
i=2: val=2,  stack=[10] → 10>2 → NGE=10, push 2 → stack=[10,2]
i=1: val=5,  stack=[10,2] → pop 2, 10>5 → NGE=10, push 5 → stack=[10,5]
i=0: val=4,  stack=[10,5] → 5>4 → NGE=5, push 4 → stack=[10,5,4]

Result: [5, 10, 10, -1, -1] ✅
```

**My doubt:** Why pop smaller elements?
**Answer:** If current element is 10 and stack has 8 — 8 will never be NGE for anything to the left because 10 is already there and 10 > 8. So 8 is useless — pop it!

**My doubt:** Why process right to left?
**Answer:** NGE is to the RIGHT so we process from right first, building up candidates in stack as we move left.

---

### Pattern 2: Stock Span
**When to use:** For each day, find how many consecutive days to the LEFT had price ≤ today's price.

**Approach:** Process left to right. Stack stores INDICES (not values).
- Pop indices where price ≤ current price
- If stack empty → span = i + 1 (no blocker, all days count)
- If stack not empty → span = i - stack.peek() (distance to blocker)
- Push current index

**Time:** O(n) | **Space:** O(n)

```java
static int[] stockSpan(int[] prices) {
    int[] span = new int[prices.length];
    Stack<Integer> stack = new Stack<>();

    for (int i = 0; i < prices.length; i++) {
        // pop indices where price <= current — they can't block future
        while (!stack.isEmpty() && prices[stack.peek()] <= prices[i]) {
            stack.pop();
        }
        // if no blocker → all days count, else distance to blocker
        span[i] = stack.isEmpty() ? i + 1 : i - stack.peek();
        stack.push(i);  // push INDEX not value!
    }

    return span;
}
```

**Dry Run:** `prices = [100, 80, 60, 70]`
```
i=0: price=100, stack=[]    → span=1, push 0 → stack=[0]
i=1: price=80,  stack=[0]   → 100>80 stop → span=1-0=1, push 1 → stack=[0,1]
i=2: price=60,  stack=[0,1] → 80>60 stop → span=2-1=1, push 2 → stack=[0,1,2]
i=3: price=70,  stack=[0,1,2] → pop 2(60≤70), 80>70 stop → span=3-1=2, push 3

Result: [1,1,1,2] ✅
```

**My doubt:** Why store indices not values in stack?
**Answer:** We need to calculate span = current index - blocker index. If we stored values we'd have no way to calculate the distance.

**My doubt:** Why span = i+1 when stack is empty?
**Answer:** No blocker exists → ALL days from 0 to i are ≤ current price. Total days including today = i+1.

**My doubt:** Why pop elements ≤ current price?
**Answer:** If price is smaller than current, it can NEVER block anything to the left of current because current already blocks them. Useless → pop!

**My doubt:** Why does stopping at bigger price work? Don't we need to count everything smaller?
**Answer:** We don't count one by one — we use index subtraction. Everything between the blocker index and current index is automatically smaller (that's why blocker stopped us). Index difference gives count directly!

---

## ⚠️ Common Mistakes

- ❌ Using if instead of while for popping — multiple elements may need popping
- ❌ Storing values instead of indices in Stock Span — need indices for distance calculation
- ❌ Processing left to right for NGE — process right to left
- ❌ Not initializing result array with -1 for NGE
- ❌ Using < instead of <= when popping — equal elements should also be popped

---

## 💡 Interview Tips

- ✅ Stack problems — always think: what information do I need to keep track of?
- ✅ NGE pattern — right to left + stack of candidates
- ✅ Span pattern — left to right + stack of indices
- ✅ When you see "next greater/smaller" → think stack immediately

---

## 📎 LeetCode Problems

| # | Problem | Difficulty | Pattern | Status |
|---|---------|------------|---------|--------|
| 20 | Valid Parentheses | Easy | Stack | ⬜ |
| 155 | Min Stack | Medium | Stack | ⬜ |
| 739 | Daily Temperatures | Medium | NGE pattern | ⬜ |
| 84 | Largest Rectangle in Histogram | Hard | Stack | ⬜ |
| 239 | Sliding Window Maximum | Hard | Deque | ⬜ |

---

## 🔗 Related Topics

- **Linked Lists** — Stack can be implemented using linked list
- **Queues** — opposite of stack, FIFO
- **Trees** — DFS uses stack internally
- **Graphs** — DFS uses stack internally