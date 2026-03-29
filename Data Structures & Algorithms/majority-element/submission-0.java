class Solution {
    public int majorityElement(int[] nums) {
        if(null == nums) return 0;

        final int len = nums.length;
        if(len == 1) return nums[0];

        Arrays.sort(nums);

        int maxCount = 1;
        int currCount = 1;
        int maxElem = nums[0];

        for(int i=1; i<len; ++i) {
            while(i < len && nums[i] == nums[i-1]) {
                ++currCount;
                ++i;
            }
            if(currCount > maxCount) {
                maxCount = currCount;
                maxElem = nums[i-1];
            }
            currCount = 1;
        }

        return maxElem;
    }
}