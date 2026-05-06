class Solution {
    public int[] getOrder(int[][] tasks) {
        if(tasks == null || tasks.length == 0) return new int[0];

        final PriorityQueue<int[]> enqueueHeap = 
            new PriorityQueue<>(Comparator.comparing((int[] a) -> a[0]).thenComparing(a -> a[2]));
        final PriorityQueue<int[]> executionHeap =
            new PriorityQueue<>(Comparator.comparing((int[] a) -> a[1]).thenComparing(a -> a[2]));
        final List<Integer> executionSequence = new ArrayList<>(tasks.length);

        for(int i = 0; i < tasks.length; ++i) {
            enqueueHeap.offer(new int[]{tasks[i][0], tasks[i][1], i});
        }

        int currTime = 0;
        while(!enqueueHeap.isEmpty()) {
            currTime = Math.max(currTime, enqueueHeap.peek()[0]);
            while(!enqueueHeap.isEmpty() && enqueueHeap.peek()[0] <= currTime) {
                executionHeap.offer(enqueueHeap.poll());
            }

            while(!executionHeap.isEmpty()) {
                currTime += executionHeap.peek()[1];
                executionSequence.add(executionHeap.poll()[2]);

                while(!enqueueHeap.isEmpty() && enqueueHeap.peek()[0] <= currTime) {
                    executionHeap.offer(enqueueHeap.poll());
                }
            }
        }

        return executionSequence.stream().mapToInt(Integer::intValue).toArray();
    }
}