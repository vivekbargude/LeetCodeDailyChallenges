// Given an integer numRows, return the first numRows of Pascal's triangle.

// In Pascal's triangle, each number is the sum of the two numbers directly above it as shown:


// Example 1:

// Input: numRows = 5
// Output: [[1],[1,1],[1,2,1],[1,3,3,1],[1,4,6,4,1]]
// Example 2:

// Input: numRows = 1
// Output: [[1]]
 

// Constraints:

// 1 <= numRows <= 30

import java.util.*;

class Solution {
    public List<List<Integer>> generate(int numRows) {
        //for storing ans 
        List<List<Integer>> ans = new ArrayList<>();

        for(int i=0;i<numRows;i++){
            ans.add(new ArrayList<>());
        }

        //for the first row
        ans.get(0).add(1);

        //iterate for the next to given row
        for(int i=1; i<numRows;i++){
            //first by default one 
            ans.get(i).add(1);
            //get the prev list to generate the curr
            List<Integer> prev = ans.get(i-1);

            for(int j=0;j<prev.size()-1;j++){
                ans.get(i).add( prev.get(j) + prev.get(j+1) );
            }

            //add the last by default one
            ans.get(i).add(1);
        }

        return ans;
    }
}