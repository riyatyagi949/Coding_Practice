/**
 * Problem Statement:
 * Given an array of integers arr[] representing non-negative integers, arrange them so that after concatenating all of them in order, it results in the largest possible number. Since the result may be very large, return it as a string.
 * Examples:
 * Input: arr[] = [3, 30, 34, 5, 9], Output: 9534330
 * Input: arr[] = [54, 546, 548, 60], Output: 6054854654
 * Input: arr[] = [3, 4, 6, 5, 9], Output: 96543
 * Constraints: 1 <= arr.size() <= 10^5, 0 <= arr[i] <= 10^5
 */
 /**
     * Approach:
     * The core idea is to sort the numbers as strings based on a custom comparison rule.
     * For any two numbers 'a' and 'b', we compare the concatenated strings "a" + "b" and "b" + "a".
     * If "b" + "a" is lexicographically greater than "a" + "b", then 'b' should come before 'a' in the sorted arrangement to form the largest number.
     * This custom comparator effectively places the strings in an order that maximizes the final concatenated number.
     *
     * Time Complexity: O(N log N * K), where N is the number of elements in the array and K is the average number of digits. The sorting takes O(N log N) comparisons, and each comparison involves string concatenation and comparison, which takes O(K) time.
     *
     * Space Complexity: O(N * K) to store the string representations of the numbers.
     */

//  Optimal Solution - 

   import java.util.*;

    class Solution {
    public String findLargest(int[] arr) {
        String[] strArr = new String[arr.length];

        for (int i = 0; i < arr.length; i++) {
            strArr[i] = String.valueOf(arr[i]);
        }
        Arrays.sort(strArr, (a, b) -> (b + a).compareTo(a + b));

        if (strArr[0].equals("0")) {
            return "0";
        }
        StringBuilder result = new StringBuilder();
        for (String s : strArr) {
            result.append(s);
        }

        return result.toString();
    }
}
