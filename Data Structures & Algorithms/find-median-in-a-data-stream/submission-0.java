class MedianFinder {
    private final PriorityQueue<Integer> minHeap;
    private final PriorityQueue<Integer> maxHeap;

    public MedianFinder() {
        minHeap = new PriorityQueue<>();
        maxHeap = new PriorityQueue<>(Comparator.reverseOrder());
    }
    
    public void addNum(int num) {
        if(minHeap.isEmpty() && maxHeap.isEmpty()) {
            maxHeap.offer(num);
            return;
        }

        if(num <= maxHeap.peek()) {
            maxHeap.offer(num);
        } else {
            minHeap.offer(num);
        }

        if(Math.abs(minHeap.size() - maxHeap.size()) > 1) {
            if(minHeap.size() > maxHeap.size()) {
                maxHeap.offer(minHeap.poll());
                return;
            }

            minHeap.offer(maxHeap.poll());
        }
    }
    
    public double findMedian() {
        //Check and throw if both heaps are empty
        
        if(minHeap.size() > maxHeap.size()) {
            return minHeap.peek();
        }

        if(maxHeap.size() > minHeap.size()) {
            return maxHeap.peek();
        }

        return (minHeap.peek() + maxHeap.peek())/2.0;
    }
}
