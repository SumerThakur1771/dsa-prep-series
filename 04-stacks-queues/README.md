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

### Pattern 3: Valid Parentheses
**When to use:** Check if brackets are properly opened and closed.

**Approach:** Push opening brackets. When closing bracket found — pop and check if it matches. If stack empty at end → valid.

**Time:** O(n) | **Space:** O(n)

```java
static boolean isValid(String s) {
    Stack<Character> stack = new Stack<>();

    for (char c : s.toCharArray()) {
        if (c == '(' || c == '{' || c == '[') {
            stack.push(c);
        } else if (c == ')') {
            if (stack.isEmpty() || stack.pop() != '(') return false;
        } else if (c == ']') {
            if (stack.isEmpty() || stack.pop() != '[') return false;
        } else if (c == '}') {
            if (stack.isEmpty() || stack.pop() != '{') return false;
        }
    }

    return stack.isEmpty();
}
```

**My doubt:** What if matching bracket exists but deeper in stack?
**Answer:** That's always invalid! For valid brackets, when you see `)` the TOP of stack must always be `(`. If anything else is on top, brackets are crossed — always invalid.
e.g. `"{(}"` — when `}` comes, top is `(` not `{` → invalid ❌

**My doubt:** Why return stack.isEmpty() at end?
**Answer:** If stack has remaining elements → unclosed opening brackets exist → invalid.
e.g. `"((("` → stack = ['(','(','('] → not empty → invalid ❌

---

### Pattern 4: Duplicate Parentheses
**When to use:** Check if an expression has redundant/useless brackets.

**Approach:** Push everything except `)`. When `)` found — pop until `(` and count elements between them. If count == 0 → duplicate!

**Time:** O(n) | **Space:** O(n)

```java
static boolean hasDuplicate(String s) {
    Stack<Character> stack = new Stack<>();

    for (char c : s.toCharArray()) {
        if (c == ')') {
            int count = 0;
            while (stack.peek() != '(') {
                stack.pop();
                count++;
            }
            stack.pop(); // remove '(' itself
            if (count == 0) return true; // duplicate found!
        } else {
            stack.push(c);
        }
    }

    return false;
}
```

**Dry Run:** `"((a+b))"`
```
( → push → stack=['(']
( → push → stack=['(','(']
a → push → stack=['(','(','a']
+ → push → stack=['(','(','a','+']
b → push → stack=['(','(','a','+','b']
) → pop until ( → popped b,+,a → count=3 → valid ✅ → stack=['(']
) → pop until ( → popped nothing → count=0 → DUPLICATE! ✅
```

**My doubt:** Why does count==0 mean duplicate?
**Answer:** If nothing between `(` and `)` — those brackets wrap nothing useful. Redundant brackets by definition contain no operators or variables directly.

**My doubt:** Why don't brackets themselves count as meaningful?
**Answer:** When we see `)`, inner brackets were already processed and popped in previous iterations. So only operators/variables remain between current `(` and `)`.

---

### Pattern 5: Max Area Histogram
**When to use:** Find largest rectangle that fits inside a histogram.

**Key insight:** For any rectangle, height = shortest bar in selected range. Width = number of bars selected.

**Brute Force — O(n²):**
- Fix starting bar with outer loop
- Expand right with inner loop
- Track minimum height as you expand
- Calculate area at each step

```java
static int maxAreaBrute(int[] heights) {
    int maxArea = 0;

    for (int i = 0; i < heights.length; i++) {
        int minHeight = heights[i];

        for (int j = i; j < heights.length; j++) {
            minHeight = Math.min(minHeight, heights[j]);
            int width = j - i + 1;
            maxArea = Math.max(maxArea, width * minHeight);
        }
    }

    return maxArea;
}
```

**My doubt:** Why minHeight and not maxHeight?
**Answer:** Rectangle can't be taller than shortest bar in range — it would go outside the histogram. Shortest bar is the limiting factor.

**My doubt:** Why two loops?
**Answer:** Need to try every possible start AND end combination. Outer loop fixes start, inner loop tries every end. One loop only gives one point — need two to define a rectangle.

---

**Stack Approach — O(n) ✅**

**Core idea:** For each bar treat it as the HEIGHT of rectangle. Find:
- First SHORTER bar to LEFT → left boundary
- First SHORTER bar to RIGHT → right boundary
- Width = right - left - 1

Three passes — find right smaller, find left smaller, calculate area.

**Why -1 for left when stack empty?**
No shorter bar to left → rectangle starts from index 0. Using -1: `width = right - (-1) - 1` gives correct count including index 0.

**Why n for right when stack empty?**
No shorter bar to right → rectangle goes till last bar. Using n: `width = n - left - 1` gives correct count including last index.

```java
static int largestRectangleArea(int[] heights) {
    int n = heights.length;
    int[] right = new int[n];
    int[] left = new int[n];
    Stack<Integer> stack = new Stack<>();

    // Pass 1: find right smaller for each bar
    for (int i = n - 1; i >= 0; i--) {
        while (!stack.isEmpty() && heights[stack.peek()] >= heights[i]) {
            stack.pop();
        }
        right[i] = stack.isEmpty() ? n : stack.peek();
        stack.push(i);
    }

    // clear stack for reuse
    while (!stack.isEmpty()) stack.pop();

    // Pass 2: find left smaller for each bar
    for (int i = 0; i < n; i++) {
        while (!stack.isEmpty() && heights[stack.peek()] >= heights[i]) {
            stack.pop();
        }
        left[i] = stack.isEmpty() ? -1 : stack.peek();
        stack.push(i);
    }

    // Pass 3: calculate max area
    int maxArea = 0;
    for (int i = 0; i < n; i++) {
        int width = right[i] - left[i] - 1;
        maxArea = Math.max(maxArea, heights[i] * width);
    }

    return maxArea;
}
```

**Dry Run:** `heights = [2, 1, 5, 6, 2, 3]`
```
right = [1, 6, 4, 4, 6, 6]
left  = [-1,-1, 1, 2, 1, 4]

i=0: width=1-(-1)-1=1,  area=2×1=2
i=1: width=6-(-1)-1=6,  area=1×6=6
i=2: width=4-1-1=2,     area=5×2=10 ✅
i=3: width=4-2-1=1,     area=6×1=6
i=4: width=6-1-1=4,     area=2×4=8
i=5: width=6-4-1=1,     area=3×1=3

maxArea = 10 ✅
```

**My doubt:** Why -1 for left and n for right when stack empty?
**Answer:** They are virtual boundaries one step OUTSIDE the array. This makes the width formula work correctly for bars that extend to the edges. Without them, bars at the edges would have incorrect width calculations.

**My doubt:** Why >= when popping (not just >)?
**Answer:** Equal height bars should also be popped — two bars of same height can't both be the "limiting factor". Only the outermost one matters.

---

## ⚠️ Common Mistakes

- ❌ Using if instead of while for popping — multiple elements may need popping
- ❌ Storing values instead of indices in Stock Span — need indices for distance calculation
- ❌ Processing left to right for NGE — process right to left
- ❌ Not initializing result array with -1 for NGE
- ❌ Using < instead of <= when popping — equal elements should also be popped

---

## 🗂️ Queue

> **One-liner:** FIFO — First In First Out. Like a line at a coffee shop — first person in line gets served first.

### Core Implementation

```java
Queue<Integer> queue = new LinkedList<>();
queue.add(1);      // enqueue — add to back
queue.poll();      // dequeue — remove from front and return
queue.peek();      // look at front without removing
queue.isEmpty();   // check if empty
queue.size();      // number of elements
```

---

### Queue using Two Stacks — O(1) amortized
**Difficulty: Medium**

Implement a Queue using only Stacks. Stack=LIFO, Queue=FIFO — they're opposites. Pouring one stack into another reverses order turning LIFO into FIFO!

- **Stack1** = inbox (enqueue here)
- **Stack2** = outbox (dequeue from here)
- Pour stack1 into stack2 ONLY when stack2 is empty

```java
class MyQueue {
    Stack<Integer> stack1 = new Stack<>();
    Stack<Integer> stack2 = new Stack<>();

    void push(int x) {
        stack1.push(x);
    }

    int pop() {
        if (stack2.isEmpty()) {
            while (!stack1.isEmpty()) {
                stack2.push(stack1.pop());
            }
        }
        return stack2.pop();
    }

    int peek() {
        if (stack2.isEmpty()) {
            while (!stack1.isEmpty()) {
                stack2.push(stack1.pop());
            }
        }
        return stack2.peek();
    }

    boolean empty() {
        return stack1.isEmpty() && stack2.isEmpty();
    }
}
```

**My doubt:** Why only pour when stack2 is empty?
**Answer:** If stack2 already has elements they're already in correct FIFO order. Pouring again would mess up the order. Only pour when stack2 runs out.

**My doubt:** Why does pouring reverse the order?
**Answer:** stack1=[1,2,3] (3 on top). Pouring into stack2: pop 3→push, pop 2→push, pop 1→push. stack2=[3,2,1] (1 on top). Now 1 comes out first = FIFO! ✅

---

### Circular Queue
**Difficulty: Medium**

Fixed size queue where end connects back to beginning using modulo (%).

- `front` — index to dequeue from
- `rear` — index to enqueue to
- `size` — current elements
- `capacity` — max elements
- Wrap around: `rear = (rear + 1) % capacity`

```java
class CircularQueue {
    int[] arr;
    int capacity, size, front, rear;

    CircularQueue(int capacity) {
        this.capacity = capacity;
        arr = new int[capacity];
        size = 0;
        front = 0;
        rear = 0;
    }

    void enqueue(int x) {
        if (isFull()) { System.out.println("Queue full!"); return; }
        arr[rear] = x;
        rear = (rear + 1) % capacity;
        size++;
    }

    int dequeue() {
        if (isEmpty()) { System.out.println("Queue empty!"); return -1; }
        int element = arr[front];
        front = (front + 1) % capacity;
        size--;
        return element;
    }

    boolean isEmpty() { return size == 0; }
    boolean isFull()  { return size == capacity; }
}
```

**My doubt:** How is the end connected to start?
**Answer:** Not physically connected — modulo (%) wraps the index. `(4+1) % 5 = 0` — goes back to start automatically!

**My doubt:** Why track size separately?
**Answer:** Without size you can't distinguish full vs empty — both cases have front==rear. Size removes ambiguity.

---

## 💡 Interview Tips

- ✅ Stack problems — always think: what information do I need to keep track of?
- ✅ NGE pattern — right to left + stack of candidates
- ✅ Span pattern — left to right + stack of indices
- ✅ When you see "next greater/smaller" → think stack immediately
- ✅ Queue using stacks — only pour stack1→stack2 when stack2 is empty
- ✅ Circular Queue — always use modulo for front/rear movement

---

## 📎 LeetCode Problems

| # | Problem | Difficulty | Pattern | Status |
|---|---------|------------|---------|--------|
| 20 | Valid Parentheses | Easy | Stack | ✅ |
| 232 | Implement Queue using Stacks | Medium | Two Stacks | ✅ |
| 155 | Min Stack | Medium | Stack | ⬜ |
| 739 | Daily Temperatures | Medium | NGE pattern | ⬜ |
| 84 | Largest Rectangle in Histogram | Hard | Stack | ✅ |
| 239 | Sliding Window Maximum | Hard | Deque | ⬜ |

---

## 🔗 Related Topics

- **Linked Lists** — Stack/Queue can be implemented using linked list
- **Trees** — BFS uses Queue, DFS uses Stack
- **Graphs** — BFS uses Queue, DFS uses Stack