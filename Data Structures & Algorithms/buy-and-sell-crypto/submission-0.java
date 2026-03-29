class Solution {
    public int maxProfit(int[] prices) {
        int maxProfit = 0;
        int leftIdx = 0;
        int rightIdx = 1;

        if(prices == null || prices.length == 0) {
            return 0;
        }

        while(rightIdx < prices.length) {
            if(prices[rightIdx] <= prices[leftIdx]) {
                leftIdx = rightIdx;
                ++rightIdx;
                continue;
            }
            //prices[rightIdx] > prices[leftIdx]
            maxProfit = Math.max(maxProfit, prices[rightIdx] - prices[leftIdx]);
            ++rightIdx;
        }
        return maxProfit;
    }
}


// In order to make profit, we must buy low and sell high
// Look for pairs of indices (i,j) where i<j and prices[i] < prices[j]
// We need to maximize prices[j]-prices[i] on all such pairs

//Brute Force
// Two loops to test out the price difference of all such pairs and return max
// O(N^2) time, O(1) space

// Traverse from left, and open a trade window
// When we see a number smaller (y) than the window's left end (x), we move the window start
// to that number/index.
    // The idea is that if a future price is bigger than x, it will be bigger than y also
    // But the profit we will take will be larger in case we buy at (y) vs. buying at (x)
// We keep expanding the window as long as we keep seeing numbers greater than window start
// and keep updating the max profit