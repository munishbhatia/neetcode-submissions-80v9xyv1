class Solution {
    public int countComponents(int n, int[][] edges) {
        if(edges == null || edges.length == 0) return n;

        final Set<Integer>[] adjList = generateGraphAdjListFromEdges(n, edges);

        final Set<Integer> visited = new HashSet<>();
        int components = 0;
        for(int i = 0; i < n; ++i) {
            if(!visited.contains(i)) {
                ++components;
                dfs(adjList, visited, i);
            }
        }

        return components;
    }

    private Set<Integer>[] generateGraphAdjListFromEdges(int n, int[][] edges) {
        final Set<Integer>[] graph = (Set<Integer>[]) new HashSet[n];
        for(int i = 0 ; i < n; ++i) {
            graph[i] = new HashSet<>();
        }

        for(int[] edge : edges) {
            graph[edge[0]].add(edge[1]);
            graph[edge[1]].add(edge[0]);
        }

        return graph;
    }

    private void dfs(Set<Integer>[] adjList, Set<Integer> visited, int node) {
        visited.add(node);

        for(int neighbor : adjList[node]) {
            if(!visited.contains(neighbor)) {
                dfs(adjList, visited, neighbor);
            }
        }
    }
}
