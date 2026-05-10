class Solution {
    public int numSquares(int n) {
        if(n == 0) return 0;
        if(n == 1) return 1;

        final int[] dp = new int[n + 1];

        dp[0] = 0;
        dp[1] = 1;

        for(int i = 2; i <= n; ++i) {
            if(isPerfectSquare(i)) {
                dp[i] = 1;
                continue;
            }

            int min = Integer.MAX_VALUE;
            // for(int j = 1; j <= (i-1); ++j) {
            for(int j = 1; j <= (i/2); ++j) {
                min = Math.min(dp[j] + dp[i-j], min);
            }
            dp[i] = min;
        }

        // System.out.println(Arrays.toString(dp));
        return dp[n];
    }

    private boolean isPerfectSquare(int n) {
        return (Math.sqrt(n) % 1 == 0);
    }
}