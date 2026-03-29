class Solution {
    public boolean isNStraightHand(int[] hand, int groupSize) {
        if(null == hand) return false;

        final int len = hand.length;

        if(len < groupSize) return false;

        final int[] freq = new int[1001];
        final Set<Integer> presence = new HashSet<>();

        for(int num : hand) {
            freq[num] ++;
            presence.add(num);
        }

        while(!presence.isEmpty()) {
            int smallest = -1;
            for(int i=0; i<1001; ++i) {
                if(freq[i] > 0) {
                    smallest = i;
                    break;
                }
            }

            for(int i=smallest; i<smallest+groupSize; ++i) {
                if(freq[i] == 0) return false;

                freq[i]--;
                if(freq[i] == 0) presence.remove(i);
            }
        }

        return true;
    }
}
