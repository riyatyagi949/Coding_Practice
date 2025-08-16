/**
 * Problem Statement:
 * Given an array of integers arr[] representing non-negative integers, arrange them so that after concatenating all of them in order, it results in the largest possible number. Since the result may be very large, return it as a string.
 * Examples:
 * Input: arr[] = [3, 30, 34, 5, 9], Output: 9534330
 * Input: arr[] = [54, 546, 548, 60], Output: 6054854654
 * Input: arr[] = [3, 4, 6, 5, 9], Output: 96543
 * Constraints: 1 <= arr.size() <= 10^5, 0 <= arr[i] <= 10^5
 */
import java.util.Arrays;
import java.util.Comparator;

class Solution {
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
    public String printLargest(String[] arr) {
        Arrays.sort(arr, new Comparator<String>() {
            @Override
            public int compare(String s1, String s2) {
                String xy = s1 + s2;
                String yx = s2 + s1;
                return yx.compareTo(xy);
            }
        });

        if (arr[0].equals("0")) {
            return "0";
        }

        StringBuilder sb = new StringBuilder();
        for (String s : arr) {
            sb.append(s);
        }

        return sb.toString();
    }
}