// Problem Statement:
// Given a string s, find all possible ways to partition it such that every substring in the partition is a palindrome.

// Approach:
// This problem can be solved using a backtracking approach. We will iterate through all possible prefixes of the given string. If a prefix is a palindrome, we consider it as a part of a valid partition and recursively call the function for the remaining suffix of the string.
//
// Let's define a helper function, say `backtrack(start, currentPartition, result, s)`.
// - `start`: The starting index in the string `s` for the current partition.
// - `currentPartition`: A list of strings representing the current partition being built.
// - `result`: A list of lists of strings to store all valid partitions.
// - `s`: The input string.
//
// The base case for the recursion is when `start` reaches the end of the string `s`. At this point, `currentPartition` represents a valid palindromic partition, so we add a copy of it to `result`.
//
// In the recursive step, we iterate from `start` to the end of the string. For each substring `s.substring(start, i + 1)`:
// 1. Check if the substring is a palindrome using a helper function `isPalindrome(String str)`.
// 2. If it is a palindrome, add it to `currentPartition`.
// 3. Recursively call `backtrack(i + 1, currentPartition, result, s)` to find partitions for the remaining string.
// 4. After the recursive call returns, backtrack by removing the last added substring from `currentPartition` to explore other possibilities.
//
// The `isPalindrome` function simply checks if a given string reads the same forwards and backwards.

// Time Complexity:
// The time complexity is difficult to express precisely with Big O notation due to the exponential nature of partitions and palindrome checks. In the worst case, it can be exponential.
// Let N be the length of the string s.
// For each starting position, we iterate through N possible ending positions. Inside the loop, we check for palindrome which takes O(length of substring).
// The number of palindromic partitions can be exponential. For a string of length N, in the worst case, the number of partitions can be proportional to 2^N.
// Each partition can have at most N substrings. Copying a partition of N substrings takes O(N) time.
// Therefore, a loose upper bound could be O(N * 2^N) in the worst case, considering all possible substrings and their palindrome checks and the number of recursive calls.

// Space Complexity:
// The space complexity is also dependent on the number of partitions and the length of the string.
// - `result` can store up to O(2^N) partitions. Each partition can have up to O(N) substrings.
// - The recursion depth can go up to O(N).
// - `currentPartition` list stores at most O(N) substrings.
// Therefore, the space complexity is O(N * 2^N) to store all the partitions.

// Optimal Solution:
class Solution {
    public ArrayList<ArrayList<String>> palinParts(String s) {
        ArrayList<ArrayList<String>> result = new ArrayList<>();
        ArrayList<String> currentPartition = new ArrayList<>();
        backtrack(0, currentPartition, result, s);
        return result;
    }
 private void backtrack(int start, ArrayList<String> currentPartition, ArrayList<ArrayList<String>> result, String s) {
        if (start == s.length()) {
            result.add(new ArrayList<>(currentPartition));
            return;
        }
      for (int i = start; i < s.length(); i++) {
            String sub = s.substring(start, i + 1);
            if (isPalindrome(sub)) {
                currentPartition.add(sub);
                backtrack(i + 1, currentPartition, result, s);
                currentPartition.remove(currentPartition.size() - 1); 
            }
        }
    }
     private boolean isPalindrome(String str) {
        int left = 0;
        int right = str.length() - 1;
        while (left < right) {
            if (str.charAt(left) != str.charAt(right)) {
                return false;
            }
            left++;
            right--;
        }
        return true;
    }
}