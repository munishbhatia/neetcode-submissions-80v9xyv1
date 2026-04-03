class Solution {
    public int uniquePaths(int m, int n) {
        int[] dp = new int[n];

        //Initialize the DP matrix by filling last row and last column with 1
        Arrays.fill(dp, 1);

        for(int i = m-2; i >= 0; --i) {
            int[] row = new int[n];
            row[n-1] = 1; //I MISSED THIS
            
            for(int j = n-2; j >= 0; --j) {
                row[j] = dp[j] + row[j+1]; //Sum of ways from block we land at upon moving down + block we land at upon moving right
            }
            
            dp = row;
        }

        return dp[0];
    }
}
