
class July30{
    public static void main(String[] args) {
        
    }

    public int longestSubarray(int[] nums) {
        int n = nums.length;
        int maxAndValue = 0;
        int len = 0;
        int maxLength = 0;

        for(int i=0;i<n;i++) {
            //if i got a max element than no point to keep previous values
            if(nums[i]>maxAndValue) {
            maxAndValue = nums[i];
            len = 0 ;
            maxLength = 0;
            }

            if(nums[i]==maxAndValue){
                len++;
            }else{
                len=0;
            }

            maxLength = Math.max(len,maxLength);
        }

        return maxLength;
    }
}