class Solution {
    public int uniquePathsWithObstacles(int[][] obstacleGrid) {
        if(obstacleGrid == null) return 0;

        final int rows = obstacleGrid.length;
        final int cols = obstacleGrid[0].length;

        if(obstacleGrid[0][0] == 1 || obstacleGrid[rows-1][cols-1] == 1) return 0;

        int[] row = new int[cols + 1];
        row[cols - 1] = 1;

        for(int i = rows-1; i >= 0; --i) {
            for(int j = cols-1; j >= 0; --j) {
                if(obstacleGrid[i][j] == 1) {
                    row[j] = 0;
                    continue;
                }

                row[j] += row[j+1];
            }
        }

        return row[0];
    }
}