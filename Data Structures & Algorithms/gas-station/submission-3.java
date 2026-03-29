class Solution {
    public int canCompleteCircuit(int[] gas, int[] cost) {
        final int len = gas.length;
        
        for(int i = 0; i<len; ++i) {
            int reserve = gas[i] - cost[i];
            if(reserve < 0) continue;

            int runningSum = reserve;
            
            int j = (i+1)%len;
            for(; j != i; j = (j+1)%len) {
                runningSum += gas[j] - cost[j];
                if(runningSum < 0) {
                    break;
                }
            }

            if(j == i) return i;
        }

        return -1;
    }
}

//Greedyness: We want to try only indexex where reserve >= 0!
