class Solution {
    public int findJudge(int n, int[][] trust) {
        if(trust == null || trust.length == 0) return -1;

        final int[] indegree = new int[n + 1]; //n+1 because people are labelled 1 to n
        final int[] outdegree = new int[n + 1];

        for(int[] edge : trust) {
            outdegree[edge[0]] ++;
            indegree[edge[1]] ++;
        }

        for(int i = 1; i <= n; ++i) {
            if(outdegree[i] == 0 && indegree[i] == (n-1)) return i;
        }

        return -1;
    }
}