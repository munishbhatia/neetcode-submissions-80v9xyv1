class Solution {
    public int coinChange(int[] coins, int amount) {
        if(amount == 0) return 0;

        if(coins == null || coins.length == 0) {
            return -1; //amount is non zero but we don't have any coins
        }

        //If amount < 0 --> bad input

        int[] minCoinsPrev = new int[amount + 1];
        int[] minCoinsCurr = new int[amount + 1];

        Arrays.fill(minCoinsPrev, 1, amount+1, amount+1);

        Arrays.sort(coins);

        for(int i=0; i<coins.length; ++i) {
            int coinValue = coins[i];
            for(int j=1; j<=amount; ++j) {
                if(coinValue > j) {
                    minCoinsCurr[j] = minCoinsPrev[j];
                    continue;
                }
                minCoinsCurr[j] = Math.min(
                                    minCoinsPrev[j], //Don't pick the current value coin since it's leading to a non-solution or suboptimal solution
                                    (1 + minCoinsCurr[j - coinValue]) //Pick the current value coin
                );
                
                // if(minCoinsPrev[j] > 0 && minCoinsCurr[j] > minCoinsPrev[j]) { //minCoinsPrev[j] > 0 is a very important check here else we might end up picking a non-solution (-1) instead of an actual solution
                //     minCoinsCurr[j] = minCoinsPrev[j];
                // }
            }

            minCoinsPrev = minCoinsCurr;
            minCoinsCurr = new int[amount + 1];
        }

        return minCoinsPrev[amount] > amount ? -1 : minCoinsPrev[amount];
    }
}

//Intuition:
//At each step, we have the following options:
//Either the current coin is part of the minimum solution, or it isn't

//When it is not part of the solution:
//1. If the coin value is GREATER than the amount being processed (we can't deduct 50 from 15 -- that would lead to negative money)
//2. If by picking this coin, we end up with a non-solution (e.g. we take 50 from 52, but we don't have coins of value 2 so can't form the solution when instead NOT TAKING this 50 from 52 would have given us a valid solution)
//3. If by picking this coin, we end up having to use more coins for the remanant amount than if we did not pick this coin

//When it is part of the solution:
//Only when the coin value <= Amount and:
//1. Picking it leads to a solution (not -1) for the current amount
//2. There isn't a more optimal solution using lesser number of coins (minCoinsPrev[j] using coins 0..[i-1] where current coin is coins[i])


//NOTE: For this solution to work, input array of coins MUST BE SORTED
