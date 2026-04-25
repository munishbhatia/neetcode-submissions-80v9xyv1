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

        final int[] rowMax = new int[n];
        floydWarshall(graphMatrix, rowMax);

        for(int i = 0; i < n; ++i) {
            for(int j = 0; j < n; ++j) {
                if(i == j) continue;
                rowMax[i] = Math.max(rowMax[i], graphMatrix[i][j]);
            }
        }

        // for(int[] row : graphMatrix) {
        //     System.out.println(Arrays.toString(row));
        // }

        // System.out.println("RowMax: " + Arrays.toString(rowMax));

        // System.out.println("MinDist: " + Arrays.stream(rowMax).min().getAsInt());

        final int minDist = Arrays.stream(rowMax).min().getAsInt();

        final List<Integer> response = new ArrayList<>();
        for(int i = 0; i < n; ++i) {
            if(rowMax[i] == minDist) {
                response.add(i);
            }
        }

        return response;
    }

    private void floydWarshall(int[][] graphMatrix, int[] rowMax) {
        final int len = graphMatrix.length;

        for(int i = 0; i < len; ++i) {
            for(int j = 0; j < len; ++j) {
                for(int k = 0; k < len; ++k) {
                    if(j == k) continue;
                    graphMatrix[j][k] = graphMatrix[k][j] = Math.min(graphMatrix[j][k], graphMatrix[j][i] + graphMatrix[i][k]);
                }
            }
            // rowMax[i] = Arrays.stream(graphMatrix[i]).max().getAsInt();
        }
    }
}