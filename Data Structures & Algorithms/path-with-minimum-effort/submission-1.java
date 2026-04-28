class Solution {
    private final int[][] neighbours = new int[][] {{-1, 0}, {1, 0}, {0, -1}, {0, 1}};

    public int minimumEffortPath(int[][] heights) {
        if (heights == null || heights.length == 0) return 0;
        return dijkstras(heights);
    }

    private int dijkstras(int[][] heights) {
        final int rows = heights.length;
        final int cols = heights[0].length;

        final int[][] distance = new int[rows][cols];
        for(int[] row : distance) {
            Arrays.fill(row, Integer.MAX_VALUE);
        }

        distance[0][0] = 0;

        final PriorityQueue<int[]> minHeap = new PriorityQueue<>((a,b) -> Integer.compare(a[2], b[2]));
        minHeap.offer(new int[]{0,0,0});

        while(!minHeap.isEmpty()) {
            int[] curr = minHeap.poll();

            if(curr[0] == rows-1 && curr[1] == cols-1) return curr[2];

            for(int[] dist : neighbours) {
                int nr = curr[0] + dist[0];
                int nc = curr[1] + dist[1];

                if (nr < 0 || nc < 0 || nr >= heights.length || nc >= heights[0].length) continue;

                int neffort = Math.max(curr[2], Math.abs(heights[curr[0]][curr[1]] - heights[nr][nc]));

                if(neffort < distance[nr][nc]) {
                    distance[nr][nc] = neffort;
                    minHeap.offer(new int[]{nr, nc, neffort});
                }
            }
        }

        return distance[rows-1][cols-1];
    }
}