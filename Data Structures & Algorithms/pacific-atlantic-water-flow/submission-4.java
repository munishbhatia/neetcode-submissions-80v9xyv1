class Solution {
    private final int[][] directions = new int[][]{{-1,0}, {1,0}, {0,-1}, {0,1}};
    
    public List<List<Integer>> pacificAtlantic(int[][] heights) {
        if(heights == null) return new ArrayList<List<Integer>>();

        final int rows = heights.length;
        final int cols = heights[0].length;

        if(rows == 0 || cols == 0) return new ArrayList<List<Integer>>();

        final boolean[][] pacific = new boolean[rows][cols];
        final boolean[][] atlantic = new boolean[rows][cols];

        for(int i = 0; i < rows; ++i) {
            dfs(heights, pacific, i, 0);
            dfs(heights, atlantic, i, cols - 1);
        }

        for(int j = 0; j < cols; ++j) {
            dfs(heights, pacific, 0, j);
            dfs(heights, atlantic, rows - 1, j);
        }

        final List<List<Integer>> result = new ArrayList<>();
        for(int i = 0; i < rows; ++i) {
            for(int j = 0; j < cols; ++j) {
                if(pacific[i][j] && atlantic[i][j]) result.add(Arrays.asList(new Integer[]{i,j}));
            }
        }       

        return result;
    }

    private void dfs(int[][] heights, boolean[][] flowsInto, int r, int c) {
        flowsInto[r][c] = true; //We are starting at the edge cells which can always flow into the ocean they border - which is the same ocean we pass in the method args

        for(int[] dir : directions) {
            int nr = r + dir[0];
            int nc = c + dir[1];

            if(withinBounds(heights.length, heights[0].length, nr, nc) &&
                !flowsInto[nr][nc] && //This check acts as the "visited" check for us
                heights[nr][nc] >= heights[r][c]) { //We reversed this condition here from the way it was stated in the problem
                    dfs(heights, flowsInto, nr, nc);
                }
        }
    }

    private boolean withinBounds(int rows, int cols, int r, int c) {
        return r >= 0 && r < rows && c >= 0 && c < cols;
    }
}
