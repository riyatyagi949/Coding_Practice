// Problem Statement:
// Given an array arr[] consisting of positive integers, your task is to find the length of the longest subarray that contains at most two distinct integers.

// Approach:
// We can use a sliding window approach. We maintain a window [left, right] and a frequency map (or an array) to store the count of distinct elements within the current window.
// We expand the window by moving the `right` pointer. For each element `arr[right]`, we increment its count in the frequency map.
// If the number of distinct elements in the window becomes greater than 2, we shrink the window from the left. We decrement the count of `arr[left]` in the frequency map. If the count of `arr[left]` becomes 0, it means that distinct element is no longer in the window, so we remove it from our distinct count. We continue shrinking the window until the number of distinct elements is at most 2.
// At each step, after ensuring the window has at most two distinct elements, we update the maximum length found so far, which is `right - left + 1`.

// Time Complexity:
// The time complexity will be O(N) where N is the length of the array `arr`. Both `left` and `right` pointers traverse the array at most once. Each operation (adding to map, removing from map, checking map size) takes O(1) on average for a HashMap, or O(1) for an array if the range of values is small.

// Space Complexity:
// The space complexity will be O(1) because the frequency map will store at most 3 distinct elements at any point in time (at most two distinct elements and one being removed). Since `arr[i]` can be up to 10^5, a HashMap is more appropriate, but the number of distinct elements in the map at any time is bounded by 3, making it effectively constant space. If we use an array as a frequency map, the space would be O(max_arr_value), which could be O(10^5), but given the problem constraint, a HashMap is a better choice for practical constant space.

class Solution {
    public int totalElements(int[] arr) {
        Map<Integer, Integer> freqMap = new HashMap<>();
        
        int start = 0;
        int maxLen = 0;
        
        for (int end = 0; end < arr.length; end++) {
            freqMap.put(arr[end], freqMap.getOrDefault(arr[end], 0) + 1);
            
            while (freqMap.size() > 2) {
                freqMap.put(arr[start], freqMap.get(arr[start]) - 1);
                if (freqMap.get(arr[start]) == 0) {
                    freqMap.remove(arr[start]);
                }
                start++;
            }
            maxLen = Math.max(maxLen, end - start + 1);
        }
          return maxLen;
    }
}
