class Solution {
    final int[][] directions = new int[][]{{0, -1}, {0, 1}, {-1, 0}, {1, 0}};

    public int maxAreaOfIsland(int[][] grid) {
        if(grid == null) return 0;

        final int rows = grid.length;
        final int cols = grid[0].length;

        if(rows == 0 || cols == 0) return 0;

        int maxSize = 0;

        for(int i = 0; i < rows; ++i) {
            for(int j = 0; j < cols; ++j) {
                if(grid[i][j] == 1) {
                    // int size = dfs(grid, rows, cols, i, j);
                    int size = bfs(grid, rows, cols, i, j);
                    maxSize = Math.max(size, maxSize);
                }
            }
        }

        return maxSize;
    }

    private int dfs(int[][] grid, int rows, int cols, int row, int col) {
        grid[row][col] = 0; //marking this cell as visited

        int size = 1;

        for(int[] dir : directions) {
            int neighbourRow = row + dir[0];
            int neighbourCol = col + dir[1];

            if(neighbourRow < 0 || neighbourRow >= rows || neighbourCol < 0 || neighbourCol >= cols) continue;

            if(grid[neighbourRow][neighbourCol] == 1) {
                size += dfs(grid, rows, cols, neighbourRow, neighbourCol);
            }
        }

        return size;
    }

    private int bfs(int[][] grid, int rows, int cols, int row, int col) {
        final Queue<int[]> queue = new LinkedList<>();
        queue.offer(new int[]{row, col});
        grid[row][col] = 0; //marking this cell as visited
        int size = 0;

        while(!queue.isEmpty()) {
            int[] curr = queue.poll();
            ++size;

            for(int[] dir : directions) {
                int neighbourRow = curr[0] + dir[0];
                int neighbourCol = curr[1] + dir[1];

                if(neighbourRow < 0 || neighbourRow >= rows || neighbourCol < 0 || neighbourCol >= cols) continue;

                if(grid[neighbourRow][neighbourCol] == 1) {
                    grid[neighbourRow][neighbourCol] = 0;
                    queue.offer(new int[]{neighbourRow, neighbourCol});
                }
            }
        }

        return size;
    }
}
