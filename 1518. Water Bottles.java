// There are numBottles water bottles that are initially full of water. You can exchange numExchange empty water bottles from the market with one full water bottle.

// The operation of drinking a full water bottle turns it into an empty bottle.

// Given the two integers numBottles and numExchange, return the maximum number of water bottles you can drink.

 

// Example 1:


// Input: numBottles = 9, numExchange = 3
// Output: 13
// Explanation: You can exchange 3 empty bottles to get 1 full water bottle.
// Number of water bottles you can drink: 9 + 3 + 1 = 13.
// Example 2:


// Input: numBottles = 15, numExchange = 4
// Output: 19
// Explanation: You can exchange 4 empty bottles to get 1 full water bottle. 
// Number of water bottles you can drink: 15 + 3 + 1 = 19.
 

// Constraints:

// 1 <= numBottles <= 100
// 2 <= numExchange <= 100

// //Approach-1 (Simple simulation)
// //T.C : O(numBottles)
// //S.C : O(1)
// //Approach-1 (Simple simulation)
// //T.C : O(numBottles)
// //S.C : O(1)
// class Solution {
//     public int numWaterBottles(int numBottles, int numExchange) {
//         int consumed = 0;

//         while (numBottles >= numExchange) {
//             consumed += numExchange;
//             numBottles -= numExchange;

//             numBottles += 1;
//         }

//         return consumed + numBottles;
//     }
// }


// //Approach-2 (Improvement on Approach-1)
// //T.C : O(log_numExchange_(numBottles))
// //S.C : O(1)
// class Solution {
//     public int numWaterBottles(int numBottles, int numExchange) {
//         int consumed = numBottles;
//         int emptyBottles = numBottles;

//         while (emptyBottles >= numExchange) {
//             int extraFullBottles = emptyBottles / numExchange;
//             int remain = emptyBottles % numExchange;

//             consumed += extraFullBottles;
//             emptyBottles = remain + extraFullBottles;
//         }

//         return consumed;
//     }
// }


//Approach-3 (Using maths):
//T.C : O(1)
//S.C:  O(1)
class Solution {
    public int numWaterBottles(int numBottles, int numExchange) {
        return numBottles + (numBottles - 1) / (numExchange - 1);
    }
}