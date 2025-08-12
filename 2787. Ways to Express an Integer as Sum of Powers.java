// Given two positive integers n and x.

// Return the number of ways n can be expressed as the sum of the xth power of unique positive integers, in other words, the number of sets of unique integers [n1, n2, ..., nk] where n = n1x + n2x + ... + nkx.

// Since the result can be very large, return it modulo 109 + 7.

// For example, if n = 160 and x = 3, one way to express n is n = 23 + 33 + 53.

// Example 1:

// Input: n = 10, x = 2
// Output: 1
// Explanation: We can express n as the following: n = 32 + 12 = 10.
// It can be shown that it is the only way to express 10 as the sum of the 2nd power of unique integers.
// Example 2:

// Input: n = 4, x = 1
// Output: 2
// Explanation: We can express n in the following ways:
// - n = 41 = 4.
// - n = 31 + 11 = 4.
 

// Constraints:

// 1 <= n <= 300
// 1 <= x <= 5

import java.util.*;

class Solution {

    int[][] t = new int[301][301];

    int mod = 1000000007;

    public int numberOfWays(int n, int x) {
        for (int i = 0; i < 301; i++) {
            Arrays.fill(t[i], -1);
        }

        return solve(n, 1, x);
    }

    public int solve(int n, int num, int x) {
        //if got one path 
        if( n == 0 ) {
            return 1;
        }

        //if by doing  n-num^x we got the negative 
        if( n < 0 ) {
            return 0;
        }

        int currPower = (int)Math.pow(num,x);

        //if by skipping some elements we reached to an element that that num^x > n
        if( currPower > n ) {
            return 0;
        }

        if(t[n][num] != -1 ) {// already solve the current problem
            return t[n][num]; //return the stored ans
        }

        //if not previously solved solve it my recursion
        int take = solve(n-currPower, num+1, x);
        int skip = solve(n, num+1, x);

        return t[n][num] = (take + skip)%mod;
    }

}