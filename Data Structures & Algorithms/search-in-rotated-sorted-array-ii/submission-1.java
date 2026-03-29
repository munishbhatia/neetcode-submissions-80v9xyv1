class Solution {
    public boolean search(int[] nums, int target) {
        if(nums == null) return false;

        final int len = nums.length;
        if(len == 0) return false;

        return binarySearch(nums, 0, len-1, target);

        // while (left <= right) {
        //     int mid = left + (right - left)/2;

        //     if(nums[mid] == target) return true;

        //     if(target < nums[mid]) {
        //         if(mid == left) return false;

        //         if(nums[left] <= nums[mid-1] && target >= nums[left]) right = mid - 1; //search left
        //         else if(nums[left] > nums[mid]) right = mid - 1; //All the numbers greater than nums[mid] fall to the right of nums[mid] and then some smaller numbers; Search right since target > nums[mid]
        //         else left = mid + 1;
        //     } else { //target > nums[mid]
        //         if(mid == right) return false;

        //         if(nums[right] >= nums[mid] && target <= nums[right]) left = mid + 1; //search right
        //         else if(nums[right] < nums[mid]) left = mid + 1; //All the numbers greater than nums[mid] fall to the right of nums[mid] and then some smaller numbers; Search right since target > nums[mid]
        //         else right = mid - 1; //Search left
        //     }
        // }

        // return false;
    }

    private boolean binarySearch(int[] nums, int left, int right, int target) {
        if(left > right) return false;
        
        final int mid = left + (right - left)/2;
        return (nums[mid] == target) || binarySearch(nums, left, mid-1, target) || binarySearch(nums, mid+1, right, target);
    }
}

//This condition check does not guarantee that the segment is sorted, hence the flaw:
//nums[left] <= nums[mid]; same for
//nums[right] >= nums[mid]
//Case in point: [1,0,1,1,1]; mid = 2; nums[left] <= nums[mid] -- false positive that left segment is sorted