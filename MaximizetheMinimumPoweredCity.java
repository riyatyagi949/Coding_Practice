/**
 * Problem Statement: Maximize the Minimum Powered City
 * ----------------------------------------------------
 * Given an array 'stations' (power stations per city), a range 'r', and 'k' 
 * additional power stations to build. A station at city 'i' powers cities 'j' 
 * where |i - j| <= r. The power of a city is the total number of stations powering it.
 * Return the maximum possible minimum power of any city after optimally building the 'k' stations.
 *
 * Constraints:
 * 1 <= n <= 10^5
 * 0 <= r < n
 * 0 <= k <= 10^9
 *//**
     * Optimal Solution: Binary Search on the Answer + Greedy Check
     * -------------------------------------------------------------
     * Since we want to maximize the minimum power (a monotonic property), we can 
     * binary search for the final minimum power, `minPower`.
     *
     * The `check(minPower)` function must determine if it's possible to make every 
     * city's power at least `minPower` using at most `k` additional stations.
     *
     * * The Greedy Check:
     * 1. To satisfy `power[i] >= minPower`, if city `i`'s current power is too low, 
     * we must build a station at some city `j` such that `|i - j| <= r`.
     * 2. To minimize the total stations built, the **greedy choice** is to build the 
     * required station at the **farthest possible city to the right**, specifically at 
     * city `i + r`. This station provides power to the current deficient city `i` while 
     * also covering future cities `i+1, i+2, ..., i+2r`.
     * 3. We use a **difference array/sliding window technique** to efficiently track the 
     * cumulative effect of the original stations plus the newly built stations as we 
     * iterate through the cities.
     *
/**
 * Time Complexity Analysis:
 * -------------------------
 * O(N * log(MaxPower)), where N is the length of 'stations' and MaxPower is 
 * the maximum possible power (approx. 10^10).
 * - **Binary Search:** The search range size is MaxPower. The number of iterations is O(log(MaxPower)).
 * - **Check Function:** The `check` function uses the **Greedy + Difference Array/Sliding Window**
 * technique, which iterates through the cities once, taking O(N) time.
 * - **Overall Complexity:** O(N * log(MaxPower)). This is efficient as N <= 10^5 and log(MaxPower) 
 * is small (log2(10^10) ~ 34).
 *
 * Space Complexity Analysis:
 * --------------------------
 * O(N)
 * - Required for the `powerDelta` array (or difference array), which has size proportional to N.
 */
// Optimal Solution in Java - 
import java.util.Arrays;

class Solution 
{
  public long maxPower(int[] stations, int r, int k) 
  {
    long left = Arrays.stream(stations).min().getAsInt();
    long right = Arrays.stream(stations).asLongStream().sum() + k + 1;

    while (left < right)
     {
      final long mid = (left + right) / 2;
      if (check(stations.clone(), r, k, mid))
        left = mid + 1;
      else
        right = mid;
    }
    return left - 1;
  }
  boolean check(int[] stations, int r, int additionalStations, long minPower) 
  {
    final int n = stations.length;
    long power = 0;

    for (int i = 0; i < r; ++i)
      power += stations[i];

    for (int i = 0; i < n; ++i) 
    {
      if (i + r < n)
        power += stations[i + r];
      if (power < minPower)
       {
        final long requiredPower = minPower - power;
        if (requiredPower > additionalStations)
          return false;

        stations[Math.min(n - 1, i + r)] += requiredPower;
        additionalStations -= requiredPower;
        power += requiredPower;
      }
      if (i - r >= 0)
        power -= stations[i - r];
    }
    return true;
  }
}
