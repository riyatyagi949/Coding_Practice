// Problem Statement:
// You are given a lowercase string s, determine the total number of distinct strings that can be formed using the following rules:
// 1. Identify all unique vowels (a, e, i, o, u) present in the string.
// 2. For each distinct vowel, choose exactly one of its occurrences from s. If a vowel appears multiple times, each occurrence represents a unique selection choice.
// 3. Generate all possible permutations of the selected vowels. Each unique arrangement counts as a distinct string.
// Return the total number of such distinct strings.

// Approach:
// The problem asks us to count the number of unique strings that can be formed by selecting exactly one occurrence of each unique vowel present in the input string 's' and then permuting these selected vowels.

// Let's break down the rules:
// 1. Identify all unique vowels (a, e, i, o, u) present in the string.
//    We need to find which of 'a', 'e', 'i', 'o', 'u' exist in 's'.
// 2. For each distinct vowel, choose exactly one of its occurrences from s. If a vowel appears multiple times, each occurrence represents a unique selection choice.
//    This is crucial. If 'a' appears 3 times (e.g., "banana"), and 'i' appears 2 times (e.g., "india"), and 'o' appears 1 time (e.g., "hello"),
//    and we consider 'a', 'i', 'o' as our unique vowels.
//    For 'a', we have 3 choices.
//    For 'i', we have 2 choices.
//    For 'o', we have 1 choice.
//    The total number of ways to pick one occurrence of each of these unique vowels is (count of 'a') * (count of 'i') * (count of 'o').
// 3. Generate all possible permutations of the selected vowels. Each unique arrangement counts as a distinct string.
//    If we have 'k' unique vowels selected (e.g., 'a', 'i', 'o'), then the number of permutations of these 'k' distinct items is k!.

// Combining these:
// First, count the occurrences of each vowel in the string 's'.
// Keep track of which vowels are present.
// Let `vowel_counts` be a map or array storing counts of 'a', 'e', 'i', 'o', 'u'.
// Let `present_vowels_count` be the number of unique vowels found in 's'.

// The number of ways to select one occurrence of each present unique vowel is the product of their individual counts.
// Let `selection_ways = 1`. For each vowel `v` in {'a', 'e', 'i', 'o', 'u'}, if `vowel_counts[v] > 0`, then `selection_ways *= vowel_counts[v]`.

// The number of permutations for `present_vowels_count` distinct items is `present_vowels_count!`.

// The total number of distinct strings is `selection_ways * present_vowels_count!`.

// Example: s = "aacidf"
// Vowel counts:
// 'a': 2
// 'e': 0
// 'i': 1
// 'o': 0
// 'u': 0

// Unique vowels present: 'a', 'i'.
// present_vowels_count = 2.

// Selection ways: (count of 'a') * (count of 'i') = 2 * 1 = 2.
// These selections are: (first 'a', 'i') or (second 'a', 'i').

// Permutations for 2 unique vowels ('a', 'i'): 2! = 2.
// The permutations for ('a', 'i') are "ai", "ia".

// Total distinct strings = selection_ways * permutations = 2 * 2 = 4.
// If we picked (first 'a', 'i'): "ai", "ia"
// If we picked (second 'a', 'i'): "ai", "ia"
// Even though the actual selected characters might be different occurrences, their values are the same ('a' and 'i'), so the permutations formed will be "ai" and "ia" in both cases. The crucial part is that the selection choices multiply, not the resulting strings themselves. The problem implies that "ai" formed from (first 'a', 'i') is distinct from "ai" formed from (second 'a', 'i') if the underlying occurrences are distinct. This interpretation comes from "Each unique arrangement counts as a distinct string." and "If a vowel appears multiple times, each occurrence represents a unique selection choice."

// Let's re-read carefully: "Each unique arrangement counts as a distinct string." and "For each distinct vowel, choose exactly one of its occurrences from s. If a vowel appears multiple times, each occurrence represents a unique selection choice."

// The example "aacidf" output 4.
// Unique vowels: 'a', 'i'.
// Count of 'a': 2 (let's say a1, a2)
// Count of 'i': 1 (let's say i1)

// Possible selections of one 'a' and one 'i':
// (a1, i1)
// (a2, i1)

// For selection (a1, i1), permutations are:
// a1i1 (conceptually "ai")
// i1a1 (conceptually "ia")

// For selection (a2, i1), permutations are:
// a2i1 (conceptually "ai")
// i1a2 (conceptually "ia")

// The problem implies that (a1,i1) as a choice and (a2,i1) as a choice are distinct. And then for each choice, we permute.
// So, the final result is (number of ways to choose occurrences) * (factorial of unique vowel count).

// Time Complexity:
// O(N) where N is the length of the string `s`, to iterate through the string and count vowel occurrences.
// Factorial calculation is for a maximum of 5 (5!), which is a constant time operation.
// So, the overall time complexity is O(N).

// Space Complexity:
// O(1) as we use a fixed-size map or array (size 5 for vowels) to store counts.
// Overall space complexity is O(1).

import java.util.*;

class Solution {
    public int vowelCount(String s) {
        Map<Character, List<Integer>> map = new HashMap<>();
        Set<Character> vowels = Set.of('a', 'e', 'i', 'o', 'u');

        // Store indices of each vowel
        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            if (vowels.contains(c)) {
                map.computeIfAbsent(c, k -> new ArrayList<>()).add(i);
            }
        }

        // Generate all combinations by picking one occurrence of each vowel
        List<List<Integer>> positions = new ArrayList<>(map.values());
        List<List<Integer>> combos = new ArrayList<>();
        backtrack(positions, 0, new ArrayList<>(), combos);

        // Generate permutations of each combo and count unique strings
        Set<String> result = new HashSet<>();
        for (List<Integer> combo : combos) {
            List<Character> chars = new ArrayList<>();
            for (int idx : combo) chars.add(s.charAt(idx));
            permute(chars, 0, result);
        }

        return result.size();
    }

    void backtrack(List<List<Integer>> lists, int idx, List<Integer> path, List<List<Integer>> res) {
        if (idx == lists.size()) {
            res.add(new ArrayList<>(path));
            return;
        }
        for (int val : lists.get(idx)) {
            path.add(val);
            backtrack(lists, idx + 1, path, res);
            path.remove(path.size() - 1);
        }
    }

    void permute(List<Character> list, int start, Set<String> res) {
        if (start == list.size()) {
            StringBuilder sb = new StringBuilder();
            for (char c : list) sb.append(c);
            res.add(sb.toString());
            return;
        }
        for (int i = start; i < list.size(); i++) {
            Collections.swap(list, i, start);
            permute(list, start + 1, res);
            Collections.swap(list, i, start);
        }
    }
}
