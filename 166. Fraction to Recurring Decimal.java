// Given two integers representing the numerator and denominator of a fraction, return the fraction in string format.

// If the fractional part is repeating, enclose the repeating part in parentheses.

// If multiple answers are possible, return any of them.

// It is guaranteed that the length of the answer string is less than 104 for all the given inputs.

 

// Example 1:

// Input: numerator = 1, denominator = 2
// Output: "0.5"
// Example 2:

// Input: numerator = 2, denominator = 1
// Output: "2"
// Example 3:

// Input: numerator = 4, denominator = 333
// Output: "0.(012)"
 

// Constraints:

// -231 <= numerator, denominator <= 231 - 1
// denominator != 0


//Approach - School wala Maths - Long Division
//T.C : O(denominator) approximately
//S.C : O(1)

import java.util.*;

class Solution {
    public String fractionToDecimal(int numerator, int denominator) {
        if (numerator == 0) return "0";

        StringBuilder result = new StringBuilder();

        // Handle sign
        if ((long) numerator * (long) denominator < 0) {
            result.append("-");
        }

        long absNumerator = Math.abs((long) numerator);
        long absDenominator = Math.abs((long) denominator);

        // Integer part
        long integerPart = absNumerator / absDenominator;
        result.append(integerPart);

        long remain = absNumerator % absDenominator;
        if (remain == 0) return result.toString();

        result.append(".");

        Map<Long, Integer> map = new HashMap<>();
        while (remain != 0) {
            if (map.containsKey(remain)) {
                // Insert "(" at the position where this remainder first appeared
                int insertPos = map.get(remain);
                result.insert(insertPos, "(");
                result.append(")");
                break;
            }

            map.put(remain, result.length());
            remain *= 10;
            long digit = remain / absDenominator;
            result.append(digit);
            remain %= absDenominator;
        }

        return result.toString();
    }
}