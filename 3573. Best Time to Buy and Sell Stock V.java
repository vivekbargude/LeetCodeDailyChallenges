// You are given an integer array prices where prices[i] is the price of a stock in dollars on the ith day, and an integer k.

// You are allowed to make at most k transactions, where each transaction can be either of the following:

// Normal transaction: Buy on day i, then sell on a later day j where i < j. You profit prices[j] - prices[i].

// Short selling transaction: Sell on day i, then buy back on a later day j where i < j. You profit prices[i] - prices[j].

// Note that you must complete each transaction before starting another. Additionally, you can't buy or sell on the same day you are selling or buying back as part of a previous transaction.

// Return the maximum total profit you can earn by making at most k transactions.

 

// Example 1:

// Input: prices = [1,7,9,8,2], k = 2

// Output: 14

// Explanation:

// We can make $14 of profit through 2 transactions:
// A normal transaction: buy the stock on day 0 for $1 then sell it on day 2 for $9.
// A short selling transaction: sell the stock on day 3 for $8 then buy back on day 4 for $2.
// Example 2:

// Input: prices = [12,16,19,19,8,1,19,13,9], k = 3

// Output: 36

// Explanation:

// We can make $36 of profit through 3 transactions:
// A normal transaction: buy the stock on day 0 for $12 then sell it on day 2 for $19.
// A short selling transaction: sell the stock on day 3 for $19 then buy back on day 4 for $8.
// A normal transaction: buy the stock on day 5 for $1 then sell it on day 6 for $19.
 

// Constraints:

// 2 <= prices.length <= 103
// 1 <= prices[i] <= 109
// 1 <= k <= prices.length / 2


//Approach-1 (Recursion + Memoization)
//T.C : O(n * k)
//S.C : O(n * k)
class Solution {

    // t[i][k][CASE]
    // CASE:
    // 0 -> No open transaction
    // 1 -> Holding long (bought, waiting to sell)
    // 2 -> Holding short (sold, waiting to buy back)
    long[][][] t;

    long solve(int i, int k, int CASE, int[] prices) {

        if (i == prices.length) {
            if (CASE == 0)
                return 0;
            return Long.MIN_VALUE / 2; // unfinished transaction is invalid
        }

        if (t[i][k][CASE] != Long.MIN_VALUE)
            return t[i][k][CASE];

        long take = Long.MIN_VALUE;
        long dontTake;

        // Do nothing today
        dontTake = solve(i + 1, k, CASE, prices);

        // Take action
        if (k > 0) {
            if (CASE == 1) {
                // Sell today (complete normal transaction)
                take = prices[i] + solve(i + 1, k - 1, 0, prices);
            }
            else if (CASE == 2) {
                // Buy back today (complete short transaction)
                take = -prices[i] + solve(i + 1, k - 1, 0, prices);
            }
            else {
                // CASE == 0: start a transaction
                take = Math.max(
                        -prices[i] + solve(i + 1, k, 1, prices), // buy
                         prices[i] + solve(i + 1, k, 2, prices)  // short sell
                );
            }
        }

        return t[i][k][CASE] = Math.max(take, dontTake);
    }

    public long maximumProfit(int[] prices, int k) {
        int n = prices.length;
        t = new long[n][k + 1][3];

        // Initialize memo table
        for (int i = 0; i < n; i++)
            for (int j = 0; j <= k; j++)
                for (int l = 0; l < 3; l++)
                    t[i][j][l] = Long.MIN_VALUE;

        return solve(0, k, 0, prices);
    }
}



// //Approach-2 (Bottom Up)
// //T.C : O(n * k)
// //S.C : O(n * k)
// class Solution {
//     public long maximumProfit(int[] prices, int K) {
//         int n = prices.length;

//         long[][][] t = new long[n + 1][K + 1][3];

//         // Base case: i == n
//         for (int k = 0; k <= K; k++) {
//             t[n][k][0] = 0;
//             t[n][k][1] = Long.MIN_VALUE / 2;
//             t[n][k][2] = Long.MIN_VALUE / 2;
//         }

//         // Fill table bottom-up
//         for (int i = n - 1; i >= 0; i--) {
//             for (int k = 0; k <= K; k++) {

//                 // CASE 0: no open transaction
//                 t[i][k][0] = t[i + 1][k][0]; // do nothing
//                 if (k > 0) {
//                     t[i][k][0] = Math.max(
//                             t[i][k][0],
//                             Math.max(
//                                     -prices[i] + t[i + 1][k][1], // buy
//                                      prices[i] + t[i + 1][k][2]  // short sell
//                             )
//                     );
//                 }

//                 // CASE 1: holding long
//                 t[i][k][1] = t[i + 1][k][1]; // hold
//                 if (k > 0) {
//                     t[i][k][1] = Math.max(
//                             t[i][k][1],
//                             prices[i] + t[i + 1][k - 1][0] // sell
//                     );
//                 }

//                 // CASE 2: holding short
//                 t[i][k][2] = t[i + 1][k][2]; // hold
//                 if (k > 0) {
//                     t[i][k][2] = Math.max(
//                             t[i][k][2],
//                             -prices[i] + t[i + 1][k - 1][0] // buy back
//                     );
//                 }
//             }
//         }

//         // Start from day 0, K transactions, no open position
//         return t[0][K][0];
//     }
// }