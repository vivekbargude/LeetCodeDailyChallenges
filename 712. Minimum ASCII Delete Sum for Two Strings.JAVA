// Given two strings s1 and s2, return the lowest ASCII sum of deleted characters to make two strings equal.

 

// Example 1:

// Input: s1 = "sea", s2 = "eat"
// Output: 231
// Explanation: Deleting "s" from "sea" adds the ASCII value of "s" (115) to the sum.
// Deleting "t" from "eat" adds 116 to the sum.
// At the end, both strings are equal, and 115 + 116 = 231 is the minimum sum possible to achieve this.
// Example 2:

// Input: s1 = "delete", s2 = "leet"
// Output: 403
// Explanation: Deleting "dee" from "delete" to turn the string into "let",
// adds 100[d] + 101[e] + 101[e] to the sum.
// Deleting "e" from "leet" adds 101[e] to the sum.
// At the end, both strings are equal to "let", and the answer is 100+101+101+101 = 403.
// If instead we turned both strings into "lee" or "eet", we would get answers of 433 or 417, which are higher.
 

// Constraints:

// 1 <= s1.length, s2.length <= 1000
// s1 and s2 consist of lowercase English letters.

//Approach-1 (Using Recursion + Memo (Top Down)) - O(m*n)
//T.C : O(m*n)
//S.C : O(m*n)
class Solution {

    int m, n;
    int[][] t;

    private int solve(String s1, String s2, int i, int j) {
        if (i >= m && j >= n)
            return 0;

        if (t[i][j] != -1)
            return t[i][j];

        if (i >= m) {
            return t[i][j] = s2.charAt(j) + solve(s1, s2, i, j + 1);
        } 
        if (j >= n) {
            return t[i][j] = s1.charAt(i) + solve(s1, s2, i + 1, j);
        }

        if (s1.charAt(i) == s2.charAt(j)) {
            return t[i][j] = solve(s1, s2, i + 1, j + 1);
        }

        int takeS1 = s1.charAt(i) + solve(s1, s2, i + 1, j);
        int takeS2 = s2.charAt(j) + solve(s1, s2, i, j + 1);

        return t[i][j] = Math.min(takeS1, takeS2);
    }

    public int minimumDeleteSum(String s1, String s2) {
        m = s1.length();
        n = s2.length();

        t = new int[m + 1][n + 1];
        for (int i = 0; i <= m; i++) {
            for (int j = 0; j <= n; j++) {
                t[i][j] = -1;
            }
        }

        return solve(s1, s2, 0, 0);
    }
}