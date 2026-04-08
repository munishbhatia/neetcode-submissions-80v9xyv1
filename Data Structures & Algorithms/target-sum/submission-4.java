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
        int[] dp = new int[2*sum+1];

        dp[sum - nums[len - 1]]++;
        dp[sum + nums[len - 1]]++;

        // System.out.println(len - 1 + " : " + Arrays.toString(dp));

        for(int i = len - 2; i >= 0; --i) {
            int[] temp = new int[2*sum+1];

            for(int j = 0; j <= 2*sum; ++j) {
                if(dp[j] > 0) {
                    temp[j - nums[i]]+= dp[j];
                    temp[j + nums[i]]+= dp[j];
                }
            }

            dp = temp;
            // System.out.println(i + " : " + Arrays.toString(dp));
        }

        return target + sum >= dp.length ? 0 : dp[target + sum];
    }
}
