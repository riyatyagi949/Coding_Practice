// Problem Statement:
// Given an array arr[], where each element contains either a 'P' for policeman or a 'T' for thief.
// Find the maximum number of thieves that can be caught by the police.
// Each policeman can catch only one thief.
// A policeman cannot catch a thief who is more than k units away from him.

// Approach:
// This problem can be solved using a two-pointer approach. We maintain two pointers, one for policemen (pIdx) and one for thieves (tIdx).
// Both pointers start from the beginning of the array.
// We iterate through the array, and when we encounter a policeman or a thief, we store their indices in separate lists (police and thief).
// After populating these lists, we use the two-pointer approach on the 'police' and 'thief' lists.
// We try to match a policeman with a thief. If the absolute difference between their indices is less than or equal to k,
// it means the policeman can catch the thief. We increment the count of caught thieves, and move both pointers forward.
// If the policeman's index is less than the thief's index, it means this policeman is too far behind the current thief (or the thief is too far ahead).
// In this case, we move the policeman pointer forward, hoping to find a thief within range.
// If the thief's index is less than the policeman's index, it means this thief is too far behind the current policeman (or the policeman is too far ahead).
// In this case, we move the thief pointer forward, hoping to find a policeman within range.

// Time Complexity:
// O(N) where N is the length of the array.
// Populating the police and thief lists takes O(N).
// The two-pointer approach on these lists also takes O(N) in the worst case, as each pointer traverses its respective list at most once.

// Space Complexity:
// O(N) in the worst case, if all elements are 'P' or 'T', we store their indices in the lists.

// Optimal Solution:
class Solution {
    public int catchThieves(char[] arr, int k) {
        // code here
        int n = arr.length;
        java.util.ArrayList<Integer> police = new java.util.ArrayList<>();
        java.util.ArrayList<Integer> thief = new java.util.ArrayList<>();

        for (int i = 0; i < n; i++) {
            if (arr[i] == 'P') {
                police.add(i);
            } else {
                thief.add(i);
            }
        }

        int pIdx = 0;
        int tIdx = 0;
        int caughtThieves = 0;

        while (pIdx < police.size() && tIdx < thief.size()) {
            if (Math.abs(police.get(pIdx) - thief.get(tIdx)) <= k) {
                caughtThieves++;
                pIdx++;
                tIdx++;
            } else if (police.get(pIdx) < thief.get(tIdx)) {
                pIdx++;
            } else {
                tIdx++;
            }
        }
        return caughtThieves;
    }
}