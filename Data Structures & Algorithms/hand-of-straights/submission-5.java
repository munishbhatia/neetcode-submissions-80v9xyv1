class Solution {
    public boolean isNStraightHand(int[] hand, int groupSize) {
        if(null == hand) return false;

        final int len = hand.length;

        if(len < groupSize) return false;

        final int[] freq = new int[1001];
        final PriorityQueue<Integer> minQ = new PriorityQueue<>();

        for(int num : hand) {
            freq[num] ++;
            if(freq[num] == 1) {
                minQ.offer(num);
            }
        }

        while(!minQ.isEmpty()) {
            final int smallest = minQ.peek();
            final int smallestElemFreq = freq[smallest];
            
            for(int i=smallest; i<smallest+groupSize; ++i) {
                if(freq[i] < smallestElemFreq) return false;

                freq[i]-= smallestElemFreq;
                if(freq[i] == 0) {
                    if(i != minQ.peek()) return false; //This would have happened in a future iteration anyway since we have a smaller number present but a larger number missing
                    minQ.poll(); //This would be the number at the head of the PQ
                }
            }
        }

        return true;
    }
}
