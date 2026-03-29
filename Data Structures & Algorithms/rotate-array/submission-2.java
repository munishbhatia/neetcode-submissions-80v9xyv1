class Solution {
    public void rotate(int[] nums, int k) {
        if(nums == null || nums.length == 0) return;

        final int len = nums.length;
        final int[] rotatedArray = new int[len];

        int shift = k%len;
        int start = len - shift;
        for(int i=0; i<len; ++i) {
            rotatedArray[i] = nums[start%len];
            ++start;
        }

        for(int i=0; i<len; ++i) {
            nums[i] = rotatedArray[i];
        }
    }
}