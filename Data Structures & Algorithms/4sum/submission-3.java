class Solution {
    public List<List<Integer>> fourSum(int[] nums, int target) {
        if(nums == null) return new ArrayList<>();

        final int len = nums.length;
        if(len < 4) return new ArrayList<>();

        Arrays.sort(nums);

        final List<List<Integer>> response = new LinkedList<>();
        fourSumRecursive(nums, 0, target, 4, new LinkedList<>(), response);

        return response;
    }

    private void fourSumRecursive(int[] nums, int idx, int target, int k, List<Integer> curr, List<List<Integer>> response) {
        if(idx >= nums.length) return;
        if(k > (nums.length - idx + 1)) return;
        if(k <= 1) return;

        if(k > 2) {
            for(int i = idx; i <= nums.length - k; ) {
                if(nums[i] > target) break;

                curr.addLast(nums[i]);
                fourSumRecursive(nums, i+1, target-nums[i], k-1, curr, response);
                curr.removeLast();

                ++i;
                while(i <= nums.length - k && nums[i] == nums[i-1]) ++i;
            }
        }

        if(k == 2) {
            int left = idx;
            int right = nums.length - 1;

            while(left < right) {
                int sum = nums[left] + nums[right];

                if(sum == target) {
                    curr.add(nums[left]);
                    curr.add(nums[right]);
                    response.add(new ArrayList<>(curr));

                    curr.removeLast();
                    curr.removeLast();

                    ++left;
                    --right;

                    while(left < right && nums[left] == nums[left - 1]) ++left;
                    while(right > left && nums[right] == nums[right + 1]) --right;
                } else if(sum < target) {
                    ++left;
                    while(left < right && nums[left] == nums[left - 1]) ++left;
                } else {
                    --right;
                    while(right > left && nums[right] == nums[right + 1]) --right;
                }
            }
        }
    }
}