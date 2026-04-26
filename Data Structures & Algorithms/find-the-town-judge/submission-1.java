class Solution {
    public int findJudge(int n, int[][] trust) {
        if(trust == null || trust.length == 0) return -1;

        //edgeDegree = inDegree[i] - outDegree[i] for node i
        final int[] edgeDegree = new int[n + 1]; //n+1 because people are labelled 1 to n

        for(int[] edge : trust) {
            edgeDegree[edge[0]] --;
            edgeDegree[edge[1]] ++;
        }

        for(int i = 1; i <= n; ++i) {
            if(edgeDegree[i] == (n-1)) return i;
        }

        return -1;
    }
}