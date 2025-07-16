// You are given an integer array nums.
// A subsequence sub of nums with length x is called valid if it satisfies:

// (sub[0] + sub[1]) % 2 == (sub[1] + sub[2]) % 2 == ... == (sub[x - 2] + sub[x - 1]) % 2.
// Return the length of the longest valid subsequence of nums.

// A subsequence is an array that can be derived from another array by deleting some or no elements without changing the order of the remaining elements.

 

// Example 1:

// Input: nums = [1,2,3,4]

// Output: 4

// Explanation:

// The longest valid subsequence is [1, 2, 3, 4].

// Example 2:

// Input: nums = [1,2,1,1,2,1,2]

// Output: 6

// Explanation:

// The longest valid subsequence is [1, 2, 1, 2, 1, 2].

// Example 3:

// Input: nums = [1,3]

// Output: 2

// Explanation:

// The longest valid subsequence is [1, 3].

 

// Constraints:

// 2 <= nums.length <= 2 * 105
// 1 <= nums[i] <= 107


//Intuition:
//The sum % 2 can be 0 or 1 
//The possible combinations for 0 can be even,even & odd,odd
//The possible combinations for 1 can be odd,even or even,odd

//The check for following possible sequence
//1.Pick all even values
//2.Pick all odd values
//3.Pick all alternate values

class Solution {
    //TC -> O(N)
    public int maximumLength(int[] nums) {

        int cntEven = 0; //for even subsequence
        int cntOdd = 0; //for odd subsequence

        for(int num:nums){
            if(num%2==0)
            cntEven++;
            else 
            cntOdd++;
        }

        int cntAlternate = 1; //for alternate subsequence
        int parity = nums[0]%2;

        for(int num:nums){
            int currParity = num%2;
            if(currParity!=parity){
            cntAlternate++;
            parity=currParity;
            }
        }

        return Math.max(cntAlternate,Math.max(cntEven,cntOdd));
    }
}