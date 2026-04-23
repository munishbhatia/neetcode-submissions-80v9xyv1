class Solution {
    public List<Boolean> checkIfPrerequisite(int numCourses, int[][] prerequisites, int[][] queries) {
        if(prerequisites.length == 0) {
            return IntStream.range(0, queries.length).mapToObj(i -> false).collect(Collectors.toList());
        }

        final boolean[][] adjMatrix = generateGraphMatrixFromPrerequisites(numCourses, prerequisites);

        // final Map<Integer, Set<Integer>> graph = generateGraphFromPrerequisites(numCourses, prerequisites);
        final List<Boolean> response = new ArrayList<>(queries.length);

        floydWarshall(numCourses, adjMatrix);
        for(int[] query : queries) {
            // response.add(dfs(graph, new HashSet<>(), query[1], query[0]));
            response.add(adjMatrix[query[1]][query[0]]);
        }

        return response;
    }

    private boolean[][] generateGraphMatrixFromPrerequisites(int numCourses, int[][] prerequisites) {
        final boolean[][] adjMatrix = new boolean[numCourses][numCourses];

        for(int[] pre : prerequisites) {
            adjMatrix[pre[1]][pre[0]] = true;
        }

        return adjMatrix;
    }

    private void floydWarshall(int numCourses, boolean[][] graph) {
        for(int i = 0; i < numCourses; ++i) {
            for(int j = 0; j < numCourses; ++j) {
                for(int k = 0; k < numCourses; ++k) {
                    graph[j][k] = graph[j][k] || (graph[j][i] && graph[i][k]);
                }
            }
        }
    }

    // private Map<Integer, Set<Integer>> generateGraphFromPrerequisites(int numCourses, int[][] prerequisites) {
    //     final Map<Integer, Set<Integer>> graph = new HashMap<>();

    //     for(int[] pre : prerequisites) {
    //         graph.computeIfAbsent(pre[1], k -> new HashSet<>()).add(pre[0]);
    //     }

    //     return graph;
    // }

    // private boolean dfs(Map<Integer, List<Integer>> graph, Set<Integer> visited, int currNode, int targetNode) {
    //     visited.add(currNode);

    //     if(currNode == targetNode) return true;

    //     boolean response = false;
    //     for(int neighbour : graph.getOrDefault(currNode, new ArrayList<>())) {
    //         if(!visited.contains(neighbour)) {
    //             response |= dfs(graph, visited, neighbour, targetNode);
    //             if(response) break;
    //         }
    //     }

    //     return response;
    // }
}