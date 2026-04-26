/**
 * Problem: Group Anagrams
 * Link: https://leetcode.com/problems/group-anagrams/
 * Difficulty: Medium
 *
 * Companies: Amazon, Google, Facebook, Apple, Microsoft, Bloomberg,
 *            Adobe, Uber, Goldman Sachs, Oracle, Salesforce and more
 *
 * Topic: Strings + Hashing
 * Pattern: HashMap + Sorting
 *
 * ─────────────────────────────────────────────
 * APPROACH
 * ─────────────────────────────────────────────
 * 1. For each string, sort its characters → gives a key
 *    All anagrams produce the same sorted string
 *    e.g. "eat", "tea", "ate" all → "aet"
 * 2. Use sorted string as key in HashMap
 *    Value = list of original strings sharing that key
 * 3. Return all values from the map
 *
 * Key Insight: Anagrams are identical when sorted.
 *              Use sorted version as HashMap key to group them.
 *              Always add ORIGINAL string to list, not sorted version.
 *
 * ─────────────────────────────────────────────
 * COMPLEXITY
 * ─────────────────────────────────────────────
 * Time:  O(n * k log k) — n strings, each sorted in k log k
 * Space: O(n * k)       — storing all strings in HashMap
 *
 * ─────────────────────────────────────────────
 * DRY RUN
 * ─────────────────────────────────────────────
 * Input: strs = ["eat", "tea", "tan", "ate", "nat", "bat"]
 *
 * str="eat" → key="aet" → map={"aet":["eat"]}
 * str="tea" → key="aet" → map={"aet":["eat","tea"]}
 * str="tan" → key="ant" → map={"aet":["eat","tea"], "ant":["tan"]}
 * str="ate" → key="aet" → map={"aet":["eat","tea","ate"], "ant":["tan"]}
 * str="nat" → key="ant" → map={"aet":["eat","tea","ate"], "ant":["tan","nat"]}
 * str="bat" → key="abt" → map={"aet":[...], "ant":[...], "abt":["bat"]}
 *
 * Output: [["eat","tea","ate"], ["tan","nat"], ["bat"]]
 */

import java.util.*;

class GroupAnagrams {
    public List<List<String>> groupAnagrams(String[] strs) {
        HashMap<String, List<String>> map = new HashMap<>();

        for (String str : strs) {
            char[] chars = str.toCharArray();  // "eat" → ['e','a','t']
            Arrays.sort(chars);                // ['e','a','t'] → ['a','e','t']
            String key = new String(chars);    // ['a','e','t'] → "aet"

            map.putIfAbsent(key, new ArrayList<>());  // create list if key new
            map.get(key).add(str);                    // add ORIGINAL string
        }

        return new ArrayList<>(map.values());
    }
}

/*
 * ─────────────────────────────────────────────
 * ALTERNATIVE APPROACHES
 * ─────────────────────────────────────────────
 * Brute Force:  O(n² * k) time
 *   → Compare every pair of strings, check if anagram
 *
 * Sort as key (above): O(n * k log k) time ✅
 *   → Sort each string, use as HashMap key
 *
 * Frequency count as key: O(n * k) time
 *   → Count frequency of each char (array of 26)
 *   → Use frequency array as key — avoids sorting
 *   → Slightly faster but more complex to implement
 *
 * ─────────────────────────────────────────────
 * COMMON MISTAKES
 * ─────────────────────────────────────────────
 * ❌ Adding sorted string to list instead of original
 *    → map.get(key).add(key) instead of map.get(key).add(str)
 * ❌ Using chars.sort() → arrays don't have .sort()
 *    → Use Arrays.sort(chars)
 * ❌ Returning map.values() directly — type mismatch
 *    → Wrap in new ArrayList<>(map.values())
 *
 * ─────────────────────────────────────────────
 * MY DOUBTS DURING LEARNING (for revision)
 * ─────────────────────────────────────────────
 * Q: Why can't we sort a String directly?
 * A: Strings are immutable in Java — can't modify them.
 *    Must convert to char[] first, sort that, convert back.
 *    str.toCharArray() → Arrays.sort() → new String(chars)
 *
 * Q: We're sorting but adding original str — won't they all be same?
 * A: No! str never changes. We only sort a COPY (chars array) to get
 *    the key. str stays as original "eat", "tea" etc.
 *    key = sorted version (for grouping)
 *    str = original version (what we store in list)
 *
 * Q: What does map.values() return?
 * A: All the values from the map — which are our grouped lists.
 *    Returns a Collection type not ArrayList, so we wrap in
 *    new ArrayList<>() to match the List return type.
 *
 * Q: Why new ArrayList<>(map.values()) and not just map.values()?
 * A: map.values() is a view tied to the map internally, not a
 *    standalone list. new ArrayList<>() creates independent copy
 *    that matches the required List<List<String>> return type.
 *
 * Q: What is putIfAbsent?
 * A: Only adds key with empty list if key doesn't exist yet.
 *    If key already exists, does nothing — existing list preserved.
 *    Alternative: if (!map.containsKey(key)) map.put(key, new ArrayList<>())
 *
 * Q: Why use HashMap<String, List<String>> not HashMap<String, String>?
 * A: Multiple strings can be anagrams of each other — need a LIST
 *    to store all of them under one key, not just one string.
 *
 * ─────────────────────────────────────────────
 * SIMILAR PROBLEMS
 * ─────────────────────────────────────────────
 * - #242 Valid Anagram — Easy (sort or frequency count)
 * - #438 Find All Anagrams in String — Medium (Sliding Window)
 * - #3   Longest Substring Without Repeating — Medium (Sliding Window)
 */