// You are given an integer array nums.

// A special triplet is defined as a triplet of indices (i, j, k) such that:

// 0 <= i < j < k < n, where n = nums.length
// nums[i] == nums[j] * 2
// nums[k] == nums[j] * 2
// Return the total number of special triplets in the array.

// Since the answer may be large, return it modulo 109 + 7.

 

// Example 1:

// Input: nums = [6,3,6]

// Output: 1

// Explanation:

// The only special triplet is (i, j, k) = (0, 1, 2), where:

// nums[0] = 6, nums[1] = 3, nums[2] = 6
// nums[0] = nums[1] * 2 = 3 * 2 = 6
// nums[2] = nums[1] * 2 = 3 * 2 = 6
// Example 2:

// Input: nums = [0,1,0,0]

// Output: 1

// Explanation:

// The only special triplet is (i, j, k) = (0, 2, 3), where:

// nums[0] = 0, nums[2] = 0, nums[3] = 0
// nums[0] = nums[2] * 2 = 0 * 2 = 0
// nums[3] = nums[2] * 2 = 0 * 2 = 0
// Example 3:

// Input: nums = [8,4,2,8,4]

// Output: 2

// Explanation:

// There are exactly two special triplets:

// (i, j, k) = (0, 1, 3)
// nums[0] = 8, nums[1] = 4, nums[3] = 8
// nums[0] = nums[1] * 2 = 4 * 2 = 8
// nums[3] = nums[1] * 2 = 4 * 2 = 8
// (i, j, k) = (1, 2, 4)
// nums[1] = 4, nums[2] = 2, nums[4] = 4
// nums[1] = nums[2] * 2 = 2 * 2 = 4
// nums[4] = nums[2] * 2 = 2 * 2 = 4
 

// Constraints:

// 3 <= n == nums.length <= 105
// 0 <= nums[i] <= 105

import java.util.*;

// //Approach-1 (Using map : 2 Pass Solution)
// //T.C : O(2*n)
// //S.C : O(n)
// class Solution {
//     static final int M = (int)1e9 + 7;

//     public int specialTriplets(int[] nums) {
//         Map<Integer, Integer> mpLeft = new HashMap<>();
//         Map<Integer, Integer> mpRight = new HashMap<>();

//         int result = 0;

//         // Fill mpRight with frequencies
//         for (int num : nums) {
//             mpRight.put(num, mpRight.getOrDefault(num, 0) + 1);
//         }

//         // Traverse again
//         for (int num : nums) {
//             // Reduce count from right map
//             mpRight.put(num, mpRight.get(num) - 1);

//             int left  = mpLeft.getOrDefault(num * 2, 0);
//             int right = mpRight.getOrDefault(num * 2, 0);

//             long add = (1L * left * right) % M;
//             result = (int)((result + add) % M);

//             mpLeft.put(num, mpLeft.getOrDefault(num, 0) + 1);
//         }

//         return result;
//     }

// }

//Approach-2 (Using map :  Pass Solution)
//T.C : O(n)
//S.C : O(n)



class Solution {
    static final int M = (int)1e9 + 7;

    public int specialTriplets(int[] nums) {
        Map<Integer, Integer> validI = new HashMap<>();
        Map<Integer, Integer> validJ = new HashMap<>();

        int result = 0;

        for (int num : nums) {

            // If num is valid k (k must be even)
            if (num % 2 == 0) {
                result = (result + validJ.getOrDefault(num / 2, 0)) % M;
            }

            // Is current num a valid j?
            int updatedJ = (validJ.getOrDefault(num, 0) +
                            validI.getOrDefault(num * 2, 0)) % M;
            validJ.put(num, updatedJ);

            // Count num as a valid i
            validI.put(num, validI.getOrDefault(num, 0) + 1);
        }

        return result;
    }
}

