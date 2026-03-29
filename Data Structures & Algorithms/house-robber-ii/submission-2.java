class Solution {
    public int rob(int[] nums) {
        if(nums == null) return 0;

        final int len = nums.length;

        if(len == 1) return nums[0]; //THIS IS IMPORTANT EDGE CONDITION THAT I MISSED. This will get excluded in both the loops below.

        int maxWithoutLast = robberyOptimizationHelper(nums, 0, len-1);
        int maxWithoutFirst = robberyOptimizationHelper(nums, 1, len);

        return Math.max(maxWithoutFirst, maxWithoutLast);
    }

    public int robberyOptimizationHelper(int[] nums, int start, int end) {
        int maxMinus1 = 0;
        int maxMinus2 = 0;
        int curr = 0;
        for(int i=start; i<end; ++i) {
            curr = Math.max(nums[i] + maxMinus2, maxMinus1);
            maxMinus2 = maxMinus1;
            maxMinus1 = curr;
        }
        return curr;
    }
}
