// Problem Statement:
// Implement FindSumPairs class:
// - FindSumPairs(nums1, nums2): Initializes with two integer arrays.
// - add(index, val): Adds val to nums2[index].
// - count(tot): Returns count of pairs (i, j) where nums1[i] + nums2[j] == tot.

// Approach:
// Store nums1 as is. Use a HashMap (freq2) to store frequencies of numbers in nums2.
// - Constructor: Populates freq2 from nums2.
// - add: Updates nums2 and decrements/increments frequencies in freq2.
// - count: Iterates through nums1, calculates required complement (tot - nums1[i]),
//   and sums up its frequency from freq2.

// Time Complexity:
// - Constructor: O(N2) where N2 is nums2.length.
// - add: O(1) average.
// - count: O(N1) average where N1 is nums1.length.

// Space Complexity:
// O(N1 + N2) for storing arrays and freq2 map.

class FindSumPairs {
    private int[] nums1;
    private int[] nums2;
    private Map<Integer, Integer> freq2;

    public FindSumPairs(int[] nums1, int[] nums2) {
        this.nums1 = nums1;
        this.nums2 = nums2;
        this.freq2 = new HashMap<>();
        for (int num : nums2) {
            freq2.put(num, freq2.getOrDefault(num, 0) + 1);
        }
    }
     public void add(int index, int val) {
        freq2.put(nums2[index], freq2.get(nums2[index]) - 1);
        nums2[index] += val;
        freq2.put(nums2[index], freq2.getOrDefault(nums2[index], 0) + 1);
    }
     public int count(int tot) {
        int pairsCount = 0;
        for (int num1 : nums1) {
            pairsCount += freq2.getOrDefault(tot - num1, 0);
        }
        return pairsCount;
    }
}

/**
 * Your FindSumPairs object will be instantiated and called as such:
 * FindSumPairs obj = new FindSumPairs(nums1, nums2);
 * obj.add(index,val);
 * int param_2 = obj.count(tot);
 */