class Solution {
    public int jump(int[] nums) {
        final int len = nums.length;
        final int[] minSteps = new int[len];
        minSteps[len-1] = 0;

        int goal = len-1;

        for(int i=len-2; i>=0; --i) {
            minSteps[i] = 500000;
            if(nums[i] + i >= goal) {
                for(int jumps = 1; jumps <= nums[i] && i+jumps < len; ++jumps) {
                    minSteps[i] = Math.min(minSteps[i], minSteps[i+jumps] + 1);
                }
                goal = i;
            }
        }

        return minSteps[0];
    }
}
