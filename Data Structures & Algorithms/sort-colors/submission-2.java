class Solution {
    public void sortColors(int[] nums) {
        if(nums == null || nums.length <= 1) return;

        sortHelper(nums);
    }

    private void sortHelper(int[] nums) {
        final int len = nums.length;

        int left = 0;
        int right = len - 1;
        int i = 0;

        while(i <= right) {
            if(nums[i] == 0) {
                swap(nums, i, left);
                ++left;
                ++i;
            } else if(nums[i] == 2) {
                swap(nums, i, right);
                --right;
            } else { //nums[i] == 1
                ++i;
            }
        }
    }

    private void swap(int[] nums, int ix1, int ix2) {
        if(ix1 < 0 || ix2 < 0 || ix1 >= nums.length || ix2 >= nums.length) throw new IllegalArgumentException();

        int temp = nums[ix1];
        nums[ix1] = nums[ix2];
        nums[ix2] = temp;
    }
}