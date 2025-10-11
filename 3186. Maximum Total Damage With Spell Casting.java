// A magician has various spells.

// You are given an array power, where each element represents the damage of a spell. Multiple spells can have the same damage value.

// It is a known fact that if a magician decides to cast a spell with a damage of power[i], they cannot cast any spell with a damage of power[i] - 2, power[i] - 1, power[i] + 1, or power[i] + 2.

// Each spell can be cast only once.

// Return the maximum possible total damage that a magician can cast.

 

// Example 1:

// Input: power = [1,1,3,4]

// Output: 6

// Explanation:

// The maximum possible damage of 6 is produced by casting spells 0, 1, 3 with damage 1, 1, 4.

// Example 2:

// Input: power = [7,1,6,6]

// Output: 13

// Explanation:

// The maximum possible damage of 13 is produced by casting spells 1, 2, 3 with damage 1, 6, 6.

 

// Constraints:

// 1 <= power.length <= 105
// 1 <= power[i] <= 109


// //Approach-1 - Recursion + Memoization
// //T.C : O(nlogn)
// //S.C : O(n)
// class Solution {
//     private int n;
//     private Map<Long, Long> freq;
//     private long[] dp;

//     private long solve(int i, List<Long> nums) {
//         if (i >= n)
//             return 0;

//         if (dp[i] != -1)
//             return dp[i];

//         // skip current damage
//         long skip = solve(i + 1, nums);

//         // take current damage
//         int j = lowerBound(nums, i + 1, nums.get(i) + 3);
//         long take = nums.get(i) * freq.get(nums.get(i)) + solve(j, nums);

//         return dp[i] = Math.max(skip, take);
//     }

//     // Helper function to replicate C++ lower_bound
//     private int lowerBound(List<Long> nums, int start, long target) {
//         int low = start, high = nums.size();
//         while (low < high) {
//             int mid = (low + high) / 2;
//             if (nums.get(mid) < target)
//                 low = mid + 1;
//             else
//                 high = mid;
//         }
//         return low;
//     }

//     public long maximumTotalDamage(int[] power) {
//         freq = new HashMap<>();
//         for (int x : power)
//             freq.put((long)x, freq.getOrDefault((long)x, 0L) + 1);

//         List<Long> nums = new ArrayList<>(freq.keySet());
//         Collections.sort(nums);
//         n = nums.size();
//         dp = new long[n];
//         Arrays.fill(dp, -1);

//         return solve(0, nums);
//     }
// }




//Approach-2 - Bottom Up
//T.C : O(nlogn)
//S.C : O(n)

import java.util.*;

class Solution {
    public long maximumTotalDamage(int[] power) {
        // Step 1: Frequency map
        Map<Long, Long> freq = new HashMap<>();
        for (int x : power)
            freq.put((long)x, freq.getOrDefault((long)x, 0L) + 1);

        // Step 2: Sort unique values
        List<Long> nums = new ArrayList<>(freq.keySet());
        Collections.sort(nums);
        int n = nums.size();
        long[] dp = new long[n];

        long result = Long.MIN_VALUE;

        for (int i = n - 1; i >= 0; i--) {
            // Skip current value
            long skip = (i + 1 < n) ? dp[i + 1] : 0;

            // Take current value
            int j = lowerBound(nums, i + 1, nums.get(i) + 3);
            long take = nums.get(i) * freq.get(nums.get(i)) + ((j < n) ? dp[j] : 0);

            dp[i] = Math.max(skip, take);
            result = Math.max(result, dp[i]);
        }

        return result;
    }

    // Helper for binary search (lower_bound equivalent)
    private int lowerBound(List<Long> nums, int start, long target) {
        int low = start, high = nums.size();
        while (low < high) {
            int mid = (low + high) / 2;
            if (nums.get(mid) < target)
                low = mid + 1;
            else
                high = mid;
        }
        return low;
    }
}