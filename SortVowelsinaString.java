// Problem Statement:
// Given a string `s`, sort its vowels in non-decreasing order of their ASCII values while keeping the consonants in their original positions.
// The vowels are 'a', 'e', 'i', 'o', 'u' and their uppercase counterparts.

// Approach:
// A straightforward approach is to first identify and collect all the vowels from the input string. These vowels are then sorted based on their ASCII values.
// Finally, we iterate through the original string and construct a new string.
// If a character in the original string is a consonant, it's appended to the new string as is.
// If it's a vowel, we append the next vowel from our sorted list of vowels.
// This ensures that consonants remain in place and vowels are replaced by their sorted counterparts in the order they appear.

// Time Complexity: O(N log N)
// We iterate through the string twice (O(N)), and the sorting of vowels takes O(V log V) time, where V is the number of vowels. In the worst case, V is proportional to N, so the time complexity is O(N log N).

// Space Complexity: O(N)
// We use a list to store the vowels, which can take up to O(N) space in the worst case where the entire string consists of vowels.

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

class Solution {
    public String sortVowels(String s) {
        String vowels = "aeiouAEIOU";
        List<Character> list = new ArrayList<>();

        for (char c : s.toCharArray()) {
            if (vowels.indexOf(c) != -1) {
                list.add(c);
            }
        }
        Collections.sort(list);

        StringBuilder result = new StringBuilder();
        int idx = 0;

        for (char c : s.toCharArray()) {
            if (vowels.indexOf(c) != -1) {
                result.append(list.get(idx++));
            } 
            else {
                result.append(c);
            }
        }
        return result.toString();
    }
}