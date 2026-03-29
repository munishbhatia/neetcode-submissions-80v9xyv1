class Solution {
    public int canCompleteCircuit(int[] gas, int[] cost) {
        final int len = gas.length;

        if(Arrays.stream(cost).sum() - Arrays.stream(gas).sum() > 0) return -1;
        
        int runningSum = 0;
        int start = 0;
        for(int i = 0; i<len; ++i) {
            runningSum += (gas[i] - cost[i]);
            if(runningSum < 0) {
                runningSum = 0;
                start = i+1;
            };
        }

        return start;
    }
}

//Greedyness: We want to try only indexes where reserve >= 0 and cut our losses/exclude an index from solution if running reserve goes -ve!
