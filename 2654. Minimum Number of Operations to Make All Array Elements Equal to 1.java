// You are given a 0-indexed array nums consisiting of positive integers. You can do the following operation on the array any number of times:

// Select an index i such that 0 <= i < n - 1 and replace either of nums[i] or nums[i+1] with their gcd value.
// Return the minimum number of operations to make all elements of nums equal to 1. If it is impossible, return -1.

// The gcd of two integers is the greatest common divisor of the two integers.

 

// Example 1:

// Input: nums = [2,6,3,4]
// Output: 4
// Explanation: We can do the following operations:
// - Choose index i = 2 and replace nums[2] with gcd(3,4) = 1. Now we have nums = [2,6,1,4].
// - Choose index i = 1 and replace nums[1] with gcd(6,1) = 1. Now we have nums = [2,1,1,4].
// - Choose index i = 0 and replace nums[0] with gcd(2,1) = 1. Now we have nums = [1,1,1,4].
// - Choose index i = 2 and replace nums[3] with gcd(1,4) = 1. Now we have nums = [1,1,1,1].
// Example 2:

// Input: nums = [2,10,6,14]
// Output: -1
// Explanation: It can be shown that it is impossible to make all the elements equal to 1.
 

// Constraints:

// 2 <= nums.length <= 50
// 1 <= nums[i] <= 106

class Solution {
    public int minOperations(int[] nums) {
        int gcd = nums[0];
        for (int i = 0; i < nums.length; i++) {
            if (nums[i] == 1) {
                int ans = 0;
                for (int x : nums) {
                    if (x != 1) ans++;
                }
                return ans;
            }
            if (i < nums.length - 1)
                gcd = gcd(gcd, nums[i + 1]);
        }

        if (gcd != 1) return -1;

        int ans = (int) 1e9;
        for (int i = 0; i < nums.length; i++) {
            int g = nums[i];
            for (int j = i + 1; j < nums.length; j++) {
                g = gcd(g, nums[j]);
                if (g == 1) {
                    ans = Math.min(ans, j - i - 1 + nums.length);
                    break;
                }
            }
        }
        return ans;
    }

    private int gcd(int a, int b) {
        if (b == 0) return a;
        return gcd(b, a % b);
    }
}