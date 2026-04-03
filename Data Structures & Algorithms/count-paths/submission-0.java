class Solution {
    public int uniquePaths(int m, int n) {
        final int[][] dp = new int[m][n];

        //Initialize the DP matrix by filling last row and last column with 1
        Arrays.fill(dp[m-1], 1);
        for(int i=0; i<m; ++i) {
            dp[i][n-1] = 1;
        }

        for(int i = m-2; i >= 0; --i) {
            for(int j = n-2; j >= 0; --j) {
                dp[i][j] = dp[i+1][j] + dp[i][j+1]; //Sum of ways from block we land at upon moving down + block we land at upon moving right
            }
        }

        return dp[0][0];
    }
}
