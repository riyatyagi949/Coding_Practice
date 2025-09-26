/**
 * Problem Statement:
 * Given an integer array `nums`, return the number of triplets (three distinct indices) chosen from the array
 * that can form the sides of a valid triangle.
 * Triangle Inequality Theorem: For any three side lengths a, b, and c to form a valid triangle, they must satisfy
 * three conditions: a + b > c, a + c > b, and b + c > a.
 *
 * Optimal Approach (Sorting + Two Pointers):
 * A naive O(n^3) approach would be to check all possible triplets, but with n <= 1000, we need a faster solution.
 * The optimal approach is O(n^2) and relies on sorting and the two-pointer technique.
 *
 * 1. Sort the array: Sorting the array `nums` is the crucial first step. If the sides are a, b, and c, and we assume
 * they are sorted such that a <= b <= c, the Triangle Inequality Theorem simplifies significantly.
 * - a + c > b (Always true since c >= b and a > 0, assuming non-zero sides).
 * - b + c > a (Always true since c >= a and b > 0).
 * - We only need to check the condition: a + b > c.
 *
 * 2. Fixing the largest side (c): We iterate through the array from the largest possible side, c, from index `k = n-1` down to `2`.
 * For a fixed `nums[k]` (our c), we need to find pairs (a, b) from the subarray `nums[0...k-1]` such that `a + b > nums[k]`.
 *
 * 3. Two Pointers for (a, b): To find the pairs (a, b) efficiently, we use the Two-Pointer technique on the subarray `nums[0...k-1]`.
 * - Initialize the left pointer `i` at the start (`0`) and the right pointer `j` at the end (`k-1`).
 * - Check the condition `nums[i] + nums[j] > nums[k]`:
 * - Case 1: If `nums[i] + nums[j] > nums[k]` (Condition met):
 * Since the array is sorted, any element between `i` and `j` (i.e., `nums[i+1], nums[i+2], ..., nums[j-1]`) when paired with `nums[j]` will also satisfy the condition
 * (e.g., `nums[i+1] + nums[j] > nums[i] + nums[j] > nums[k]`).
 * Therefore, all pairs `(nums[i], nums[j]), (nums[i+1], nums[j]), ..., (nums[j-1], nums[j])` form a valid triangle with `nums[k]`.
 * The number of such valid pairs is `j - i`. We add this to the total count.
 * Since we've found all valid pairs with `nums[j]`, we can move the right pointer inward: `j--`.
 * - Case 2: If `nums[i] + nums[j] <= nums[k]` (Condition NOT met):
 * The sum is too small. To increase the sum, we must include a larger number.
 * We move the left pointer to the right: `i++`.
 *
 * 4. Handle Zeroes: The constraint `0 <= nums[i] <= 1000` means we can have zeroes. A side of length 0 cannot form a triangle.
 * However, since we start our outer loop at $k=n-1$ and the two pointers move inward from $i=0$ and $j=k-1$, any zero elements will naturally be on the left side (since the array is sorted).
 * If $\text{nums}[i]$ is 0, $\text{nums}[i] + \text{nums}[j] \le \text{nums}[k]$ will be more likely true, causing $i$ to increment past the zeros. The two-pointer logic correctly handles this by skipping invalid sides.
 *
 * Time Complexity: O(n^2). The dominant part is the nested loops structure: an outer loop runs $n$ times, and the inner two-pointer mechanism runs a total of $O(n)$ steps for each outer loop iteration, resulting in $O(n^2)$. Sorting takes $O(n \log n)$.
 * Space Complexity: O(1) if sorting is done in-place, or $O(\log n)$ to $O(n)$ depending on the space complexity of the sorting algorithm used by the language implementation.
 */
// Optimal Solution  in Java - 
class Solution {
    public int triangleNumber(int[] nums) {
        Arrays.sort(nums);
        int n = nums.length;
        int count = 0;

        for (int k = n - 1; k >= 2; k--)
         {
            int i = 0, j = k - 1;
            while (i < j)
             {
                if (nums[i] + nums[j] > nums[k])
                 {
                    count += (j - i);
                    j--;
                } 
                else {
                    i++;
                }
            }
        }
        return count;
    }
}