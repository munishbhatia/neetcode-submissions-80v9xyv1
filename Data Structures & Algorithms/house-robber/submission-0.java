class Solution {
    public int rob(int[] nums) {
        final int len = nums.length;
        if(len == 0) return nums[0];

        int nMinus1 = 0;
        int nMinus2 = 0;
        int curr = 0;

        for(int i=0; i<len; ++i) {
            curr = Math.max(nums[i] + nMinus2, nMinus1);
            nMinus2 = nMinus1;
            nMinus1 = curr;
        }

        return curr;
    }
}
