class Solution {
    public List<List<Integer>> combinationSum2(int[] candidates, int target) {
        if(candidates == null || candidates.length == 0) return null;

        final List<List<Integer>> result = new ArrayList<>();
        int startIndex = 0;

        Arrays.sort(candidates);
        combinationSum2Helper(candidates, startIndex, target, new ArrayList<>(), result);

        return result;
    }

    private void combinationSum2Helper(int[] candidates, int startIndex, int target, List<Integer> curr, List<List<Integer>> result) {
        if(startIndex > candidates.length || target < 0) return; //Preempt recursion chain

        if(target == 0) {
            result.add(new ArrayList<>(curr));
            return;
        }

        for(int i=startIndex; i<candidates.length; ) {
            curr.add(candidates[i]); //Add candidates[i] to solution
            combinationSum2Helper(candidates, i+1, target-candidates[i], curr, result); //Recurse

            curr.remove(curr.size() - 1); //Backtrack to explore other solutions where candidates[i] is not part of the solution

            do {
                ++i;
            } while(i<candidates.length && candidates[i] == candidates[i-1]); //Skip the same numbers to exclude duplicate solutions
        }
    }
}
