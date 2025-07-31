import java.util.*;

class Solution {
    //Brute force goes to O(n^3)
    // public int subarrayBitwiseORs(int[] arr) {
    //     int n = arr.length;
    //     Set<Integer> uniqueOr = new HashSet<>();

    //     for(int i=0;i<n;i++){
            
    //         for(int j=i;j<n;j++){

    //             int currOr = 0;
    //             for(int k=i;k<=j;k++){
    //                 currOr |= arr[k];
    //             }
    //             uniqueOr.add(currOr);
    //         }
    //     }

    //     return uniqueOr.size();
    // }


    public int subarrayBitwiseORs(int[] arr) {
    int n = arr.length;
    Set<Integer> prev = new HashSet<>();
    Set<Integer> result = new HashSet<>();

    for (int i = 0; i < n; i++) {
        Set<Integer> curr = new HashSet<>();

        // Extend subarrays from previous results
        for (int x : prev) {
            curr.add(x | arr[i]);
        }

        // Start new subarray from arr[i]
        curr.add(arr[i]);

        result.addAll(curr);
        prev = curr;
    }

    return result.size();
}

}