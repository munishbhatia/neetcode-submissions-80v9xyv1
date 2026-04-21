class Solution {
    public int[] findOrder(int numCourses, int[][] prerequisites) {
        if(numCourses <= 0) return new int[0];

        if(prerequisites.length == 0) {
            final int[] result = new int[numCourses];
            for(int i = 0; i < numCourses; ++i) {
                result[i] = i;
            }
            return result;
        }

        final Map<Integer, List<Integer>> graph = createGraphFromDependencies(prerequisites);

        final List<Integer> orderedCourses = topologicalSortCourses(graph, numCourses);

        return (orderedCourses.size() == numCourses ? orderedCourses.stream().mapToInt(Integer::intValue).toArray() : new int[0]);
    }

    //Performs a topological sorting of the course work graph using Kahn's algorithm
    private List<Integer> topologicalSortCourses(Map<Integer, List<Integer>> graph, int numCourses) {
        final List<Integer> courseWorkOrder = new ArrayList<>();
        final Queue<Integer> queue = new LinkedList<>();
        
        final int[] inDegree = getNodeInDegrees(graph, numCourses);
        for(int i = 0; i < inDegree.length; ++i) {
            if(inDegree[i] == 0) queue.offer(i);
        }

        while(!queue.isEmpty()) {
            int curr = queue.poll();
            courseWorkOrder.add(curr);

            if(graph.get(curr) == null || graph.get(curr).size() == 0) continue;

            for(int neighbour : graph.get(curr)) {
                inDegree[neighbour] --;
                if(inDegree[neighbour] == 0) queue.offer(neighbour);
            }
        }

        return courseWorkOrder;
    }

    private Map<Integer, List<Integer>> createGraphFromDependencies(int[][] prerequisites) {
        final Map<Integer, List<Integer>> graph = new HashMap<>();

        for(int[] dependency : prerequisites) {
            graph.putIfAbsent(dependency[1], new ArrayList<>());
            graph.get(dependency[1]).add(dependency[0]);
        }

        return graph;
    }

    private int[] getNodeInDegrees(Map<Integer, List<Integer>> graph, int numCourses) {
        final int[] inDegree = new int[numCourses];

        for(List<Integer> values : graph.values()) {
            for(int i : values) {
                inDegree[i] ++;
            }
        }

        return inDegree;
    }
}
