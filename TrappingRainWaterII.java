/**
 * Problem Statement:
 * Given an m x n integer matrix `heightMap` representing the height of each unit cell in a 2D elevation map,
 * return the volume of water it can trap after raining.
 *
 * Optimal Approach: Trapping Rain Water II (Min-Heap / Dijkstra-like BFS)
 *
 * This problem is a 2D extension of the classic "Trapping Rain Water" problem. The water trapped at any cell (r, c)
 * is determined by the minimum height of the "walls" surrounding that cell. In 2D, the water can flow in four directions.
 *
 * The key insight is that water can only flow *out* from the center to the *boundaries*. The volume of water trapped at a cell
 * is constrained by the lowest boundary cell that connects it to the outside.
 *
 * We can model this problem as a **Multi-Source Shortest Path** problem, similar to Dijkstra's algorithm or a specialized BFS,
 * where the "distance" is the boundary height, and we start from all boundary cells.
 *
 * 1. Initialization:
 * - Start by adding all cells on the **outer boundary** of the `heightMap` to a **Min-Heap (PriorityQueue)**.
 * - Each element in the Min-Heap stores `[row, col, height]`, and the heap is ordered by `height`.
 * - Mark all boundary cells as `visited` to prevent revisiting them.
 * - Initialize `trappedWater` to 0.
 *
 * 2. Processing (Simulating water flow):
 * - While the Min-Heap is not empty:
 * - **Extract the cell with the smallest height** (`curr`) from the Min-Heap. This height, `h`, represents the **true barrier height** discovered so far that limits the water level for its unvisited neighbors.
 * - For each of its four unvisited neighbors (`nx`, `ny`):
 * - If the neighbor's height, `heightMap[nx][ny]`, is **lower** than the current barrier height `h`:
 * - Water is trapped at this neighbor. The volume is `h - heightMap[nx][ny]`.
 * - Add this volume to `trappedWater`.
 * - The new effective barrier height for this neighbor is still `h` (since the water level is constrained by the current lowest boundary).
 * - If the neighbor's height is **higher** than or equal to `h`:
 * - No water is trapped.
 * - The new effective barrier height for this neighbor is its own height, `heightMap[nx][ny]`.
 *
 * - **Crucially**: Add the neighbor to the Min-Heap with its new effective barrier height. This ensures that we always process the next cell with the *smallest* surrounding barrier.
 * - Mark the neighbor as `visited`.
 *
 * 3. Result:
 * - After the loop finishes, `trappedWater` holds the total volume.
 *
 * This greedy approach works because any water trapped in an inner cell is limited by the minimum height path connecting it to the boundary.
 * By always expanding from the *lowest* boundary cell, we correctly establish the water level/barrier height for adjacent inner cells.
 *
 * Time Complexity: O(m * n * log(m + n))
 * - We add O(m + n) initial boundary cells to the heap.
 * - In the worst case, every cell (m * n total) is added to the heap once and extracted once.
 * - Heap operations (insertion and extraction) take O(log(size of heap)). The maximum size of the heap is O(m * n).
 * - Therefore, the total time complexity is O(m * n * log(m * n)). However, a tighter bound for the Priority Queue implementation with $m \times n$ vertices and $4 \times m \times n$ edges is **O(m * n * log(m * n))** or **O(m * n * log(m + n))** as the heap size is bounded by $m \times n$.
 *
 * Space Complexity: O(m * n)
 * - Required for the `visited` array and the Priority Queue, both of which can store up to O(m * n) elements.
 */
// Optimal Solution in Java - 

import java.util.PriorityQueue;

