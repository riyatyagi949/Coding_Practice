/*
Problem Statement:
You are given an integer n representing the number of digits in a number, and an array arr[] containing digits from 0 to 9. You have to count how many n-digit positive integers can be formed such that at least one digit from the array arr[] appears in the number.

Approach:
The problem asks us to count n-digit numbers that contain at least one digit from a given array `arr`. It's often easier to solve "at least one" problems by using the principle of inclusion-exclusion. In this case, we can calculate the total number of n-digit numbers and subtract the number of n-digit numbers that *do not* contain any digit from `arr`.

1. Total n-digit numbers:
   - For a 1-digit number, there are 9 possibilities (1-9).
   - For an n-digit number, the first digit cannot be 0. So, there are 9 choices for the first digit (1-9).
   - For the remaining (n-1) digits, there are 10 choices each (0-9).
   - Total n-digit numbers = 9 * 10^(n-1).

2. Number of n-digit numbers that do *not* contain any digit from `arr`:
   - First, identify the set of "allowed" digits, which are digits from 0-9 that are *not* present in `arr`.
   - Let `count_allowed_digits` be the number of digits in the set {0, 1, ..., 9} \ `arr`.
   - For the first digit of an n-digit number:
     - It cannot be 0.
     - It must be one of the `allowed` digits.
     - So, we count how many non-zero digits are in the `allowed` set. Let this be `count_allowed_non_zero_digits`.
   - For the remaining (n-1) digits:
     - They can be any of the `count_allowed_digits`.
   - Number of n-digit numbers without any digit from `arr` = `count_allowed_non_zero_digits` * `count_allowed_digits`^(n-1).

3. Final Result:
   - Result = (Total n-digit numbers) - (Number of n-digit numbers that do not contain any digit from `arr`).

Example Walkthrough: n=2, arr=[3, 5]
1. Total 2-digit numbers: 9 * 10^(2-1) = 9 * 10 = 90 (from 10 to 99).

2. Numbers that do not contain 3 or 5:
   - Digits in `arr`: {3, 5}
   - Allowed digits (not in `arr`): {0, 1, 2, 4, 6, 7, 8, 9}. `count_allowed_digits` = 8.
   - Allowed non-zero digits: {1, 2, 4, 6, 7, 8, 9}. `count_allowed_non_zero_digits` = 7.
   - Number of 2-digit numbers without 3 or 5:
     - First digit can be any of {1, 2, 4, 6, 7, 8, 9} (7 choices).
     - Second digit can be any of {0, 1, 2, 4, 6, 7, 8, 9} (8 choices).
     - Total = 7 * 8 = 56.

3. Result: 90 - 56 = 34.

Time Complexity:
- O(1) for boolean array initialization (constant size 10).
- O(arr.length) to mark digits present in `arr`.
- O(10) to count allowed digits (constant size 10).
- Calculating powers: O(n) for Math.pow, or O(log n) for efficient power calculation, but given n <= 9, it's effectively O(1).
- Overall time complexity: O(1) (constant time operations dominate as array size and n are small constants).

Space Complexity:
- O(1) for the boolean array `presentInArr` (constant size 10).
- Overall space complexity: O(1).
*/

class Solution {
    public int countValid(int n, int[] arr) {
        boolean[] hasDigit = new boolean[10];
        for (int digit : arr) {
            hasDigit[digit] = true;
        }

        int total;
        if (n == 1) {
            total = 9;
        } else {
            total = (int)Math.pow(10, n) - (int)Math.pow(10, n - 1);
        }

        boolean[] banned = new boolean[10];
        int bannedCount = 0;
        for (int i = 0; i < 10; i++) {
            if (!hasDigit[i]) {
                banned[i] = true;
                bannedCount++;
            }
        }

        if (bannedCount == 0) return 0;
        if (bannedCount == 10) return 0;

        int countBannedOnly = 0;
        for (int d = 1; d < 10; d++) {
            if (banned[d]) {
                countBannedOnly += countCombinations(d, banned, n - 1);
            }
        }

        return total - countBannedOnly;
    }
 private int countCombinations(int firstDigit, boolean[] banned, int remainingDigits) {
        int count = 1;
        for (int i = 0; i < remainingDigits; i++) {
            int options = 0;
            for (int d = 0; d < 10; d++) {
                if (banned[d]) options++;
            }
            count *= options;
        }
        return count;
    }
}
