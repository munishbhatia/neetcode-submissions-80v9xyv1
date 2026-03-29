class Solution {
    public int[][] insert(int[][] intervals, int[] newInterval) {
        if(intervals == null || intervals.length == 0) {
            return List.of(newInterval).toArray(new int[0][]);
        }

        int insertIndex = Arrays.binarySearch(intervals, newInterval, (a,b) -> a[0] - b[0]);
        insertIndex = insertIndex < 0 ? (-1 * insertIndex) - 1 : insertIndex;

        System.out.println(insertIndex);

        final List<int[]> result = new ArrayList<>();

        for(int i=0; i<insertIndex; ++i) {
            System.out.print("Adding as-is " + Arrays.toString(intervals[i]));
            result.add(intervals[i]);
        }

        if(insertIndex == 0 && intervalsOverlap(intervals[0], newInterval)) {
            newInterval = getMergedInterval(intervals[0], newInterval);
        }
        if(insertIndex > 0 && intervalsOverlap(intervals[insertIndex - 1], newInterval)) {
            System.out.println("Merged with previous");
            result.remove(result.size() - 1);
            newInterval = getMergedInterval(intervals[insertIndex - 1], newInterval);
        }
        result.add(newInterval);

        int i = insertIndex;

        System.out.println("Resuming at " + i);

        while(i < intervals.length && intervalsOverlap(newInterval, intervals[i])) ++i;


        if(i > insertIndex) { //i is still at the insert index, we did not find any overlap in the while loop above
            newInterval = getMergedInterval(intervals[i-1], newInterval);
            result.remove(result.size() - 1);
            result.add(newInterval);
        }

        for(; i < intervals.length; ++i) {
            result.add(intervals[i]);
        }

        // for(int[] res : result) {
        //     System.out.print(String.format("(%d,%d), ", res[0], res[1]));
        // }

        return result.toArray(new int[0][]);
    }

    private boolean intervalsOverlap(int[] int1, int[] int2) {
        boolean result = int2[0] >= int1[0] && int2[0] <= int1[1];
        return result;
    }

    private int[] getMergedInterval(int[] int1, int[] int2) {
        int[] merged = new int[2];
        merged[0] = Math.min(int1[0], int2[0]);
        merged[1] = Math.max(int1[1], int2[1]);

        return merged;
    }
}
