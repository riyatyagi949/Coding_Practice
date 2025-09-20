/**
 * Problem Statement:
 * You are given an array of integers `arr[]`. Your task is to find the length of the longest subarray such that all the elements of the subarray
 * are smaller than or equal to the length of the subarray.
 *
 * Example:
 * Input: arr[] = [1, 2, 3]
 * Output: 3
 * Explanation: The longest subarray is [1, 2, 3] itself, which has a length of 3. All elements (1, 2, 3) are <= 3.
 *
 * Optimal Approach:
 * The problem requires finding the longest subarray `arr[i...j]` such that for every element `arr[k]` in this subarray, `arr[k] <= (j - i + 1)`.
 * This condition is equivalent to `max(arr[i...j]) <= (j - i + 1)`.
 *
 * A brute-force approach would be to check every possible subarray, which would be inefficient with a time complexity of O(n^2).
 * A more optimal approach is to use a monotonic stack to efficiently find the nearest greater element on both the left and right sides for each element `arr[i]`.
 *
 * For each element `arr[i]`, we can find the longest subarray `arr[l...r]` where `arr[i]` is the maximum element.
 * - `l` is the index of the nearest element to the left of `i` that is greater than `arr[i]` (Previous Greater Element or PGE).
 * - `r` is the index of the nearest element to the right of `i` that is greater than `arr[i]` (Next Greater Element or NGE).
 *
 * The length of this subarray is `r - l - 1`. The key insight is that if the maximum element `arr[i]` in this subarray is less than or equal to the subarray's length (`arr[i] <= r - l - 1`), then all other elements in the subarray, which are smaller than or equal to `arr[i]`, must also be less than or equal to the length.
 *
 * The algorithm proceeds as follows:
 * 1. Find the index of the Next Greater Element (NGE) for each element in the array. This can be done in O(n) time using a monotonic stack.
 * 2. Find the index of the Previous Greater Element (PGE) for each element. This also takes O(n) time using a monotonic stack.
 * 3. Iterate through the array from `i = 0` to `n-1`. For each element `arr[i]`:
 * - The length of the subarray where `arr[i]` is the maximum is `nge[i] - pge[i] - 1`.
 * - Check if `arr[i] <= nge[i] - pge[i] - 1`.
 * - If the condition holds, update the maximum length found so far.
 * 4. Return the maximum length found. If no such subarray exists, the initial value of 0 is returned.
 *
 * Time Complexity: O(n). We iterate through the array three times: once for PGE, once for NGE, and once to check the condition. Each of these loops is linear.
 * Space Complexity: O(n) to store the `nge` and `pge` arrays, as well as the stack.
 */

import java.util.Stack;

class Solution {
    static void findnge(int arr[],int nge[],int n)
    {
        Stack<Integer>st=new Stack<>();
        for(int i=n-1;i>=0;i--)
        {
            while(!st.isEmpty() && arr[st.peek()]<=arr[i])
            {
                st.pop();
            }
            if(st.isEmpty())nge[i]=n;
            else nge[i]=st.peek();
            st.push(i);
        }
    }
    static void findpge(int arr[],int pge[],int n)
    {
        Stack<Integer>st=new Stack<>();
        for(int i=0;i<n;i++)
        {
            while(!st.isEmpty() && arr[st.peek()]<=arr[i])
            {
                st.pop();
            }
            if(st.isEmpty())pge[i]=-1;
            else pge[i]=st.peek();
            st.push(i);
        }
    }
    public static int longestSubarray(int[] arr) {
        int n=arr.length;
        int nge[]=new int[n];
        int pge[]=new int[n];
        findnge(arr,nge,n);
        findpge(arr,pge,n);
        
        int ans=0;
        for(int i=0;i<n;i++)
        {
            int l=pge[i];
            int r=nge[i];
            if(r-l-1>=arr[i])ans=Math.max(ans,r-l-1);
        }
        return ans;
    }
}

