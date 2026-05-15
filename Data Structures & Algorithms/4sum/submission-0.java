class Solution {
    public List<List<Integer>> fourSum(int[] nums, int target) {
        if(nums == null) return new ArrayList<>();

        final int len = nums.length;
        if(len < 4) return new ArrayList<>();

        Arrays.sort(nums);

        final List<List<Integer>> response = new LinkedList<>();
        for(int i = 0; i < len-3; ) {
            if(nums[i] > target) break;
            for(int j = i+1; j < len-2; ) {
                if(nums[i] + nums[j] > target) break;
                
                int requiredSum = target - (nums[i] + nums[j]);
                int left = j+1;
                int right = len-1;

                while(left < right) {
                    int sum = nums[left] + nums[right];
                    
                    if(sum == requiredSum) {
                        response.add(List.of(nums[i], nums[j], nums[left], nums[right]));
                        ++left; --right;
                        while(left < right && nums[left] == nums[left-1]) ++left;
                        while(right > left && nums[right] == nums[right+1]) --right;
                    } else if (sum < requiredSum) {
                        ++left;
                        while(left < right && nums[left] == nums[left-1]) ++left;
                    } else {
                        --right;
                        while(right > left && nums[right] == nums[right+1]) --right;
                    }
                }

                ++j;
                while(j < len-2 && nums[j] == nums[j-1]) ++j;
            }
            ++i;
            while(i < len-3 && nums[i] == nums[i-1]) ++i;
        }

        return response;
    }
}