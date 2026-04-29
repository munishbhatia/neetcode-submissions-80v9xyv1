class Solution {
    public int networkDelayTime(int[][] times, int n, int k) {
        final int[][] graph = prepareGraph(times, n+1);
        floydWarshall(graph);

        final int maxTime = Arrays.stream(graph[k]).skip(1).max().getAsInt();

        return (maxTime == Integer.MAX_VALUE) ? -1 : maxTime;
    }

    private int[][] prepareGraph(int[][] edges, int nodes) {
        final int[][] graph = new int[nodes][nodes];

        for(int[] row : graph) {
            Arrays.fill(row, Integer.MAX_VALUE);
        }

        for(int i = 0; i < nodes; ++i) {
            graph[i][i] = 0;
        }

        for(int[] edge : edges) {
            graph[edge[0]][edge[1]] = edge[2];
        }

        return graph;
    }

    private void floydWarshall(int[][] graph) {
        final int n = graph.length;

        for(int i = 1; i < n; ++i) {
            for(int j = 1; j < n; ++j) {
                for(int k = 1; k < n; ++k) {
                    graph[j][k] = Math.min(graph[j][k], getPathWeight(graph[j][i], graph[i][k]));
                }
            }
        }
    }

    private int getPathWeight(int weight1, int weight2) {
        return (weight1 == Integer.MAX_VALUE || weight2 == Integer.MAX_VALUE) ? Integer.MAX_VALUE : (weight1 + weight2);
    }
}
