class Solution {
    public int minCostClimbingStairs(int[] cost) {
        final int len = cost.length;
        if(len <= 2) {
            return Math.min(cost[0], len == 2 ? cost[1] : 0);
        }

        int twoDown = 0;
        int oneDown = 0;
        int curr = 0;

        for(int i=2; i<=len; ++i) {
            curr = Math.min(cost[i-2] + twoDown, cost[i-1] + oneDown);
            twoDown = oneDown;
            oneDown = curr;
        }

        return curr;

        // final int[] minCost = new int[len+1];
        // minCost[0] = 0;
        // minCost[1] = 0;
        
        // for(int i=2; i<=len; ++i) {
        //     minCost[i] = Math.min(cost[i-2] + minCost[i-2], cost[i-1] + minCost[i-1]);
        // }

        // return minCost[len];
    }
}

//Intuition:
//We pay the cost of a floor ONLY IF WE STEP AHEAD from it
//Min cost to land at floor 0 or floor 1 themselves is 0 since we can start from those floors -- THIS WAS A CRUCIAL INSIGHT
//Min cost to land at floor 2 = Min(PayCost[0] + Min cost to land at 0 --> and go two floors up,
//                                  PayCost[1] + Min cost to land at 1 --> and go one floor up)
//We build onto the solution from there
//Floor 3 = roof top here where we are only given two costs (for floor 0 and 1)
//Question asks us to calculate the min cost to go to the roof top
