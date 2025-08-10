// You are given an integer n. We reorder the digits in any order (including the original order) such that the leading digit is not zero.

// Return true if and only if we can do this so that the resulting number is a power of two.

// Example 1:

// Input: n = 1
// Output: true
// Example 2:

// Input: n = 10
// Output: false
 

// Constraints:

// 1 <= n <= 109

//Approach-1 (Using sorting)
//T.C : O(dlogd), d = number of digits
//S.C : O(d)

import java.util.Arrays;

class Solution {

    public String getSortedStr(int n) {
        char[] chars = String.valueOf(n).toCharArray();
        Arrays.sort(chars);
        return new String(chars);
    }

    public boolean reorderedPowerOf2(int n) {
        String sortedStr = getSortedStr(n);

        // Check all powers of 2 up to 2^29
        for (int p = 0; p <= 29; p++) {
            if (sortedStr.equals(getSortedStr(1 << p))) {
                return true;
            }
        }

        return false;
    }
}

//Approach-3 (count and store digits in a vector)
//T.C : O(d), d = number of digits
//S.C : O(1)
// class Solution {
//     private int[] getCountVector(int n) {
//         int[] count = new int[10];
//         while (n > 0) {
//             count[n % 10]++;
//             n /= 10;
//         }
//         return count;
//     }

//     public boolean reorderedPowerOf2(int n) {
//         int[] inputCount = getCountVector(n);

//         for (int p = 0; p <= 29; p++) {
//             if (Arrays.equals(inputCount, getCountVector(1 << p))) {
//                 return true;
//             }
//         }

//         return false;
//     }
// }


//Approach-4 (count and store digits in a number)
//T.C : O(d), d = number of digits
//S.C : O(1)
// class Solution {
//     private int getCountNumber(int n) {
//         int num = 0;
//         while (n > 0) {
//             num += Math.pow(10, n % 10);
//             n /= 10;
//         }
//         return num;
//     }

//     public boolean reorderedPowerOf2(int n) {
//         int inputCount = getCountNumber(n);

//         for (int p = 0; p <= 29; p++) {
//             if (inputCount == getCountNumber(1 << p)) {
//                 return true;
//             }
//         }

//         return false;
//     }
// }