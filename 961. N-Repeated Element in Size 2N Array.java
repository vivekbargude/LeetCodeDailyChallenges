// You are given an integer array nums with the following properties:

// nums.length == 2 * n.
// nums contains n + 1 unique elements.
// Exactly one element of nums is repeated n times.
// Return the element that is repeated n times.

 

// Example 1:

// Input: nums = [1,2,3,3]
// Output: 3
// Example 2:

// Input: nums = [2,1,2,5,3,2]
// Output: 2
// Example 3:

// Input: nums = [5,1,5,2,5,3,5,4]
// Output: 5
 

// Constraints:

// 2 <= n <= 5000
// nums.length == 2 * n
// 0 <= nums[i] <= 104
// nums contains n + 1 unique elements and one of them is repeated exactly n times.

//Approach-1 (Using Hashset)
//T.C : O(n)
//S.C : O(n)
// class Solution {
//     public int repeatedNTimes(int[] nums) {
//         Set<Integer> set = new HashSet<>();
        
//         for (int num : nums) {
//             if (set.contains(num))
//                 return num;
//             set.add(num);
//         }
        
//         return -1;
//     }
// }

//Approach-2 (Using 10^4 Size array ~ Constant Space)
//T.C : O(n)
//S.C : O(1)
// class Solution {
//     public int repeatedNTimes(int[] nums) {
//         int[] freq = new int[10001];
        
//         for (int num : nums) {
//             freq[num]++;
//             if (freq[num] > 1) // all other elements appear exactly once
//                 return num;
//         }
        
//         return -1;
//     }
// }

// Approach 2 : Optimized Approach 
// the target element can be in the array in such cases :
// Case 1 : 1 , 1 , 2 , 3 , 4 or 2 , 3 , 4 , 1 , 1 -> one beside another. 
// Check : nums[i]==nums[i+1])

// Case 2 : 1 , 2 , 1 , 3 , 4 -> in a gap of one element.
// Check : ( i<n-2 && nums[i]==nums[i+2]
//Note : In any of the cases the element at the i th index is our ans.

//TC -> O(N)
//SC -> O(1)
class Solution {
    public int repeatedNTimes(int[] nums) {
        int n = nums.length;
        
        for (int i = 2; i < n; i++) {
            if (nums[i] == nums[i - 1] || nums[i] == nums[i - 2])
                return nums[i];
        }
        
        return nums[n - 1]; //or, nums[0]
    }
}