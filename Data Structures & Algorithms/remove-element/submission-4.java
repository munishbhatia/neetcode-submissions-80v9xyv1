class Solution {
    public int removeElement(int[] nums, int val) {
        final int len = nums.length;

        if(len == 0) return 0;

        int left = 0;
        int right = len - 1;

        while(left < right) {
            // System.out.println("Left" + left);
            while(left < right && nums[left] == val) {
                while(right > left && nums[right] == val) --right;
                // System.out.println("Right" + right);
                
                //Swap
                int temp = nums[left];
                nums[left] = nums[right];
                nums[right] = temp;

                --right;
            }

            if(nums[left] == val) break;
            ++left;
        }
        
        return (nums[left] == val ? left : left + 1);
    }
}