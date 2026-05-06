class FreqStack {
    private int counter;
    private Map<Integer, Integer> frequencyMap;
    private PriorityQueue<int[]> maxHeap;

    public FreqStack() {
        counter = 0;
        frequencyMap = new HashMap<>();
        maxHeap = new PriorityQueue<>(Comparator.comparingInt((int[] a) -> a[1]).thenComparingInt(a -> a[2]).reversed());

        //Here max heap contains int[] with 3 members each
        //arr[0] = actual value; arr[1] = frequency of the element at the time of push; arr[2] = time of push/counter
    }
    
    public void push(int val) {
        ++counter;
        frequencyMap.put(val, frequencyMap.getOrDefault(val, 0) + 1);
        maxHeap.offer(new int[]{val, frequencyMap.get(val), counter});
    }
    
    public int pop() {
        int[] top = maxHeap.poll();
        frequencyMap.put(top[0], frequencyMap.get(top[0]) - 1);
        return top[0];
    }
}

/**
 * Your FreqStack object will be instantiated and called as such:
 * FreqStack obj = new FreqStack();
 * obj.push(val);
 * int param_2 = obj.pop();
 */