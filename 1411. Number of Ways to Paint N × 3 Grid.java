// You have a grid of size n x 3 and you want to paint each cell of the grid with exactly one of the three colors: Red, Yellow, or Green while making sure that no two adjacent cells have the same color (i.e., no two cells that share vertical or horizontal sides have the same color).

// Given n the number of rows of the grid, return the number of ways you can paint this grid. As the answer may grow large, the answer must be computed modulo 109 + 7.

 

// Example 1:


// Input: n = 1
// Output: 12
// Explanation: There are 12 possible way to paint the grid as shown.
// Example 2:

// Input: n = 5000
// Output: 30228214
 

// Constraints:

// n == grid.length
// 1 <= n <= 5000



//Approach (Recursion + Memoization : DP On Grids) - If you want, I can make a separate video on Bottom Up. Let me know in the comments
//T.C : O(n)
//S.C : O(n)
class Solution {
    int M = 1_000_000_007;
    int[][] t;

    // 12 possible row patterns
    String[] states = {
        "RYG", "RGY", "RYR", "RGR",
        "YRG", "YGR", "YGY", "YRY",
        "GRY", "GYR", "GRG", "GYG"
    };

    int solve(int n, int prev) {
        if (n == 0)
            return 1;

        if (t[n][prev] != -1)
            return t[n][prev];

        int result = 0;
        String last = states[prev];

        for (int curr = 0; curr < 12; curr++) {
            if (curr == prev)
                continue;

            String currPat = states[curr];
            boolean conflict = false;

            for (int col = 0; col < 3; col++) {
                if (currPat.charAt(col) == last.charAt(col)) {
                    conflict = true;
                    break;
                }
            }

            if (!conflict) {
                result = (result + solve(n - 1, curr)) % M;
            }
        }

        return t[n][prev] = result;
    }

    public int numOfWays(int n) {
        t = new int[n][12];

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < 12; j++) {
                t[i][j] = -1;
            }
        }

        int result = 0;

        // choose first row
        for (int i = 0; i < 12; i++) {
            result = (result + solve(n - 1, i)) % M;
        }

        return result;
    }
}