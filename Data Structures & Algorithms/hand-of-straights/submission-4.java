class Solution {
    public boolean isNStraightHand(int[] hand, int groupSize) {
        if(null == hand) return false;

        final int len = hand.length;

        if(len < groupSize) return false;

        final int[] freq = new int[1001];
        final Set<Integer> presence = new HashSet<>();
        int smallest = 5000;

        for(int num : hand) {
            freq[num] ++;
            presence.add(num);
            smallest = Math.min(smallest, num);
        }

        int prevSmallest = smallest;
        while(!presence.isEmpty()) {
            // System.out.println("Smallest: " + smallest);
            // int smallest = 5000;
            int newSmallest = 5000;

            if(smallest == 5000) {
                for(int i=prevSmallest+1; i<1001; ++i) {
                    if(freq[i] > 0) {
                        smallest = i;
                        break;
                    }
                }
            }

            prevSmallest = smallest;
            final int smallestElemFreq = freq[smallest];
            for(int i=smallest; i<smallest+groupSize; ++i) {
                if(freq[i] < smallestElemFreq) return false;

                freq[i]-= smallestElemFreq;
                if(freq[i] == 0) presence.remove(i);
                if(freq[i] != 0) {
                    newSmallest = Math.min(newSmallest, i);
                    // System.out.println("New Smallest: " + newSmallest);
                }
            }

            smallest = newSmallest;
        }

        return true;
    }
}
