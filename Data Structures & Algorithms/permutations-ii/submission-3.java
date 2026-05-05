class Solution {
    public List<List<Integer>> permuteUnique(int[] nums) {
        if(nums == null) return new ArrayList<>();

        final List<List<Integer>> permutations = new LinkedList<>();
        permutate(nums, new LinkedList<>(), permutations, 0);

        return permutations;
    }

    private void permutate(int[] nums, List<Integer> curr, List<List<Integer>> permutations, int currIdx) {
        if(currIdx >= nums.length) {
            permutations.add(new ArrayList<>(curr));
            return;
        }

        final Set<Integer> usedAtHead = new HashSet<>();

        for(int i = currIdx; i < nums.length; ++i) {
            if(usedAtHead.contains(nums[i])) continue;

            //swap nums[i], nums[currIdx]
            int temp = nums[i];
            nums[i] = nums[currIdx];
            nums[currIdx] = temp;

            //Recurse
            curr.addLast(nums[currIdx]);
            usedAtHead.add(nums[currIdx]);
            permutate(nums, curr, permutations, currIdx + 1);

            //Backtrack
            curr.removeLast();
                //Swap back nums[i], nums[currIdx]
            temp = nums[i];
            nums[i] = nums[currIdx];
            nums[currIdx] = temp;
        }
    }
}