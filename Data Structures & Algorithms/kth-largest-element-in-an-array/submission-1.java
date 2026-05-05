class Solution {
    public int findKthLargest(int[] nums, int k) {
        if(nums == null) return 0;

        final int len = nums.length;
        if(k > len) return 0; //Or throw

        return quickselect(nums, 0, len-1, k);

        // return 4;
    }

    private int quickselect(int[] nums, int start, int end, int k) {
        //Throw if k > (end - start + 1)
        if(start == end) return nums[start];

        final int mid = (start + (end-start)/2);
        final int pivot = nums[mid];

        // System.out.println(String.format("Start: %d; End: %d; Mid: %d; Pivot: %d", start, end, mid, pivot));

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

        // System.out.println(String.format("Left: %d; Right: %d; Size: %d; k: %d; Arr: %s", left, right, (end - left + 1), k, Arrays.toString(nums)));
        if(end - left + 1 >= k) return quickselect(nums, left, end, k);
        //else
        return quickselect(nums, start, left - 1, k - (end - left + 1));

        // return 1;
    }
}
