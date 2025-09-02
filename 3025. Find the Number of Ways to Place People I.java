// You are given a 2D array points of size n x 2 representing integer coordinates of some points on a 2D plane, where points[i] = [xi, yi].

// Count the number of pairs of points (A, B), where

// A is on the upper left side of B, and
// there are no other points in the rectangle (or line) they make (including the border).
// Return the count.

 

// Example 1:

// Input: points = [[1,1],[2,2],[3,3]]

// Output: 0

// Explanation:



// There is no way to choose A and B so A is on the upper left side of B.

// Example 2:

// Input: points = [[6,2],[4,4],[2,6]]

// Output: 2

// Explanation:



// The left one is the pair (points[1], points[0]), where points[1] is on the upper left side of points[0] and the rectangle is empty.
// The middle one is the pair (points[2], points[1]), same as the left one it is a valid pair.
// The right one is the pair (points[2], points[0]), where points[2] is on the upper left side of points[0], but points[1] is inside the rectangle so it's not a valid pair.
// Example 3:

// Input: points = [[3,1],[1,3],[1,1]]

// Output: 2

// Explanation:

// The left one is the pair (points[2], points[0]), where points[2] is on the upper left side of points[0] and there are no other points on the line they form. Note that it is a valid state when the two points form a line.
// The middle one is the pair (points[1], points[2]), it is a valid pair same as the left one.
// The right one is the pair (points[1], points[0]), it is not a valid pair as points[2] is on the border of the rectangle.
 

// Constraints:

// 2 <= n <= 50
// points[i].length == 2
// 0 <= points[i][0], points[i][1] <= 50
// All points[i] are distinct.


class Solution {

    //Approach 1 = Brute Force
    //TC = O(n^3)
    //SC = O(1)
    // public int numberOfPairs(int[][] points) {
    //     int n = points.length;
    //     int result = 0;

    //     for(int i = 0; i < n ; i++) {
    //         //A -> Upper left wala point
    //         int x1 = points[i][0];
    //         int y1 = points[i][1];

    //         //Find B -> Lower right wla point 
    //         for(int j = 0; j < n ; j++) {

    //             //A==B then skip
    //             if(i==j)
    //             continue;

    //             int x2 = points[j][0];
    //             int y2 = points[j][1];

    //             //Condition for B
    //             if( x2 >= x1 && y2 <= y1) {

    //                 boolean hasPointInside = false;

    //                 //For checking in between points
    //                 for(int k = 0; k < n ; k++) {
    //                     if(k == i || k == j) {
    //                         continue;
    //                     }

    //                     int x3 = points[k][0];
    //                     int y3 = points[k][1];

    //                     //Found in between point
    //                     if( x3 >= x1 && x3 <= x2 && y3 <= y1 && y3 >= y2) {
    //                         hasPointInside = true;
    //                         break;
    //                     }
    //                 }

    //                 //No a valid combination
    //                 if(!hasPointInside){
    //                     result++;
    //                 }

    //             }

    //         }
    //     }

    //     return result;
    // }


    //Approach 2 = Improvement

    //1. For getting the B point we can sort the array based on x co- ordinates such that the valid points should be present only on the right side of the current point to verify. x2>=x1
    //2. And for checking y co-ordinate if the current point ka y co-ordinate is greater than select A ka y co-ordinates then skip
    //3. Sort on basis of x co-ordinates and if x is equal then sort descending based on y co-ordinates.


    public int numberOfPairs(int[][] points) {
        int n = points.length;
        int result = 0;

        // Sort: x ascending, if x same then sort as y descending
        Arrays.sort(points, (point1, point2) -> {
            if (point1[0] == point2[0]) {
                return point2[1] - point1[1];
            }
            return point1[0] - point2[0];
        });

        for(int i = 0; i < n ; i++) {
            //A -> Upper left wala point
            int x1 = points[i][0];
            int y1 = points[i][1];

            int bestY = Integer.MIN_VALUE;

            //Find B -> Lower right wla point 
            for(int j = i+1; j < n ; j++) {

                int x2 = points[j][0];
                int y2 = points[j][1];

                // Condition: (x2, y2) must be above (x1, y1)
                if (y2 > y1) { //not lower right
                    continue;
                }

                if (y2 > bestY) {
                    result++;
                    bestY = y2;
                }

            }
        }

        return result;
    }
}
