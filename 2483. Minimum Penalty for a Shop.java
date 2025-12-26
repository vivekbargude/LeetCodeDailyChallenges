// You are given the customer visit log of a shop represented by a 0-indexed string customers consisting only of characters 'N' and 'Y':

// if the ith character is 'Y', it means that customers come at the ith hour
// whereas 'N' indicates that no customers come at the ith hour.
// If the shop closes at the jth hour (0 <= j <= n), the penalty is calculated as follows:

// For every hour when the shop is open and no customers come, the penalty increases by 1.
// For every hour when the shop is closed and customers come, the penalty increases by 1.
// Return the earliest hour at which the shop must be closed to incur a minimum penalty.

// Note that if a shop closes at the jth hour, it means the shop is closed at the hour j.

 

// Example 1:

// Input: customers = "YYNY"
// Output: 2
// Explanation: 
// - Closing the shop at the 0th hour incurs in 1+1+0+1 = 3 penalty.
// - Closing the shop at the 1st hour incurs in 0+1+0+1 = 2 penalty.
// - Closing the shop at the 2nd hour incurs in 0+0+0+1 = 1 penalty.
// - Closing the shop at the 3rd hour incurs in 0+0+1+1 = 2 penalty.
// - Closing the shop at the 4th hour incurs in 0+0+1+0 = 1 penalty.
// Closing the shop at 2nd or 4th hour gives a minimum penalty. Since 2 is earlier, the optimal closing time is 2.
// Example 2:

// Input: customers = "NNNNN"
// Output: 0
// Explanation: It is best to close the shop at the 0th hour as no customers arrive.
// Example 3:

// Input: customers = "YYYY"
// Output: 4
// Explanation: It is best to close the shop at the 4th hour as customers arrive at each hour.
 

// Constraints:

// 1 <= customers.length <= 105
// customers consists only of characters 'Y' and 'N'.

class Solution {

    //Approach-1 : T.C - O(n^2) - TLE

    // public int bestClosingTime(String customers) {
    //     int n = customers.length();
    //     int minPenalty = Integer.MAX_VALUE;
    //     int minHr = Integer.MAX_VALUE; 
        
    //     for(int i=0; i<n; i++) {
    //         int currPenalty = 0;
    //         int j = i-1;
    
    //         while(j>=0){
    //             if(customers.charAt(j)=='N')
    //                 currPenalty++;

    //             j--;
    //         }
            
    //         j = i;
    //         while(j<n){
    //             if(customers.charAt(j)=='Y')
    //                 currPenalty++;

    //             j++;
    //         }
           
    //         if(currPenalty<minPenalty){
    //             minPenalty = currPenalty;
    //             minHr = i;
    //         }
    //     }

            //Trying closing at nth Hour
    //     int nthHrPenalty = 0;
    //     for(int i=0;i<n;i++){
    //          if(customers.charAt(i)=='N')
    //             nthHrPenalty++;
    //     }

    //     if(nthHrPenalty<minPenalty){
    //             minHr = n;
    //     }

    //     return minHr;
    // }


    //Approach-2 (keeping track of count of 'N' and 'Y' for any index)
    //Note that for any index i we close at that time, we want to know 
    //--how many 'N' were there from [0, i-1]
    //--how many 'Y' were there from [i, n-1]
    //T.C : O(n)
    //S.C : O(n)

    // public int bestClosingTime(String customers) {
    //     int n = customers.length();
    //     int[] prefix_N = new int[n+1];
    //     prefix_N[0] = 0;
    //     int[] suffix_Y = new int[n+1];
    //     suffix_Y[n] = 0;

    //     int minPenalty = Integer.MAX_VALUE;
    //     int minHr = Integer.MAX_VALUE; 

    //     for(int i=1; i<=n; i++){
            
    //         if(i<n && customers.charAt(i-1)=='N')
    //         prefix_N[i] = prefix_N[i-1] + 1;
    //         else 
    //         prefix_N[i] = prefix_N[i-1];
    //     }

    //     for(int i=n-1; i>=0; i--) {
    //         if(customers.charAt(i)=='Y')
    //         suffix_Y[i] = suffix_Y[i+1] + 1;
    //         else 
    //         suffix_Y[i] = suffix_Y[i+1];
    //     }

    //     for(int i=0; i<=n; i++) {
    //         int curr = prefix_N[i] + suffix_Y[i];
    //         if(curr<minPenalty) {
    //             minPenalty = curr;
    //             minHr = i;
    //         }
    //     }
    //     return minHr;
    // }

    //Approach-3 - 2 Pass Solution
    //(T.C : O(n)
    //S.C : O(1)
    
    public int bestClosingTime(String customers) {
        int n = customers.length();
        int minHr = 0;

        int currPenalty = 0;

        for(int i = 0; i<n; i++) {
            if(customers.charAt(i)=='Y')
            currPenalty++;
        }

        int minPenalty = currPenalty;

        for(int i=0; i<n; i++){
            if(customers.charAt(i)=='Y')
            currPenalty--;
            else 
            currPenalty++;

            if(currPenalty < minPenalty) {
                minPenalty = currPenalty;
                minHr = i+1;
            }
        }

        return minHr;
    }
}