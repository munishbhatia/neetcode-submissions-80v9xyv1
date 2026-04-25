class Solution {
    public List<Integer> findMinHeightTrees(int n, int[][] edges) {
        if(n == 0 || edges == null || edges.length == 0) return List.of(0);

        final int[][] graphMatrix = new int[n][n];
        for(int[] row : graphMatrix) {
            Arrays.fill(row, 50000);
        }

        for(int[] edge : edges) {
            int u = edge[0];
            int v = edge[1];

            graphMatrix[u][v] = 1;
            graphMatrix[v][u] = 1;
        }

        floydWarshall(graphMatrix);

        final int[] rowMax = IntStream.range(0, n)
                .map(
                    r -> IntStream.range(0, n)
                    .filter(c -> r != c)
                    .map(c -> graphMatrix[r][c])
                    .max()
                    .getAsInt()
                ).toArray();

        final int minDist = Arrays.stream(rowMax).min().getAsInt();

        return IntStream.range(0, n).filter(i -> rowMax[i] == minDist).boxed().toList();
    }

    private void floydWarshall(int[][] graphMatrix) {
        final int len = graphMatrix.length;

        for(int i = 0; i < len; ++i) {
            for(int j = 0; j < len; ++j) {
                for(int k = 0; k < len; ++k) {
                    if(j == k) continue;
                    graphMatrix[j][k] = graphMatrix[k][j] = Math.min(graphMatrix[j][k], graphMatrix[j][i] + graphMatrix[i][k]);
                }
            }
        }
    }
}