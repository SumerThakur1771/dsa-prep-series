/**
 * Problem: Valid Palindrome
 * Link: https://leetcode.com/problems/valid-palindrome/
 * Difficulty: Easy
 *
 * Topic: Strings
 * Pattern: Two Pointers
 *
 * ─────────────────────────────────────────────
 * APPROACH
 * ─────────────────────────────────────────────
 * 1. Two pointers — i from start, j from end
 * 2. Skip non-alphanumeric characters on both sides
 * 3. Lowercase both characters and compare
 * 4. If mismatch → return false
 * 5. If pointers meet → return true
 *
 * Key Insight: No need to create a new cleaned string.
 *              Just skip invalid characters in place using
 *              inner while loops with i < j guard.
 *
 * ─────────────────────────────────────────────
 * COMPLEXITY
 * ─────────────────────────────────────────────
 * Time:  O(n)  — i and j together make at most n total moves.
 *               Nested loops don't mean O(n²) here because
 *               i and j never restart — they only move forward/backward.
 * Space: O(1)  — no extra string created, just two pointers
 *
 * ─────────────────────────────────────────────
 * DRY RUN
 * ─────────────────────────────────────────────
 * Input: s = "A man, a plan, a canal: Panama"
 *
 * i=0('A'), j=29('a') → toLowerCase → 'a'=='a' ✅ → i++, j--
 * i=1(' ') → skip → i=2('m')
 * j=28('m') → 'm'=='m' ✅ → i++, j--
 * ... continues until i >= j
 *
 * Output: true
 *
 * Input: s = "race a car"
 * ... eventually 'e' != 'a' → return false
 * Output: false
 */

class ValidPalindrome {
    public boolean isPalindrome(String s) {
        int i = 0, j = s.length() - 1;

        while (i < j) {
            while (i < j && !Character.isLetterOrDigit(s.charAt(i))) i++;
            while (i < j && !Character.isLetterOrDigit(s.charAt(j))) j--;

            char left = Character.toLowerCase(s.charAt(i));
            char right = Character.toLowerCase(s.charAt(j));

            if (left != right) return false;
            i++;
            j--;
        }
        return true;
    }
}

/*
 * ─────────────────────────────────────────────
 * ALTERNATIVE APPROACHES
 * ─────────────────────────────────────────────
 * Brute Force (clean string first):  O(n) time, O(n) space
 *   → Remove non-alphanumeric, lowercase, check if equals reverse
 *   → Works but uses extra space
 *
 * Optimal (Two Pointers in place): O(n) time, O(1) space ✅
 *   → Skip invalid chars in place, compare directly
 *
 * ─────────────────────────────────────────────
 * COMMON MISTAKES
 * ─────────────────────────────────────────────
 * ❌ Missing i < j guard in inner while loops
 *    → i can go out of bounds if string has no alphanumeric chars
 *    → e.g. s = " " or s = ",." crashes without the guard
 * ❌ Forgetting i++ and j-- at end of outer while → infinite loop
 * ❌ Writing j++ instead of j-- when moving inward
 * ❌ Not storing toLowerCase result — it returns a value, doesn't modify in place
 *    → Wrong: Character.toLowerCase(s.charAt(i))
 *    → Right: char left = Character.toLowerCase(s.charAt(i))
 * ❌ Using if instead of while for skipping non-alphanumeric
 *    → if only skips once, while keeps skipping until valid char found
 *
 * ─────────────────────────────────────────────
 * MY DOUBTS DURING LEARNING (for revision)
 * ─────────────────────────────────────────────
 * Q: How to remove non-alphanumeric characters from string?
 * A: You don't need to remove them! Just skip over them using
 *    inner while loops. Move i forward and j backward until both
 *    pointers land on valid alphanumeric characters.
 *
 * Q: Is this O(n²) because of nested while loops?
 * A: No — it's O(n). i only moves right and j only moves left.
 *    Total moves across ALL loops combined = n.
 *    Nested loops are O(n²) only when inner loop RESTARTS from
 *    scratch for each outer iteration (like in 3Sum or brute force).
 *    Here the inner loops just continue from where they left off.
 *
 * Q: Why use while for inner skip loops instead of if?
 * A: if only skips ONE non-alphanumeric character. But there could
 *    be multiple consecutive ones (e.g. "a,,,b"). while keeps
 *    skipping until it finds a valid character.
 *
 * Q: Why add i < j inside inner while loops?
 * A: Without it, if the entire string is non-alphanumeric (e.g. " "),
 *    i keeps incrementing past s.length() → index out of bounds crash.
 *    i < j stops the skipping when pointers meet.
 *
 * ─────────────────────────────────────────────
 * SIMILAR PROBLEMS
 * ─────────────────────────────────────────────
 * - #234 Palindrome Linked List — Easy (Fast/Slow + Reverse)
 * - #647 Palindromic Substrings — Medium (Expand Center)
 * - #5   Longest Palindromic Substring — Medium (Expand Center)
 */