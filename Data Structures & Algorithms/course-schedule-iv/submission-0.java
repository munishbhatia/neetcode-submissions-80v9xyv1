class Solution {
    public List<Boolean> checkIfPrerequisite(int numCourses, int[][] prerequisites, int[][] queries) {
        if(prerequisites.length == 0) {
            return IntStream.range(0, queries.length).mapToObj(i -> false).collect(Collectors.toList());
        }

        final Map<Integer, List<Integer>> graph = generateGraphFromPrerequisites(numCourses, prerequisites);

        final List<Boolean> response = new ArrayList<>(queries.length);

        for(int[] query : queries) {
            response.add(dfs(graph, new HashSet<>(), query[1], query[0]));
        }

        return response;
    }

    private Map<Integer, List<Integer>> generateGraphFromPrerequisites(int numCourses, int[][] prerequisites) {
        final Map<Integer, List<Integer>> graph = new HashMap<>();

        for(int[] pre : prerequisites) {
            graph.computeIfAbsent(pre[1], k -> new ArrayList<>()).add(pre[0]);
        }

        return graph;
    }

    private boolean dfs(Map<Integer, List<Integer>> graph, Set<Integer> visited, int currNode, int targetNode) {
        visited.add(currNode);

        if(currNode == targetNode) return true;

        boolean response = false;
        for(int neighbour : graph.getOrDefault(currNode, new ArrayList<>())) {
            if(!visited.contains(neighbour)) {
                response |= dfs(graph, visited, neighbour, targetNode);
                if(response) break;
            }
        }

        return response;
    }
}