class Solution {
    public int minSubArrayLen(int target, int[] nums) {
        if (nums == null) {
            if(target == 0) return 0;
            if(target < 0 || target > 0) return -1; //Not possible
        }

        int minWindow = Integer.MAX_VALUE;
        long windowSum = 0;
        int left = 0;

        for(int right = 0; right < nums.length; ++right) {
            windowSum += nums[right];
            while(left<=right && windowSum >= target) {
                minWindow = Math.min(minWindow, (right - left +1));

                //squeeze window
                windowSum -= nums[left];
                ++left;
            }
        }

        return (minWindow == Integer.MAX_VALUE) ? 0 : minWindow;
    }
}