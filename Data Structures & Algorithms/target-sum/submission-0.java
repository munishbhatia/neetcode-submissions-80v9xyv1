class Solution {
    private int result = 0;

    public int findTargetSumWays(int[] nums, int target) {
        if(nums == null || nums.length == 0) return 0;

        dfs(nums, target, 0, 0);

        return result;
    }

    private void dfs(int[] nums, int target, int startIndex, int currAmt) {
        if(startIndex > nums.length) return;

        if(startIndex == nums.length) {
            if(currAmt == target) ++result;
            return;
        }

        dfs(nums, target, startIndex + 1, currAmt + nums[startIndex]);
        dfs(nums, target, startIndex +1, currAmt - nums[startIndex]);
    }
}
