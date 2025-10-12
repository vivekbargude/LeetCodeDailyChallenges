// You are given two integers, m and k, and an integer array nums.

// A sequence of integers seq is called magical if:
// seq has a size of m.
// 0 <= seq[i] < nums.length
// The binary representation of 2seq[0] + 2seq[1] + ... + 2seq[m - 1] has k set bits.
// The array product of this sequence is defined as prod(seq) = (nums[seq[0]] * nums[seq[1]] * ... * nums[seq[m - 1]]).

// Return the sum of the array products for all valid magical sequences.

// Since the answer may be large, return it modulo 109 + 7.

// A set bit refers to a bit in the binary representation of a number that has a value of 1.

 

// Example 1:

// Input: m = 5, k = 5, nums = [1,10,100,10000,1000000]

// Output: 991600007

// Explanation:

// All permutations of [0, 1, 2, 3, 4] are magical sequences, each with an array product of 1013.

// Example 2:

// Input: m = 2, k = 2, nums = [5,4,3,2,1]

// Output: 170

// Explanation:

// The magical sequences are [0, 1], [0, 2], [0, 3], [0, 4], [1, 0], [1, 2], [1, 3], [1, 4], [2, 0], [2, 1], [2, 3], [2, 4], [3, 0], [3, 1], [3, 2], [3, 4], [4, 0], [4, 1], [4, 2], and [4, 3].

// Example 3:

// Input: m = 1, k = 1, nums = [28]

// Output: 28

// Explanation:

// The only magical sequence is [0].

 

// Constraints:

// 1 <= k <= m <= 30
// 1 <= nums.length <= 50
// 1 <= nums[i] <= 108


class Solution {
    public int magicalSum(int m, int k, int[] nums) {
        int MOD = (int) (1e9 + 7);
        int n = nums.length;

        int[][] C = new int[m + 1][m + 1];
        for (int i = 0; i <= m; i++) {
            C[i][0] = C[i][i] = 1;
            for (int j = 1; j < i; j++) {
                C[i][j] = (C[i-1][j-1] + C[i-1][j]) % MOD;
            }
        }

        int[][] pow = new int[n][m + 1];
        for (int i = 0; i < n; i++) {
            pow[i][0] = 1;
            for (int cnt = 1; cnt <= m; cnt++) {
                pow[i][cnt] = (int)((long)pow[i][cnt-1] * nums[i] % MOD);
            }
        }

        int[][][][] dp = new int[n + 1][k + 1][m + 1][m + 1];
        dp[0][0][0][0] = 1;

        for (int pos = 0; pos < n; pos++) {
            for (int bits = 0; bits <= k; bits++) {
                for (int carry = 0; carry <= m; carry++) {
                    for (int chosen = 0; chosen <= m; chosen++) {
                        if (dp[pos][bits][carry][chosen] == 0) continue;
                        
                        int remaining = m - chosen;
                        for (int cnt = 0; cnt <= remaining; cnt++) {
                            int total = carry + cnt;
                            int new_bits = bits + (total & 1);
                            int new_carry = total >> 1;
                            
                            if (new_bits <= k && new_carry <= m) {
                                long add = (long)dp[pos][bits][carry][chosen] 
                                    * C[remaining][cnt] % MOD 
                                    * pow[pos][cnt] % MOD;
                                
                                dp[pos+1][new_bits][new_carry][chosen+cnt] = 
                                    (dp[pos+1][new_bits][new_carry][chosen+cnt] + (int)add) % MOD;
                            }
                        }
                    }
                }
            }
        }


        int res = 0;
        for (int carry = 0; carry <= m; carry++) {
            int final_bits = k;

            int carry_bits = Integer.bitCount(carry);
            if (carry_bits <= k) {
                res = (res + dp[n][k - carry_bits][carry][m]) % MOD;
            }
        }

        return res;
    }
}