class Solution {
    public int findKthLargest(int[] nums, int k) {
        if(nums == null) return 0;

        final PriorityQueue<Integer> minHeap = new PriorityQueue<>(k);

        for(int num : nums) {
            if(minHeap.size() < k) {
                minHeap.offer(num);
                continue;
            }

            if(num > minHeap.peek()) {
                minHeap.poll();
                minHeap.offer(num);
            }
        }

        return minHeap.peek();
    }
}
