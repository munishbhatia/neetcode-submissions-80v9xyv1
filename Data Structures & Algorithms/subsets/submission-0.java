class Solution {
    public List<List<Integer>> subsets(int[] nums) {
        final List<List<Integer>> response = new ArrayList<>();
        subsetsHelper(nums, 0, new ArrayList<>(), response);
        return response;
    }

    private void subsetsHelper(int[] nums, int startIdx, List<Integer> result, List<List<Integer>> response) {
        if(startIdx > nums.length) return;

        response.add(new ArrayList<>(result));
        for(int i=startIdx; i<nums.length; ++i) {
            result.add(nums[i]);
            subsetsHelper(nums, i+1, result, response);
            result.remove(result.size() - 1); //Backtrack
        }
    }
}
