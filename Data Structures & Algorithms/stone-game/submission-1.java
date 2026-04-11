class Solution {
    public boolean stoneGame(int[] piles) {
        if(piles == null) return true;

        final int len = piles.length;
        
        if(len <= 2) return true;

        int[][] dp = new int[len][len];

        for(int[] row : dp) {
            Arrays.fill(row, -1);
        }

        int result = stoneGameHelper(piles, 0, piles.length - 1, true, dp);
        int sum = Arrays.stream(piles).sum();

        return result > sum/2;
    }

    private int stoneGameHelper(int[] piles, int left, int right, boolean aliceTurn, int[][] dp) {
        if(left < 0 || right >= piles.length || left > right) return 0;

        if(dp[left][right] != -1) return dp[left][right];
        
        int leftPick = aliceTurn ? piles[left] : 0;
        int rightPick = aliceTurn ? piles[right] : 0;

        leftPick += stoneGameHelper(piles, left + 1, right, !aliceTurn, dp);
        rightPick += stoneGameHelper(piles, left, right - 1, !aliceTurn, dp);

        int result = Math.max(leftPick, rightPick);

        dp[left][right] = result;

        return result;
    }
}