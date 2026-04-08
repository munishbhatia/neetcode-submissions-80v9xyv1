class Solution {
    // private int result = 0;

    // public int findTargetSumWays(int[] nums, int target) {
    //     if(nums == null || nums.length == 0) return 0;

    //     dfs(nums, target, 0, 0);

    //     return result;
    // }

    // private void dfs(int[] nums, int target, int startIndex, int currAmt) {
    //     if(startIndex > nums.length) return;

    //     if(startIndex == nums.length) {
    //         if(currAmt == target) ++result;
    //         return;
    //     }

    //     dfs(nums, target, startIndex + 1, currAmt + nums[startIndex]);
    //     dfs(nums, target, startIndex + 1, currAmt - nums[startIndex]);
    // }

    public int findTargetSumWays(int[] nums, int target) {
        if(nums == null || nums.length == 0) return 0;

        final int len = nums.length;
        final int sum = Arrays.stream(nums).sum();
        final int[][] dp = new int[len][2*sum+1];

        dp[len - 1][sum - nums[len - 1]]++;
        dp[len - 1][sum + nums[len - 1]]++;

        // System.out.println(len - 1 + " : " + Arrays.toString(dp[len - 1]));

        for(int i = len - 2; i >= 0; --i) {
            for(int j = 0; j <= 2*sum; ++j) {
                if(dp[i + 1][j] > 0) {
                    dp[i][j - nums[i]]+= dp[i+1][j];
                    dp[i][j + nums[i]]+= dp[i+1][j];
                }
            }
            // System.out.println(i + " : " + Arrays.toString(dp[i]));
        }

        return target + sum >= dp[0].length ? 0 : dp[0][target + sum];
    }
}
