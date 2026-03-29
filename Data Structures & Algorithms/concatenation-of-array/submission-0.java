class Solution {
    public int[] getConcatenation(int[] nums) {
        if(nums == null) return nums;

        final int len = nums.length;

        final int[] ans = new int[2*len];

        for(int i=0; i<len; ++i) {
            ans[i] = ans[len + i] = nums[i];
        }

        return ans;
    }
}