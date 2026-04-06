class Solution {
    public int minPathSum(int[][] grid) {
        if(null == grid) return 0;

        final int rows = grid.length;
        final int cols = grid[0].length;

        if(rows == 0) return 0;

        final int[] minSum = new int[cols + 1];
        Arrays.fill(minSum, Integer.MAX_VALUE);
        minSum[cols - 1] = grid[rows - 1][cols - 1];

        for(int i = rows - 1; i >= 0; --i) {
            for(int j = cols - 1; j >= 0; --j) {
                if(i == rows - 1 && j == cols - 1) continue;

                minSum[j] = Math.min(minSum[j], minSum[j+1]) + grid[i][j];
            }
        }

        return minSum[0];
    }
}