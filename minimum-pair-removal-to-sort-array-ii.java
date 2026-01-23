/**
 * PROBLEM STATEMENT: 3510. Minimum Pair Removal to Sort Array II
 * --------------------------------------------------------------------------------
 * You are given an array 'nums'. You can perform the following operation any number of times:
 * 1. Select the adjacent pair with the minimum sum. If multiple such pairs exist, 
 * choose the leftmost one.
 * 2. Replace the pair with their sum.
 * * Goal: Return the minimum number of operations needed to make the array non-decreasing.
 * * Example:
 * Input: nums = [5,2,3,1]
 * 1. Min sum pair is (3,1) = 4. Array becomes [5,2,4].
 * 2. Min sum pair is (2,4) = 6. Array becomes [5,6].
 * Result: 2 operations.
 * --------------------------------------------------------------------------------
 * OPTIMAL SOLUTION: Simulation with Data Structures (TreeSet + Linked List Logic)
 * --------------------------------------------------------------------------------
 * The problem requires us to always process the global minimum sum pair. 
 * To do this efficiently:
 * 1. Use a TreeSet (or PriorityQueue) to keep track of adjacent pairs ordered by 
 * sum and then index (to handle the "leftmost" rule).
 * 2. Maintain 'prev' and 'next' pointers (like a Doubly Linked List) to keep 
 * track of logical adjacency after elements are "merged" (replaced by sum).
 * 3. Track 'badPairs' (where nums[i] > nums[i+1]). The simulation stops when 
 * badPairs == 0.
 * 4. Update the sum of affected neighbors in the TreeSet whenever a pair is merged.
 * --------------------------------------------------------------------------------
 * COMPLEXITY ANALYSIS:
 * --------------------------------------------------------------------------------
 * Time Complexity: O(N log N)
 * - Initializing the TreeSet and identifying bad pairs: O(N log N).
 * - Each operation involves a fixed number of TreeSet updates (log N). 
 * - In the worst case, we might perform N-1 operations.
 * - Total: O(N log N).
 * * Space Complexity: O(N)
 * - To store the TreeSet, the array copies, and the adjacency pointers.
 * --------------------------------------------------------------------------------
 */
// Optimal Solution in Java - 

import java.util.*;

class Solution {
    static class Pair {
        long sum;
        int idx;

        Pair(long sum, int idx) {
            this.sum = sum;
            this.idx = idx;
        }
        @Override
        public boolean equals(Object o) {
            if (this == o)
             return true;

            if (!(o instanceof Pair))
             return false;

            Pair p = (Pair) o;
            return sum == p.sum && idx == p.idx;
        }
       @Override
        public int hashCode() {
            return Objects.hash(sum, idx);
        }
    }
     public int minimumPairRemoval(int[] nums) {
        int n = nums.length;

        long[] temp = new long[n];
        for (int i = 0; i < n; i++) {
            temp[i] = nums[i];
         }
         TreeSet<Pair> minPairSet = new TreeSet<>(
            (a, b) -> {
                if (a.sum != b.sum) return Long.compare(a.sum, b.sum);
                return Integer.compare(a.idx, b.idx);
            }
        );
        int[] nextIndex = new int[n];
        int[] prevIndex = new int[n];

        for (int i = 0; i < n; i++) {
            nextIndex[i] = i + 1;
            prevIndex[i] = i - 1;
        }
        int badPairs = 0;
        for (int i = 0; i < n - 1; i++) {
            if (temp[i] > temp[i + 1]) {
                badPairs++;
            }
            minPairSet.add(new Pair(temp[i] + temp[i + 1], i));
        }
        int operations = 0;

        while (badPairs > 0) {

            Pair cur = minPairSet.first();
            minPairSet.remove(cur);

            int first = cur.idx;
            int second = nextIndex[first];

            int first_left = prevIndex[first];
            int second_right = nextIndex[second];

            if (temp[first] > temp[second]) {
                badPairs--;
            }
            if (first_left >= 0) {
                if (temp[first_left] > temp[first] && temp[first_left] <= temp[first] + temp[second]) {
                    badPairs--;
                }
                else if (temp[first_left] <= temp[first] && temp[first_left] > temp[first] + temp[second]) {
                    badPairs++;
                }
            }
            if (second_right < n) {
                if (temp[second_right] >= temp[second] && temp[second_right] < temp[first] + temp[second]) {
                    badPairs++;
                }
                else if (temp[second_right] < temp[second] && temp[second_right] >= temp[first] + temp[second]) {
                    badPairs--;
                }
            }
            if (first_left >= 0) {
                minPairSet.remove(
                    new Pair(temp[first_left] + temp[first], first_left)
                );
                minPairSet.add(
                    new Pair(temp[first_left] + temp[first] + temp[second], first_left)
                );
            }
             if (second_right < n) {
                minPairSet.remove(
                    new Pair(temp[second] + temp[second_right], second)
                );
                minPairSet.add(
                    new Pair(temp[first] + temp[second] + temp[second_right], first)
                );
                prevIndex[second_right] = first;
            }
            nextIndex[first] = second_right;
            temp[first] += temp[second];

            operations++;
        }
        return operations;
    }
}

