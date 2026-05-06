class FreqStack {
    private Map<Integer, Integer> frequencyMap;
    private Stack<int[]> mainStack;
    private Stack<int[]> backupStack;

    public FreqStack() {
        frequencyMap = new HashMap<>();
        mainStack = new Stack<>();
        backupStack = new Stack<>();
    }
    
    public void push(int val) {
        frequencyMap.put(val, frequencyMap.getOrDefault(val, 0) + 1);
        int freq = frequencyMap.get(val);

        while(!mainStack.isEmpty() && mainStack.peek()[1] > freq) {
            backupStack.push(mainStack.pop());
        }

        mainStack.push(new int[]{val, freq});

        while(!backupStack.isEmpty()) {
            mainStack.push(backupStack.pop());
        }
    }
    
    public int pop() {
        int[] top = mainStack.pop();
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