class Solution {
    public int[][] merge(int[][] intervals) {
        if(intervals == null || intervals.length <= 1) return intervals;

        final int len = intervals.length;
        final List<int[]> response = new ArrayList<>();
        Arrays.sort(intervals, (a,b) -> a[0] - b[0]);

        int[] curr = intervals[0];

        for(int right = 1; right < len; ++right) {
            if(intervalsOverlap(curr, intervals[right])) {
                curr = mergeIntervals(curr, intervals[right]);
                continue;
            }

            response.add(curr);
            curr = intervals[right];
        }

        //Add last curr
        response.add(curr);

        return response.toArray(new int[0][]);
    }

    private boolean intervalsOverlap(int[] intervalA, int[] intervalB) {
        return intervalB[0] >= intervalA[0] && intervalB[0] <= intervalA[1]; 
    }

    private int[] mergeIntervals(int[] intervalA, int[] intervalB) {
        final int[] mergedInterval = new int[2];

        mergedInterval[0] = Math.min(intervalA[0], intervalB[0]);
        mergedInterval[1] = Math.max(intervalA[1], intervalB[1]);

        return mergedInterval;
    }
}
