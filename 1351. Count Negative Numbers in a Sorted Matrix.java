// Given a m x n matrix grid which is sorted in non-increasing order both row-wise and column-wise, return the number of negative numbers in grid.

 

// Example 1:

// Input: grid = [[4,3,2,-1],[3,2,1,-1],[1,1,-1,-2],[-1,-1,-2,-3]]
// Output: 8
// Explanation: There are 8 negatives number in the matrix.
// Example 2:

// Input: grid = [[3,2],[1,0]]
// Output: 0
 

// Constraints:

// m == grid.length
// n == grid[i].length
// 1 <= m, n <= 100
// -100 <= grid[i][j] <= 100
 

// Follow up: Could you find an O(n + m) solution?


class Solution {

    /**
     * ================================
     * APPROACH 1 : Optimal Traversal
     * ================================
     *
     * We start from bottom-left corner of the matrix.
     *
     * Why bottom-left?
     * - From this position:
     *      If current value is negative → all elements to the right are negative.
     *      If current value is non-negative → move right.
     *
     * Example:
     * [ 4   3   1  -1 ]
     * [ 3   2   0  -2 ]
     * [ 1   1  -1  -3 ]
     * [ -1 -2 -3  -4 ]  <-- start here
     *
     * Time Complexity:  O(m + n)
     * Space Complexity: O(1)
     */
    public int countNegativesOptimal(int[][] grid) {

        int m = grid.length;
        int n = grid[0].length;

        int i = m - 1;   // start from last row
        int j = 0;       // start from first column
        int count = 0;

        while (i >= 0 && j < n) {

            if (grid[i][j] < 0) {
                // all elements to the right are negative
                count += n - j;
                i--;   // move up
            } else {
                j++;   // move right
            }
        }
        return count;
    }


    /**
     * =====================================
     * APPROACH 2 : Binary Search Per Row
     * =====================================
     *
     * Each row is sorted in non-increasing order.
     * Find the first negative number using Binary Search.
     *
     * Example row:
     * [5, 3, 1, 0, -2, -5]
     *                ↑
     *          first negative index = 4
     * negatives = cols - 4
     *
     * Time Complexity:  O(m log n)
     * Space Complexity: O(1)
     */
    public int countNegativesBinarySearch(int[][] grid) {

        int rows = grid.length;
        int cols = grid[0].length;
        int count = 0;

        for (int i = 0; i < rows; i++) {

            int left = 0;
            int right = cols - 1;
            int firstNeg = cols;   // assume no negatives

            while (left <= right) {

                int mid = left + (right - left) / 2;

                if (grid[i][mid] < 0) {
                    firstNeg = mid;
                    right = mid - 1;
                } else {
                    left = mid + 1;
                }
            }

            count += cols - firstNeg;
        }

        return count;
    }


    // LeetCode will call this method
    public int countNegatives(int[][] grid) {
        // Use optimal approach
        return countNegativesOptimal(grid);
        // Or use binary search approach
        // return countNegativesBinarySearch(grid);
    }
}