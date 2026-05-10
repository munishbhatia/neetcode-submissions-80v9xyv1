class Solution {
    public int numSquares(int n) {
        if(n == 0) return 0;
        if(Math.sqrt(n) % 1 == 0) return 1; //n is a perfect square

        final int[] dp = new int[n + 1];

        dp[0] = 0;
        dp[1] = 1;

        for(int i = 2; i <= n; ++i) {
            if(dp[i] != 1) {
                int min = Integer.MAX_VALUE;
                for(int j = 1; j <= (i/2); ++j) {
                    min = Math.min(dp[j] + dp[i-j], min);
                }
                dp[i] = min;
            }
            if(i*i <= n) dp[i*i] = 1; //Keep setting solution for perfect squares to 1 as we iterate
        }

        return dp[n];
    }
}