class Solution {
    public void sortColors(int[] nums) {
        if(nums == null || nums.length <= 1) return;

        int nextStart = sortHelper(nums, 0, 0, 1);
        sortHelper(nums, nextStart, 1, 2);
    }

    private int sortHelper(int[] nums, int start, int n1, int n2) {
        final int len = nums.length;

        int left = start;
        int right = start;

        while(left < len && right < len) {
            while(left < len && nums[left] == n1) ++left;

            if(left == len) break; //no n2 in the input

            right = left + 1;
            while(right < len && nums[right] != n1) ++right; //we're finding the next n1 to swap with

            if(right == len) {
                //swap left & right and finish - only 1 N2 was remaining that is at the left, swap it with the last index
                // swap(nums, left, len-1);
                break;
            } else {
                swap(nums, left, right);
                ++left;
                ++right;
            }
        }

        return left;
    }

    private void swap(int[] nums, int ix1, int ix2) {
        if(ix1 < 0 || ix2 < 0 || ix1 >= nums.length || ix2 >= nums.length) throw new IllegalArgumentException();

        int temp = nums[ix1];
        nums[ix1] = nums[ix2];
        nums[ix2] = temp;
    }
}