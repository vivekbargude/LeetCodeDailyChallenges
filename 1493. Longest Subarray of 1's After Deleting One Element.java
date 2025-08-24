// Given a binary array nums, you should delete one element from it.

// Return the size of the longest non-empty subarray containing only 1's in the resulting array. Return 0 if there is no such subarray.

 

// Example 1:

// Input: nums = [1,1,0,1]
// Output: 3
// Explanation: After deleting the number in position 2, [1,1,1] contains 3 numbers with value of 1's.
// Example 2:

// Input: nums = [0,1,1,1,0,1,1,0,1]
// Output: 5
// Explanation: After deleting the number in position 4, [0,1,1,1,1,1,0,1] longest subarray with value of 1's is [1,1,1,1,1].
// Example 3:

// Input: nums = [1,1,1]
// Output: 2
// Explanation: You must delete one element.
 

// Constraints:

// 1 <= nums.length <= 105
// nums[i] is either 0 or 1.

class Solution {

    // int n;

    // int finMaxSubArr(int[] nums, int skip_idx) {
    //     int currLen = 0;
    //     int maxLen = 0;

    //     for(int i=0; i<n; i++) {

    //         if(i==skip_idx)
    //         continue;

    //         if(nums[i]==1){
    //             currLen++;
    //             maxLen = Math.max(currLen,maxLen);
    //         }else {
    //             currLen = 0;
    //         }
    //     }

    //     return maxLen;
    // }

    
    // public int longestSubarray(int[] nums) {
    //     n = nums.length;
    //     int result = 0;
    //     int cntZero = 0;

    //     for(int i=0; i<n; i++) {

    //         if(nums[i] == 0) {
    //             cntZero++;
    //             result = Math.max(result, finMaxSubArr(nums,i));
    //         }
    //     }

    //     if(cntZero==0)
    //     return n-1;

    //     return result;
    // }


    public int longestSubarray(int[] nums) {
        int n = nums.length;
        int i = 0, lastZeroIdx = -1, result = 0,j = 0;

        while(j<n) {
            
            if(nums[j]==0){
                i = lastZeroIdx+1;
                lastZeroIdx = j;
            }

            result = Math.max(result, j - i);
            j++;
        }
        return result;
    }
}