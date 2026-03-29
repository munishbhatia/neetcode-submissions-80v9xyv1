class Solution {
    public int canCompleteCircuit(int[] gas, int[] cost) {
        final int len = gas.length;

        final int[] reserve = new int[len];

        for(int i = 0; i<len; ++i) {
            reserve[i] = gas[i] - cost[i];
        }

        // int index = -1;
        // int chainSize = 0;
        // int maxChainSize = 0;
        // int maxChainStart = -1;
        // int chainStart = 0;

        // for(int i=0; i<len; ++i) {
        //     if(reserve[i] < 0) {
        //         chainSize = 0;
        //         chainStart = i+1;
        //         continue;
        //     }

        //     //reserve >= 0
        //     chainSize += 1;
        //     if(chainSize > maxChainSize) {
        //         maxChainSize = chainSize;
        //         maxChainStart = chainStart;
        //         System.out.println("i: " + i + "; maxChainStart: " + maxChainStart + "; maxChainSize: " + maxChainSize);
        //     }

        //     if(chainSize == maxChainSize && maxChainStart != -1 && reserve[chainStart] > reserve[maxChainStart]) {
        //         maxChainSize = chainSize;
        //         maxChainStart = chainStart;
        //         System.out.println("i: " + i + "; maxChainStart: " + maxChainStart + "; maxChainSize: " + maxChainSize);
        //     }
        // }

        // System.out.println("MaxChainStart: " + maxChainStart);
        // System.out.println("MaxChainLength: " + maxChainSize);

        // if(maxChainStart == -1) return -1; //No chain of reserves found with elements >= 0

        // int runningSum = reserve[maxChainStart];
        
        int i = -1, j = -1, runningSum = 0;
        boolean found = true;

        for(i = 0; i<len; ++i) {
            if(reserve[i] < 0) continue;

            // System.out.println("Trying: " + i);
            runningSum = reserve[i];
            found = true;
            for(j = (i+1)%len; j != i; j = (j+1)%len) {
                runningSum += reserve[j];
                if(runningSum < 0) {
                    found = false;
                    // System.out.println(String.format("Running Sum: %d at index: %d; Breaking loop", runningSum, j));
                    break;
                }
            }

            if(found) return i;
        }

        // System.out.println(String.format("i: %d; j: %d", i, j));
        // if(i == len && j == i-1) return j; //We finished the loop without breaking

        return -1;
    }
}


//Greedyness: We want to basically return the index which starts the longest chain of non-zero reserves!
