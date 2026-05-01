class Solution {
    // public int subarraySum(int[] nums, int k) {
    //     if(nums == null) return 0;

    //     final int len = nums.length;
    //     if(len == 0) return 0;

    //     int result = 0;
    //     int left = 0;
    //     int right = 0;
    //     int currSum = 0;

    //     while(left <= right && right < len) {
    //         if(currSum <= k) {
    //             currSum += nums[right++];
    //             result += (currSum == k) ? 1 : 0;
    //             if(currSum == k) {
    //                 System.out.println("Left: " + left + "; Right: " + right);
    //             }
    //             continue;
    //         }

    //         if(currSum > k) { //This is skipping potential windows where negative numbers ahead will balance out the extra sum
    //             currSum -= nums[left++];
    //             result += (currSum == k) ? 1 : 0;
    //             if(currSum == k) {
    //                 System.out.println("Left: " + left + "; Right: " + right);
    //             }
    //             continue;
    //         }
    //     }

    //     //handle last window
    //     while(left < (right-1)) { //not left <= right because right would be at len now; using <= would lead to ArrayIndexOutOfBoundsException
    //         currSum -= nums[left++];
    //         result += (currSum == k && left < right) ? 1 : 0;
    //         // if(currSum == k && left < right) {
    //         //     System.out.println("Left: " + left + "; Right: " + right);
    //         // }
    //     }

    //     return result;
    // }

    public int subarraySum(int[] nums, int k) {
        if(nums == null) return 0;

        final int len = nums.length;
        if(len == 0) return 0;
        
        int result = 0;
        int left = 0;
        int right = 0;
        int currSum = 0;

        for(left = 0; left < len; ++left) {
            currSum = 0;
            for(right = left; right < len; ++right) {
                currSum += nums[right];
                result += (currSum == k) ? 1 : 0;
            }
        }

        return result;
    }
}