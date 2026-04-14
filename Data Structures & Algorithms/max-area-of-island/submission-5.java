class Solution {
    final int[][] directions = new int[][]{{0, -1}, {0, 1}, {-1, 0}, {1, 0}};

    public int maxAreaOfIsland(int[][] grid) {
        if(grid == null) return 0;

        final int rows = grid.length;
        final int cols = grid[0].length;

        if(rows == 0 || cols == 0) return 0;

        // int maxSize = 0;

        // for(int i = 0; i < rows; ++i) {
        //     for(int j = 0; j < cols; ++j) {
        //         if(grid[i][j] == 1) {
        //             int size = dfs(grid, rows, cols, i, j);
        //             int size = bfs(grid, rows, cols, i, j);
        //             maxSize = Math.max(size, maxSize);
        //         }
        //     }
        // }

        // return maxSize;

        final int len = rows * cols;
        final int[] parent = new int[len];
        final int[] size = new int[len];
        uf(grid, rows, cols, parent, size);

        return Arrays.stream(size).max().getAsInt();
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

    private void uf(int[][] grid, int rows, int cols, int[] parent, int[] size) {
        
        for(int i = 0; i < rows; ++i) {
            for(int j = 0; j < cols; ++j) {
                int cellNum = getCellNumber(i, j, cols);
                
                parent[cellNum] = cellNum;
                size[cellNum] = (grid[i][j] == 1 ? 1 : 0);
            }
        }

        for(int i = 0; i < rows; ++i) {
            for(int j = 0; j < cols; ++j) {
                if(grid[i][j] == 1) {

                    for(int[] dir : directions) {
                        int nr = i + dir[0];
                        int nc = j + dir[1];

                        if(nr < 0 || nr >= rows || nc < 0 || nc >= cols) continue; //out of graph bounds

                        if(grid[nr][nc] == 1) {
                            union(parent, size, getCellNumber(i, j, cols), getCellNumber(nr, nc, cols));
                        }
                    }
                }
            }
        }
    }

    private int getCellNumber(int row, int col, int cols) {
        return (row * cols) + col;
    }

    private int find(int[] parent, int cellNum) {
        if(parent[cellNum] != cellNum) {
            parent[cellNum] = find(parent, parent[cellNum]);
        }

        return parent[cellNum];
    }

    private void union(int[] parent, int[] size, int c1, int c2) {
        int p1 = find(parent, c1);
        int p2 = find(parent, c2);

        if(p1 == p2) return;

        if(size[p1] < size[p2]) {
            parent[p1] = p2;
            size[p2] += size[p1];
            return;
        }

        //size[p1] >= size[p2]
        parent[p2] = p1;
        size[p1] += size[p2];
        return;
    }
}
