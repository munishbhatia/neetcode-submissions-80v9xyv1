class Solution {
    public int firstMissingPositive(int[] nums) {
        if(nums == null) return 1;

        final int len = nums.length;
        // final int MAX = len + 1; //An array of length n can contain at most numbers 1..n as the positive numbers

        if(len == 0) return 1;

        for(int i = 0; i < len; ++i) {
            if(nums[i] <= 0) {
                // nums[i] = MAX; //perhaps we can leave it as is? -- we can but then we should handle negative numbers in the cycle sort step below
                continue;
            }

            while(nums[i] != i && nums[i] >= 0 && nums[i] < len) {
                if(nums[i] == nums[nums[i]]) break; //this will otherwise create an infinite loop
                
                int temp = nums[nums[i]]; //cycle sort
                nums[nums[i]] = nums[i];
                nums[i] = temp;
            }
        }

        for(int i = 1; i < len; ++i) {
            if(nums[i] != i) return i;
        }

        return nums[0] == nums.length ? nums.length + 1 : nums.length;
    }
}