class Solution {
    public int orangesRotting(int[][] grid) {
        if(grid == null) return -1;

        final int rows = grid.length;
        final int cols = grid[0].length;

        if(rows == 0 || cols == 0) return -1;

        final int[][] timer = new int[rows][cols];

        for(int[] row : timer) {
            Arrays.fill(row, Integer.MAX_VALUE);
        }

        for(int i = 0; i < rows; ++i) {
            for(int j = 0; j < cols; ++j) {
                if(grid[i][j] == 2) {
                    timer[i][j] = 0;
                    bfs(grid, timer, rows, cols, i, j);
                }
            }
        }

        // System.out.println("Timer: ");
        // for(int[] row : timer) System.out.println(Arrays.toString(row));

        //Calculate response
        int max = 0;
        for(int i = 0; i < rows; ++i) {
            int rowMax = 0;
            for(int j = 0; j < cols; ++j) {
                if(grid[i][j] != 0) rowMax = Math.max(rowMax, timer[i][j]);
            }
            max = Math.max(max, rowMax);
        }

        return (max == Integer.MAX_VALUE ? -1 : max);
    }

    private void bfs(int[][] grid, int[][] timer, int rows, int cols, int r, int c) {
        final Set<Integer> visited = new HashSet<>();
        final Queue<int[]> queue = new LinkedList<>();
        final int[][] directions = new int[][]{{-1,0}, {1,0}, {0,-1}, {0,1}};

        visited.add(cellNumber(cols, r, c));
        queue.offer(new int[]{r, c});

        while(!queue.isEmpty()) {
            int[] curr = queue.poll();
            int distance = timer[curr[0]][curr[1]];

            for(int[] dir : directions) {
                int nr = curr[0] + dir[0]; //nr = neighbour's row
                int nc = curr[1] + dir[1]; //nc = neighbour's column

                if(withinBounds(rows, cols, nr, nc) && !visited.contains(cellNumber(cols, nr, nc)) && grid[nr][nc] == 1) {
                    timer[nr][nc] = Math.min(distance + 1, timer[nr][nc]);
                    queue.offer(new int[]{nr, nc});
                    visited.add(cellNumber(cols, nr, nc));
                }
            }
        }
    }

    private int cellNumber(int cols, int r, int c) {
        return (r * cols) + c;
    }

    private boolean withinBounds(int rows, int cols, int r, int c) {
        return r >= 0 && r < rows && c >= 0 && c < cols;
    }
}
