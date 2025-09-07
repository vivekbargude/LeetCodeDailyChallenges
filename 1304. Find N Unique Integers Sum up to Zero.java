// Given an integer n, return any array containing n unique integers such that they add up to 0.

 

// Example 1:

// Input: n = 5
// Output: [-7,-1,1,3,4]
// Explanation: These arrays also are accepted [-5,-1,1,2,3] , [-3,-1,2,-2,4].
// Example 2:

// Input: n = 3
// Output: [-1,0,1]
// Example 3:

// Input: n = 1
// Output: [0]
 

// Constraints:

// 1 <= n <= 1000


class Solution {
    // //Approach 1:
    // public int[] sumZero(int n) {
    //     int element = 1;
    //     int[] ans = new int[n];
    //     int i = 0;
    //     while(i+1<n){
    //         ans[i] = element;
    //         ans[i+1] = -element;
    //         element++;
    //         i+=2;
    //     }

    //     return ans;
    // }

    //Approach 2:
    public int[] sumZero(int n) {
        int element = 1;
        int[] ans = new int[n];
        int i = 0,j = n-1;
        while(i<j){
            ans[i] = element;
            ans[j] = -element;
            element++;
            i++;
            j--;

            if(i==j)
            break;
        }

        return ans;
    }
}