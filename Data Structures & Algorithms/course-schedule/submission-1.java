class Solution {
    public boolean canFinish(int numCourses, int[][] prerequisites) {
        if(prerequisites == null) return true;

        final int rows = prerequisites.length;
        if(numCourses == 0 || rows == 0) return true;

        final Map<Integer, List<Integer>> graph = new HashMap<>();
        for(int[] dependency : prerequisites) {
            int from = dependency[0];
            int to = dependency[1];

            graph.putIfAbsent(from, new ArrayList<Integer>());
            graph.get(from).add(to);
        }

        final Set<Integer> visited = new HashSet<>();
        for(int key : graph.keySet()) {
            if(!visited.contains(key)) {
                if(!bfs(graph, visited, key)) return false;
            }
        }

        return true;
    }

    private boolean bfs(Map<Integer, List<Integer>> graph, Set<Integer> visited, int node) {
        final Queue<Integer> queue = new LinkedList<>();
        final Set<Integer> temp = new HashSet<>();
        queue.offer(node);

        while(!queue.isEmpty()) {
            int curr = queue.poll();
            visited.add(curr);
            temp.add(curr);

            if(graph.get(curr) == null || graph.get(curr).size() == 0) continue;

            for(int n : graph.get(curr)) {
                if(temp.contains(n)) return false; //This is a cycle

                queue.offer(n);
            }
        }

        return true;
    }
}
