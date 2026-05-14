class Solution {
    public List<List<Integer>> threeSum(int[] nums) {
        if(nums == null) return new ArrayList<>();

        final int len = nums.length;
        if(len == 0) return new ArrayList<>();

        Arrays.sort(nums);
        
        final List<List<Integer>> response = new ArrayList<>();
        for(int i = 0; i < len-2; ) {
            int left = i+1;
            int right = len-1;
            int requiredSum = 0 - nums[i];

            while(left < right) {
                int sum = nums[left] + nums[right];

                if(sum == requiredSum) {
                    response.add(List.of(nums[i], nums[left++], nums[right--]));
                    while(left < right && nums[left] == nums[left-1]) ++left;
                    while(right > left && right < len-1 && nums[right] == nums[right+1]) --right;
                } else if (sum < requiredSum) {
                    ++left;
                    while(left < right && nums[left] == nums[left-1]) ++left;
                } else {
                    --right;
                    while(right > left && right < len-1 && nums[right] == nums[right+1]) --right;
                }
            }

            ++i;
            while(i < len-2 && nums[i] == nums[i-1]) ++i;
        }

        return response;
    }
}
