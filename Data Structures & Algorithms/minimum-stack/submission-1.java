class MinStack {
    private Stack<Integer> minStack;
    private Stack<Integer> auxStack;

    public MinStack() {
        minStack = new Stack<>();
        auxStack = new Stack<>();
    }
    
    public void push(int val) {
        minStack.push(val);
        if(auxStack.isEmpty() || auxStack.peek() >= val) {
            auxStack.push(val);
        }
    }
    
    public void pop() {
        // System.out.println("Aux Peek: " + auxStack.peek());
        // System.out.println("Main Peek: " + minStack.peek());
        if(auxStack.peek().compareTo(minStack.peek()) == 0) {
            auxStack.pop();
        }
        minStack.pop();
    }
    
    public int top() {
        return minStack.peek();
    }
    
    public int getMin() {
        return auxStack.peek();
    }
}