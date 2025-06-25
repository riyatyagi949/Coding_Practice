// Problem Statement:
// Koko is given an array arr[], where each element represents a pile of bananas. She has exactly k hours to eat all the bananas.
// Each hour, Koko can choose one pile and eat up to s bananas from it.
// If the pile has at least s bananas, she eats exactly s bananas.
// If the pile has fewer than s bananas, she eats the entire pile in that hour.
// Koko can only eat from one pile per hour.
// Your task is to find the minimum value of s (bananas per hour) such that Koko can finish all the piles within k hours.

// Approach:
// This problem can be solved using binary search. We are looking for the minimum 's' such that Koko can eat all bananas within 'k' hours.
// The range for 's' will be from 1 (minimum possible eating rate) to the maximum number of bananas in any pile (maximum possible eating rate, as eating more than the largest pile's size doesn't reduce time).
// We can define a helper function `canEat(s, arr, k)` that checks if Koko can finish all bananas with a given eating rate 's' within 'k' hours.
// Inside `canEat`, we iterate through each pile. For each pile `piles[i]`, the time taken to eat it is `(piles[i] + s - 1) / s` (integer division to simulate ceiling, e.g., if pile is 7 and s is 4, time is 2 hours). We sum up these times. If the total time is less than or equal to `k`, then `s` is a valid eating rate.
// The binary search will work as follows:
// - Initialize `low = 1` and `high = max_banana_in_pile`.
// - While `low <= high`:
//   - Calculate `mid = low + (high - low) / 2`.
//   - If `canEat(mid, arr, k)` is true, it means `mid` is a possible answer, and we might find a smaller `s`, so we store `mid` as a potential answer and try `high = mid - 1`.
//   - If `canEat(mid, arr, k)` is false, it means `mid` is too slow, so we need to increase `s`, thus `low = mid + 1`.
// The final answer will be the smallest `s` that satisfies the condition.

// Time Complexity:
// The time complexity will be O(N * log M), where N is the number of piles (length of arr) and M is the maximum number of bananas in any pile.
// The `log M` comes from the binary search on the range of 's' (from 1 to max_banana_in_pile).
// The `N` comes from the `canEat` function, which iterates through all piles.

// Space Complexity:
// The space complexity is O(1) as we are only using a few variables.

class Solution {
    public int kokoEat(int[] arr, int k) {
        int maxBanana = 0;
        for (int pile : arr) {
            if (pile > maxBanana) {
                maxBanana = pile;
            }
        }
        int low = 1;
        int high = maxBanana;
        int minS = maxBanana; 

        while (low <= high) {
            int mid = low + (high - low) / 2;
            if (canEat(mid, arr, k)) {
                minS = mid;
                high = mid - 1;
            } else {
                low = mid + 1;
            }
        }
        return minS;
    }
     private boolean canEat(int s, int[] arr, int k) {
        long hoursNeeded = 0;
        for (int pile : arr) {
            hoursNeeded += (pile + s - 1) / s; 
            if (hoursNeeded > k) {
                return false;
            }
        }
        return hoursNeeded <= k;
    }
}