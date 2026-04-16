/**
 * Problem: Longest Substring Without Repeating Characters
 * Link: https://leetcode.com/problems/longest-substring-without-repeating-characters/
 * Difficulty: Medium
 *
 * Topic: Strings
 * Pattern: Sliding Window + HashMap
 *
 * ─────────────────────────────────────────────
 * APPROACH
 * ─────────────────────────────────────────────
 * 1. Use two pointers left and right — both start at 0
 * 2. Expand window by moving right
 * 3. For each character at right:
 *    - If in map AND index >= left → repeat in current window!
 *      Move left to map.get(char) + 1
 *    - Update map with current index (always)
 *    - Update maxLength = max(maxLength, right - left + 1)
 * 4. Return maxLength
 *
 * Key Insight: HashMap stores character → last seen index.
 *              Only treat as repeat if previous index >= left
 *              (inside current window). If outside window, ignore.
 *
 * ─────────────────────────────────────────────
 * COMPLEXITY
 * ─────────────────────────────────────────────
 * Time:  O(n)  — single pass, right moves n times
 * Space: O(n)  — HashMap stores at most n characters
 *
 * ─────────────────────────────────────────────
 * DRY RUN
 * ─────────────────────────────────────────────
 * Input: s = "abcab"
 *
 * right=0('a'): not in map → put {a:0}, maxLength=1
 * right=1('b'): not in map → put {a:0,b:1}, maxLength=2
 * right=2('c'): not in map → put {a:0,b:1,c:2}, maxLength=3
 * right=3('a'): in map at 0, 0>=left(0) → repeat! left=1
 *               put {a:3,b:1,c:2}, maxLength=max(3,3-1+1)=3
 * right=4('b'): in map at 1, 1>=left(1) → repeat! left=2
 *               put {a:3,b:4,c:2}, maxLength=max(3,4-2+1)=3
 *
 * Output: 3 ("abc") ✅
 */

import java.util.HashMap;

class LongestSubstringWithoutRepeating {
    public int lengthOfLongestSubstring(String s) {
        int left = 0, right = 0;
        HashMap<Character, Integer> map = new HashMap<>();
        int maxLength = 0;

        while (right < s.length()) {
            if (map.containsKey(s.charAt(right)) && map.get(s.charAt(right)) >= left) {
                left = map.get(s.charAt(right)) + 1;
            }
            maxLength = Math.max(maxLength, right - left + 1);
            map.put(s.charAt(right), right);
            right++;
        }

        return maxLength;
    }
}

/*
 * ─────────────────────────────────────────────
 * ALTERNATIVE APPROACHES
 * ─────────────────────────────────────────────
 * Brute Force:  O(n²) or O(n³) time, O(n) space
 *   → Check every substring, use HashSet to check for repeats
 *
 * Optimal (Sliding Window + HashMap): O(n) time, O(n) space ✅
 *
 * ─────────────────────────────────────────────
 * COMMON MISTAKES
 * ─────────────────────────────────────────────
 * ❌ Using > left instead of >= left
 *    → misses case where repeat is exactly at left boundary
 *    → e.g. "aa" → without >=, second 'a' not treated as repeat
 * ❌ Putting maxLength update inside if block
 *    → misses updates when window grows without repeats
 *    → e.g. "abc" → if never executes, maxLength stays 0
 * ❌ Using s.charAt(left) instead of s.charAt(right) when moving left
 *    → left is not where the repeat is, right is
 * ❌ Starting right = 1 instead of 0 — skips first character
 *
 * ─────────────────────────────────────────────
 * MY DOUBTS DURING LEARNING (for revision)
 * ─────────────────────────────────────────────
 * Q: Why left = map.get(s.charAt(right)) + 1 and not just map.get()?
 * A: map.get() gives index of previous occurrence. New window should
 *    start ONE AFTER that — otherwise old duplicate is still in window.
 *
 * Q: Why map.get(s.charAt(right)) >= left and not just containsKey?
 * A: Character might be in map but from a PREVIOUS window (index < left).
 *    That's not a real repeat in current window.
 *    e.g. "tmmzuxt" — first 't' at index 0, when we see 't' again at
 *    index 6, left is already at 3. Index 0 < left(3) → not a repeat!
 *    Without >= left check, left would move backwards to index 1. ❌
 *
 * Q: Why is maxLength outside the if block?
 * A: Max length should update every step — repeat or not.
 *    If only inside if block, strings with no repeats (like "abc")
 *    would never update maxLength and return 0. ❌
 *
 * Q: Why use s.charAt(right) not s.charAt(left) when moving left?
 * A: The repeat character was found at RIGHT pointer, not left.
 *    Using left gives wrong character → wrong index → wrong left position.
 *    e.g. "abcb" right=3('b'), left=0('a') → map.get('a') gives index
 *    of 'a' not 'b' → moves left to wrong position. ❌
 *
 * ─────────────────────────────────────────────
 * SIMILAR PROBLEMS
 * ─────────────────────────────────────────────
 * - #424 Longest Repeating Character Replacement — Medium (Sliding Window)
 * - #76  Minimum Window Substring — Hard (Sliding Window)
 * - #567 Permutation in String — Medium (Sliding Window)
 */