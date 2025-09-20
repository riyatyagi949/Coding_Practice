/**
 * Problem Statement:
 * Design a `Router` class to manage data packets with a fixed memory limit. The router should support the following operations:
 * - `Router(int memoryLimit)`: Initializes the router with a maximum capacity for packets. If adding a new packet exceeds this limit, the oldest packet is removed.
 * - `addPacket(int source, int destination, int timestamp)`: Adds a packet. A packet is defined by its source, destination, and timestamp. Duplicates are not added. Returns true if added, false otherwise.
 * - `forwardPacket()`: Forwards and removes the oldest packet (FIFO). Returns the packet as an array. Returns an empty array if no packets exist.
 * - `getCount(int destination, int startTime, int endTime)`: Returns the count of packets currently in the router with a matching destination and a timestamp within the specified range.
 *
 * Note: `addPacket` calls will have strictly increasing timestamps.
 *
 * Optimal Approach:
 * This problem requires a data structure that can handle three main functionalities efficiently:
 * 1. A fixed-size, FIFO queue for managing packet storage and removal.
 * 2. Fast lookup for duplicate packets.
 * 3. Efficient counting of packets based on destination and timestamp range.
 *
 * To handle the FIFO (First-In, First-Out) behavior and the memory limit, a `Queue` or `LinkedList` is a natural choice. We can use a `LinkedList` as it allows adding to the end and removing from the beginning in `O(1)` time.
 *
 * To check for duplicates efficiently, we need a way to store and query the unique packet identifier. A packet is unique by its `(source, destination, timestamp)` triplet. A `HashSet` is an excellent choice for this, as it provides `O(1)` average-time complexity for adding and checking for existence. We can create a custom `Packet` class or a simple unique identifier, like a string, to represent the triplet in the `HashSet`.
 *
 * For the `getCount` operation, which involves range queries on timestamps and a filter on destination, a simple `LinkedList` would require a full scan, leading to `O(N)` complexity where `N` is the number of stored packets. Given the constraints and the potential for a large number of packets, we need a more efficient way. The key hint is that timestamps are always increasing. This allows us to use a `HashMap` where keys are destinations, and values are sorted lists of timestamps for packets with that destination.
 *
 * Combining these, the optimal data structure design is:
 * - A `LinkedList<Packet>` to maintain the FIFO order of packets. This is for `addPacket` (oldest removal) and `forwardPacket`.
 * - A `HashSet<String>` to quickly check for duplicate packets. The string key can be `source + "," + destination + "," + timestamp`.
 * - A `HashMap<Integer, List<Integer>>` to handle `getCount` queries efficiently. The key is the `destination`, and the value is a sorted `List` of `timestamps` for that destination. Since `addPacket` timestamps are increasing, we can simply append new timestamps to the end of the list.
 *
 * The implementation of each method would be as follows:
 * - **`Router(int memoryLimit)`**: Initialize the `LinkedList`, `HashSet`, and `HashMap`. Store the `memoryLimit`.
 * - **`addPacket(int source, int destination, int timestamp)`**:
 * - Create a string key for the packet.
 * - Check if the key exists in the `HashSet`. If it does, return `false`.
 * - If the `LinkedList` size is equal to `memoryLimit`, remove the oldest packet from the head of the `LinkedList` and its corresponding entries from the `HashSet` and `HashMap`.
 * - Add the new packet to the tail of the `LinkedList`.
 * - Add the new packet's key to the `HashSet`.
 * - Add the new packet's timestamp to the corresponding destination list in the `HashMap`. If the destination doesn't exist, create a new list.
 * - Return `true`.
 * - **`forwardPacket()`**:
 * - If the `LinkedList` is empty, return an empty array.
 * - Remove the packet from the head of the `LinkedList`.
 * - Remove its key from the `HashSet` and its timestamp from the `HashMap`. Removing from the `HashMap` list can be `O(N)` but since `forwardPacket` is also removing the oldest packet, we can optimize by storing a pointer to the current `forwardPacket` in each destination's list, and just remove it. A better approach is to not remove timestamps from the map, but rather to use a two-pointer approach or binary search in `getCount` to handle stale timestamps. A `LinkedList` is good for adding/removing from both ends, so a `Deque` is also an option. A `LinkedList` as a queue is perfect here.
 * - **`getCount(int destination, int startTime, int endTime)`**:
 * - Retrieve the list of timestamps for the given `destination` from the `HashMap`.
 * - If the list doesn't exist, return 0.
 * - Use binary search (`lower_bound` and `upper_bound` or `Collections.binarySearch`) on the sorted timestamp list to find the count of timestamps within the `[startTime, endTime]` range. This gives `O(log N)` complexity for the count, where `N` is the number of packets for that destination.
 *
 * This approach provides `O(1)` average time for `addPacket` and `forwardPacket`, and `O(log N)` for `getCount`, making it optimal.
 *
 * Time Complexity:
 * - `Router`: O(1)
 * - `addPacket`: O(1) average.
 * - `forwardPacket`: O(1) average.
 * - `getCount`: O(log M) where M is the number of packets with the given destination.
 *
 * Space Complexity:
 * - O(N), where N is the `memoryLimit`. The `LinkedList`, `HashSet`, and `HashMap` will store up to `N` packets.
 */
// Optimal Solution in Java - 
import java.util.*;

class Router {
    private int memoryLimit;
    private Deque<int[]> queue; 
    private Set<String> seen;   

    private Map<Integer, List<Integer>> timeMap;
    private Map<Integer, List<Integer>> prefixMap;

    public Router(int memoryLimit) {
        this.memoryLimit = memoryLimit;
        this.queue = new ArrayDeque<>();
        this.seen = new HashSet<>();
        this.timeMap = new HashMap<>();
        this.prefixMap = new HashMap<>();
    }

    public boolean addPacket(int source, int destination, int timestamp) {
        String key = source + "-" + destination + "-" + timestamp;
        if (seen.contains(key))
         return false;

        if (queue.size() == memoryLimit) {
            int[] old = queue.pollFirst();
            String oldKey = old[0] + "-" + old[1] + "-" + old[2];
            seen.remove(oldKey);

            List<Integer> times = timeMap.get(old[1]);
            List<Integer> prefix = prefixMap.get(old[1]);
            int idx = Collections.binarySearch(times, old[2]);

            for (int i = idx; i < prefix.size(); i++) {
                prefix.set(i, prefix.get(i) - 1);
            }
            if (prefix.get(prefix.size() - 1) == 0) {
                timeMap.remove(old[1]);
                prefixMap.remove(old[1]);
            }
        }

        queue.offerLast(new int[]{source, destination, timestamp});
        seen.add(key);

        timeMap.putIfAbsent(destination, new ArrayList<>());
        prefixMap.putIfAbsent(destination, new ArrayList<>());

        List<Integer> times = timeMap.get(destination);
        List<Integer> prefix = prefixMap.get(destination);

        times.add(timestamp);
        if (prefix.isEmpty()) prefix.add(1);
        else prefix.add(prefix.get(prefix.size() - 1) + 1);

        return true;
    }

    public int[] forwardPacket() {
        if (queue.isEmpty()) return new int[]{};

        int[] packet = queue.pollFirst();
        String key = packet[0] + "-" + packet[1] + "-" + packet[2];
        seen.remove(key);

        List<Integer> times = timeMap.get(packet[1]);
        List<Integer> prefix = prefixMap.get(packet[1]);
        int idx = Collections.binarySearch(times, packet[2]);

        for (int i = idx; i < prefix.size(); i++) {
            prefix.set(i, prefix.get(i) - 1);
        }
        if (prefix.get(prefix.size() - 1) == 0) {
            timeMap.remove(packet[1]);
            prefixMap.remove(packet[1]);
        }

        return packet;
    }

    public int getCount(int destination, int startTime, int endTime) {
        if (!timeMap.containsKey(destination)) return 0;

        List<Integer> times = timeMap.get(destination);
        List<Integer> prefix = prefixMap.get(destination);

        int hi = upperBound(times, endTime);
        if (hi < 0) 
        return 0;

        int lo = upperBound(times, startTime - 1);

        int total = prefix.get(hi);
        int before = lo >= 0 ? prefix.get(lo) : 0;
        return total - before;
    }
    private int upperBound(List<Integer> arr, int target) {
        int l = 0, r = arr.size() - 1, ans = -1;
        
        while (l <= r) {
            int m = l + (r - l) / 2;
            if (arr.get(m) <= target) {
                ans = m;
                l = m + 1;
            }
             else {
                r = m - 1;
            }
        }
        return ans;
    }
}
