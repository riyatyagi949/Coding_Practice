/**
 * Problem Statement: Strings Rotations of Each Other
 * --------------------------------------------------
 * You are given two strings s1 and s2, of equal lengths. The task is to check if s2 
 * is a rotated version of the string s1.
 * * Note: A string is a rotation of another if it can be formed by moving characters 
 * from the start to the end (or vice versa) without rearranging them.
 * * Examples:
 * Input: s1 = "abcd", s2 = "cdab" -> Output: true
 * Input: s1 = "abcd", s2 = "acbd" -> Output: false
 * * Constraints:
 * 1 <= s1.size(), s2.size() <= 10^5
 * s1, s2 consists of lowercase English alphabets.
 *//**
     * Optimal Solution: String Concatenation and Substring Search
     * -----------------------------------------------------------
     * A string s2 is a rotation of string s1 if and only if s2 is a substring of the 
     * string formed by concatenating s1 with itself (s1 + s1).
     * * Let N be the length of s1 and s2.
     * Consider s1 = "ABCD". s1 + s1 = "ABCDABCD".
     * Any rotation of s1 ("BCDA", "CDAB", "DABC") must appear as a substring within 
     * "ABCDABCD".
     * - Rotation "CDAB" is found starting at index 2 of s1 + s1.
     * * * Algorithm Steps:
     * 1. Check Lengths: If s1 and s2 have different lengths, they cannot be rotations. Return false.
     * 2. Concatenate: Create a new string temp = s1 + s1.
     * 3. Search: Check if s2 is a substring of temp. If it is, return true. Otherwise, return false.
     * * This approach is efficient because modern string searching algorithms (like those 
     * implemented in Java's `indexOf` or `contains`) can find a substring in O(N) time.
     */
class Solution {
  public boolean areRotations(String s1, String s2) {
        if (s1.length() != s2.length()) {
            return false;
        }
            if (s1.length() == 0) {
            return true;
        }
        String temp = s1 + s1;
        return temp.contains(s2);
    }
}
/**
 * Time Complexity Analysis:
 * -------------------------
 * O(N), where N is the length of s1 and s2.
 * - Concatenation (s1 + s1) takes O(N) time.
 * - Substring search (`temp.contains(s2)`) uses optimized algorithms and takes O(N) time.
 * - Overall complexity is O(N).
 * * Space Complexity Analysis:
 * --------------------------
 * O(N), where N is the length of s1 and s2.
 * - O(N) space is used to store the concatenated string `temp` (length 2N).
 */
