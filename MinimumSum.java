/*
Problem Statement:
Given an array arr[] consisting of digits, your task is to form two numbers using all the digits such that their sum is minimized. Return the minimum possible sum as a string with no leading zeroes.

Approach:
To minimize the sum of two numbers formed by the given digits, we should try to make the numbers as small as possible. This can be achieved by sorting the digits in ascending order. Then, we can alternate placing the digits into two separate numbers. For example, the smallest digit goes to the first number, the second smallest to the second number, the third smallest back to the first number, and so on. This ensures that the smaller digits contribute to the higher place values less frequently.

After forming the two numbers, we need to sum them. Since the problem asks for the result as a string and the numbers can be large, we should use String manipulation for addition or BigInteger if direct conversion to long is not sufficient. Given the constraints (arr.size() <= 10^6), BigInteger is the safest approach.

Steps:
1. Sort the input array `arr` in ascending order.
2. Initialize two StringBuilder objects, `num1` and `num2`, to store the digits of the two numbers.
3. Iterate through the sorted array. If the index `i` is even, append `arr[i]` to `num1`. If `i` is odd, append `arr[i]` to `num2`.
4. Convert `num1` and `num2` to BigInteger objects.
5. Calculate their sum using `add()` method of BigInteger.
6. Convert the sum back to a String.
7. Handle leading zeros: The BigInteger `toString()` method naturally handles leading zeros for numbers other than "0". If the sum is "0", it will correctly return "0". The problem states "no leading zeroes", which `BigInteger.toString()` already ensures for non-zero numbers.

Time Complexity:
Sorting the array takes O(N log N) time, where N is the number of digits in `arr`.
Iterating through the array to form the two numbers takes O(N) time.
Converting StringBuilders to BigIntegers and performing addition on BigIntegers depends on the number of digits, say M. BigInteger operations are typically O(M) or O(M log M) for very large numbers. Here, M can be up to N. So, it will be roughly O(N) for addition.
Overall, the dominant factor is sorting, so the time complexity is O(N log N).

Space Complexity:
Storing the sorted array takes O(N) space (if a new array is created, otherwise O(1) if sorting in-place).
The StringBuilder objects `num1` and `num2` can store up to N/2 digits each, so O(N) space.
BigInteger objects also use space proportional to the number of digits, so O(N) space.
Overall, the space complexity is O(N).
*/

class Solution {
    String minSum(int[] arr) {
        Arrays.sort(arr);

        StringBuilder num1 = new StringBuilder();
        StringBuilder num2 = new StringBuilder();

        for (int i = 0; i < arr.length; i++) {
            if (i % 2 == 0) {
                num1.append(arr[i]);
            } else {
                num2.append(arr[i]);
            }
        }
        java.math.BigInteger bNum1 = new java.math.BigInteger(num1.toString());
        java.math.BigInteger bNum2 = new java.math.BigInteger(num2.toString());

        java.math.BigInteger sum = bNum1.add(bNum2);

        return sum.toString();
    }
}