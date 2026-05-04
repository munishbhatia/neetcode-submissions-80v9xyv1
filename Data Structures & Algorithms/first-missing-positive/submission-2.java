class Solution {
    public int firstMissingPositive(int[] nums) {
        if(nums == null) return 1;

        final int len = nums.length;
        if(len == 0) return 1;

        for(int i = 0; i < len; ++i) {
            while(Math.abs(nums[i]) != i) {
                int prev = Math.abs(nums[i]);
                int swapIdx = Math.min(prev, len-1);

                if(nums[swapIdx] == swapIdx) break;

                // System.out.println("Swapping " + nums[i] + " to index " + swapIdx);

                swap(nums, i, swapIdx);

                // System.out.println("After Sort: " + Arrays.toString(nums));
                if(nums[i] == prev || Math.abs(nums[i]) >= len) break; //We don't want an infinite loop here
            }
        }

        // System.out.println("Cycle Sorted: " + Arrays.toString(nums));

        int i = 1;
        for(; i < len; ++i) {
            if(nums[i] != i && nums[0] != i) return i;
        }

        return i == nums[0] ? i+1 : i;
    }

    private void swap(int[] nums, int idx1, int idx2) {
        int temp = nums[idx1];
        nums[idx1] = nums[idx2];
        nums[idx2] = temp;
    }
}