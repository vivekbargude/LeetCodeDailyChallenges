// There is a malfunctioning keyboard where some letter keys do not work. All other keys on the keyboard work properly.

// Given a string text of words separated by a single space (no leading or trailing spaces) and a string brokenLetters of all distinct letter keys that are broken, return the number of words in text you can fully type using this keyboard.

 

// Example 1:

// Input: text = "hello world", brokenLetters = "ad"
// Output: 1
// Explanation: We cannot type "world" because the 'd' key is broken.
// Example 2:

// Input: text = "leet code", brokenLetters = "lt"
// Output: 1
// Explanation: We cannot type "leet" because the 'l' and 't' keys are broken.
// Example 3:

// Input: text = "leet code", brokenLetters = "e"
// Output: 0
// Explanation: We cannot type either word because the 'e' key is broken.
 

// Constraints:

// 1 <= text.length <= 104
// 0 <= brokenLetters.length <= 26
// text consists of words separated by a single space without any leading or trailing spaces.
// Each word only consists of lowercase English letters.
// brokenLetters consists of distinct lowercase English letters.

//Approach - simple iteration
//T.C : O(m+n), m = length of text, n = length of brokenLetters
//S.C : O(1)
class Solution {
    public int canBeTypedWords(String text, String brokenLetters) {
        // Step 1: Create a boolean array to mark broken letters
        boolean[] broken = new boolean[26];
        for (char ch : brokenLetters.toCharArray()) {
            broken[ch - 'a'] = true;
        }

        int result = 0;
        boolean foundBroken = false;

        // Step 2: Iterate through text character by character
        for (int i = 0; i < text.length(); i++) {
            char ch = text.charAt(i);

            if (ch == ' ') { // End of a word
                if (!foundBroken) {
                    result++;
                }
                foundBroken = false; // reset for next word
            } else if (broken[ch - 'a']) {
                foundBroken = true; // found a broken letter in this word
            }
        }

        // Step 3: Check last word (no space after last word)
        if (!foundBroken) {
            result++;
        }

        return result;
    }
}