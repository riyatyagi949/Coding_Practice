/*
Problem Statement:
Given an array arr[] of positive integers, your task is to find the maximum possible sum of all elements that are not part of the Longest Increasing Subsequence (LIS).

Approach:
The problem asks us to find the maximum sum of elements NOT part of an LIS. This is equivalent to finding the total sum of all elements in the array and then subtracting the minimum possible sum of an LIS. To minimize the sum of an LIS, we should aim for an LIS that uses the smallest possible values while maintaining its longest length. However, standard LIS algorithms find *any* LIS of maximum length, not necessarily the one with the minimum sum.

A different perspective is to recognize that if we want to maximize the sum of elements *not* in an LIS, it means we want to find an LIS such that the sum of its elements is minimized. The total sum of the array is constant. So, if we subtract the minimum sum of an LIS from the total sum of the array, we will get the maximum sum of elements not part of that LIS.

The length of the LIS can be found using dynamic programming. Let dp[i] be the length of the longest increasing subsequence ending at index i.
dp[i] = 1 + max(dp[j]) for all j < i and arr[j] < arr[i]. If no such j exists, dp[i] = 1.

Once we have the lengths of LIS ending at each index, we can find the maximum LIS length, let's call it 'maxLength'.
Now, to find the minimum sum of an LIS of length 'maxLength', we need to consider all possible LIS of that length. This becomes complex.

A more straightforward approach, which is commonly used for this type of problem, is to calculate the total sum of all elements in the array. Then, find the Longest Increasing Subsequence. The problem statement implies we need to find the sum of elements *not* in *any* LIS that achieves the maximum length. This phrasing usually means we need to pick *one* such LIS and sum the remaining elements. If multiple LIS with the maximum length exist, we should choose the one that minimizes its sum to maximize the sum of non-LIS elements.

Let's re-evaluate. The problem asks for the "maximum possible sum of all elements that are not part of the Longest Increasing Subsequence (LIS)". This implies that there might be multiple LISs of the same maximum length. We want to choose the LIS such that the sum of the remaining elements is maximized. This is equivalent to choosing the LIS whose sum is minimized.

So, the problem boils down to:
1. Calculate the total sum of all elements in the array.
2. Find the minimum sum of an LIS among all possible LISs of maximum length.
3. Subtract the minimum LIS sum from the total sum.

Let `dp[i]` be the minimum sum of an increasing subsequence of length `length[i]` ending at index `i`.
And `length[i]` be the length of the LIS ending at index `i`.

Initialize `length[i] = 1` and `dp[i] = arr[i]` for all `i`.

Iterate `i` from `0` to `n-1`:
  Iterate `j` from `0` to `i-1`:
    If `arr[j] < arr[i]`:
      If `length[j] + 1 > length[i]`:
        `length[i] = length[j] + 1`
        `dp[i] = dp[j] + arr[i]`
      Else if `length[j] + 1 == length[i]`:
        `dp[i] = min(dp[i], dp[j] + arr[i])`

After filling `dp` and `length` arrays:
Find `maxLength = max(length[i])` for all `i`.
Find `minLisSum = min(dp[i])` for all `i` where `length[i] == maxLength`.

The final answer will be `totalSum - minLisSum`.

Example walk-through: arr[] = [4, 6, 1, 2, 3, 8]
n = 6
totalSum = 4 + 6 + 1 + 2 + 3 + 8 = 24

length array and dp array (min sum of LIS ending at i):
i | arr[i] | length | dp (min sum)
--------------------------------------
0 | 4      | 1      | 4
1 | 6      | 2      | 4+6=10 (from [4,6])
2 | 1      | 1      | 1
3 | 2      | 2      | 1+2=3 (from [1,2])
4 | 3      | 3      | 1+2+3=6 (from [1,2,3])
5 | 8      | 4      | 1+2+3+8=14 (from [1,2,3,8])

Let's refine the dp state. `length[i]` is the length of the LIS ending at index `i`. `sum[i]` is the minimum sum of an LIS of length `length[i]` ending at index `i`.

Initialize `length[i] = 1` and `sum[i] = arr[i]` for all `i`.

arr[] = [4, 6, 1, 2, 3, 8]

i = 0, arr[0] = 4
  length[0] = 1, sum[0] = 4

i = 1, arr[1] = 6
  j = 0: arr[0] = 4 < arr[1] = 6
    length[0] + 1 = 2 > length[1] (initially 1)
    length[1] = 2
    sum[1] = sum[0] + arr[1] = 4 + 6 = 10

i = 2, arr[2] = 1
  length[2] = 1, sum[2] = 1 (no j < 2 satisfies arr[j] < arr[2])

i = 3, arr[3] = 2
  j = 0: arr[0] = 4 !< arr[3] = 2
  j = 1: arr[1] = 6 !< arr[3] = 2
  j = 2: arr[2] = 1 < arr[3] = 2
    length[2] + 1 = 2 > length[3] (initially 1)
    length[3] = 2
    sum[3] = sum[2] + arr[3] = 1 + 2 = 3

i = 4, arr[4] = 3
  j = 0: arr[0] = 4 !< arr[4] = 3
  j = 1: arr[1] = 6 !< arr[4] = 3
  j = 2: arr[2] = 1 < arr[4] = 3
    length[2] + 1 = 2
    If 2 > length[4] (1):
      length[4] = 2
      sum[4] = sum[2] + arr[4] = 1 + 3 = 4
  j = 3: arr[3] = 2 < arr[4] = 3
    length[3] + 1 = 3
    If 3 > length[4] (current 2):
      length[4] = 3
      sum[4] = sum[3] + arr[4] = 3 + 3 = 6

i = 5, arr[5] = 8
  j = 0: arr[0] = 4 < arr[5] = 8
    length[0] + 1 = 2
    If 2 > length[5] (1):
      length[5] = 2
      sum[5] = sum[0] + arr[5] = 4 + 8 = 12
  j = 1: arr[1] = 6 < arr[5] = 8
    length[1] + 1 = 3
    If 3 > length[5] (2):
      length[5] = 3
      sum[5] = sum[1] + arr[5] = 10 + 8 = 18
  j = 2: arr[2] = 1 < arr[5] = 8
    length[2] + 1 = 2
    If 2 == length[5] (current 3 - no update to length but can update sum if smaller): no, not equal
  j = 3: arr[3] = 2 < arr[5] = 8
    length[3] + 1 = 3
    If 3 == length[5] (current 3):
      sum[5] = min(sum[5], sum[3] + arr[5]) = min(18, 3 + 8) = min(18, 11) = 11
  j = 4: arr[4] = 3 < arr[5] = 8
    length[4] + 1 = 4
    If 4 > length[5] (current 3):
      length[5] = 4
      sum[5] = sum[4] + arr[5] = 6 + 8 = 14

Final length and sum arrays:
i | arr[i] | length | sum
--------------------------------------
0 | 4      | 1      | 4
1 | 6      | 2      | 10
2 | 1      | 1      | 1
3 | 2      | 2      | 3
4 | 3      | 3      | 6
5 | 8      | 4      | 14

maxLength = max(1, 2, 1, 2, 3, 4) = 4

minLisSum = min(sum[i] where length[i] == 4)
Only one such i: i = 5, length[5] = 4, sum[5] = 14.
So, minLisSum = 14.

Total sum = 24.
Result = totalSum - minLisSum = 24 - 14 = 10.
This matches the example output.

Consider Input: arr[] = [5, 4, 3, 2, 1]
totalSum = 5+4+3+2+1 = 15

i | arr[i] | length | sum
--------------------------------------
0 | 5      | 1      | 5
1 | 4      | 1      | 4
2 | 3      | 1      | 3
3 | 2      | 1      | 2
4 | 1      | 1      | 1

maxLength = 1.
minLisSum = min(5, 4, 3, 2, 1) = 1. (LIS is [1], [2], [3], [4], [5])
Result = 15 - 1 = 14.
This also matches the example output.

This approach seems correct.

Time Complexity:
Outer loop runs N times. Inner loop runs N times. Inside the inner loop, constant time operations.
Total time complexity: O(N^2).
Given N <= 1000, N^2 = 10^6, which is acceptable.

Space Complexity:
We use two arrays, `length` and `sum`, each of size N.
Total space complexity: O(N).
Given N <= 1000, O(N) space is acceptable.
*/
class Solution {
    public int maxSumSubsequence(int[] arr) {
        int n = arr.length;
        if (n == 0) {
            return 0;
        }
        int[] length = new int[n];
        long[] sum = new long[n];

        long totalSum = 0;
        for (int i = 0; i < n; i++) {
            length[i] = 1;
            sum[i] = arr[i];
            totalSum += arr[i];
        }
        int maxLength = 1;

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < i; j++) {
                if (arr[j] < arr[i]) {
                    if (length[j] + 1 > length[i]) {
                        length[i] = length[j] + 1;
                        sum[i] = sum[j] + arr[i];
                    } else if (length[j] + 1 == length[i]) {
                        sum[i] = Math.min(sum[i], sum[j] + arr[i]);
                    }
                }
            }
            maxLength = Math.max(maxLength, length[i]);
        }
        long minLisSum = Long.MAX_VALUE;
        for (int i = 0; i < n; i++) {
            if (length[i] == maxLength) {
                minLisSum = Math.min(minLisSum, sum[i]);
            }
        }

        return (int) (totalSum - minLisSum);
    }
}