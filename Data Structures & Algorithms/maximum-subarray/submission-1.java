class Solution {
    public int maxSubArray(int[] nums) {
        if(nums == null) return 0;

        final int len = nums.length;
        int currSum = nums[0];
        int maxSum = nums[0];

        for(int right = 1; right < len; ++right) {
            if(currSum + nums[right] < 0) {
                currSum = nums[right];
            } else {
                currSum += nums[right];
            }

            maxSum = Math.max(maxSum, currSum);
            if(currSum < 0) currSum = 0;
        }

        return maxSum;
    }
}
