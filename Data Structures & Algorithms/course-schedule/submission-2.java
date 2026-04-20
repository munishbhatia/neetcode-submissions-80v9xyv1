class Solution {
    public boolean canFinish(int numCourses, int[][] prerequisites) {
        if(prerequisites == null) return true;

        final int rows = prerequisites.length;
        if(numCourses == 0 || rows == 0) return true;

        final int[] indegree = new int[numCourses];
        final Map<Integer, List<Integer>> graph = new HashMap<>();

        for(int[] dependency : prerequisites) {
            int from = dependency[0];
            int to = dependency[1];

            indegree[to] ++;
            graph.putIfAbsent(from, new ArrayList<>());
            graph.get(from).add(to);
        }

        int nodeCount = topologicalSort(graph, indegree);

        return nodeCount == numCourses;
    }

    private int topologicalSort(Map<Integer, List<Integer>> graph, int[] indegree) {
        final Queue<Integer> q = new LinkedList<>();

        int sorted = 0;

        for(int i = 0; i < indegree.length; ++i) {
            if(indegree[i] == 0) q.offer(i);
        }

        while(!q.isEmpty()) {
            int curr = q.poll();
            ++sorted;

            if(graph.get(curr) == null || graph.get(curr).size() == 0) continue;

            for(int n : graph.get(curr)) {
                indegree[n] --;
                if(indegree[n] == 0) q.offer(n);
            }
        }

        return sorted;
    }
}
