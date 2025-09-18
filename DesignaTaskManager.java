/**
 * Problem Statement:
 * Design a `TaskManager` class to manage tasks with user IDs, task IDs, and priorities.
 * The class must support the following operations:
 * - `TaskManager(vector<vector<int>>& tasks)`: Initializes the system with a list of tasks.
 * - `void add(int userId, int taskId, int priority)`: Adds a new task.
 * - `void edit(int taskId, int newPriority)`: Updates the priority of an existing task.
 * - `void rmv(int taskId)`: Removes an existing task.
 * - `int execTop()`: Executes the task with the highest priority. In case of a tie, the one with the highest `taskId` is executed.
 * The executed task is removed. Returns the `userId` of the executed task, or -1 if no tasks exist.
 *
 * Optimal Approach:
 * The primary challenge is efficiently handling the `execTop` operation, which requires finding and removing the task with the
 * highest priority and tie-breaking by `taskId`. This is a classic use case for a **max-priority queue**.
 *
 * We need a data structure that can:
 * 1.  Maintain tasks ordered by priority (descending).
 * 2.  Handle tie-breaking with `taskId` (descending).
 * 3.  Support fast `add`, `edit`, and `rmv` operations.
 *
 * A **PriorityQueue** in Java is a good choice for `execTop`. We'll define a custom comparator to sort tasks first by priority (descending)
 * and then by taskId (descending).
 *
 * The `edit` and `rmv` operations pose a challenge. Removing an arbitrary element from a standard `PriorityQueue` is an `O(n)` operation,
 * which is inefficient. To make these operations fast, we can use a **HashMap** to store a mapping from `taskId` to its corresponding `Task` object.
 *
 * However, just using a map doesn't solve the `edit` problem. When a task's priority is edited, its position in the priority queue becomes invalid.
 * Instead of trying to update the task in the priority queue (which is complex and inefficient), a common and simpler strategy is **lazy deletion**.
 *
 * Here's the combined approach:
 * - Use a **`PriorityQueue`** (`pq`) to store tasks, ordered by priority and `taskId`.
 * - Use a **`HashMap`** (`map`) to store a mapping from `taskId` to a custom `Task` object.
 * The `Task` object will contain `userId`, `taskId`, and `priority`.
 * - `add(userId, taskId, priority)`: Create a new `Task` object, add it to both the `map` and the `pq`.
 * - `edit(taskId, newPriority)`:
 * 1.  Get the old `Task` object from the `map`.
 * 2.  Create a **new** `Task` object with the same `userId` and `taskId` but the `newPriority`.
 * 3.  Update the `map` to point the `taskId` to this new `Task` object.
 * 4.  Add the new `Task` object to the `pq`. We don't remove the old one from the `pq` yet; this is the lazy deletion part.
 * - `rmv(taskId)`: Simply remove the entry from the `map`. We again rely on lazy deletion for the `pq`.
 * - `execTop()`:
 * 1.  Loop while the `pq` is not empty.
 * 2.  Peek at the top `Task` from the `pq`.
 * 3.  Check if this task is still "valid" by looking it up in the `map`. A task is valid if the entry in the `map` for its `taskId`
 * is identical to the one at the top of the `pq`. If the `map` entry for this `taskId` is different, or non-existent, it means the
 * task was either edited or removed.
 * 4.  If the task is invalid, poll it from the `pq` and continue the loop.
 * 5.  If the task is valid, this is the task to execute. Poll it from the `pq`, remove it from the `map`, and return its `userId`.
 * 6.  If the `pq` becomes empty, no tasks are available, so return -1.
 *
 * This lazy deletion approach keeps `add`, `edit`, and `rmv` operations efficient (`O(log n)` or `O(1)` on average), and `execTop`
 * remains efficient by only processing invalid entries when necessary.
 *
 * Time Complexity:
 * - `TaskManager` constructor: O(N log N) where N is the number of initial tasks, due to adding to the PriorityQueue.
 * - `add()`: O(log N), for adding to the PriorityQueue.
 * - `edit()`: O(log N), for adding a new entry to the PriorityQueue.
 * - `rmv()`: O(1) on average for `HashMap` removal.
 * - `execTop()`: O(log N) on average. In the worst-case, if many tasks are invalid, it could be O(N log N), but this is amortized over
 * all operations. Each task is added to the PQ once and removed once (either validly or as a lazy deletion).
 *
 * Space Complexity: O(N) where N is the total number of tasks added over time. The `HashMap` and `PriorityQueue` will store a number of
 * tasks proportional to the number of tasks in the system. The PQ may temporarily hold more tasks than the map due to lazy deletion.
 */

// Optimal Solution in Java -
import java.util.*;
