// Problem Statement:
// Given an integer n, return any array containing n unique integers such that they add up to 0.

// Approach:
// The problem asks us to construct an array of n unique integers that sum up to zero.
// A simple and effective way to achieve this is to use a symmetrical approach around zero.
// For an even number n, we can create pairs of numbers (x, -x). For example, for n = 4, we can use [-2, -1, 1, 2]. The sum of each pair is 0, so the total sum is also 0.
// For an odd number n, we can use the same strategy as for the even case, but with an additional element: 0. This is because we need n unique numbers, and for an odd n, we'll have an odd number of pairs. For instance, if n = 5, we can use the pairs from the n-1 (even) case, which is n=4. So we can use [-2, -1, 1, 2], and add 0 to get [-2, -1, 0, 1, 2]. The sum of all these numbers is 0.

// Algorithm:
// 1. Initialize an array of size n.
// 2. If n is odd, the middle element of the array can be 0.
// 3. For all other elements, we can generate pairs of numbers (i, -i) for i from 1 up to n/2.
// 4. For an even n, we'll have n/2 pairs. We can fill the array with i and -i for i from 1 to n/2. For n=4, we get [-1, 1, -2, 2]. The sum is 0.
// 5. For an odd n, we can fill the array with i and -i for i from 1 to (n-1)/2. This leaves one element to be 0, which we can place in the middle. For n=5, we fill with i=1, 2 which gives us [-1, 1, -2, 2]. Then we add 0 to get [-1, 1, -2, 2, 0].

// Time Complexity:
// The time complexity is O(n), as we iterate through a loop n/2 times to fill the array.

// Space Complexity:
// The space complexity is O(n), as we need an array of size n to store the result.

// Optimal Solution in Java-

class Solution {
    public int[] sumZero(int n) {
        int[] result = new int[n];
        int index = 0;
        
        for (int i = 1; i <= n / 2; i++) {
            result[index++] = i;
            result[index++] = -i;
        }
        if (n % 2 != 0) {
            result[index] = 0;
        }
        
        return result;
    }
}
