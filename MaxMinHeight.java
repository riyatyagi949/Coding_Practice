/*
Problem Statement:
Given a garden with n flowers planted in a row, that is represented by an array arr[], where arr[i] denotes the height of the ith flower. You will water them for k days. In one day you can water w continuous flowers. Whenever you water a flower its height increases by 1 unit. You have to maximize the minimum height of all flowers after k days of watering.

Approach:
This problem can be solved using binary search on the answer. The possible range for the minimum height of all flowers can be from the minimum initial height in the array up to (minimum initial height + k).
For a given `mid` value (which represents a potential minimum height we want to achieve), we need to check if it's possible to make all flowers at least `mid` height using at most `k` watering days.

The `check` function simulates the watering process. It iterates through the flowers. If a flower's current height (considering previous waterings) is less than `mid`, it calculates how much more height is needed for that flower. It then uses `w` continuous watering days to increase the height of the current flower and `w-1` subsequent flowers. The number of watering days used is accumulated. If the total watering days exceed `k`, then `mid` is not achievable.

In the `check` function:
1. We create a `diff` array to store the effective height increase at each position due to watering.
2. We iterate through the flowers. `currentHeight` at index `i` is `arr[i] + diff[i]`.
3. If `currentHeight < mid`, we need to water. The `needed` amount is `mid - currentHeight`.
4. We add `needed` to `totalDaysUsed`. If `totalDaysUsed` exceeds `k`, we return `false`.
5. We apply the watering: `diff[i] += needed` and `diff[i + w] -= needed` (if `i + w` is within bounds). This is a common technique for range updates using prefix sums or difference arrays.
6. After iterating through all flowers, if `totalDaysUsed` is less than or equal to `k`, we return `true`.

The binary search works as follows:
- `low` is initialized to the minimum height in `arr`.
- `high` is initialized to `low + k`.
- While `low <= high`:
    - `mid = low + (high - low) / 2`.
    - If `check(arr, n, k, w, mid)` is true, it means `mid` is achievable, so we try for a higher minimum height by setting `ans = mid` and `low = mid + 1`.
    - If `check` is false, it means `mid` is not achievable, so we need to try a lower minimum height by setting `high = mid - 1`.

Finally, `ans` will hold the maximum possible minimum height.

Time Complexity:
The time complexity is O(N log(MaxHeight - MinHeight)).
- The `minHeight` calculation takes O(N).
- The binary search performs log(MaxHeight - MinHeight) iterations.
- Inside each iteration, the `check` function takes O(N) time to iterate through the flowers.
So, the overall time complexity is O(N log(MaxHeight)). Given that MaxHeight can be up to 10^9 + 10^5, log(MaxHeight) is roughly log(10^9) which is around 30. So, it's efficient enough.

Space Complexity:
The space complexity is O(N) due to the `diff` array used in the `check` function.
*/

class Solution {
    public int maxMinHeight(int[] arr, int k, int w) {
        int n = arr.length;

        int low = Arrays.stream(arr).min().getAsInt();
        int high = low + k;

        int answer = low; 

        while (low <= high) {
            int mid = low + (high - low) / 2;
            if (isPossible(arr, mid, k, w)) {
                answer = mid;    
                low = mid + 1;   
            } else {
                high = mid - 1;  
            }
        }
          return answer; 
    }
    private boolean isPossible(int[] arr, int minHeight, int k, int w) {
        int n = arr.length;
        int[] diff = new int[n + 1];
        long used = 0; 
        long currAdd = 0; 

        for (int i = 0; i < n; i++) {
            currAdd += diff[i]; 
            long currentHeight = arr[i] + currAdd;
            if (currentHeight < minHeight) {
                long delta = minHeight - currentHeight;

                if (used + delta > k)
                    return false;

                used += delta;       
                currAdd += delta;    
                 // Apply immediately
                diff[i] += delta;    
                 // Start of range increment
                if (i + w < n)
                    diff[i + w] -= delta;
                     // End of range increment
            }
        }
        return true; 
        // It's possible to reach the required minHeight
    }
}
