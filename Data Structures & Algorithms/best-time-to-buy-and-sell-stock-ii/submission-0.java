class Solution {
    public int maxProfit(int[] prices) {
        if(prices == null) return 0;

        final int len = prices.length;
        if(len == 0) return 0;

        int max = prices[len - 1];
        int profit = 0;

        for(int i = len-2; i >= 0; --i) {
            max = Math.max(max, prices[i]);
            if(max > prices[i]) {
                profit += max - prices[i];
                max = prices[i];
            }
        }

        return profit;
    }
}