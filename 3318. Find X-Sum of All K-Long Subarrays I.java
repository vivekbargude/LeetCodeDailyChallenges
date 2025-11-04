// You are given an array nums of n integers and two integers k and x.

// The x-sum of an array is calculated by the following procedure:

// Count the occurrences of all elements in the array.
// Keep only the occurrences of the top x most frequent elements. If two elements have the same number of occurrences, the element with the bigger value is considered more frequent.
// Calculate the sum of the resulting array.
// Note that if an array has less than x distinct elements, its x-sum is the sum of the array.

// Return an integer array answer of length n - k + 1 where answer[i] is the x-sum of the subarray nums[i..i + k - 1].

 

// Example 1:

// Input: nums = [1,1,2,2,3,4,2,3], k = 6, x = 2

// Output: [6,10,12]

// Explanation:

// For subarray [1, 1, 2, 2, 3, 4], only elements 1 and 2 will be kept in the resulting array. Hence, answer[0] = 1 + 1 + 2 + 2.
// For subarray [1, 2, 2, 3, 4, 2], only elements 2 and 4 will be kept in the resulting array. Hence, answer[1] = 2 + 2 + 2 + 4. Note that 4 is kept in the array since it is bigger than 3 and 1 which occur the same number of times.
// For subarray [2, 2, 3, 4, 2, 3], only elements 2 and 3 are kept in the resulting array. Hence, answer[2] = 2 + 2 + 2 + 3 + 3.
// Example 2:

// Input: nums = [3,8,7,8,7,5], k = 2, x = 2

// Output: [11,15,15,15,12]

// Explanation:

// Since k == x, answer[i] is equal to the sum of the subarray nums[i..i + k - 1].

 

// Constraints:

// 1 <= n == nums.length <= 50
// 1 <= nums[i] <= 50
// 1 <= x <= k <= nums.length


import java.util.*;

class Solution {

    private int find(Map<Integer, Integer> freqMap, int x) {
        // Max-heap based on frequency
        PriorityQueue<int[]> pq = new PriorityQueue<>((a, b) -> b[0] - a[0]);

        for (Map.Entry<Integer, Integer> entry : freqMap.entrySet()) {
            pq.offer(new int[]{entry.getValue(), entry.getKey()});
        }

        int sum = 0;
        // Get the top x most frequent elements
        while (x-- > 0 && !pq.isEmpty()) {
            int[] top = pq.poll();
            int freq = top[0];
            int num = top[1];
            // Add all occurrences of this number to sum
            sum += num * freq;
        }

        return sum;
    }

    public int[] findXSum(int[] nums, int k, int x) {
        Map<Integer, Integer> freqMap = new HashMap<>();
        List<Integer> resultList = new ArrayList<>();
        int l = 0;

        for (int r = 0; r < nums.length; r++) {
            freqMap.put(nums[r], freqMap.getOrDefault(nums[r], 0) + 1);

            // Shrink the window if it exceeds size k
            while (l < r && (r - l + 1) > k) {
                freqMap.put(nums[l], freqMap.get(nums[l]) - 1);
                if (freqMap.get(nums[l]) == 0) {
                    freqMap.remove(nums[l]);
                }
                l++;
            }

            // If window size == k, compute the X-sum
            if ((r - l + 1) == k) {
                resultList.add(find(freqMap, x));
            }
        }

        // Convert List<Integer> → int[]
        int[] result = new int[resultList.size()];
        for (int i = 0; i < resultList.size(); i++) {
            result[i] = resultList.get(i);
        }

        return result;
    }
}
