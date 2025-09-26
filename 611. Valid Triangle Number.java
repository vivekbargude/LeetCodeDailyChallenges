// Given an integer array nums, return the number of triplets chosen from the array that can make triangles if we take them as side lengths of a triangle.

 

// Example 1:

// Input: nums = [2,2,3,4]
// Output: 3
// Explanation: Valid combinations are: 
// 2,3,4 (using the first 2)
// 2,3,4 (using the second 2)
// 2,2,3
// Example 2:

// Input: nums = [4,2,3,4]
// Output: 4
 

// Constraints:

// 1 <= nums.length <= 1000
// 0 <= nums[i] <= 1000

// //Approach-1 (Brute Force)
// //T.C : O(n^3)
// //S.C : O(1)
// class Solution {
//     public int triangleNumber(int[] nums) {
//         int n = nums.length;
//         int count = 0;

//         // check all triplets
//         for (int i = 0; i < n; i++) {
//             for (int j = i + 1; j < n; j++) {
//                 for (int k = j + 1; k < n; k++) {
//                     if (nums[i] + nums[j] > nums[k] &&
//                         nums[i] + nums[k] > nums[j] &&
//                         nums[j] + nums[k] > nums[i]) {
//                         count++;
//                     }
//                 }
//             }
//         }
//         return count;
//     }
// }


// //Approach-2 (Binary Search)
// //T.C : O(n^2 * logn)
// //S.C : O(1)
// class Solution {
//     private int binarySearch(int[] nums, int l, int r, int target) {
//         int k = -1;
//         while (l <= r) {
//             int mid = l + (r - l) / 2;
//             if (nums[mid] < target) {
//                 k = mid;          // mid is valid
//                 l = mid + 1;      // try to go right
//             } else {
//                 r = mid - 1;      // go left
//             }
//         }
//         return k;
//     }

//     public int triangleNumber(int[] nums) {
//         int n = nums.length;
//         if (n < 3) return 0;

//         Arrays.sort(nums);
//         int count = 0;

//         for (int i = 0; i < n; i++) {
//             if (nums[i] == 0) continue;

//             for (int j = i + 1; j < n; j++) {
//                 int sum = nums[i] + nums[j];
//                 int k = binarySearch(nums, j + 1, n - 1, sum);
//                 if (k != -1) {
//                     count += (k - j);
//                 }
//             }
//         }
//         return count;
//     }
// }



//Approach-3 (Two Pointer)
//T.C : O(n^2)
//S.C : O(1)

import java.util.*;

class Solution {
    public int triangleNumber(int[] nums) {
        int n = nums.length;
        if (n < 3) return 0;

        Arrays.sort(nums);
        int count = 0;

        for (int k = n - 1; k > 1; k--) {  // k is the largest side
            int i = 0, j = k - 1;          // two pointers
            while (i < j) {
                if (nums[i] + nums[j] > nums[k]) {
                    count += (j - i);      // all pairs (i..j-1, j) will satisfy inequality
                    j--;
                } else {
                    i++;
                }
            }
        }
        return count;
    }
}