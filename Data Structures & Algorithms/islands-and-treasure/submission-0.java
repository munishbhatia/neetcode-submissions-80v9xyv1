class Solution {
    private final int MAX = 2147483647;
    private final int[][] directions = new int[][]{{-1,0}, {1,0}, {0,-1}, {0,1}};

    public void islandsAndTreasure(int[][] grid) {
        if(grid == null) return;

        final int rows = grid.length;
        final int cols = grid[0].length;

        if(rows == 0 || cols == 0) return;

        for(int i = 0; i < rows; ++i) {
            for(int j = 0; j < cols; ++j) {
                if(grid[i][j] == 0) {
                    multiSourceBFS(grid, rows, cols, i, j);
                }
            }
        }
    }

    private void multiSourceBFS(int[][] grid, int rows, int cols, int r, int c) {
        final Set<Integer> visited = new HashSet<>();
        final Queue<int[]> queue = new LinkedList<>();
        queue.offer(new int[]{r, c});

        while(!queue.isEmpty()) {
            int[] curr = queue.poll();
            int distance = grid[curr[0]][curr[1]];

            if(distance == MAX) continue;

            for(int[] dir : directions) {
                int nr = curr[0] + dir[0];
                int nc = curr[1] + dir[1];

                if(isWithinBounds(nr, nc, rows, cols) && grid[nr][nc] > 0 && !visited.contains(getCellNumber(nr, nc, cols))) {
                    grid[nr][nc] = Math.min(grid[nr][nc], distance + 1);
                    visited.add(getCellNumber(nr, nc, cols));
                    queue.offer(new int[]{nr, nc});
                }
            }
        }
    }

    private int getCellNumber(int row, int col, int cols) {
        return (row * cols) + col;
    }

    private boolean isWithinBounds(int r, int c, int rows, int cols) {
        return !(r < 0 || r >= rows || c < 0 || c >= cols);
    }
}
