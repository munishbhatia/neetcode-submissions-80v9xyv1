class StockSpanner {
    private final Stack<int[]> spanner;

    public StockSpanner() {
        spanner = new Stack<>();
    }
    
    public int next(int price) {
        int span = 1;
        while(!spanner.isEmpty() && spanner.peek()[0] <= price) {
            span += spanner.pop()[1];
        }
        spanner.push(new int[]{price, span});
        return span;
    }
}

/**
 * Your StockSpanner object will be instantiated and called as such:
 * StockSpanner obj = new StockSpanner();
 * int param_1 = obj.next(price);
 */