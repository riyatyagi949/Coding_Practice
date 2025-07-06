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
        int n = a.length;
        Arrays.sort(a);
        Arrays.sort(b);

        PriorityQueue<Pair> maxHeap = new PriorityQueue<>((p1, p2) -> p2.sum - p1.sum);
        Set<String> visited = new HashSet<>();

        int i = n - 1, j = n - 1;
        maxHeap.offer(new Pair(i, j, a[i] + b[j]));
        visited.add(i + "#" + j);

        ArrayList<Integer> result = new ArrayList<>();

        while (k-- > 0 && !maxHeap.isEmpty()) {
            Pair top = maxHeap.poll();
            result.add(top.sum);

            i = top.i;
            j = top.j;

            if (i - 1 >= 0 && visited.add((i - 1) + "#" + j)) {
                maxHeap.offer(new Pair(i - 1, j, a[i - 1] + b[j]));
            }

            if (j - 1 >= 0 && visited.add(i + "#" + (j - 1))) {
                maxHeap.offer(new Pair(i, j - 1, a[i] + b[j - 1]));
            }
        }
          return result;
    }
     static class Pair {
        int i, j, sum;
        Pair(int i, int j, int sum) {
            this.i = i;
            this.j = j;
            this.sum = sum;
        }
    }
}
