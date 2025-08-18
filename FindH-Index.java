// Problem Statement:
// You are given an array citations[], where each element citations[i] represents the number of citations received by the ith paper of a researcher. You have to calculate the researcher’s H-index.
// The H-index is defined as the maximum value H, such that the researcher has published at least H papers, and all those papers have citation value greater than or equal to H.

// Approach:
// A straightforward approach is to sort the array of citations in non-decreasing order.
// After sorting, we can iterate through the sorted array. The H-index will be at most the total number of papers. We can iterate from the largest citation value downwards.
// Let's say we have a sorted array `citations` of size `n`.
// The H-index `H` means there are `H` papers with at least `H` citations.
// If we sort the array in ascending order, the number of papers with at least `citations[i]` citations is `n - i`.
// We can iterate from `i = 0` to `n-1` and check if `citations[i]` is greater than or equal to `n - i`.
// The first `i` that satisfies this condition gives us the H-index, which is `n - i`.
// The problem with this approach is that sorting takes `O(n log n)` time.

// A more optimal approach is to use a counting sort-like method.
// We can create a frequency array `counts` of size `n + 1` (since the H-index can be at most `n`).
// `counts[i]` will store the number of papers with `i` citations.
// We iterate through the `citations` array and populate the `counts` array.
// For any citation `c` greater than `n`, it can be treated as `n` because it can't contribute to an H-index greater than `n`.
// After populating the `counts` array, we can iterate backwards from `n` down to `0`.
// We keep a running sum of papers. Let's call it `papersCount`.
// At each step `i` (from `n` down to `0`), we add `counts[i]` to `papersCount`.
// If at any point `papersCount` is greater than or equal to `i`, we have found our H-index, which is `i`.
// This is because we have found `i` papers with at least `i` citations. Since we are iterating downwards, the first `i` we find that satisfies this condition will be the maximum possible `H`.
// This approach has a time complexity of `O(n)` and a space complexity of `O(n)`.

// Time Complexity: O(n)
// Space Complexity: O(n)

// Optimal Solution:
