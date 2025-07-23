// Problem Statement:
// You are given a string s and two integers x and y. You can perform two types of operations any number of times.
// 1. Remove substring "ab" and gain x points.
// 2. Remove substring "ba" and gain y points.
// Return the maximum points you can gain after applying the above operations on s.

// Approach:
// The key insight is that to maximize the score, we should prioritize removing the substring that gives more points.
// For example, if x > y, we should try to remove as many "ab" substrings as possible first, then "ba".
// If y > x, we should prioritize "ba" first.
// If x == y, the order doesn't matter.

// This greedy approach works because "ab" and "ba" operations do not interfere with each other in a way that
// performing one first would prevent a greater total score. When we remove "ab", we reduce the number of 'a' and 'b' characters.
// Similarly for "ba". By prioritizing the higher-scoring pair, we ensure that we get the maximum points from that pair,
// and any remaining characters can then form pairs of the lower-scoring type.

// We can use a stack or a string builder to simulate the removal process.
// Let's assume x >= y (we prioritize "ab").
// First pass: Iterate through the string. If the current character is 'b' and the top of the stack is 'a', it forms "ab".
// Pop 'a' and add x to the score. Otherwise, push the current character onto the stack.
// After the first pass, the remaining characters in the stack form a new string where all "ab" pairs have been removed.
// Second pass: Now, process this new string for "ba" pairs. Iterate through the new string (or the characters in the stack).
// If the current character is 'a' and the top of the temporary stack is 'b', it forms "ba".
// Pop 'b' and add y to the score. Otherwise, push the current character.

// If y > x (we prioritize "ba"), the logic is similar, just swap 'a' and 'b' and x and y in the first pass.

// Time Complexity: O(N)
// We iterate through the string a maximum of two times. Each character is pushed and popped from the stack at most once.

// Space Complexity: O(N)
// In the worst case, the stack can store all characters of the string (e.g., "aaaaa" or "bbbbb").


class Solution {
    public int maximumGain(String s, int x, int y) {
        if (x > y) {
            Result r1 = removePair(s, 'a', 'b', x);
            Result r2 = removePair(r1.updatedString, 'b', 'a', y);
            return r1.score + r2.score;
        } else {
            Result r1 = removePair(s, 'b', 'a', y);
            Result r2 = removePair(r1.updatedString, 'a', 'b', x);
            return r1.score + r2.score;
        }
    }

    private Result removePair(String s, char first, char second, int points) {
        StringBuilder sb = new StringBuilder();
        int score = 0;

        for (char c : s.toCharArray()) {
            int len = sb.length();
            if (len > 0 && sb.charAt(len - 1) == first && c == second) {
                sb.deleteCharAt(len - 1);
                score += points;
            } else {
                sb.append(c);
            }
        }

        return new Result(sb.toString(), score);
    }
    private static class Result {
        String updatedString;
        int score;

        Result(String updatedString, int score) {
            this.updatedString = updatedString;
            this.score = score;
        }
    }
}
