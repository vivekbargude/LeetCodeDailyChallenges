// There is a school that has classes of students and each class will be having a final exam. You are given a 2D integer array classes, where classes[i] = [passi, totali]. You know beforehand that in the ith class, there are totali total students, but only passi number of students will pass the exam.

// You are also given an integer extraStudents. There are another extraStudents brilliant students that are guaranteed to pass the exam of any class they are assigned to. You want to assign each of the extraStudents students to a class in a way that maximizes the average pass ratio across all the classes.

// The pass ratio of a class is equal to the number of students of the class that will pass the exam divided by the total number of students of the class. The average pass ratio is the sum of pass ratios of all the classes divided by the number of the classes.

// Return the maximum possible average pass ratio after assigning the extraStudents students. Answers within 10-5 of the actual answer will be accepted.

 

// Example 1:

// Input: classes = [[1,2],[3,5],[2,2]], extraStudents = 2
// Output: 0.78333
// Explanation: You can assign the two extra students to the first class. The average pass ratio will be equal to (3/4 + 3/5 + 2/2) / 3 = 0.78333.
// Example 2:

// Input: classes = [[2,4],[3,9],[4,5],[2,10]], extraStudents = 4
// Output: 0.53485
 

// Constraints:

// 1 <= classes.length <= 105
// classes[i].length == 2
// 1 <= passi <= totali <= 105
// 1 <= extraStudents <= 105



// //Approach-1 : (Chossing class with max delta/improvement everytime)
// //T.C : O(extraStudents * n)
// //S.C : O(n)
// class Solution {
//     public double maxAverageRatio(int[][] classes, int extraStudents) {
//         int n = classes.length;

//         // Array to store the current pass ratio for each class
//         double[] PR = new double[n];
//         for (int i = 0; i < n; i++) {
//             double ratio = (double) classes[i][0] / classes[i][1];
//             PR[i] = ratio;
//         }

//         while (extraStudents-- > 0) {
//             // Array to store the updated pass ratio for each class
//             double[] updatedPR = new double[n];
//             for (int i = 0; i < n; i++) {
//                 double newRatio = (double) (classes[i][0] + 1) / (classes[i][1] + 1);
//                 updatedPR[i] = newRatio;
//             }

//             // Find the class that has the maximum improvement in pass ratio
//             int bestClassIdx = 0;
//             double bestDelta = 0;

//             for (int i = 0; i < n; i++) {
//                 double delta = updatedPR[i] - PR[i];
//                 if (delta > bestDelta) {
//                     bestDelta = delta;
//                     bestClassIdx = i;
//                 }
//             }

//             // Update the best class with an extra student
//             PR[bestClassIdx] = updatedPR[bestClassIdx];
//             classes[bestClassIdx][0]++;
//             classes[bestClassIdx][1]++;
//         }

//         // Calculate the final average pass ratio
//         double result = 0.0;
//         for (int i = 0; i < n; i++) {
//             result += PR[i];
//         }

//         return result / n;
//     }
// }



//Approach-2 : (Chossing class with max delta/improvement everytime - Improving with max heap)
//T.C : O(extraStudents * log(n))
//S.C : O(n)
import java.util.PriorityQueue;

class Solution {
    public double maxAverageRatio(int[][] classes, int extraStudents) {
        int n = classes.length;

        // Priority queue to act as a max-heap, storing pairs of {max-delta, index}
        PriorityQueue<double[]> pq = new PriorityQueue<>((a, b) -> Double.compare(b[0], a[0]));

        // Initialize the priority queue with the delta values and indices
        for (int i = 0; i < n; i++) {
            double currentPR = (double) classes[i][0] / classes[i][1];
            double newPR = (double) (classes[i][0] + 1) / (classes[i][1] + 1);
            double delta = newPR - currentPR;
            pq.offer(new double[]{delta, i});
        }

        // Allocate extra students
        while (extraStudents-- > 0) {
            // Extract the class with the maximum delta
            double[] curr = pq.poll();
            int idx = (int) curr[1];

            // Update the class with an extra student
            classes[idx][0]++;
            classes[idx][1]++;

            // Recalculate the delta for this class
            double currentPR = (double) classes[idx][0] / classes[idx][1];
            double newPR = (double) (classes[idx][0] + 1) / (classes[idx][1] + 1);
            double delta = newPR - currentPR;

            // Push the updated delta and index back into the priority queue
            pq.offer(new double[]{delta, idx});
        }

        // Calculate the final average pass ratio
        double result = 0.0;
        for (int i = 0; i < n; i++) {
            result += (double) classes[i][0] / classes[i][1];
        }

        return result / n;
    }
}