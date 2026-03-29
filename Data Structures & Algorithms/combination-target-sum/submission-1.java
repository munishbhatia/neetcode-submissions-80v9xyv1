class Solution {
    public List<List<Integer>> combinationSum(int[] nums, int target) {
        final List<List<Integer>> result = new ArrayList<>();
        combinationSumHelper(nums, 0, target, new ArrayList<Integer>(), result);
        return result;
    }

    private void combinationSumHelper(int[]nums, int startIdx, int target, List<Integer> runningSeq, List<List<Integer>> result) {
        if(startIdx > nums.length) return;
        if(target < 0) return;

        if(target == 0) {
            result.add(new ArrayList<>(runningSeq));
            return;
        }

        for(int i=startIdx; i<nums.length; ++i) {
            //Build and recurse
            runningSeq.add(nums[i]);
            combinationSumHelper(nums, i, target-nums[i], runningSeq, result);

            //Backtrack for next iteration
            runningSeq.remove(runningSeq.size() - 1);
        }
    }
}
