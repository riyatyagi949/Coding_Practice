// Problem Statement:
// Given an array `arr` of integers representing the number of pages in each book and an integer `k` representing the number of students. The goal is to allocate all books to `k` students such that the maximum number of pages a student receives is minimized.
// The allocation rules are:
// 1. Each student gets at least one book.
// 2. Books must be assigned in a contiguous sequence.
// 3. Each book is assigned to exactly one student.
// If it is not possible to allocate books to all students, return -1.

// Approach:
// This problem can be solved using binary search on the answer. The "answer" we are searching for is the minimum possible value of the maximum number of pages a student can receive.
//
// First, let's define the search space for our answer.
// The lower bound of the search space is the maximum number of pages in any single book, as each student must receive at least one book. So, `low = max(arr)`.
// The upper bound of the search space is the total number of pages, which is the sum of all pages in all books. So, `high = sum(arr)`.
//
// Now, we can perform a binary search within this range `[low, high]`.
// For a given `mid` value (which represents a potential maximum number of pages per student), we need to check if it's possible to allocate the books to `k` students such that no student receives more than `mid` pages.
//
// We can use a helper function `isPossible(mid, arr, k)` for this check.
// `isPossible` will work as follows:
// - Initialize `studentsNeeded = 1` and `currentPages = 0`.
// - Iterate through the `arr` array.
// - For each book `pages` in `arr`:
//   - If `currentPages + pages <= mid`, add the book to the current student's allocation: `currentPages += pages`.
//   - If `currentPages + pages > mid`, we need to start a new student. Increment `studentsNeeded` and assign the current book to this new student: `currentPages = pages`.
//   - We also need to handle the case where a single book has more pages than `mid`. This is covered by the initial lower bound check (`max(arr) <= mid`), but a good practice is to check `if (pages > mid)` and return `false` early.
//
// After iterating through all the books, if `studentsNeeded <= k`, it means `mid` is a possible maximum page limit. In this case, we try to find a smaller maximum, so we update `ans = mid` and search in the left half: `high = mid - 1`.
// If `studentsNeeded > k`, it means `mid` is too small, and we need to allow more pages per student. So we search in the right half: `low = mid + 1`.
//
// A special case to handle: if `k > arr.length`, it's impossible to give each student a book, so we should return -1. This should be the first check.
//
// The binary search continues until `low > high`. The final `ans` will hold the minimum possible maximum number of pages.
//
// Time Complexity:
// The binary search performs `O(log(sum(arr) - max(arr)))` iterations.
// In each iteration, the `isPossible` function takes `O(n)` time, where `n` is the number of books.
// So, the total time complexity is `O(n * log(sum(arr)))`.
// Given the constraints `n <= 10^6` and `arr[i] <= 10^3`, `sum(arr)` can be up to `10^6 * 10^3 = 10^9`. `log(10^9)` is approximately 30. The complexity is efficient enough.
//
// Space Complexity:
// The space complexity is `O(1)` as we are only using a few variables for the binary search and the helper function.

