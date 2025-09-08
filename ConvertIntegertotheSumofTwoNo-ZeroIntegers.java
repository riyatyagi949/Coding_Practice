// Problem Statement:
// A "No-Zero integer" is a positive integer that does not contain any 0 in its decimal representation.
// Given an integer n, return a list of two integers [a, b] where:
// a and b are No-Zero integers.
// a + b = n.
// The test cases are generated so that there is at least one valid solution. If there are many valid solutions, you can return any of them.

// Approach:
// The problem asks us to find two No-Zero integers, a and b, such that their sum equals a given integer n.
// A simple and straightforward approach is to iterate through possible values for 'a' starting from 1 up to n-1.
// For each value of 'a', we can calculate the corresponding value of 'b' as `b = n - a`.
// Then, we check if both 'a' and 'b' are "No-Zero integers".
// To check if an integer is a No-Zero integer, we can convert it to a string and check if it contains the character '0'.
// Alternatively, we can use a loop and the modulo operator (`%`) to check each digit of the number.
// The loop `while (num > 0)` can be used. Inside the loop, `digit = num % 10` gives the last digit. If `digit == 0`, the number is not a No-Zero integer.
// We then update `num = num / 10` to move to the next digit.
// The first pair [a, b] that satisfies the condition is a valid solution, and since the problem guarantees a solution exists, we can return it immediately.

// Time Complexity:
// O(N * log N), where N is the input integer.
// We iterate from 1 to N-1. In each iteration, we check if two numbers (a and n-a) are No-Zero integers.
// The check for a No-Zero integer takes time proportional to the number of digits, which is log10(number).
// Since the numbers are at most N, the check takes O(log N).
// Therefore, the overall time complexity is O(N * log N).

// Space Complexity:
// O(1).
// We are only using a few variables to store the current numbers and do the checks.
// The space used does not depend on the input size N.

// Optimal  Solution in Java - 
class Solution {
    public int[] getNoZeroIntegers(int n) {
        for (int a = 1; a < n; a++) {
            int b = n - a;
            if (isNoZero(a) && isNoZero(b)) {
                return new int[]{a, b};
            }
        }
        return new int[]{-1, -1};
    }
    private boolean isNoZero(int num) {
        while (num > 0) {
            if (num % 10 == 0) 
            return false;
            num /= 10;
        }
        return true;
    }
}