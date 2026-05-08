class Solution {
    public int findMaximizedCapital(int k, int w, int[] profits, int[] capital) {
        final int len = profits.length;
        int currCap = w;
        
        final PriorityQueue<int[]> capitalMinHeap = new PriorityQueue<>((a, b) -> Integer.compare(a[1], b[1])); //index, capital, profit
        final PriorityQueue<int[]> profitMaxHeap = new PriorityQueue<>(Comparator.comparingInt((int[] a) -> a[2]).reversed());

        IntStream.range(0, len).forEach(i -> {
            profitMaxHeap.offer(new int[]{i, capital[i], profits[i]});
        });

        for(int i = 0; i < k; ++i) {
            while(!capitalMinHeap.isEmpty() && capitalMinHeap.peek()[1] <= currCap) {
                // System.out.println("Popping " + Arrays.toString(capitalMinHeap.peek()) + " from capitalMinHeap. CurrCap: " + currCap);
                profitMaxHeap.offer(capitalMinHeap.poll());
            }
            
            while(!profitMaxHeap.isEmpty() && profitMaxHeap.peek()[1] > currCap) {
                // System.out.println("Popping " + Arrays.toString(profitMaxHeap.peek()) + " from profitMaxHeap. CurrCap: " + currCap);
                capitalMinHeap.offer(profitMaxHeap.poll());
            }

            if(profitMaxHeap.isEmpty()) {
                // System.out.println("ProfitMaxHeap is empty");
                break;
            }

            // System.out.println("Taking Profit " + profitMaxHeap.peek()[2] + " from " + Arrays.toString(profitMaxHeap.peek()));
            currCap += profitMaxHeap.poll()[2];
        }

        return currCap > w ? currCap : w;
    }
}