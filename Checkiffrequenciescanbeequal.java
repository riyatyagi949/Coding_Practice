// Problem Statement:
// Given a string s consisting only lowercase alphabetic characters, check whether it is possible to remove at most one character such that the frequency of each distinct character in the string becomes same. Return true if it is possible; otherwise, return false.

// Approach:
// The approach involves first calculating the frequency of each character in the string.
// Then, we analyze the frequencies to determine if they can be made equal by removing at most one character.
//
// 1. Calculate character frequencies:
//    Use a HashMap to store the frequency of each character in the input string `s`.
//
// 2. Count occurrences of each frequency:
//    Use another HashMap to store the count of how many times each frequency appears. For example, if characters 'a' and 'b' both appear 3 times, then the frequency '3' would have a count of 2.
//
// 3. Analyze the frequency counts:
//    a. If `countMap.size()` is 1:
//       This means all characters already have the same frequency. So, no removal is needed, and it's possible. Return true.
//
//    b. If `countMap.size()` is 2:
//       This means there are two distinct frequencies among the characters. We need to check if removing one character can make all frequencies equal.
//       Let the two frequencies be `freq1` and `freq2`, and their respective counts be `count1` and `count2`.
//
//       i. One of the frequencies is 1 and its count is 1:
//          If `freq1 == 1` and `count1 == 1`, it means there's exactly one character with a frequency of 1. Removing this character will make all other frequencies equal (assuming they were already equal or can be made equal with this removal). The same logic applies if `freq2 == 1` and `count2 == 1`.
//
//       ii. Frequencies differ by 1, and the higher frequency has a count of 1:
//           If the absolute difference between `freq1` and `freq2` is 1 (e.g., 3 and 2), and the higher of these two frequencies has a count of 1, it means there's exactly one character with that higher frequency. Removing one occurrence of this character will reduce its frequency to match the other frequency.
//           For example, if frequencies are {3:1, 2:X} where X is any count, removing one character from the one with frequency 3 makes it 2, thus all frequencies become 2.
//
//    c. If `countMap.size()` is greater than 2:
//       It's impossible to make all frequencies equal by removing at most one character. This is because removing one character can only affect the frequency of one character by decreasing it by one. If there are more than two distinct frequencies, one removal is not enough. Return false.

// Time Complexity:
// O(N), where N is the length of the input string `s`.
// - The first loop to calculate character frequencies iterates through the string once, taking O(N) time.
// - The second loop to calculate frequency counts iterates through the values of the character frequency map. In the worst case, this map can have up to 26 entries (for lowercase English alphabets), so this is O(1) in practice or O(min(N, K)) where K is alphabet size.
// - The subsequent checks are constant time operations.
// Therefore, the overall time complexity is O(N).

// Space Complexity:
// O(K), where K is the number of distinct characters in the string (at most 26 for lowercase English alphabets).
// - `freqMap` stores character frequencies, at most K entries.
// - `countMap` stores counts of frequencies, at most K entries.
// Therefore, the space complexity is O(1) in practice (since K is a small constant like 26) or O(K).

// Optimal Solution:-

import java.util.*;

class Solution {
    boolean sameFreq(String s) {
        Map<Character, Integer> freqMap = new HashMap<>();
        for (char c : s.toCharArray()) {
            freqMap.put(c, freqMap.getOrDefault(c, 0) + 1);
        }
        Map<Integer, Integer> countMap = new HashMap<>();
        for (int freq : freqMap.values()) {
            countMap.put(freq, countMap.getOrDefault(freq, 0) + 1);
        }
        if (countMap.size() == 1) {
            return true; 
        } 
        else if (countMap.size() == 2) {
            List<Integer> keys = new ArrayList<>(countMap.keySet());
            int freq1 = keys.get(0), freq2 = keys.get(1);
            int count1 = countMap.get(freq1), count2 = countMap.get(freq2);

            if ((freq1 == 1 && count1 == 1) || (freq2 == 1 && count2 == 1)) {
                return true;
            }
            if ((Math.abs(freq1 - freq2) == 1) && 
                ((freq1 > freq2 && count1 == 1) || (freq2 > freq1 && count2 == 1))) {
                return true;
            }
        }
       return false;
    }
}


