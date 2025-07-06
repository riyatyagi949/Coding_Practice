// Problem Statement:
// Given two arrays `a` and `b` of equal size, find the top `k` maximum sum combinations (a[i] + b[j]).
// Return them sorted in non-increasing order.

// Approach:
// 1. Sort both arrays `a` and `b` in descending order.
// 2. Use a Max-Priority Queue (PQ) to store `(sum, index_a, index_b)` tuples, prioritizing by `sum`.
// 3. Use a `HashSet` to keep track of visited `(index_a, index_b)` pairs to avoid duplicates.
// 4. Start by adding `(a[0] + b[0], 0, 0)` to the PQ and mark (0,0) as visited.
// 5. In a loop `k` times:
//    a. Extract the max sum `(s, i, j)` from PQ, add `s` to result.
//    b. Add `(a[i] + b[j+1], i, j+1)` and `(a[i+1] + b[j], i+1, j)` to PQ if valid indices and not visited.

// Time Complexity: O(N log N + K log K) where N is array size.
// Space Complexity: O(N + K).

import java.util.*;

class Solution {
    public ArrayList<Integer> topKSumPairs(int[] a, int[] b, int k) {
        Arrays.sort(a);
        Arrays.sort(b);

        int n = a.length;
        // Reverse for descending order
        for (int i = 0; i < n / 2; i++) { int temp = a[i]; a[i] = a[n - 1 - i]; a[n - 1 - i] = temp; }
        for (int i = 0; i < n / 2; i++) { int temp = b[i]; b[i] = b[n - 1 - i]; b[n - 1 - i] = temp; }

        PriorityQueue<int[]> pq = new PriorityQueue<>((x, y) -> y[0] - x[0]);
        Set<Long> visited = new HashSet<>();
        ArrayList<Integer> result = new ArrayList<>();

        pq.offer(new int[]{a[0] + b[0], 0, 0});
        visited.add(0L); 

        while (k > 0 && !pq.isEmpty()) {
            int[] current = pq.poll();
            int sum = current[0];
            int i = current[1];
            int j = current[2];

            result.add(sum);
            k--;

            if (j + 1 < n) {
                long nextPair = (long) i * n + (j + 1);
                if (!visited.contains(nextPair)) {
                    pq.offer(new int[]{a[i] + b[j + 1], i, j + 1});
                    visited.add(nextPair);
                }
            }
            if (i + 1 < n) {
                long nextPair = (long) (i + 1) * n + j;
                if (!visited.contains(nextPair)) {
                    pq.offer(new int[]{a[i + 1] + b[j], i + 1, j});
                    visited.add(nextPair);
                }
            }
        }
        return result;
    }
}