// You are given an n x n integer matrix grid where each value grid[i][j] represents the elevation at that point (i, j).

// It starts raining, and water gradually rises over time. At time t, the water level is t, meaning any cell with elevation less than equal to t is submerged or reachable.

// You can swim from a square to another 4-directionally adjacent square if and only if the elevation of both squares individually are at most t. You can swim infinite distances in zero time. Of course, you must stay within the boundaries of the grid during your swim.

// Return the minimum time until you can reach the bottom right square (n - 1, n - 1) if you start at the top left square (0, 0).

 

// Example 1:


// Input: grid = [[0,2],[1,3]]
// Output: 3
// Explanation:
// At time 0, you are in grid location (0, 0).
// You cannot go anywhere else because 4-directionally adjacent neighbors have a higher elevation than t = 0.
// You cannot reach point (1, 1) until time 3.
// When the depth of water is 3, we can swim anywhere inside the grid.
// Example 2:


// Input: grid = [[0,1,2,3,4],[24,23,22,21,5],[12,13,14,15,16],[11,17,18,19,20],[10,9,8,7,6]]
// Output: 16
// Explanation: The final route is shown.
// We need to wait until time 16 so that (0, 0) and (4, 4) are connected.
 

// Constraints:

// n == grid.length
// n == grid[i].length
// 1 <= n <= 50
// 0 <= grid[i][j] < n2
// Each value grid[i][j] is unique.


// //Approach -1 (Binary Search + DFS) Time : O(n^2 * log(n))
// //NOTE - In the paths, we have to find the maximum from the minimum path (Minimize the Maximum hints towards Binary Search)
// //T.C : O(n^2 * log(n^2)) = O(n^2 log n)
// //S.C : O(n^2) for visited
// class Solution {
//     int n;
//     int[][] directions = {{1, 0}, {-1, 0}, {0, 1}, {0, -1}};
//     boolean[][] visited;

//     private boolean reachable(int[][] grid, int i, int j, int mid) {
//         if (i < 0 || i >= n || j < 0 || j >= n || visited[i][j] || grid[i][j] > mid)
//             return false;

//         visited[i][j] = true;

//         if (i == n - 1 && j == n - 1)
//             return true;

//         for (int[] dir : directions) {
//             int new_i = i + dir[0];
//             int new_j = j + dir[1];
//             if (reachable(grid, new_i, new_j, mid))
//                 return true;
//         }

//         return false;
//     }

//     public int swimInWater(int[][] grid) {
//         n = grid.length;

//         int l = grid[0][0], r = n * n - 1;
//         int result = 0;

//         while (l <= r) {
//             int mid = l + (r - l) / 2;
//             visited = new boolean[n][n];

//             if (reachable(grid, 0, 0, mid)) {
//                 result = mid;
//                 r = mid - 1;
//             } else {
//                 l = mid + 1;
//             }
//         }

//         return result;
//     }
// }




//Approach-2 Dijkstra's Algo (PriorityQueue) Time : O(n^2 * log(n))
//Single Source, Single Destination — find the shortest path to reach n-1, n-1
//T.C : O(n^2 * log(n^2)) = O(n^2 log n) because Dijkstra runs in O(E log V)
//S.C : O(n^2) for result + PQ

import java.util.*;

class Solution {
    int[][] directions = {{1, 0}, {-1, 0}, {0, 1}, {0, -1}};

    public int swimInWater(int[][] grid) {
        int n = grid.length;
        int[][] result = new int[n][n];
        for (int[] row : result)
            Arrays.fill(row, Integer.MAX_VALUE);

        // Min-heap based on current time
        PriorityQueue<int[]> pq = new PriorityQueue<>((a, b) -> a[0] - b[0]);
        result[0][0] = grid[0][0];
        pq.offer(new int[]{grid[0][0], 0, 0}); // {time, i, j}

        while (!pq.isEmpty()) {
            int[] curr = pq.poll();
            int currTime = curr[0];
            int i = curr[1];
            int j = curr[2];

            if (i == n - 1 && j == n - 1)
                return currTime;

            if (currTime > result[i][j])
                continue;

            for (int[] dir : directions) {
                int i_ = i + dir[0];
                int j_ = j + dir[1];

                if (i_ >= 0 && i_ < n && j_ >= 0 && j_ < n) {
                    int nextTime = Math.max(currTime, grid[i_][j_]);
                    if (nextTime < result[i_][j_]) {
                        result[i_][j_] = nextTime;
                        pq.offer(new int[]{nextTime, i_, j_});
                    }
                }
            }
        }

        return -1; // should never reach here
    }
}