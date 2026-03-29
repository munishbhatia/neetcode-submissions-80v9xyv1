class Solution {
    public List<List<Integer>> combine(int n, int k) {
        if(n <= 0) return null;

        final List<List<Integer>> solution = new ArrayList<>();

        for(int i=1; i<=(n-k)+1; ++i) {
            final List<Integer> curr = new ArrayList<>();
            curr.add(i);
            combineHelper(n, i+1, k, curr, solution);
        }

        return solution;
    }

    private void combineHelper(int n, int startIdx, int k, List<Integer> curr, List<List<Integer>> solution) {
        if(curr.size() == k) {
            solution.add(new ArrayList<>(curr));
            return;
        }

        for(int i=startIdx; i<=n; ++i) {
            curr.add(i); //Add curr number to solution
            combineHelper(n, i+1, k, curr, solution); //Recurse
            curr.remove(curr.size() - 1);
        }
    }
}

//BAD
// if(startIdx > n) {
//     System.out.println("Returning; startIdx: " + startIdx + "; n: " + n);
//     return;
// }