class Solution {
    public List<List<Integer>> subsetsWithDup(int[] nums) {
        if(nums == null || nums.length == 0) return null;

        Arrays.sort(nums);
        final List<List<Integer>> result = new ArrayList<>();
        subsetsWithDupHelper(nums, 0, new ArrayList<>(), result);

        return result;
    }

    private void subsetsWithDupHelper(int[] nums, int startIndex, List<Integer> curr, List<List<Integer>> result) {
        if(startIndex > nums.length) return;

        result.add(new ArrayList<>(curr));

        for(int i=startIndex; i<nums.length; ) {
            curr.add(nums[i]); //Include nums[i] in the subset
            subsetsWithDupHelper(nums, i+1, curr, result); //Recurse
            curr.remove(curr.size() - 1); //Backtrack

            do{
                ++i;
            } while(i<nums.length && nums[i] == nums[i-1]); //Skip duplicate elements
        }
    }
}
