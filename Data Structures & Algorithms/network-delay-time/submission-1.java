class Solution {
    public int networkDelayTime(int[][] times, int n, int k) {
        final List<List<int[]>> graph = prepareGraph(times, n+1);
        final int[] distance = dijkstras(graph, k);

        final int maxTime = Arrays.stream(distance).skip(1).max().getAsInt();

        return (maxTime == Integer.MAX_VALUE) ? -1 : maxTime;
    }

    private List<List<int[]>> prepareGraph(int[][] edges, int nodes) {
        final List<List<int[]>> graph = new ArrayList<>(nodes);

        for(int i = 0; i < nodes; ++i) {
            graph.add(new ArrayList<>());
        }

        for(int[] edge : edges) {
            graph.get(edge[0]).add(new int[]{edge[1], edge[2]});
        }

        return graph;
    }

    private int[] dijkstras(List<List<int[]>> graph, int start) {
        final int nodes = graph.size();
        final Set<Integer> visited = new HashSet<>();
        final PriorityQueue<int[]> minHeap = new PriorityQueue<>((a, b) -> Integer.compare(a[1], b[1]));

        final int[] distance = new int[nodes];
        Arrays.fill(distance, Integer.MAX_VALUE);
        distance[start] = 0;

        minHeap.offer(new int[]{start, 0});

        while(!minHeap.isEmpty()) {
            int curr = minHeap.peek()[0];
            int dist = minHeap.poll()[1];
            visited.add(curr);

            if(dist > distance[curr]) continue;

            for(int[] pairs : graph.get(curr)) {
                if(dist + pairs[1] < distance[pairs[0]]) {
                    distance[pairs[0]] = Math.min(distance[pairs[0]], dist + pairs[1]);
                    minHeap.offer(new int[]{pairs[0], distance[pairs[0]]});
                }
            }
        }
        return distance;
    }
}
