class Solution {
    public int lastStoneWeightII(int[] stones) {
        if(stones == null || stones.length == 0) return 0;

        if(stones.length == 1) return stones[0];

        List<Integer> sortedList = Arrays.stream(stones).boxed().collect(Collectors.toList());
        Collections.sort(sortedList);
        Integer[] stones2 = sortedList.toArray(new Integer[0]);

        final int sum = Arrays.stream(stones).sum();

        // System.out.println("Sum: " + sum);

        for(int i=sum/2; i <= sum; ++i) {
            // System.out.println("Trying: " + i);
            if(subsetWithSumExists(new HashMap<>(), stones2, i, 0)) {
                // System.out.println("Found: " + i);
                return Math.abs((sum-i) - i);
            }
        }

        return 50000; //Should not reach here
    }

    private boolean subsetWithSumExists(Map<Integer, Boolean> cache, Integer[] arr, int sum, int startIndex) {
        if(startIndex >= arr.length) return false;

        if(arr[startIndex] > sum) {
            return false;
        }

        if(sum == 0) return true;
        
        if(arr[startIndex] == sum) return true;

        return subsetWithSumExists(cache, arr, sum - arr[startIndex], startIndex + 1) ||
            subsetWithSumExists(cache, arr, sum, startIndex + 1);
    }
}