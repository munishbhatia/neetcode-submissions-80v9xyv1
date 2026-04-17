class Solution {
    private int[][] directions = new int[][]{{-1,0}, {1,0}, {0,-1}, {0,1}};

    public void solve(char[][] board) {
        if(board == null) return;

        final int rows = board.length;
        final int cols = board[0].length;

        if(rows == 0 || cols == 0) return;

        final List<List<int[]>> switchToX = new ArrayList<>();
        final Set<Integer> visited = new HashSet<>();

        for(int i = 0; i < rows; ++i) {
            for(int j = 0; j < cols; ++j) {
                if(board[i][j] == 'O' && !visited.contains(cellId(cols, i, j)) && !edgeNode(rows, cols, i, j)) {
                    final List<int[]> temp = new ArrayList<>();
                    boolean result = dfs(board, visited, temp, rows, cols, i, j);
                    if(result) {
                        switchToX.add(temp);
                    }
                }
            }
        }

        switchToX.stream().flatMap(List::stream).forEach(x -> board[x[0]][x[1]] = 'X');
    }

    private boolean dfs(char[][] board, Set<Integer> visited, List<int[]> islandNodes, int rows, int cols, int r, int c) {
        visited.add(cellId(cols, r, c));
        islandNodes.add(new int[]{r,c});

        boolean success = !edgeNode(rows, cols, r, c);

        for(int[] dir : directions) {
            int nr = r + dir[0];
            int nc = c + dir[1];

            if(!withinBounds(rows, cols, nr, nc) || visited.contains(cellId(cols, nr, nc)) || board[nr][nc] != 'O') continue;

            success &= dfs(board, visited, islandNodes, rows, cols, nr, nc);
        }

        return success;
    }

    private int cellId(int cols, int r, int c) {
        return r * cols + c;
    }

    private boolean withinBounds(int rows, int cols, int r, int c) {
        return r >= 0 && r < rows && c >= 0 && c < cols;
    }

    private boolean edgeNode(int rows, int cols, int r, int c) {
        return r == 0 || r == (rows - 1) || c == 0 || c == (cols - 1);
    }
}
