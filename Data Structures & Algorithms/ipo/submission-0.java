class Solution {
    public int findMaximizedCapital(int k, int w, int[] profits, int[] capital) {
        int currCap = w;
        // int maxProfit = 0;
        for(int i = 0; i < k; ++i) {
            int maxProfit = 0;
            int maxIndex = -1;
            for(int j = 0; j < capital.length; ++j) {   
                if(capital[j] > currCap) continue;
                if(profits[j] > maxProfit) {
                    maxProfit = profits[j];
                    maxIndex = j;
                }
            }

            if(maxIndex == -1) break; //we can't find more projects that can be completed

            currCap += maxProfit;
            capital[maxIndex] = Integer.MAX_VALUE;
        }

        return currCap > w ? currCap : w;
    }
}