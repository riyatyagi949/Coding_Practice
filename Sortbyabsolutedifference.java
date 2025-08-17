// Problem Statement:
// Given an integer x and an array arr[], rearrange the elements of the array
// based on their absolute difference with x. Elements with a smaller absolute
// difference should come first. If two or more elements have the same absolute
// difference, their relative order should remain the same as in the original array.

// Approach:
// This problem can be solved by creating a custom sorting comparator.
// The comparator will take two elements, a and b, from the array and a
// reference integer x. The comparison logic is as follows:
// 1. Calculate the absolute difference of a with x: |a - x|.
// 2. Calculate the absolute difference of b with x: |b - x|.
// 3. Compare these two absolute differences.
//    - If |a - x| < |b - x|, a comes before b.
//    - If |a - x| > |b - x|, b comes before a.
//    - If |a - x| == |b - x|, their original relative order must be preserved.
// To preserve the original relative order (a stable sort), we can use a wrapper
// class or a lambda expression that captures the original index. However, a simpler
// approach that guarantees stability is to use a custom comparator with a stable
// sorting algorithm like Arrays.sort() in Java. The `Comparator` will only
// compare based on the absolute difference. If the differences are equal, the
// `Comparator` should return 0, allowing the stable sort to maintain the original
// relative order.

// Optimal Solution:
// 1. Create a custom `Comparator<Integer>` to define the sorting logic.
// 2. The `compare(a, b)` method of the comparator will return `|a - x| - |b - x|`.
// 3. To use a custom comparator with `Arrays.sort()`, we need to sort an
//    array of `Integer` objects, not primitive `int`s. So, we'll first convert
//    the `int[]` to `Integer[]`.
// 4. Use `Arrays.sort()` with the custom comparator to sort the array.
// 5. If the original array was a primitive `int[]`, convert the sorted `Integer[]`
//    back to `int[]`.

// Time Complexity: O(N log N), where N is the size of the array, due to the sorting operation.
// Space Complexity: O(N), for converting the primitive int array to an Integer array.

import java.util.*;

class Solution {
    public void rearrange(int[] arr, int x) {
        Integer[] temp = Arrays.stream(arr).boxed().toArray(Integer[]::new);
        Arrays.sort(temp, (a, b) -> {

            int diffA = Math.abs(a - x);
            int diffB = Math.abs(b - x);
            return diffA == diffB ? 0 : diffA - diffB;
        });
        for (int i = 0; i < arr.length; i++) arr[i] = temp[i];
    }
}
