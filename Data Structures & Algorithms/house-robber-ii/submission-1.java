class Solution {
    public int rob(int[] nums) {
        if(nums == null) return 0;

        final int len = nums.length;

        if(len == 1) return nums[0]; //THIS IS IMPORTANT EDGE CONDITION THAT I MISSED. This will get excluded in both the loops below.

        int maxWithoutLast = 0;
        int maxWithoutFirst = 0;
        
        int maxMinus1 = 0;
        int maxMinus2 = 0;
        int curr = 0;
        for(int i=0; i<len-1; ++i) {
            curr = Math.max(nums[i] + maxMinus2, maxMinus1);
            maxMinus2 = maxMinus1;
            maxMinus1 = curr;
        }
        maxWithoutLast = curr;

        //Reset these variables before reuse in next iteration
        maxMinus1 = 0;
        maxMinus2 = 0;
        for(int i=1; i<len; ++i) {
            curr = Math.max(nums[i] + maxMinus2, maxMinus1);
            maxMinus2 = maxMinus1;
            maxMinus1 = curr;
        }
        maxWithoutFirst = curr;

        return Math.max(maxWithoutFirst, maxWithoutLast);
    }
}
