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
