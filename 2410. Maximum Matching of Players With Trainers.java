// You are given a 0-indexed integer array players, where players[i] represents the ability of the ith player. You are also given a 0-indexed integer array trainers, where trainers[j] represents the training capacity of the jth trainer.

// The ith player can match with the jth trainer if the player's ability is less than or equal to the trainer's training capacity. Additionally, the ith player can be matched with at most one trainer, and the jth trainer can be matched with at most one player.

// Return the maximum number of matchings between players and trainers that satisfy these conditions.

 

// Example 1:
// Input: players = [4,7,9], trainers = [8,2,5,8]
// Output: 2
// Explanation:
// One of the ways we can form two matchings is as follows:
// - players[0] can be matched with trainers[0] since 4 <= 8.
// - players[1] can be matched with trainers[3] since 7 <= 8.
// It can be proven that 2 is the maximum number of matchings that can be formed.


// Example 2:
// Input: players = [1,1,1], trainers = [10]
// Output: 1
// Explanation:
// The trainer can be matched with any of the 3 players.
// Each player can only be matched with one trainer, so the maximum answer is 1.
 

// Constraints:

// 1 <= players.length, trainers.length <= 105
// 1 <= players[i], trainers[j] <= 109




//Intuition : find the trainer having capicity greater than equal to than players ability 
 
//Brute Force: 

// for(int i=0;i<m;i++){
//     for(int j=0;j<n;j++){
//      if(trainer[j]>=player[i]){
//         count++;
//          trainer[]
// 
//         }
//     }
// }

import java.util.Arrays;
class Solution {
    public static void main(String[] args) {
        
    }

    //O(n*m)
    //edges case : players={1,10}
    //trainers={10,1}

    // public int matchPlayersAndTrainers(int[] players, int[] trainers) {
    //     int cnt =0;
    //     int m = players.length;
    //     int n = trainers.length;

    //     for(int i=0;i<m;i++){
    //         for(int j=0;i<n;j++){
    //             if(trainers[j]>=players[i]){
    //                 cnt++;
    //                 trainers[j]=-1;
    //                 break;
    //             }
    //         }
    //     }

    //     return cnt;
    // }

    //Edges cases can be avoided and trainer with small capacity should be allotted accordingly
    //sorting is a option

    //Intuition : SOrt and iterate 
    //discarding unnesscary trainers in the line and saving space complexity


    //O(mlogm+nlogn).
    //O(logm+logn)

    public int matchPlayersAndTrainers(int[] players, int[] trainers) {
        Arrays.sort(players);
        Arrays.sort(trainers);
        
        int cnt =0;
        int m = players.length;
        int n = trainers.length;
        int i=0,j=0;
        while(i<m && j<n){
            if(players[i]>trainers[j]){
                j++;
            }else{
                cnt++;
                i++;
                j++;
            }
        }

        return cnt;
    }

    
}
