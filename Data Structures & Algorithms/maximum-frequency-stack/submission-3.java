class FreqStack {
    private Map<Integer, Integer> frequencyMap;
    private TreeMap<Integer, List<Integer>> treeMapStack;

    public FreqStack() {
        frequencyMap = new HashMap<>();
        treeMapStack = new TreeMap<>();
    }
    
    public void push(int val) {
        frequencyMap.put(val, frequencyMap.getOrDefault(val, 0) + 1);
        int freq = frequencyMap.get(val);

        treeMapStack.computeIfAbsent(freq, v -> new LinkedList<>()).addLast(val);
    }
    
    public int pop() {
        int top = treeMapStack.get(treeMapStack.lastKey()).removeLast();
        if(treeMapStack.get(treeMapStack.lastKey()) == null ||
            treeMapStack.get(treeMapStack.lastKey()).isEmpty()) {
                treeMapStack.remove(treeMapStack.lastKey());
        }
        
        frequencyMap.put(top, frequencyMap.get(top) - 1);
        return top;
    }
}

/**
 * Your FreqStack object will be instantiated and called as such:
 * FreqStack obj = new FreqStack();
 * obj.push(val);
 * int param_2 = obj.pop();
 */