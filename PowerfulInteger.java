/*
Problem Statement:
You are given a 2D integer array intervals[][] of length n, where each intervals[i] = [start, end] represents a closed interval (i.e., all integers from start to end, inclusive). You are also given an integer k. An integer is called Powerful if it appears in at least k intervals. Find the maximum Powerful Integer.
Note: If no integer occurs at least k times return -1.

Approach:
This problem is a classic application of the sweep-line algorithm. The large range of coordinates (up to 10^9) means we cannot use a simple frequency array. Instead, we only care about points where the number of overlapping intervals changes. These points are the start and end+1 of each given interval.

1.Event Point Creation : For each interval `[start, end]`, we create two "events":
    * A "start event" at `start` with a value of `+1` (indicating an interval begins).
    * An "end event" at `end + 1` with a value of `-1` (indicating an interval ends just before this point).
    We use `end + 1` to correctly count the end boundary. For example, if an interval is `[1, 3]`, it covers 1, 2, 3. The count should drop *after* 3, so at 4.

2. Storing Events: A `TreeMap<Integer, Integer>` is ideal for storing these events. The keys of the `TreeMap` will be the coordinates, and the values will be the net change in the overlap count at that coordinate. `TreeMap` automatically keeps the keys (coordinates) sorted, which is essential for a sweep-line algorithm. If multiple events occur at the same coordinate, `TreeMap` naturally aggregates their changes.

3.  Sweep Line Simulation:
    * Initialize `maxPowerfulInteger = -1` (to store the maximum powerful integer found) and `currentOverlap = 0` (to keep track of how many intervals currently overlap).
    * Initialize `prevCoordinate = -1`. This variable will help us determine the length of the segment where `currentOverlap` was constant.
    * Iterate through the `entrySet()` of the `TreeMap`. The `TreeMap` guarantees iteration in ascending order of coordinates.
    * For each `(currentCoordinate, change)` entry:
        * Check Previous Segment: Before applying the `change` at `currentCoordinate`, the `currentOverlap` value represents the number of intervals covering the segment from `prevCoordinate` up to `currentCoordinate - 1`. If `prevCoordinate` is valid (not -1) and `currentOverlap` was `>= k` for this segment, then all integers in `[prevCoordinate, currentCoordinate - 1]` are powerful. The maximum among these is `currentCoordinate - 1`. Update `maxPowerfulInteger = Math.max(maxPowerfulInteger, currentCoordinate - 1)`.

        * Apply Change:Update `currentOverlap += change`. Now `currentOverlap` reflects the count of intervals active *at or after* `currentCoordinate`.

        * Check Current Point: If, *after* applying the change, `currentOverlap` is `>= k`, it means the integer `currentCoordinate` itself is powerful. Update `maxPowerfulInteger = Math.max(maxPowerfulInteger, currentCoordinate)`. This handles cases where an integer becomes powerful exactly at its coordinate (e.g., `[[5, 5]]`, `k=1`, `5` becomes powerful at coordinate `5`).

        * Update `prevCoordinate`:** Set `prevCoordinate = currentCoordinate` for the next iteration.

4.  Return Result: After processing all events, `maxPowerfulInteger` will hold the largest integer that appeared in at least `k` intervals.

Time Complexity:
-  Building the `TreeMap`: We iterate through `n` intervals, and for each, we perform two `put` operations on the `TreeMap`. A `TreeMap.put()` operation takes `O(log M)` time, where `M` is the number of distinct keys in the map. In the worst case, `M` can be `2n` (two events for each interval). So, this step is `O(n log n)`.

-  Iterating the `TreeMap`: We iterate through at most `2n` entries in the `TreeMap`. Each step inside the loop involves constant time operations (arithmetic, `Math.max`). So, this step is `O(n)`.

-  Overall: The dominant factor is building the `TreeMap`, leading to a total time complexity of `O(N log N)`.

Space Complexity:
-   The `TreeMap` stores at most `2n` distinct event points. Each entry consumes constant space.
-   Therefore, the space complexity is `O(N)`.

This sweep-line approach efficiently handles the large coordinate range by only considering the points where the overlap count changes, rather than iterating through every single integer in the range.
*/

import java.util.TreeMap;

class Solution {
    public int powerfulInteger(int[][] intervals, int k) {
        TreeMap<Integer, Integer> map = new TreeMap<>();

        for (int[] interval : intervals) {
            map.put(interval[0], map.getOrDefault(interval[0], 0) + 1);
            map.put(interval[1] + 1, map.getOrDefault(interval[1] + 1, 0) - 1);
        }

        int currCount = 0;
        int prev = -1;
        int maxPowerful = -1;

        for (int point : map.keySet()) {
            int delta = map.get(point);

            if (currCount >= k) {
                maxPowerful = Math.max(maxPowerful, point - 1);
            }

            currCount += delta;
            prev = point;
        }

        return maxPowerful;
    }
}
