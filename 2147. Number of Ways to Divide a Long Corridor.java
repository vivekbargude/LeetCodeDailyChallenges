// Along a long library corridor, there is a line of seats and decorative plants. You are given a 0-indexed string corridor of length n consisting of letters 'S' and 'P' where each 'S' represents a seat and each 'P' represents a plant.

// One room divider has already been installed to the left of index 0, and another to the right of index n - 1. Additional room dividers can be installed. For each position between indices i - 1 and i (1 <= i <= n - 1), at most one divider can be installed.

// Divide the corridor into non-overlapping sections, where each section has exactly two seats with any number of plants. There may be multiple ways to perform the division. Two ways are different if there is a position with a room divider installed in the first way but not in the second way.

// Return the number of ways to divide the corridor. Since the answer may be very large, return it modulo 109 + 7. If there is no way, return 0.

 

// Example 1:


// Input: corridor = "SSPPSPS"
// Output: 3
// Explanation: There are 3 different ways to divide the corridor.
// The black bars in the above image indicate the two room dividers already installed.
// Note that in each of the ways, each section has exactly two seats.
// Example 2:


// Input: corridor = "PPSPSP"
// Output: 1
// Explanation: There is only 1 way to divide the corridor, by not installing any additional dividers.
// Installing any would create some section that does not have exactly two seats.
// Example 3:


// Input: corridor = "S"
// Output: 0
// Explanation: There is no way to divide the corridor because there will always be a section that does not have exactly two seats.
 

// Constraints:

// n == corridor.length
// 1 <= n <= 105
// corridor[i] is either 'S' or 'P'.

import java.util.*;

// class Solution {
//     // Store 1000000007 in a variable for convenience
//     private static final int MOD = 1_000_000_007;

//     // Count the number of ways to divide from "index" to the last index
//     // with "seats" number of "S" in the current section
//     private int count(int index, int seats, String corridor, Map<Pair<Integer, Integer>, Integer> cache) {
//         // If we have reached the end of the corridor, then
//         // the current section is valid only if "seats" is 2
//         if (index == corridor.length()) {
//             return seats == 2 ? 1 : 0;
//         }

//         // If we have already computed the result of this sub-problem,
//         // then return the cached result
//         if (cache.containsKey(new Pair<>(index, seats))) {
//             return cache.get(new Pair<>(index, seats));
//         }

//         // Result of the sub-problem
//         int result = 0;

//         // If the current section has exactly 2 "S"
//         if (seats == 2) {
//             // If the current element is "S", then we have to close the
//             // section and start a new section from this index. Next index
//             // will have one "S" in the current section
//             if (corridor.charAt(index) == 'S') {
//                 result = count(index + 1, 1, corridor, cache);
//             } else {
//                 // If the current element is "P", then we have two options
//                 // 1. Close the section and start a new section from this index
//                 // 2. Keep growing the section
//                 result = (count(index + 1, 0, corridor, cache) + count(index + 1, 2, corridor, cache)) % MOD;  
//             }
//         } else {
//             // Keep growing the section. Increment "seats" if present
//             // element is "S"
//             if (corridor.charAt(index) == 'S') {
//                 result = count(index + 1, seats + 1, corridor, cache);
//             } else {
//                 result = count(index + 1, seats, corridor, cache);
//             }
//         }

//         // Memoize the result, and return it
//         cache.put(new Pair<>(index, seats), result);
//         return result;
//     }


//     public int numberOfWays(String corridor) {
//         // Cache the result of each sub-problem
//         Map<Pair<Integer, Integer>, Integer> cache = new HashMap<>();

//         // Call the count function
//         return count(0, 0, corridor, cache);
//     }
// }