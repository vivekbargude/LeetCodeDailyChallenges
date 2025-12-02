// You are given a 2D integer array points, where points[i] = [xi, yi] represents the coordinates of the ith point on the Cartesian plane.

// A horizontal trapezoid is a convex quadrilateral with at least one pair of horizontal sides (i.e. parallel to the x-axis). Two lines are parallel if and only if they have the same slope.

// Return the number of unique horizontal trapezoids that can be formed by choosing any four distinct points from points.

// Since the answer may be very large, return it modulo 109 + 7.

 

// Example 1:

// Input: points = [[1,0],[2,0],[3,0],[2,2],[3,2]]

// Output: 3

// Explanation:



// There are three distinct ways to pick four points that form a horizontal trapezoid:

// Using points [1,0], [2,0], [3,2], and [2,2].
// Using points [2,0], [3,0], [3,2], and [2,2].
// Using points [1,0], [3,0], [3,2], and [2,2].
// Example 2:

// Input: points = [[0,0],[1,0],[0,1],[2,1]]

// Output: 1

// Explanation:



// There is only one horizontal trapezoid that can be formed.

 

// Constraints:

// 4 <= points.length <= 105
// –108 <= xi, yi <= 108
// All points are pairwise distinct.


//Approach - Simple Maths counting
//T.C : O(n)
//S.C : O(n)

import java.util.*;

class Solution {
    static final int M = (int)1e9 + 7;

    public int countTrapezoids(int[][] points) {
        // Map y -> count of points having that y-coordinate
        Map<Integer, Integer> mp = new HashMap<>();
        for (int[] point : points) {
            int y = point[1];
            mp.put(y, mp.getOrDefault(y, 0) + 1);
        }

        long result = 0;
        long prevHorizontalLines = 0;

        // Iterate over the hashmap
        for (int count : mp.values()) {

            long horizontalLines = (long) count * (count - 1) / 2;  // C(n, 2)

            result = (result + horizontalLines * prevHorizontalLines) % M;

            prevHorizontalLines += horizontalLines;
        }

        return (int)(result % M);
    }
}