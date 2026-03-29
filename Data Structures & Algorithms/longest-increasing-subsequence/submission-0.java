class Solution {
    public int lengthOfLIS(int[] nums) {
        if(nums == null) return 0;

        final int len = nums.length;
        final int[] dp = new int[len];

        int lis = Integer.MIN_VALUE;

        for(int i=len-1; i>=0; --i) {
            dp[i] = 1; //Initialize the chain to itself
            for(int j=i+1; j<len; ++j) {
                if(nums[j] > nums[i]) {
                    dp[i] = Math.max(dp[i], dp[j] + 1);
                }
            }
            lis = Math.max(lis, dp[i]);
        }

        return lis;
    }
}
