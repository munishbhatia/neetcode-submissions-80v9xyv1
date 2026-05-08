class Solution {
    public int findMaximizedCapital(int k, int w, int[] profits, int[] capital) {
        final int len = profits.length;
        final int[] indicesByCapital = IntStream.range(0, len)
                                        .boxed()
                                        .sorted(Comparator.comparingInt(i -> capital[i]))
                                        .mapToInt(Integer::intValue)
                                        .toArray(); //Sort by capital value; original index is retained in this structure

        final PriorityQueue<Integer> profitMaxHeap = new PriorityQueue<>(Comparator.reverseOrder()); //index, capital, profit

        int currCap = w;
        int capIdx = 0;
        for(int i = 0; i < k; ++i) {
            while(capIdx < len && capital[indicesByCapital[capIdx]] <= currCap) {
                profitMaxHeap.offer(profits[indicesByCapital[capIdx++]]);
            }

            if(profitMaxHeap.isEmpty()) {
                break;
            }

            currCap += profitMaxHeap.poll();
        }

        return currCap;
    }
}