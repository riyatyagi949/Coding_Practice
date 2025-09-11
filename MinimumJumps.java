// Problem Statement:
// You are given an array `arr` of non-negative numbers. Each number `arr[i]` represents the maximum number of steps you can jump forward from that position.
// Your task is to find the minimum number of jumps needed to reach the end of the array from the first position.
// If it's not possible to reach the end, return -1.

// Approach:
// This problem can be solved using a greedy approach. We want to make the minimum number of jumps, which means each jump should take us as far as possible. We can iterate through the array and at each step, determine the farthest reachable point.
// We maintain three variables:
// 1. `jumps`: to count the number of jumps.
// 2. `currentEnd`: to mark the end of the current jump's range.
// 3. `farthest`: to track the farthest reachable index from the current position.
// We iterate through the array from the starting index up to the second-to-last index. At each position `i`, we update `farthest` with `i + arr[i]`, which represents the maximum reach from the current position. When we reach the `currentEnd` of our previous jump, it means we have exhausted all the positions we could reach with that jump. At this point, we increment our `jumps` count and update `currentEnd` to `farthest`.
// If at any point the `currentEnd` is less than or equal to the current index `i`, it means we are stuck and cannot move forward. This happens when `arr[i]` is 0 and `i` is the last position of the previous jump's range. In this case, we cannot reach the end, so we return -1.
// The loop runs until we've processed all reachable indices up to the second-to-last element. Finally, we return the total number of jumps.

// Time Complexity: O(N)
// The approach involves a single pass through the array, making it a linear time complexity solution.

// Space Complexity: O(1)
// We use a few extra variables to store state, which takes constant space.

class Solution {
    public int minJumps(int[] arr) {
        int n = arr.length;
        if (n <= 1) return 0; 
        if (arr[0] == 0) return -1; 

        int maxReach = arr[0]; 
        int steps = arr[0]; 
        int jumps = 1; 

        for (int i = 1; i < n; i++) {
            if (i == n - 1) return jumps; 

            maxReach = Math.max(maxReach, i + arr[i]); 
            steps--; 

            if (steps == 0) {
                jumps++; 
                if (i >= maxReach) return -1; 
                steps = maxReach - i; 
            }
        }
        return -1; 
    }
}
