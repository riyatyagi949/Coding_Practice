/*
Problem Statement:
You are given an integer n representing an array nums from 1 to n. You are also given a 2D array conflictingPairs, where conflictingPairs[i] = [a, b] indicates a conflict. Remove exactly one conflicting pair to maximize the number of non-empty subarrays of nums that do not contain any remaining conflicting pairs.

Approach:
The total number of subarrays is n * (n + 1) / 2. A subarray [l, r] is invalid if it contains a conflicting pair [a, b] (assuming a < b), which means l <= a and r >= b. We can use a sweep-line approach to find the number of valid subarrays. Let's define an array max_a_val[i] as the maximum `a` value of all conflicting pairs [a, b] where b <= i. The number of invalid subarrays ending at `i` is max_a_val[i], so the number of valid subarrays is i - max_a_val[i]. The total number of valid subarrays with all pairs present is the sum of these values for all i from 1 to n.

To find the maximum number of subarrays after removing one pair, we can calculate the "gain" for removing each pair. The gain is the number of subarrays that become valid due to the removal. A subarray [l, r] becomes valid if the removed pair [a, b] was the *only* pair making it invalid. This happens when [a, b] was the dominant conflicting pair for subarrays ending at or after b. We can track the top two conflicting pairs for each index `i` (based on their `a` values) and, for each removed pair, calculate the gain by summing the differences between the top and second-top `a` values over the relevant indices. This can be done efficiently in O(N + M) time, where M is the number of pairs.

Time Complexity:
The time complexity is O(N + M), where N is the size of the array and M is the number of conflicting pairs. This is achieved by using a single pass to compute the `max1` and `max2` conflicting start values, and another pass with a precomputed map and suffix sums to calculate the maximum possible gain.

Space Complexity:
The space complexity is O(N + M) to store the precomputed arrays and maps for the sweep-line algorithm and gain calculation.

Optimal Solution:
*/
