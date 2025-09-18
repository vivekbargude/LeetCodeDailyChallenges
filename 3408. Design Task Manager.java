// There is a task management system that allows users to manage their tasks, each associated with a priority. The system should efficiently handle adding, modifying, executing, and removing tasks.

// Implement the TaskManager class:

// TaskManager(vector<vector<int>>& tasks) initializes the task manager with a list of user-task-priority triples. Each element in the input list is of the form [userId, taskId, priority], which adds a task to the specified user with the given priority.

// void add(int userId, int taskId, int priority) adds a task with the specified taskId and priority to the user with userId. It is guaranteed that taskId does not exist in the system.

// void edit(int taskId, int newPriority) updates the priority of the existing taskId to newPriority. It is guaranteed that taskId exists in the system.

// void rmv(int taskId) removes the task identified by taskId from the system. It is guaranteed that taskId exists in the system.

// int execTop() executes the task with the highest priority across all users. If there are multiple tasks with the same highest priority, execute the one with the highest taskId. After executing, the taskId is removed from the system. Return the userId associated with the executed task. If no tasks are available, return -1.

// Note that a user may be assigned multiple tasks.

 

// Example 1:

// Input:
// ["TaskManager", "add", "edit", "execTop", "rmv", "add", "execTop"]
// [[[[1, 101, 10], [2, 102, 20], [3, 103, 15]]], [4, 104, 5], [102, 8], [], [101], [5, 105, 15], []]

// Output:
// [null, null, null, 3, null, null, 5]

// Explanation

// TaskManager taskManager = new TaskManager([[1, 101, 10], [2, 102, 20], [3, 103, 15]]); // Initializes with three tasks for Users 1, 2, and 3.
// taskManager.add(4, 104, 5); // Adds task 104 with priority 5 for User 4.
// taskManager.edit(102, 8); // Updates priority of task 102 to 8.
// taskManager.execTop(); // return 3. Executes task 103 for User 3.
// taskManager.rmv(101); // Removes task 101 from the system.
// taskManager.add(5, 105, 15); // Adds task 105 with priority 15 for User 5.
// taskManager.execTop(); // return 5. Executes task 105 for User 5.
 

// Constraints:

// 1 <= tasks.length <= 105
// 0 <= userId <= 105
// 0 <= taskId <= 105
// 0 <= priority <= 109
// 0 <= newPriority <= 109
// At most 2 * 105 calls will be made in total to add, edit, rmv, and execTop methods.
// The input is generated such that taskId will be valid.




//Approach (Using proper Data Structures)
// T.C. : add -> O(log n), edit -> O(log n), rmv -> O(1), execTop -> O(n log n) worst case
// S.C. : O(n)
// class TaskManager {
//     private static class Task {
//         int priority;
//         int taskId;

//         Task(int priority, int taskId) {
//             this.priority = priority;
//             this.taskId = taskId;
//         }
//     }

//     // Max-heap: higher priority first, break ties by higher taskId
//     private PriorityQueue<Task> maxHeap;
//     private Map<Integer, Integer> taskPriorityMap; // taskId -> current priority
//     private Map<Integer, Integer> taskOwnerMap;    // taskId -> userId

//     public TaskManager(List<List<Integer>> tasks) {
//         maxHeap = new PriorityQueue<>(
//             (a, b) -> {
//                 if (a.priority != b.priority) {
//                     return b.priority - a.priority; // higher priority first
//                 }
//                 return b.taskId - a.taskId;         // break tie by higher taskId
//             }
//         );
//         taskPriorityMap = new HashMap<>();
//         taskOwnerMap = new HashMap<>();

//         for (List<Integer> t : tasks) {
//             add(t.get(0), t.get(1), t.get(2));
//         }
//     }

//     // Add a new task
//     public void add(int userId, int taskId, int priority) {
//         maxHeap.offer(new Task(priority, taskId));
//         taskPriorityMap.put(taskId, priority);
//         taskOwnerMap.put(taskId, userId);
//     }

//     // Edit an existing task's priority
//     public void edit(int taskId, int newPriority) {
//         maxHeap.offer(new Task(newPriority, taskId));
//         taskPriorityMap.put(taskId, newPriority);
//     }

//     // Remove a task
//     public void rmv(int taskId) {
//         taskPriorityMap.put(taskId, -1); // mark as invalid
//     }

//     // Execute the top task and return its userId
//     public int execTop() {
//         while (!maxHeap.isEmpty()) {
//             Task top = maxHeap.poll();
//             int currPriority = taskPriorityMap.getOrDefault(top.taskId, -1);

//             if (top.priority == currPriority) {
//                 taskPriorityMap.put(top.taskId, -1); // mark executed
//                 return taskOwnerMap.get(top.taskId);
//             }
//         }
//         return -1; // no valid tasks
//     }
// }