// Given an array of points on the X-Y plane points where points[i] = [xi, yi], return the area of the largest triangle that can be formed by any three different points. Answers within 10-5 of the actual answer will be accepted.

 

// Example 1:


// Input: points = [[0,0],[0,1],[1,0],[0,2],[2,0]]
// Output: 2.00000
// Explanation: The five points are shown in the above figure. The red triangle is the largest.
// Example 2:

// Input: points = [[1,0],[0,0],[0,1]]
// Output: 0.50000
 

// Constraints:

// 3 <= points.length <= 50
// -50 <= xi, yi <= 50
// All the given points are unique.


class Solution {
    public double largestTriangleArea(int[][] points) {
        double maxArea = 0;
        int n = points.length;

        for (int i = 0; i < n; i++) {
            for (int j = i + 1; j < n; j++) {
                for (int k = j + 1; k < n; k++) {
                    // Calculate side lengths of the triangle
                    double a = getDist(points[i], points[j]);
                    double b = getDist(points[j], points[k]);
                    double c = getDist(points[k], points[i]);

                    // Semi-perimeter
                    double S = (a + b + c) / 2.0;

                    // Heron's formula: Area^2 = S(S-a)(S-b)(S-c)
                    double radicand = S * (S - a) * (S - b) * (S - c);

                    // Numerical safeguard: avoid negative due to floating-point precision
                    radicand = Math.max(0.0, radicand);

                    double area = Math.sqrt(radicand);

                    // Track the maximum area
                    maxArea = Math.max(maxArea, area);
                }
            }
        }
        return maxArea;
    }

    // Helper method to compute Euclidean distance between two points
    private double getDist(int[] p1, int[] p2) {
        int dx = p1[0] - p2[0];
        int dy = p1[1] - p2[1];
        return Math.sqrt(dx * dx + dy * dy);
    }

}