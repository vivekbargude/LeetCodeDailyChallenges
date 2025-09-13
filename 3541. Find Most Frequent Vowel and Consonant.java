// You are given a string s consisting of lowercase English letters ('a' to 'z').

// Your task is to:

// Find the vowel (one of 'a', 'e', 'i', 'o', or 'u') with the maximum frequency.
// Find the consonant (all other letters excluding vowels) with the maximum frequency.
// Return the sum of the two frequencies.

// Note: If multiple vowels or consonants have the same maximum frequency, you may choose any one of them. If there are no vowels or no consonants in the string, consider their frequency as 0.

// The frequency of a letter x is the number of times it occurs in the string.
 

// Example 1:

// Input: s = "successes"

// Output: 6

// Explanation:

// The vowels are: 'u' (frequency 1), 'e' (frequency 2). The maximum frequency is 2.
// The consonants are: 's' (frequency 4), 'c' (frequency 2). The maximum frequency is 4.
// The output is 2 + 4 = 6.
// Example 2:

// Input: s = "aeiaeia"

// Output: 3

// Explanation:

// The vowels are: 'a' (frequency 3), 'e' ( frequency 2), 'i' (frequency 2). The maximum frequency is 3.
// There are no consonants in s. Hence, maximum consonant frequency = 0.
// The output is 3 + 0 = 3.
 

// Constraints:

// 1 <= s.length <= 100
// s consists of lowercase English letters only.


// class Solution {
//     public int maxFreqSum(String s) {
//         Map<Character,Integer> map = new HashMap<>();
//         int n = s.length();
//         int vowel=0,consonant=0;

//         for(int i=0; i<n; i++) {
//             char ch = s.charAt(i);

//             if(ch=='a'||ch=='e'||ch=='i'||ch=='o'||ch=='u'){
//                 int curr = map.getOrDefault(ch,0)+1;
//                 vowel=Math.max(curr,vowel);
//                 map.put(ch,curr);
//             }else{
//                 int curr = map.getOrDefault(ch,0)+1;
//                 consonant=Math.max(curr,consonant);
//                 map.put(ch,curr);
//             }
//         }
//         return vowel + consonant;
//     }
// }

class Solution {
    public int maxFreqSum(String s) {
        s=s.toLowerCase();
        int arr[]=new int[26];
        for(int i=0;i<s.length();i++){
            char c = s.charAt(i);
            arr[c-'a']++;
        }
        int vmax=0;
        int cmax=0;
        for(int i=0;i<arr.length;i++){
            
            if((i==0||i==4||i==8||i==14||i==20)&&arr[i]>vmax){
                vmax=arr[i];
            }
            else if((i!=0&&i!=4&&i!=8&&i!=14&&i!=20)&&arr[i]>cmax){
                cmax=arr[i];
            }
        }
        return vmax+cmax;
    }
}