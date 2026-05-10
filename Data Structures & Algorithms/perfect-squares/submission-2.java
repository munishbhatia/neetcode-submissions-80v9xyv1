class Solution {
    public int numSquares(int n) {
        final int[] dp = new int[n + 1];

        dp[0] = 0;
        dp[1] = 1;

        for(int i = 2; i <= n; ++i) {
            // System.out.println(i);
            if(dp[i] == 1) {
                if(i*i <= n) dp[i*i] = 1; //Keep setting solution for perfect squares to 1 as we iterate
                continue;
            }

            if(i*i <= n) dp[i*i] = 1; //Keep setting solution for perfect squares to 1 as we iterate

            int min = Integer.MAX_VALUE;
            for(int j = 1; j <= (i/2); ++j) {
                min = Math.min(dp[j] + dp[i-j], min);
            }
            dp[i] = min;
        }

        return dp[n];
    }
}