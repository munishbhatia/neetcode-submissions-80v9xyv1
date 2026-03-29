class Solution {
    public boolean canJump(int[] nums) {
        if(nums == null) return false;

        final int len = nums.length;
        
        if(len == 0) return false;

        final boolean[] canReachTop = new boolean[len];
        canReachTop[len-1] = true;

        for(int i=len-2; i>=0; --i) {
            for(int jumps=1; jumps <= nums[i] && i+jumps < len; ++jumps) {
                if(canReachTop[i+jumps]) {
                    canReachTop[i] = true;
                    break; //We know we can reach top from here; preempt
                }
            }
        }

        return canReachTop[0];
    }
}
