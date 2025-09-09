// On day 1, one person discovers a secret.

// You are given an integer delay, which means that each person will share the secret with a new person every day, starting from delay days after discovering the secret. You are also given an integer forget, which means that each person will forget the secret forget days after discovering it. A person cannot share the secret on the same day they forgot it, or on any day afterwards.

// Given an integer n, return the number of people who know the secret at the end of day n. Since the answer may be very large, return it modulo 109 + 7.

 

// Example 1:

// Input: n = 6, delay = 2, forget = 4
// Output: 5
// Explanation:
// Day 1: Suppose the first person is named A. (1 person)
// Day 2: A is the only person who knows the secret. (1 person)
// Day 3: A shares the secret with a new person, B. (2 people)
// Day 4: A shares the secret with a new person, C. (3 people)
// Day 5: A forgets the secret, and B shares the secret with a new person, D. (3 people)
// Day 6: B shares the secret with E, and C shares the secret with F. (5 people)
// Example 2:

// Input: n = 4, delay = 1, forget = 3
// Output: 6
// Explanation:
// Day 1: The first person is named A. (1 person)
// Day 2: A shares the secret with B. (2 people)
// Day 3: A and B share the secret with 2 new people, C and D. (4 people)
// Day 4: A forgets the secret. B, C, and D share the secret with 3 new people. (6 people)
 

// Constraints:

// 2 <= n <= 1000
// 1 <= delay < forget <= n


// // Approach-1 - Recursion + Memoization
// // T.C : O(n * (forget - delay))
// // S.C : O(n)
// class Solution {
//     int MOD = 1_000_000_007;
//     int n, delay, forget;
//     int[] t; // memoization array

//     // f(day) = number of people who first learn the secret on "day"
//     private int solve(int day) {
//         if (day == 1) return 1;
//         if (day <= 0) return 0;

//         if (t[day] != -1) return t[day];

//         long result = 0;
//         // people who can share today are those who learned in [day - forget + 1, day - delay]
//         for (int prev = day - forget + 1; prev <= day - delay; prev++) {
//             if (prev > 0) {
//                 result = (result + solve(prev)) % MOD;
//             }
//         }

//         return t[day] = (int) result;
//     }

//     public int peopleAwareOfSecret(int n, int delay, int forget) {
//         this.n = n;
//         this.delay = delay;
//         this.forget = forget;
//         t = new int[n + 1];
//         Arrays.fill(t, -1);

//         int total = 0;
//         // only count people who still remember on day n
//         for (int day = n - forget + 1; day <= n; day++) {
//             if (day > 0) {
//                 total = (total + solve(day)) % MOD;
//             }
//         }

//         return total;
//     }
// }


// Approach-2 - Bottom Up
// T.C : O(n * (forgt - delay))
// S.C : O(n)
class Solution {
    public int peopleAwareOfSecret(int n, int delay, int forget) {
        int MOD = (int)1e9 + 7;
        int[] t = new int[n + 1]; // t[day] = number of people who learn the secret on "day"

        t[1] = 1;

        for (int day = 2; day <= n; day++) {
            long count = 0;
            for (int prev = day - forget + 1; prev <= day - delay; prev++) {
                if (prev > 0) {
                    count = (count + t[prev]) % MOD;
                }
            }
            t[day] = (int)count;
        }

        int result = 0;
        for (int day = n - forget + 1; day <= n; day++) {
            if (day > 0) {
                result = (result + t[day]) % MOD;
            }
        }

        return result;
    }
}
