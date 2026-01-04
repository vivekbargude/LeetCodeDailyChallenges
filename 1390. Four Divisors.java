// Given an integer array nums, return the sum of divisors of the integers in that array that have exactly four divisors. If there is no such integer in the array, return 0.

 

// Example 1:

// Input: nums = [21,4,7]
// Output: 32
// Explanation: 
// 21 has 4 divisors: 1, 3, 7, 21
// 4 has 3 divisors: 1, 2, 4
// 7 has 2 divisors: 1, 7
// The answer is the sum of divisors of 21 only.
// Example 2:

// Input: nums = [21,21]
// Output: 64
// Example 3:

// Input: nums = [1,2,3,4,5]
// Output: 0
 

// Constraints:

// 1 <= nums.length <= 104
// 1 <= nums[i] <= 105

//Approach - Simple maths - Iterate and find factors
//T.C : O(n * sqrt(maxNumber))
//S.C : O(1)
class Solution {

    private int sumIfFourDivisors(int num) {
        int divisors = 0;
        int sum = 0;

        for (int div = 1; div * div <= num; div++) {
            if (num % div == 0) {
                int other = num / div;

                if (div == other) {
                    divisors++;
                    sum += div;
                } else {
                    divisors += 2;
                    sum += div + other;
                }
            }

            if (divisors > 4) {
                return 0;
            }
        }

        return divisors == 4 ? sum : 0;
    }

    public int sumFourDivisors(int[] nums) {
        int result = 0;

        for (int num : nums) {
            result += sumIfFourDivisors(num);
        }

        return result;
    }
}