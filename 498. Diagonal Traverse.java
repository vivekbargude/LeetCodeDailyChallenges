// Given an m x n matrix mat, return an array of all the elements of the array in a diagonal order.

 

// Example 1:


// Input: mat = [[1,2,3],[4,5,6],[7,8,9]]
// Output: [1,2,4,7,5,3,6,8,9]
// Example 2:

// Input: mat = [[1,2],[3,4]]
// Output: [1,2,3,4]
 

// Constraints:

// m == mat.length
// n == mat[i].length
// 1 <= m, n <= 104
// 1 <= m * n <= 104
// -105 <= mat[i][j] <= 105

import java.util.*;

class Solution {
    public int[] findDiagonalOrder(int[][] mat) {
        int m = mat.length;
        int n = mat[0].length;

        Map<Integer, List<Integer>> map = new HashMap<>();

        for(int i=0; i<m; i++) {
            for(int j=0; j<n; j++){
                int key = i+j;
                map.putIfAbsent(key, new ArrayList<>());
                map.get(key).add(mat[i][j]);
            }
        }

        List<Integer> result = new ArrayList<>();
        boolean flip = true;

        for(int i=0; i<=m+n-2; i++) {
            List<Integer> diagonal = map.get(i);

            if(flip) {
                Collections.reverse(diagonal);
            }

            result.addAll(diagonal);

            flip = !flip;
        }

        int[] ans = new int[result.size()];

        for(int i=0; i<result.size(); i++) {
            ans[i] = result.get(i);
        }

        return ans;
    }
}