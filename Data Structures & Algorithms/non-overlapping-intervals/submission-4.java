class Solution {
    public int eraseOverlapIntervals(int[][] intervals) {
        if(null == intervals || intervals.length <= 1) return 0;

        final int len = intervals.length;

        Arrays.sort(intervals, Comparator.comparingInt((int[] arr) -> arr[0])
            .thenComparing(Comparator.comparingInt((int[] arr) -> arr[1]).reversed()));

        // System.out.print("[");
        // for(int[] interval : intervals) {
        //     System.out.print(Arrays.toString(interval) + ",");
        // }
        // System.out.println("]");

        final int[] overlapCounts = new int[len];
        Arrays.fill(overlapCounts, 0);

        int removalCount = 0;

        // int left = 0;
        // for(int right = 1; right < len; ) {
        //     System.out.print(String.format("Comparing %s and %s -- ", Arrays.toString(intervals[left]), Arrays.toString(intervals[right])));
        //     if(intervalsOverlap(intervals[left], intervals[right])) {
        //         ++removalCount;
        //         if(intervals[left][0] == intervals[right][0]) {
        //             System.out.println("Removing: " + Arrays.toString(intervals[left]));
        //             ++left;
        //             ++right;
        //         } else if(intervalLength(intervals[left]) == intervalLength(intervals[right])) {
        //             System.out.println("Removing: " + Arrays.toString(intervals[right]));
        //             ++right; //Remove the interval starting later
        //         } else {
        //             if(intervalLength(intervals[left]) > intervalLength(intervals[right])) {
        //                 System.out.println("Removing: " + Arrays.toString(intervals[left]));
        //                 ++left;
        //                 ++right;
        //             } else {
        //                 System.out.println("Removing: " + Arrays.toString(intervals[right]));
        //                 ++right;
        //             }
        //         }
        //     } else {
        //         System.out.println("No overlap");
        //         left = right;
        //         ++right;
        //     }
        // }

        final PriorityQueue<int[]> pq = new PriorityQueue<>((a,b) -> b[0] - a[0]); //Max Heap - int[] = overlap count, index in sorted intervals array

        for(int i=0; i<len; ++i) {
            for(int j=i+1; j<len; ++j) {
                if(intervalsOverlap(intervals[i], intervals[j])) {
                    overlapCounts[i]++;
                    overlapCounts[j]++;
                } else {
                    break;
                }
            }
            pq.offer(new int[]{overlapCounts[i], i});
        }

        // System.out.println("overlapCounts: " + Arrays.toString(overlapCounts));

        List<int[]> removedIntervals = new ArrayList<>();

        while(!pq.isEmpty()) {
            int[] curr = pq.poll();
            // System.out.println("Processing: " + Arrays.toString(intervals[curr[1]]));
            if(overlapCounts[curr[1]] <= 0) continue;

            //We will be removing this element, so decrement the overlap count on all the colliding intervals
            int right = curr[1] + 1;
            int left = curr[1] - 1;

            while(left >= 0) {
                if(intervals[left] == null) {
                    --left;
                    continue;
                }
                
                if(!intervalsOverlap(intervals[left], intervals[curr[1]])) break;

                overlapCounts[left]--;
                --left;
            }

            while(right < len) {
                // System.out.println("Right: " + right);
                if(intervals[right] == null) {
                    ++right;
                    continue;
                }
                
                if(!intervalsOverlap(intervals[curr[1]], intervals[right])) break;
                
                overlapCounts[right]--;
                ++right;
            }

            // while(right < len && intervals[right] != null && intervalsOverlap(intervals[curr[1]], intervals[right])) {
            //     overlapCounts[right]--;
            //     ++right;
            // }

            // System.out.println("Removing: " + Arrays.toString(intervals[curr[1]]));
            overlapCounts[curr[1]] = 0;
            removedIntervals.add(new int[]{intervals[curr[1]][0], intervals[curr[1]][1], curr[1]}); //[arr[0], arr[1]] represent the interval stored at index i which is stored in arr[2]
            intervals[curr[1]] = null;
            ++removalCount;

            // System.out.println("overlapCounts pro: " + Arrays.toString(overlapCounts));
        }

        // System.out.print("[");
        // for(int[] interval : intervals) {
        //     if(interval == null) {
        //         System.out.print("NULL,");
        //         continue;
        //     }
        //     System.out.print(Arrays.toString(interval) + ",");
        // }
        // System.out.println("]");

        for(int i = removedIntervals.size() - 1; i >= 0; --i) {
            int[] removedInterval = removedIntervals.get(i);
            if(canBeIntroducedSafely(intervals, removedInterval)) {
                --removalCount;
                intervals[removedInterval[2]] = new int[]{removedInterval[0], removedInterval[1]};
                // System.out.println("Reintroduced: " + Arrays.toString(intervals[removedInterval[2]]));
            }
        }

        return removalCount;
    }

    private boolean intervalsOverlap(int[] intervalA, int[] intervalB) { //This assumes intervalA and intervalB are sorted by their start times in the parameter list
        return intervalB[0] >= intervalA[0] && intervalB[0] < intervalA[1];
    }

    private boolean canBeIntroducedSafely(int[][] intervals, int[] toIntroduce) {
        int index = toIntroduce[2];

        int[] testInterval = new int[]{toIntroduce[0], toIntroduce[1]};

        for(int left = index - 1; left >= 0; --left) {
            if(intervals[left] == null) continue;
            if(intervalsOverlap(intervals[left], testInterval)) return false;
        }

        for(int right = index + 1; right < intervals.length; ++right) {
            if(intervals[right] == null) continue;
            if(intervalsOverlap(testInterval, intervals[right])) return false;
        }

        return true;
    }

    // private int intervalLength(int[] interval) {
    //     final int len = interval[1] - interval[0];
    //     // System.out.println("Interval Len of " + Arrays.toString(interval) + ": " + len);
    //     return len;
    // }
}
