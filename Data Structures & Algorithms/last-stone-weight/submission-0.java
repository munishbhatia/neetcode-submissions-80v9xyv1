class Solution {
    public int lastStoneWeight(int[] stones) {
        if(stones == null || stones.length == 0) {
            return 0;
        }
        final PriorityQueue<Integer> maxHeap = new PriorityQueue<>(Comparator.reverseOrder());
        for(int stone : stones) {
            maxHeap.offer(stone);
        }

        while(maxHeap.size() > 1) {
            int stone1 = maxHeap.poll();
            int stone2 = maxHeap.poll();

            int diff = Math.abs(stone1 - stone2);
            if(diff > 0) {
                maxHeap.offer(diff);
            }
        }

        return maxHeap.size() == 1 ? maxHeap.poll() : 0;
    }
}

//Use a max heap to keep the heaviest stone at the top
//To get the two heaviest stones, pop heap twice
//Smash based on the given rules and add the new weight (if non zero) to the heap
//Stop when heap size = 1 or 0 (in case we take last two stones and they destroy each other)
