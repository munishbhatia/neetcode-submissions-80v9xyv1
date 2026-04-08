class Solution {
    public int change(int amount, int[] coins) {
        if(coins == null) return 0;

        if(amount == 0) return 1; //could be coins.length as well - not clear in the question

        Arrays.sort(coins);

        final int[][] cache = new int[amount + 1][coins.length];
        Arrays.fill(cache[0], 1); //There is exactly 1 way to form

        for(int i=1; i<=amount; ++i) {
            Arrays.fill(cache[i], -1);
        }

        return countChangeHelper(cache, coins, amount, 0);
    }

    private int countChangeHelper(int[][] cache, int[] coins, int amount, int startIndex) {
        // System.out.println("Amount: " + amount + "; StartIndex: " + startIndex);

        if(startIndex >= coins.length) return 0;

        // if(amount < coins[startIndex]) return 0;

        if(cache[amount][startIndex] != -1) {
            // System.out.println("Returning from Cache");
            return cache[amount][startIndex];
        }

        int ways = 0;
        for(int i=startIndex; i<coins.length; ++i) {
            if(coins[i] > amount) break;

            ways += countChangeHelper(cache, coins, amount - coins[i], i);
        }

        cache[amount][startIndex] = ways;

        // System.out.println("Ways: " + ways + ": " + cache[amount][startIndex]);

        return ways;
    }
}
