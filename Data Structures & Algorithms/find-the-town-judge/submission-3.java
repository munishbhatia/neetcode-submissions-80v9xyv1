class Solution {
    public int findJudge(int n, int[][] trust) {
        if(trust == null || trust.length == 0) return -1;

        //edgeDegree = inDegree[i] - outDegree[i] for node i
        final int[] edgeDegree = new int[n + 1]; //n+1 because people are labelled 1 to n

        for(int[] edge : trust) {
            edgeDegree[edge[0]] --;
            edgeDegree[edge[1]] ++;
        }

        return IntStream.range(1, n+1).filter(i -> edgeDegree[i] == (n-1)).findFirst().orElse(-1);
    }
}