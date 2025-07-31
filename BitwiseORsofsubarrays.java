/*
Problem Statement:
Find the maximum integer that appears in at least 'k' intervals from a given list of 'n' intervals. Return -1 if no such integer exists.

Approach:
Use a sweep-line algorithm with a TreeMap to track interval overlaps.
1. Represent each interval [start, end] as two events: +1 at 'start' and -1 at 'end + 1'. Store these in a TreeMap, which keeps them sorted by coordinate.
2. Iterate through the sorted events in the TreeMap. Maintain a 'currentOverlap' count.
3. Before processing a new event's change, if 'currentOverlap' was >= 'k' for the segment ending just before the current event's coordinate, update the 'maxPowerfulInteger'.
4. After applying the current event's change, if 'currentOverlap' becomes >= 'k', the current coordinate itself is powerful, so update 'maxPowerfulInteger'.

Time Complexity: O(N log N), where N is the number of intervals.
Space Complexity: O(N), for the TreeMap.
*/

import java.util.Map;
import java.util.TreeMap;

