class Solution {
    public boolean canJump(int[] nums) {
        if(nums == null) return false;

        final int len = nums.length;
        
        if(len == 0) return false;

        int goalIndex = len-1;

        for(int i=len-2; i>=0; --i) {
            if(i + nums[i] >= goalIndex) { //We can jump to or past the goalIndex from this point/index
                goalIndex = i; //This becomes our new goal. We just have to reach this point from the starting and we know we will be able to reach the end since there exists a path from this index to the end (directly or through other intermediate goal indexes)
            }
        }

        return goalIndex == 0;
    }
}
