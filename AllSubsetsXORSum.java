/**
 * Problem Statement: All Subsets XOR Sum
 * --------------------------------------
 * Given an array arr[], return the sum of the XOR of all elements for every possible 
 * subset of the array. The XOR sum of an empty subset is 0.
 *
 * Examples:
 * Input: arr[] = [7, 2]
 * Output: 14
 * Subsets XORs: 0 (empty) + 7 + 2 + (7 ^ 2) = 14
 *
 * Constraints:
 * 1 <= arr.size() <= 30
 * 1 <= arr[i] <= 10^3
 * The answer fits within a 32-bit integer.
 *//**
     * Optimal Solution: Bitwise Contribution Analysis
     * ---------------------------------------------
     * The total sum of all subset XORs can be calculated by analyzing the contribution 
     * of each bit position to the final sum.
     *
     * * Key Observation (Bitwise):
     * The k-th bit of the total sum is determined by the k-th bit of the subset XOR sums.
     * The k-th bit of a subset XOR sum is 1 if and only if the subset contains an ODD 
     * number of elements whose k-th bit is 1.
     *
     * * Count of Subsets with k-th bit set:
     * 1. Let N be the total number of elements in 'arr'.
     * 2. Consider the k-th bit.
     * a. If *no* element in 'arr' has the k-th bit set (i.e., (arr[i] & (1 << k)) == 0 for all i), 
     * then this bit will never contribute 1 to any subset XOR sum.
     * b. If at least one element 'x' in 'arr' has the k-th bit set, let M be the count 
     * of elements with the k-th bit set (M >= 1). The remaining (N - M) elements have 
     * the k-th bit as 0.
     * 3. The total number of subsets is 2^N.
     * 4. For any bit position 'k' that is set in at least one element: 
     * *Exactly half* of the $2^N$ total subsets will have their k-th bit set to 1 in the final XOR sum.
     * - Subsets with k-th bit set = $2^N / 2 = 2^{N-1}$.
     * - This comes from the fact that the elements with the k-th bit set form a non-empty set.
     *
     * * Final Calculation:
     * The contribution of the k-th bit to the total sum is: 
     * (Value of k-th bit, $2^k$) * (Number of subsets where XOR sum has k-th bit set)
     *
     * Total Sum = $\sum_{k=0}^{10} [ (1 \ll k) \times (\text{Number of subsets with k-th bit set}) ]$
     *
     * The number of subsets with the k-th bit set is:
     * - $2^{N-1}$, if the k-th bit is set in at least one element of 'arr'.
     * - $0$, if the k-th bit is not set in any element of 'arr'.
     *
     * This condition can be summarized using the bitwise OR of all elements:
     * **Total Sum = (arr[0] | arr[1] | ... | arr[N-1]) $\times 2^{N-1}$**
     *
     * The bitwise OR, (arr[0] | ... | arr[N-1]), captures all bit positions that are set 
     * in at least one element, and this entire value is multiplied by the factor $2^{N-1}$.
     */
// Code - 
class Solution{
    public int subsetXORSum(int[] arr)
     {
        if (arr == null || arr.length == 0)
             {
            return 0;
        }
        int overallOR = 0;
        for (int x : arr)
             {
            overallOR |= x;
        }
        int n = arr.length;
        int factor = 1 << (n - 1);

        return overallOR * factor;
    }
}

/**
 * Time Complexity Analysis:
 * -------------------------
 * O(N), where N is the length of the array 'arr'.
 * - The algorithm involves a single pass through the array to compute the bitwise OR of all elements. 
 * All other operations (multiplication, bit shift) are O(1).
 * - Total time complexity: O(N).
 *
 * Space Complexity Analysis:
 * --------------------------
 * O(1)
 * - The algorithm uses only a few integer variables (overallOR, n, factor) and no auxiliary 
 * data structures that scale with the input size.
 */
