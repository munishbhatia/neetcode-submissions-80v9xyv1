class Solution {
    public double[] calcEquation(List<List<String>> equations, double[] values, List<List<String>> queries) {
        if(equations == null) return new double[0];

        final int count = equations.size();
        if(count == 0 || count != values.length) return new double[0]; //Bad input actually

        // final int nodeCount = (int) equations.stream().flatMap(List::stream).distinct().count();
        final Map<String, Integer> nodeMap = new HashMap<>();
        int counter = 0;
        
        for(List<String> equation : equations) {
            for(String node : equation) {
                if(!nodeMap.containsKey(node)) {
                    nodeMap.put(node, counter++);
                }
            }
        }

        final int nodeCount = nodeMap.size();

        //Prepare weighted graph - adj matrix representation
        final double[][] graph = new double[nodeCount][nodeCount];
        for(double[] row : graph) {
            Arrays.fill(row, Double.MAX_VALUE);
        }
        
        for(int i = 0; i < count; ++i) {
            List<String> eq = equations.get(i);
            int u = nodeMap.get(eq.get(0));
            int v = nodeMap.get(eq.get(1));

            // System.out.println(u + ":" + v);
            // System.out.println(graph.length);

            graph[u][v] = values[i];
            graph[v][u] = 1.0/values[i];
        }

        //Run floyd-warshall's algo to find all pairs shortest paths
        floydWarshall(graph);

        final int queryCount = queries.size();
        final double[] answers = new double[queryCount];

        for(int i = 0; i < queryCount; ++i) {
            List<String> query = queries.get(i);
            if(!nodeMap.containsKey(query.get(0)) || 
                !nodeMap.containsKey(query.get(1))) {
                    answers[i] = -1.0;
                    continue;
            }

            int u = nodeMap.get(query.get(0));
            int v = nodeMap.get(query.get(1));

            answers[i] = (graph[u][v] == Double.MAX_VALUE ? -1.0 : graph[u][v]);
        }

        return answers;
    }

    private void floydWarshall(double[][] graph) {
        final int nodeCount = graph.length;

        for(int i = 0; i < nodeCount; ++i) {
            for(int j = 0; j < nodeCount; ++j) {
                for(int k = 0; k < nodeCount; ++k) {
                    if(graph[j][k] == Double.MAX_VALUE) {
                        graph[j][k] = getPathWeight(graph[j][i], graph[i][k]);
                    }
                }
            }
        }
    }

    private double getPathWeight(double w1, double w2) {
        if(w1 == Double.MAX_VALUE || w2 == Double.MAX_VALUE) return Double.MAX_VALUE;

        return w1 * w2; //Assuming this won't overflow
    }
}