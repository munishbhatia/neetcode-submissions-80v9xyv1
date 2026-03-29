class Solution {
    public int coinChange(int[] coins, int amount) {
        if(amount == 0) return 0;

        if(coins == null || coins.length == 0) {
            return -1; //amount is non zero but we don't have any coins
        }

        //If amount < 0 --> bad input

        int[] minCoinsPrev = new int[amount + 1];
        int[] minCoinsCurr = new int[amount + 1];

        Arrays.fill(minCoinsPrev, 1, amount+1, -1);

        Arrays.sort(coins);

        for(int i=0; i<coins.length; ++i) {
            int coinValue = coins[i];
            for(int j=1; j<=amount; ++j) {
                if(coinValue > j) {
                    minCoinsCurr[j] = minCoinsPrev[j];
                    continue;
                }
                minCoinsCurr[j] = (minCoinsCurr[j - coinValue] == -1) ? -1 : (1 + minCoinsCurr[j - coinValue]); //Pick the current value coin
                if(minCoinsCurr[j] == -1) {
                    minCoinsCurr[j] = minCoinsPrev[j]; //Don't pick the current value coin
                }
                if(minCoinsPrev[j] > 0 && minCoinsCurr[j] > minCoinsPrev[j]) {
                    minCoinsCurr[j] = minCoinsPrev[j];
                }
            }

            minCoinsPrev = minCoinsCurr;
            minCoinsCurr = new int[amount + 1];
        }

        return minCoinsPrev[amount];
    }
}
