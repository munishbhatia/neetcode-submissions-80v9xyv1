class Solution {
    public int searchInsert(int[] nums, int target) {
        if(nums == null) throw new IllegalArgumentException("Null array provided");

        int left = 0;
        int right = nums.length-1; //CAREFUL HERE. I initialized to nums.length by mistake and tripped on ArrayIndexOutOfBounds exception

        while (left <= right) {
            int mid = left + (right-left)/2;

            if(nums[mid] == target) return mid;
            if(nums[mid] < target) {
                left = mid+1;
            } else { //nums[mid] > target
                right = mid-1;
            }
        }

        //If we reach here, target does not exist in the array
        return left;
    }
}