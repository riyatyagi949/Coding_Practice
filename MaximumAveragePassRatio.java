// Problem Statement:
// There is a school that has classes of students and each class will be having a final exam. You are given a 2D integer array classes, where classes[i] = [passi, totali]. You know beforehand that in the ith class, there are totali total students, but only passi number of students will pass the exam. You are also given an integer extraStudents. There are another extraStudents brilliant students that are guaranteed to pass the exam of any class they are assigned to. You want to assign each of the extraStudents students to a class in a way that maximizes the average pass ratio across all the classes.
// The pass ratio of a class is equal to the number of students of the class that will pass the exam divided by the total number of students of the class. The average pass ratio is the sum of pass ratios of all the classes divided by the number of the classes.
// Return the maximum possible average pass ratio after assigning the extraStudents students. Answers within 10^-5 of the actual answer will be accepted.

// Approach:
// This problem can be solved using a greedy approach. To maximize the average pass ratio, we should always add the next extra student to the class that will yield the largest increase in its pass ratio. We can calculate the gain for each class by adding one student and then using a max-heap (priority queue) to efficiently find the class with the maximum gain.
// The gain for a class with 'pass' and 'total' students is given by the change in ratio: ((pass + 1) / (total + 1)) - (pass / total).
// The algorithm is as follows:
// 1. Initialize a max-heap to store the potential gain and the current pass and total students for each class.
// 2. Iterate through the `classes` array. For each class, calculate the gain and push it into the max-heap.
// 3. Loop `extraStudents` times. In each iteration:
//    a. Pop the class with the maximum gain from the heap.
//    b. Increment the pass and total student counts for that class by one.
//    c. Calculate the new gain for the updated class and push it back into the heap.
// 4. After distributing all extra students, calculate the sum of the final pass ratios for all classes in the heap.
// 5. The maximum average pass ratio is this sum divided by the total number of classes.

// Time Complexity:
// O(N log N + extraStudents * log N), where N is the number of classes.
// Initializing the heap takes O(N log N). Each of the `extraStudents` operations involves a heap poll and a heap offer, both of which take O(log N).

// Space Complexity:
// O(N), for storing the classes in the priority queue.

import java.util.*;
class Solution {
    public double maxAverageRatio(int[][] classes, int extraStudents) {
        PriorityQueue<double[]> pq = new PriorityQueue<>((a, b) -> Double.compare(b[0], a[0]));

        for (int[] cls : classes) {
            double pass = cls[0];
            double total = cls[1];
            double gain = (pass + 1) / (total + 1) - pass / total;
            pq.add(new double[]{gain, pass, total});
        }

        for (int i = 0; i < extraStudents; i++) {
            double[] top = pq.poll();
            double pass = top[1];
            double total = top[2];
            pass++;
            total++;
            double newGain = (pass + 1) / (total + 1) - pass / total;
            pq.add(new double[]{newGain, pass, total});
        }

        double totalRatio = 0;
        while (!pq.isEmpty()) {
            double[] cls = pq.poll();
            totalRatio += cls[1] / cls[2];
        }

        return totalRatio / classes.length;
    }
}