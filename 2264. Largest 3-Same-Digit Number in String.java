// You are given a string num representing a large integer. An integer is good if it meets the following conditions:

// It is a substring of num with length 3.
// It consists of only one unique digit.
// Return the maximum good integer as a string or an empty string "" if no such integer exists.

// Note:

// A substring is a contiguous sequence of characters within a string.
// There may be leading zeroes in num or a good integer.
 

// Example 1:

// Input: num = "6777133339"
// Output: "777"
// Explanation: There are two distinct good integers: "777" and "333".
// "777" is the largest, so we return "777".
// Example 2:

// Input: num = "2300019"
// Output: "000"
// Explanation: "000" is the only good integer.
// Example 3:

// Input: num = "42352338"
// Output: ""
// Explanation: No substring of length 3 consists of only one unique digit. Therefore, there are no good integers.
 

// Constraints:

// 3 <= num.length <= 1000
// num only consists of digits.


// class Solution {
//     
// }


class Solution {
    public String largestGoodInteger(String num) {
        int i=0;
        String max="";
        while(i<num.length())
        {
            if(i+1<num.length() && num.charAt(i)==num.charAt(i+1) && i+2 < num.length() && num.charAt(i)==num.charAt(i+2))
                if(max.compareTo(num.substring(i,i+3))<0)
                {
                    max=num.substring(i,i+3);
                }
            i++;
        }
       return max;
    }

    public String largestGoodInteger1(String num) {

        int n = num.length();
        String maxSeq = "";
        int count = 1;

        for(int i = 1; i < n; i++) {
            if(num.charAt(i)==num.charAt(i-1) && Character.isDigit(num.charAt(i))){
                count++;

                //got continue sequence of three
                if(count==3){
                    String currSeq = "" + num.charAt(i) + num.charAt(i) + num.charAt(i);

                    if(maxSeq.isEmpty() || currSeq.compareTo(maxSeq) > 0 )
                    maxSeq = currSeq;
                }
            } else{
                //reset the counter
                count = 1;
            }
        }

        return maxSeq;
    }
}