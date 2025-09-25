// Given a triangle array, return the minimum path sum from top to bottom.

// For each step, you may move to an adjacent number of the row below. More formally, if you are on index i on the current row, you may move to either index i or index i + 1 on the next row.

 

// Example 1:

// Input: triangle = [[2],[3,4],[6,5,7],[4,1,8,3]]
// Output: 11
// Explanation: The triangle looks like:
//    2
//   3 4
//  6 5 7
// 4 1 8 3
// The minimum path sum from top to bottom is 2 + 3 + 5 + 1 = 11 (underlined above).
// Example 2:

// Input: triangle = [[-10]]
// Output: -10
 

// Constraints:

// 1 <= triangle.length <= 200
// triangle[0].length == 1
// triangle[i].length == triangle[i - 1].length + 1
// -104 <= triangle[i][j] <= 104
 

// Follow up: Could you do this using only O(n) extra space, where n is the total number of rows in the triangle?

// //Approach-1 Recursion with Memoization - Accepted (In the video I initialized the t[][] by -1 by mistake. That's why you will see TLE in the video)
// //T.C : O(n^2) states in t[][]
// //S.C : O(n^2)
// class Solution {
//     int[][] t;
//     int n;

//     private int solve(List<List<Integer>> triangle, int row, int col) {
//         if (row == n - 1) {
//             return triangle.get(row).get(col);
//         }

//         if (t[row][col] != Integer.MAX_VALUE) {
//             return t[row][col];
//         }

//         int minPath = triangle.get(row).get(col) +
//                       Math.min(solve(triangle, row + 1, col),
//                                solve(triangle, row + 1, col + 1));

//         return t[row][col] = minPath;
//     }

//     public int minimumTotal(List<List<Integer>> triangle) {
//         n = triangle.size();
//         t = new int[n][n];

//         for (int i = 0; i < n; i++) {
//             Arrays.fill(t[i], Integer.MAX_VALUE);
//         }

//         return solve(triangle, 0, 0);
//     }
// }



// //Approach-2 : Bottom Up - Just like "Minimum Falling Path Sum"
// //T.C : O(n^2)
// //S.C : O(n^2)
// class Solution {
//     public int minimumTotal(List<List<Integer>> triangle) {
//         int n = triangle.size();
//         int[][] t = new int[n][n];

//         // Copy triangle into t
//         for (int i = 0; i < n; i++) {
//             for (int j = 0; j <= i; j++) {
//                 t[i][j] = triangle.get(i).get(j);
//             }
//         }

//         // Bottom-up calculation
//         for (int row = n - 2; row >= 0; row--) {
//             for (int col = 0; col <= row; col++) {
//                 t[row][col] += Math.min(t[row + 1][col], t[row + 1][col + 1]);
//             }
//         }

//         return t[0][0];
//     }
// }


//Approach-3 : Bottom Up optimized - O(n) space
//T.C : O(n^2)
//S.C : O(n)

import java.util.List;

class Solution {
    public int minimumTotal(List<List<Integer>> triangle) {
        int n = triangle.size();
        int[] t = new int[n];

        // Initialize with last row
        for (int i = 0; i < n; i++) {
            t[i] = triangle.get(n - 1).get(i);
        }

        for (int row = n - 2; row >= 0; row--) {
            for (int col = 0; col <= row; col++) {
                t[col] = triangle.get(row).get(col) + Math.min(t[col], t[col + 1]);
            }
        }

        return t[0];
    }
}