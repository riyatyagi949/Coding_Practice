 /**
 * Problem: 2106. Maximum Fruits Harvested After at Most K Steps
 * Approach:
 * The problem is to find the maximum fruits harvested within `k` steps starting from `startPos`. Since the fruits are sorted by position, a sliding window approach with two pointers is efficient. We can precompute a prefix sum array to quickly calculate the number of fruits in any given range.
 *
 * The core idea is to use a sliding window `[left, right]` on the `fruits` array. For each `right` pointer, we find the furthest `left` pointer such that the total steps to collect all fruits in the range `[fruits[left][0], fruits[right][0]]` do not exceed `k`.
 *
 * The total steps to cover a range `[l, r]` from `startPos` depends on the position of `startPos` relative to the range:
 * - If `startPos < l`, we must travel right. Steps = `r - startPos`.
 * - If `startPos > r`, we must travel left. Steps = `startPos - l`.
 * - If `l <= startPos <= r`, we can either go left then right or right then left. The total steps will be the length of the range `(r-l)` plus the distance to the closer endpoint from `startPos`. This is `min((startPos - l) + (r - l), (r - startPos) + (r - l))`.
 *
 * We iterate the `right` pointer from `0` to `n-1`. For each `right`, we shrink the window by incrementing the `left` pointer until the travel cost is within `k`. We then update the maximum fruits collected.
 *
 * Time Complexity: O(N), where N is the number of fruits. The `left` and `right` pointers traverse the fruits array at most once.
 * Space Complexity: O(N) for the prefix sum array.
 */
class Solution {
    public int maxTotalFruits(int[][] fruits, int startPos, int k) {
        int n = fruits.length;
        int[] prefixSums = new int[n + 1];
        for (int i = 0; i < n; i++) {
            prefixSums[i + 1] = prefixSums[i] + fruits[i][1];
        }

        int maxFruits = 0;
        int left = 0;
        for (int right = 0; right < n; right++) {
            int rightPos = fruits[right][0];
            
            while (left <= right) {
                int leftPos = fruits[left][0];
                int travelCost;
                
                if (startPos < leftPos) {
                    travelCost = rightPos - startPos;
                } else if (startPos > rightPos) {
                    travelCost = startPos - leftPos;
                } else {
                    travelCost = Math.min((startPos - leftPos) + (rightPos - leftPos), (rightPos - startPos) + (rightPos - leftPos));
                }

                if (travelCost <= k) {
                    maxFruits = Math.max(maxFruits, prefixSums[right + 1] - prefixSums[left]);
                    break;
                } else {
                    left++;
                }
            }
        }
        return maxFruits;
    }
}

