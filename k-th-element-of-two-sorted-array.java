/**
 * PROBLEM STATEMENT: K-th element of two Arrays
 * --------------------------------------------------------------------------------
 * Given two sorted arrays a[] and b[] and an integer k, find the element that 
 * would be at the kth position of the combined sorted array.
 * * * Example 1:
 * Input: a[] = [2, 3, 6, 7, 9], b[] = [1, 4, 8, 10], k = 5
 * Output: 6
 * Explanation: Combined sorted array: [1, 2, 3, 4, 6, 7, 8, 9, 10]. 5th element is 6.
 * * * Constraints:
 * - 1 <= a.size(), b.size() <= 10^6
 * - 1 <= k <= a.size() + b.size()
 * - 0 <= a[i], b[i] <= 10^8
 * --------------------------------------------------------------------------------
 * * OPTIMAL SOLUTION: BINARY SEARCH ON PARTITIONS (O(log(min(n, m))))
 * --------------------------------------------------------------------------------
 * Instead of merging the arrays (O(n+m)), we use binary search to find the 
 * correct partition point.
 * * 1. We partition arrays 'a' and 'b' into two halves (Left and Right) such that:
 * - The total number of elements in the Left halves of both arrays equals 'k'.
 * - All elements in the Left halves are less than or equal to all elements 
 * in the Right halves.
 * 2. To ensure the partition is valid, we must satisfy:
 * - leftA <= rightB
 * - leftB <= rightA
 * 3. Binary Search Range:
 * - 'low' must be at least max(0, k - m) because even if we take all elements 
 * from array 'b', we might still need some from 'a'.
 * - 'high' must be at most min(k, n) because we can't take more than 'k' 
 * elements or more than what's available in 'a'.
 * --------------------------------------------------------------------------------
 * * COMPLEXITY ANALYSIS:
 * --------------------------------------------------------------------------------
 * Time Complexity: O(log(min(n, m)))
 * - We perform binary search on the smaller array, halving the search space 
 * in each step.
 * * Space Complexity: O(1)
 * - Only a few variables are used to store indices and boundary values.
 * --------------------------------------------------------------------------------
 */
// Optimal Solution in Java - 
class Solution {
    public int kthElement(int a[], int b[], int k) {
        int n = a.length;
        int m = b.length;

        if (n > m)
        return kthElement(b, a, k);

        int low = Math.max(0, k - m);
        int high = Math.min(k, n);

        while (low <= high) 
        {
            int cutA = (low + high) / 2;
            int cutB = k - cutA;

            int leftA = (cutA == 0) ? Integer.MIN_VALUE : a[cutA - 1];
            int leftB = (cutB == 0) ? Integer.MIN_VALUE : b[cutB - 1];

            int rightA = (cutA == n) ? Integer.MAX_VALUE : a[cutA];
            int rightB = (cutB == m) ? Integer.MAX_VALUE : b[cutB];

            if (leftA <= rightB && leftB <= rightA) {
                return Math.max(leftA, leftB);
            } 
            else if (leftA > rightB) {
                high = cutA - 1;
            } 
            else {
                low = cutA + 1;
            }
        }
        return -1;
    }
}



