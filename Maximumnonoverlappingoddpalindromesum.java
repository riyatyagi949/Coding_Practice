// Problem Statement:
// Given a string s consisting of lowercase English letters, find the maximum possible sum of the lengths of any two non-empty and non-overlapping palindromic substrings of odd length.
// Formally, choose any two substrings s[i...j] and s[k...l] such that 1 <= i <= j < k <= l <= s.size(), both substrings are palindromes of odd length, and they do not overlap. Return the maximum sum of their lengths.
// Note: A palindrome is a string that reads the same forward and backward. A substring is a contiguous sequence of characters within the string.

// Approach:
// The problem asks for the maximum sum of lengths of two non-overlapping odd-length palindromic substrings. Let's say the two substrings are s[i...j] and s[k...l] where j < k. To maximize the sum of lengths, we need to find the longest possible odd-length palindrome that ends at or before a certain index `p`, and the longest possible odd-length palindrome that starts at or after index `p + 1`.
// We can use a dynamic programming approach with two arrays:
// 1. `leftMax[i]`: Stores the maximum length of an odd-length palindromic substring ending at index `i`.
// 2. `rightMax[i]`: Stores the maximum length of an odd-length palindromic substring starting at index `i`.
// The final answer will be the maximum value of `leftMax[i] + rightMax[i+1]` for all valid `i`.

// To populate `leftMax` and `rightMax` efficiently, we can use the "expand from center" technique to find all odd-length palindromes.
// We iterate through each character of the string `s` as a potential center of an odd-length palindrome.
// For each center `i`, we expand outwards to find the longest palindrome centered at `i`. Let the length of this palindrome be `len`. This palindrome extends from `i - (len - 1)/2` to `i + (len - 1)/2`.
// We can use this information to update our `leftMax` and `rightMax` arrays.
// For a palindrome of length `len` centered at `i`, its start index is `i - (len - 1)/2` and end index is `i + (len - 1)/2`.
// We can update `leftMax` and `rightMax` as follows:
// For `leftMax`, as we find palindromes, we update the `leftMax` array. `leftMax[j]` will be the maximum length of an odd-length palindrome ending at `j`. We can do a prefix-max sweep to ensure that `leftMax[i]` contains the max length ending at or before `i`.
// For `rightMax`, similarly, we update the `rightMax` array and then perform a suffix-max sweep.

// Let's refine the approach:
// 1. Create two arrays, `maxLenEndingHere` and `maxLenStartingHere`, both of size `s.length()`. Initialize with zeros.
// 2. Iterate through the string from `i = 0` to `s.length() - 1`. For each `i`, expand from center `i` to find the lengths of all odd-length palindromes centered at `i`.
// 3. For each palindrome of length `len` centered at `i`, which starts at index `start = i - (len - 1)/2` and ends at index `end = i + (len - 1)/2`, we update:
//    `maxLenEndingHere[end] = max(maxLenEndingHere[end], len)`.
//    `maxLenStartingHere[start] = max(maxLenStartingHere[start], len)`.
// 4. After iterating through all possible centers, we do two passes:
//    - For `maxLenEndingHere`, a prefix-max pass: `maxLenEndingHere[i] = max(maxLenEndingHere[i], maxLenEndingHere[i-1])` for `i > 0`.
//    - For `maxLenStartingHere`, a suffix-max pass: `maxLenStartingHere[i] = max(maxLenStartingHere[i], maxLenStartingHere[i+1])` for `i < s.length() - 1`.
// 5. Finally, iterate from `i = 0` to `s.length() - 2` and find the maximum sum `max(maxLenEndingHere[i] + maxLenStartingHere[i+1])`.

// Time Complexity:
// O(N^2) where N is the length of the string. The "expand from center" technique takes O(N^2) in the worst case (e.g., for a string of all 'a's).
// The prefix/suffix max passes take O(N).
// The final iteration to find the max sum takes O(N).
// Overall time complexity is dominated by the palindrome finding part.

// Space Complexity:
// O(N) for the two auxiliary arrays.

