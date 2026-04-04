class Solution {
    public int uniquePathsWithObstacles(int[][] obstacleGrid) {
        if(obstacleGrid == null) return 0;

        final int rows = obstacleGrid.length;
        final int cols = obstacleGrid[0].length;

        if(obstacleGrid[0][0] == 1 || obstacleGrid[rows-1][cols-1] == 1) return 0;

        int[] row = new int[cols];
        int[] next = new int[cols];
        next[cols - 1] = 1;

        for(int i = cols - 2; i >= 0; --i) {
            if(obstacleGrid[rows-1][i] == 1) {
                next[i] = 0;
                continue;
            }
            next[i] = next[i+1];
        }

        for(int i = rows-2; i >= 0; --i) {
            row = new int[cols];
            row[cols-1] = 1 - obstacleGrid[i][cols-1];
            if(next[cols-1] == 0) row[cols-1] = 0;
            
            for(int j = cols-2; j >= 0; --j) {
                if(obstacleGrid[i][j] == 1) {
                    row[j] = 0;
                    continue;
                }

                row[j] = row[j+1] + next[j];
            }
            next = row;
        }

        return next[0];
    }
}