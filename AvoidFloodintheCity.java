/**
 * Problem Statement:
 * Given an array `rains` where `rains[i] > 0` indicates rain over lake `rains[i]` and `rains[i] == 0` means a day to dry one lake.
 * If it rains on a full lake, a flood occurs. You must choose a lake to dry on a day where `rains[i] == 0`.
 * Return an array `ans` where `ans[i] = -1` for a rain day, and `ans[i]` is the lake to dry on a dry day (`rains[i] == 0`).
 * If it's impossible to avoid a flood, return an empty array.
 *
 * Optimal Approach: Greedy Strategy with Priority Tracking
 * The core challenge is deciding which full lake to dry on a day with no rain (`rains[i] == 0`).
 * Since we want to avoid a flood, we must dry a lake that is scheduled to fill up *again* as soon as possible. Drying a lake that won't rain again until much later is wasteful.
 * This suggests a **greedy approach** based on *future necessity*.
 *
 * We need two main data structures for this:
 * 1. A **HashMap (`full`)**: To keep track of the *last day* a lake filled up. `Map<LakeID, DayIndex>`.
 * 2. A **TreeSet (`dryDays`)**: To store the *indices* (days) when we have a dry day (`rains[i] == 0`). A `TreeSet` is crucial because it allows us to quickly find the *next available* dry day *after* a specific rainy day using the `higher()` method. This is a form of priority for the dry days.
 *
 * Algorithm Steps:
 * 1. Initialize `ans` array of the same length as `rains`.
 * 2. Initialize `full` (HashMap) to store `{lakeID: lastRainDayIndex}`.
 * 3. Initialize `dryDays` (TreeSet) to store all dry day indices.
 *
 * 4. Iterate through the `rains` array from `i = 0` to `n-1`:
 * a. **If `rains[i] == 0` (Dry Day):**
 * - Mark `ans[i] = 1` initially. We'll update this later to the actual lake to dry. Any non-negative value is fine, but 1 is a safe placeholder as lake IDs are $>0$.
 * - Add the current day index `i` to `dryDays`.
 *
 * b. **If `rains[i] > 0` (Rain Day on Lake `L = rains[i]`):**
 * - Mark `ans[i] = -1`.
 * - **Check for Flood**: If lake `L` is already in `full`, it means this lake is currently full and will flood today. We *must* have dried it between its last rain day and today.
 * - Let `lastRainDay = full.get(L)`.
 * - We need a dry day `dryDayIndex` such that `lastRainDay < dryDayIndex < i`.
 * - Use `dryDays.higher(lastRainDay)` to find the **earliest available dry day** after the lake filled up.
 * - **If `dryDayIndex` is `null`**: No dry day exists between the two rains on lake $L$. It's *impossible* to avoid the flood. Return an empty array `new int[0]`.
 * - **If a dry day is found**:
 * - We use this `dryDayIndex` to dry lake `L`.
 * - Update `ans[dryDayIndex] = L`.
 * - Remove `dryDayIndex` from `dryDays` as it's now used.
 * - **Update Lake State**: Update the `full` map with the current day for lake $L$.
 * `full.put(L, i)`.
 *
 * 5. **Handle Remaining Dry Days**: After the loop, any dry day indices remaining in `dryDays` can be used to dry any lake (e.g., lake 1). Since the problem allows for drying an empty lake and nothing changes, and we need a non-negative value, we assign a placeholder lake ID (like 1) to these unused dry days in `ans`. The initial placeholder in step 4.a already handles this for any `rains[i] == 0` that was never assigned a specific lake to dry.
 *
 * Time Complexity: O(n log n)
 * - The main loop iterates `n` times.
 * - Inside the loop, `HashMap` operations are $O(1)$.
 * - `TreeSet` operations (`add`, `higher`, `remove`) take $O(\log n)$ time.
 * - Since all critical operations are bounded by $O(\log n)$, the total time complexity is $O(n \log n)$.
 *
 * Space Complexity: O(n)
 * - The `ans` array takes $O(n)$.
 * - The `full` HashMap can store up to $n$ entries in the worst case (if all lakes are unique).
 * - The `dryDays` TreeSet can store up to $n$ indices in the worst case (if half the days are dry).
 */
// Optimal Solution in Java -
import java.util.*;

class Solution {
    public int[] avoidFlood(int[] rains) {
        int n = rains.length;
        int[] ans = new int[n];
        Map<Integer, Integer> full = new HashMap<>();
        TreeSet<Integer> dryDays = new TreeSet<>();

        for (int i = 0; i < n; i++) {
            if (rains[i] == 0) 
            {
                dryDays.add(i);
                ans[i] = 1;
            }
             else {
                ans[i] = -1;
                if (full.containsKey(rains[i]))
                 {
                    Integer dry = dryDays.higher(full.get(rains[i]));
                    if (dry == null)
                     return new int[0];
                    ans[dry] = rains[i];
                    dryDays.remove(dry);
                }
                full.put(rains[i], i);
            }
        }
        return ans;
    }
}