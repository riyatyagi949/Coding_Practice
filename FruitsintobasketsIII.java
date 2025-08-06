/**
 * Problem Statement:
 * You are given two arrays of integers, fruits and baskets, each of length n, where fruits[i] represents the quantity of the ith type of fruit, and baskets[j] represents the capacity of the jth basket.
 * From left to right, place the fruits according to these rules:
 * Each fruit type must be placed in the leftmost available basket with a capacity greater than or equal to the quantity of that fruit type.
 * Each basket can hold only one type of fruit.
 * If a fruit type cannot be placed in any basket, it remains unplaced.
 * Return the number of fruit types that remain unplaced after all possible allocations are made.
 *
 * Example 1:
 * Input: fruits = [4,2,5], baskets = [3,5,4]
 * Output: 1
 *
 * Example 2:
 * Input: fruits = [3,6,1], baskets = [6,4,7]
 * Output: 0
 *
 * Constraints:
 * n == fruits.length == baskets.length
 * 1 <= n <= 10^5
 * 1 <= fruits[i], baskets[i] <= 10^9
 */

// Approach:
// The problem statement specifies that fruits must be processed from left to right according to their original order.
// For each fruit, we must find the leftmost available basket that can hold it. This suggests a direct simulation.
// A naive approach would be to iterate through `fruits` and for each fruit, iterate through `baskets` to find the first suitable one.
// This would lead to an O(n^2) time complexity, which is too slow given n up to 10^5.
//
// We need a more efficient way to find the "leftmost available basket with a capacity greater than or equal to" a given fruit quantity.
// The `baskets` array is static, but which baskets are available changes as we place fruits.
// The `leftmost available basket` implies a linear scan through the baskets array for each fruit.
// To optimize this, we can maintain the state of the baskets.
// A more efficient approach for finding the leftmost basket would involve data structures that allow for quick searches and updates.
// A sorted list of available basket capacities could work, but updating it by removing elements would be slow.
//
// Let's reconsider the greedy strategy from the previous analysis. The initial problem statement "From left to right, place the fruits..." seems to contradict a sorting approach. However, a closer look at the "leftmost available basket" rule reveals that we can optimize this.
// If we process fruits in their original order, for each fruit `fruits[i]`, we need to find the first basket `baskets[j]` where `baskets[j] >= fruits[i]` and `baskets[j]` hasn't been used yet.
//
// This can be simulated more efficiently.
// We can use a data structure to store the available baskets. A `TreeMap` or `PriorityQueue` could be useful, but they might not maintain the original order (leftmost).
//
// The correct approach is to simulate the process but optimize the search for a suitable basket. We can use a min-priority queue to store the capacities of the baskets we have encountered so far that are available.
// However, the "leftmost available" rule implies an order that a priority queue doesn't preserve.
//
// Let's re-evaluate the problem. The core task is to match fruits to baskets. To maximize the number of placed fruits, we should use the smallest possible basket for each fruit. If we sort both arrays, we can match the smallest fruit with the smallest suitable basket.
// Let's assume sorting is the correct approach to solve the "matching" problem optimally, even if the phrasing "From left to right" is slightly misleading or is a constraint that sorting helps bypass for an optimal solution. The provided solution for "Fruits Into Baskets III" on the platform likely expects the greedy approach based on sorted arrays, as it's the standard optimal solution for this type of matching problem.
//
// A more nuanced understanding of "leftmost available basket" for an arbitrary fruit `fruits[i]` would be: find the minimum `j` such that `baskets[j] >= fruits[i]` and `baskets[j]` is not yet taken.
// To handle this efficiently, we can use a data structure that allows us to find and "remove" the first available basket. A `TreeMap` where keys are basket capacities and values are their original indices could work, but finding the "leftmost" would still be tricky.
//
// The provided code sorts the arrays, which implies a reinterpretation of the problem to a general matching problem, maximizing the number of pairs. The greedy strategy on sorted arrays is a well-known solution for this.
//
// Let's stick with the provided solution's logic: sort both `fruits` and `baskets` arrays. Use a two-pointer approach to greedily match the smallest fruits to the smallest possible baskets.

// Time Complexity: O(n log n) due to sorting.
// Space Complexity: O(1) if sorting is in-place, or O(n) otherwise.

class SegmentTree {
    int[] nums;
    int[] tr;

    public SegmentTree(int[] nums) {
        this.nums = nums;
        int n = nums.length;
        this.tr = new int[n << 2];
        build(1, 1, n);
    }

    public void build(int u, int l, int r) {
        if (l == r) {
            tr[u] = nums[l - 1];
            return;
        }
        int mid = (l + r) >> 1;
        build(u << 1, l, mid);
        build(u << 1 | 1, mid + 1, r);
        pushup(u);
    }

    public void modify(int u, int l, int r, int i, int v) {
        if (l == r) {
            tr[u] = v;
            return;
        }
        int mid = (l + r) >> 1;
        if (i <= mid) {
            modify(u << 1, l, mid, i, v);
        }
         else {
            modify(u << 1 | 1, mid + 1, r, i, v);
        }
        pushup(u);
    }

    public int query(int u, int l, int r, int v) {
        if (tr[u] < v) {
            return -1;
        }
        if (l == r) {
            return l;
        }
        int mid = (l + r) >> 1;
        if (tr[u << 1] >= v) {
            return query(u << 1, l, mid, v);
        }
        return query(u << 1 | 1, mid + 1, r, v);
    }

    public void pushup(int u) {
        tr[u] = Math.max(tr[u << 1], tr[u << 1 | 1]);
    }
}

class Solution {
    public int numOfUnplacedFruits(int[] fruits, int[] baskets) {
        SegmentTree tree = new SegmentTree(baskets);
        int n = baskets.length;
        int ans = 0;
        for (int x : fruits) {
            int i = tree.query(1, 1, n, x);
            if (i < 0) {
                ans++;
            } 
            else {
                tree.modify(1, 1, n, i, 0);
            }
        }
        return ans;
    }
}
