// Problem Statement:
// Given a string `word` representing the final output on Alice's screen. Alice might have pressed a key for too long at most once, causing a character to be typed multiple times. Return the total number of possible original strings Alice might have intended to type.

// Approach:
// The problem implies that we need to count unique strings that can be formed under two conditions:
// 1. The `word` itself (no key was pressed too long).
// 2. Exactly one block of consecutive identical characters in `word` originated from a shorter sequence in the original string due to a single "over-press" event.
// We parse the `word` into blocks of (character, count). We then iterate through each block. If a block has length `N > 1`, we generate `N-1` new strings by assuming the original count for that character was `1` to `N-1`, while keeping all other blocks unchanged. All generated strings are stored in a `HashSet` to ensure uniqueness.

// Time Complexity:
// O(L^2), where L is the length of the `word`.
// This is because parsing the string takes O(L). In the worst case (e.g., "aaaa..."), we might iterate L times in the inner loop, and each string construction can take O(L) time.

// Space Complexity:
// O(L^2) in the worst case.
// This is because the `HashSet` can store up to O(L) strings, and each string can have a maximum length of O(L). Therefore, the total space complexity for storing all unique strings is O(L * L) = O(L^2).

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

class Solution {
    public int possibleStringCount(String word) {
        Set<String> possibleStrings = new HashSet<>();
        possibleStrings.add(word); 

        List<Pair> blocks = new ArrayList<>();
        if (word.length() > 0) {
            char currentChar = word.charAt(0);
            int currentCount = 1;
            for (int i = 1; i < word.length(); i++) {
                if (word.charAt(i) == currentChar) {
                    currentCount++;
                } else {
                    blocks.add(new Pair(currentChar, currentCount));
                    currentChar = word.charAt(i);
                    currentCount = 1;
                }
            }
            blocks.add(new Pair(currentChar, currentCount));
        }
        for (int i = 0; i < blocks.size(); i++) {
            Pair currentBlock = blocks.get(i);
            if (currentBlock.count > 1) {
                for (int k = 1; k < currentBlock.count; k++) {
                    StringBuilder sb = new StringBuilder();
                    for (int j = 0; j < i; j++) {
                        appendCharRepeated(sb, blocks.get(j).character, blocks.get(j).count);
                    }
                    appendCharRepeated(sb, currentBlock.character, k);
                    for (int j = i + 1; j < blocks.size(); j++) {
                        appendCharRepeated(sb, blocks.get(j).character, blocks.get(j).count);
                    }
                    possibleStrings.add(sb.toString());
                }
            }
        }
        return possibleStrings.size();
    }

    private void appendCharRepeated(StringBuilder sb, char c, int count) {
        for (int i = 0; i < count; i++) {
            sb.append(c);
        }
    }

    private static class Pair {
        char character;
        int count;

        Pair(char character, int count) {
            this.character = character;
            this.count = count;
        }
    }
}