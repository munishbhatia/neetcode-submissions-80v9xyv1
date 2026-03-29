class Solution {
    public List<List<Integer>> permute(int[] nums) {
        if(nums == null || nums.length == 0) return null;

        final List<List<Integer>> result = new ArrayList<>();
        permuteHelper(nums, 0, new ArrayList<>(), result);

        return result;
    }

    private void permuteHelper(int[] nums, int startIndex, List<Integer> curr, List<List<Integer>> result) {
        if(startIndex > nums.length) return; //This should never happen but being defensive/cautious here

        if(startIndex == nums.length) {
            //Add to solution
            result.add(new ArrayList<>(curr));
            return;
        }

        for(int i=startIndex; i<nums.length; ++i) {
            swap(nums, startIndex, i); //Swap elements at index startIndex and 'i' to put the i-th element at the front of the subsolution
            curr.add(nums[startIndex]); //Put the num at startIndex at the head of the solution
            permuteHelper(nums, startIndex+1, curr, result); //Recurse

            //Backtrack to find other solutions
            curr.remove(curr.size() - 1);
            swap(nums, startIndex, i);
        }
    }

    private void swap(int[] nums, int i, int j) {
        if(i < 0 || j < 0 || i >= nums.length || j >= nums.length) throw new ArrayIndexOutOfBoundsException(String.format("i: %d, j: %d", i, j));

        int temp = nums[i];
        nums[i] = nums[j];
        nums[j] = temp;
    }
}
