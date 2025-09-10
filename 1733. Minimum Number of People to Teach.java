// On a social network consisting of m users and some friendships between users, two users can communicate with each other if they know a common language.

// You are given an integer n, an array languages, and an array friendships where:

// There are n languages numbered 1 through n,
// languages[i] is the set of languages the i​​​​​​th​​​​ user knows, and
// friendships[i] = [u​​​​​​i​​​, v​​​​​​i] denotes a friendship between the users u​​​​​​​​​​​i​​​​​ and vi.
// You can choose one language and teach it to some users so that all friends can communicate with each other. Return the minimum number of users you need to teach.

// Note that friendships are not transitive, meaning if x is a friend of y and y is a friend of z, this doesn't guarantee that x is a friend of z.
 

// Example 1:

// Input: n = 2, languages = [[1],[2],[1,2]], friendships = [[1,2],[1,3],[2,3]]
// Output: 1
// Explanation: You can either teach user 1 the second language or user 2 the first language.
// Example 2:

// Input: n = 3, languages = [[2],[1,3],[1,2],[3]], friendships = [[1,4],[1,2],[3,4],[2,3]]
// Output: 2
// Explanation: Teach the third language to users 1 and 3, yielding two users to teach.
 

// Constraints:

// 2 <= n <= 500
// languages.length == m
// 1 <= m <= 500
// 1 <= languages[i].length <= n
// 1 <= languages[i][j] <= n
// 1 <= u​​​​​​i < v​​​​​​i <= languages.length
// 1 <= friendships.length <= 500
// All tuples (u​​​​​i, v​​​​​​i) are unique
// languages[i] contains only unique values



//Approach (Greedily picking the mostKnown language among the sadUsers (those who cannpt talk to their friends))
//T.C : O(m*n)
//S.C : O(m+n)

import java.util.*;

class Solution {
    public int minimumTeachings(int n, int[][] languages, int[][] friendships) {
        Set<Integer> sadUsers = new HashSet<>(); // users who can't talk to their friend
        
        // Find sadUsers
        for (int[] friends : friendships) {
            int u = friends[0] - 1;
            int v = friends[1] - 1;
            
            // check if u and v share a common language
            Set<Integer> langSet = new HashSet<>();
            for (int lang : languages[u]) {
                langSet.add(lang);
            }
            
            boolean canTalk = false;
            for (int lang : languages[v]) {
                if (langSet.contains(lang)) {
                    canTalk = true;
                    break;
                }
            }
            
            // if they cannot talk, mark them as sad
            if (!canTalk) {
                sadUsers.add(u);
                sadUsers.add(v);
            }
        }
        
        // Count how many sadUsers already know each language
        int[] languageCount = new int[n + 1];
        int mostKnownLang = 0;
        
        for (int user : sadUsers) {
            for (int lang : languages[user]) {
                languageCount[lang]++;
                mostKnownLang = Math.max(mostKnownLang, languageCount[lang]);
            }
        }
        
        // total sadUsers - mostKnownLang
        return sadUsers.size() - mostKnownLang;
    }
}