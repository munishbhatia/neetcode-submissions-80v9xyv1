class Solution {
    public boolean canPartition(int[] nums) {
        if(nums == null) return false;

        final int len = nums.length;
        if(len == 1) return false;

        final int sum = Arrays.stream(nums).sum();
        final int find = sum/2;

        if(sum%2 != 0) return false;

        Arrays.sort(nums);

        boolean[] dp = new boolean[find+1];
        dp[0] = true;

        for(int i=1; i<=find; ++i) {
            dp[i] = (nums[len-1] == i);
        }

        for(int i=len-2; i>=0; --i) {
            boolean[] curr = new boolean[find+1];
            curr[0] = true;

            for(int j=1; j<=find; ++j) {
                if(nums[i] > j) {
                    curr[j] = false;
                    continue;
                }
                curr[j] = dp[j - nums[i]] || dp[j];
            }

            dp = curr;
        }

        return dp[find];  
    }
}
