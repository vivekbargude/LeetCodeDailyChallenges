// Given an integer n, return true if it is a power of three. Otherwise, return false.

// An integer n is a power of three, if there exists an integer x such that n == 3x.

 

// Example 1:

// Input: n = 27
// Output: true
// Explanation: 27 = 33
// Example 2:

// Input: n = 0
// Output: false
// Explanation: There is no x where 3x = 0.
// Example 3:

// Input: n = -1
// Output: false
// Explanation: There is no x where 3x = (-1).

class PowerOfThree {
    //Approach 1:
    //TC-> O(log3 n)
    //SC-> O(1)
    public boolean isPowerOfThree1(int n) {
        if(n<=0) return false;
        while(n%3==0) n/=3;
        return n==1;
    }

    //Approach 2:
    //TC-> O(log3 n)
    //SC-> O(logn 3)
    public boolean isPowerOfThree2(int n) {
        if(n<=0)
        return false;

        if(n==1)
        return true;

        if(n%3!=0)
        return false;

        return isPowerOfThree2(n/3);
    }



    //Approach 3:
    //TC-> O(1)
    //SC-> O(1)
    public boolean isPowerOfThree3(int n) {
       if(n<=0)
       return false;

       double x = Math.log10(n)/Math.log10(3);

       return x == (int) x;
    }

    // //Approach 4:
    // //TC-> O(1)
    // //SC-> O(1)
     public boolean isPowerOfThree4(int n) {
         return n>0 && 1162261467 % n==0;
     }
}
