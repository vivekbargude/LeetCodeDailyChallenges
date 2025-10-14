// Given an array nums of n integers and an integer k, determine whether there exist two adjacent subarrays of length k such that both subarrays are strictly increasing. Specifically, check if there are two subarrays starting at indices a and b (a < b), where:

// Both subarrays nums[a..a + k - 1] and nums[b..b + k - 1] are strictly increasing.
// The subarrays must be adjacent, meaning b = a + k.
// Return true if it is possible to find two such subarrays, and false otherwise.

 

// Example 1:

// Input: nums = [2,5,7,8,9,2,3,4,3,1], k = 3

// Output: true

// Explanation:

// The subarray starting at index 2 is [7, 8, 9], which is strictly increasing.
// The subarray starting at index 5 is [2, 3, 4], which is also strictly increasing.
// These two subarrays are adjacent, so the result is true.
// Example 2:

// Input: nums = [1,2,3,4,4,4,4,5,6,7], k = 5

// Output: false

 

// Constraints:

// 2 <= nums.length <= 100
// 1 < 2 * k <= nums.length
// -1000 <= nums[i] <= 1000


// //Approach-1 (Brute Force)
// //T.C : O(n^2)
// //S.C : O(1)
// class Solution {
//     public boolean hasIncreasingSubarrays(List<Integer> nums, int k) {
//         int n = nums.size();

//         for (int start = 0; start + 2 * k <= n; start++) {
//             boolean firstInc = isIncreasing(nums, start, start + k);
//             boolean secondInc = isIncreasing(nums, start + k, start + 2 * k);
//             if (firstInc && secondInc)
//                 return true;
//         }
//         return false;
//     }

//     private boolean isIncreasing(List<Integer> nums, int start, int end) {
//         for (int i = start + 1; i < end; i++) {
//             if (nums.get(i) <= nums.get(i - 1))
//                 return false;
//         }
//         return true;
//     }
// }


//Approach (Loop and keep checking)
//T.C : O(n) 
//S.C : O(1)

import java.util.List;

class Solution {
    public boolean hasIncreasingSubarrays(List<Integer> nums, int k) {
        int n = nums.size();

        int currRun = 1;
        int prevRun = 0;

        for (int i = 1; i < n; i++) {
            if (nums.get(i) > nums.get(i - 1)) {
                currRun++;
            } else { // increasing order breaks here
                prevRun = currRun;
                currRun = 1;
            }

            // if current run itself can fit two subarrays of length k
            if (currRun >= 2 * k) {
                return true;
            }

            // if current and previous increasing runs can fit one subarray each
            if (Math.min(currRun, prevRun) >= k) {
                return true;
            }
        }

        return false;
    }
}