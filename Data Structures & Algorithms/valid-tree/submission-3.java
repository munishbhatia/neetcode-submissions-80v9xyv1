class Solution {
    public boolean validTree(int n, int[][] edges) {
        if(n == 0) return true; //empty tree

        // if(n == 1 && (edges == null || edges.length == 0)) return true; //root only tree, i.e. single node tree -- it is covered already in the dfs approach

        final Map<Integer, List<Integer>> graph = createGraphFromEdges(edges);
        final Set<Integer> visited = new HashSet<>();

        for(Map.Entry<Integer, List<Integer>> entry : graph.entrySet()) {
            // System.out.println(String.format("Node: %d -- Neighbours: %s", entry.getKey(), entry.getValue()));
        }

        boolean noCycleFound = dfs(graph, visited, 0, -1);

        // System.out.println("No Cycle: " + noCycleFound);
        // System.out.println("Visited: " + visited);

        return (noCycleFound && visited.size() == n); //So we were able to traverse all the nodes without finding a cycle - which means this is an acyclic connected graph - hence a tree
    }

    private boolean dfs(Map<Integer, List<Integer>> graph, Set<Integer> visited, int curr, int parent) {
        visited.add(curr);

        boolean result = true;
        for(int n : graph.getOrDefault(curr, new ArrayList<>())) {
            // System.out.println(String.format("Curr: %d -- Neighbour: %d", curr, n));
            if(n == parent) continue;

            if(visited.contains(n)) {
                // System.out.println(String.format("Curr: %d -- Neighbour: %d -- already visited", curr, n));
                return false; //Found a cycle
            }

            result &= dfs(graph, visited, n, curr);
            if(!result) return false;
        }

        return true;
    }

    private Map<Integer, List<Integer>> createGraphFromEdges(int[][] edges) {
        final Map<Integer, List<Integer>> graph = new HashMap<>();

        for(int[] edge : edges) {
            int from = edge[0];
            int to = edge[1];

            // if(!graph.containsKey(from) && graph.containsKey(to)) {
            //     int temp = from;
            //     from = to;
            //     to = temp;
            // }

            graph.computeIfAbsent(from, f -> new ArrayList<>()).add(to);
            graph.computeIfAbsent(to, f -> new ArrayList<>()).add(from);
        }

        return graph;
    }
}
