class Solution {
    public int findKthLargest(int[] nums, int k) {
        if(nums == null) return 0;

        final int len = nums.length;
        if(k > len) return 0; //Or throw

        return quickselect(nums, 0, len-1, k);
    }

    private int quickselect(int[] nums, int start, int end, int k) {
        //Throw if k > (end - start + 1)
        if(start == end) return nums[start];

        final int mid = (start + (end-start)/2);
        final int pivot = nums[mid];

        int left = start, right = end;
        while(left <= right) {
            if(nums[left] < pivot) {
                ++left;
                continue;
            }

            if(nums[right] > pivot) {
                --right;
                continue;
            }

            //swap nums[left], nums[right]
            int temp = nums[left];
            nums[left] = nums[right];
            nums[right] = temp;

            //Move pointers
            ++left;
            --right;
        }

        if(end - left + 1 >= k)
            return quickselect(nums, left, end, k);
        return quickselect(nums, start, left - 1, k - (end - left + 1));
    }
}
