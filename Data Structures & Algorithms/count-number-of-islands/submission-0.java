class Solution {
    private int[][] neighbours = new int[][]{{0, -1}, {0, 1}, {-1, 0}, {1, 0}};

    public int numIslands(char[][] grid) {
        if(grid == null) return 0;

        final int rows = grid.length;
        final int cols = grid[0].length;

        if(rows == 0 || cols == 0) return 0;

        int numIslands = 0;
        final Set<Integer> visited = new HashSet<>();

        for(int i = 0; i < rows; ++ i) {
            for(int j = 0; j < cols; ++j) {
                if(grid[i][j] == '1' && !visited.contains(getCellNumber(i, j, cols))) {
                    ++numIslands;
                    dfs(grid, i, j, rows, cols, visited);
                }
            }
        }

        return numIslands;
    }

    private int getCellNumber(int row, int col, int cols) {
        return (row * cols) + col;
    }

    private void dfs(char[][] grid, int row, int col, int rows, int cols, Set<Integer> visited) {
        visited.add(getCellNumber(row, col, cols));

        for(int[] neighbour : neighbours) {
            int nr = row + neighbour[0];
            int nc = col + neighbour[1];

            if(nr < 0 || nr >= rows || nc < 0 || nc >= cols) continue;

            if(grid[nr][nc] == '1' && !visited.contains(getCellNumber(nr, nc, cols))) {
                dfs(grid, nr, nc, rows, cols, visited);
            }
        }
    }
}
