class Solution {
    public boolean carPooling(int[][] trips, int capacity) {
        if(capacity <= 0) return false;

        if(trips == null || trips.length == 0) return true;

        final int len = trips.length;
        Arrays.sort(trips, Comparator.comparing((int[] a) -> a[1])); //Ordered by the trip start position

        final PriorityQueue<int[]> pq = new PriorityQueue<>(Comparator.comparing((int[] a) -> a[2])); //Ordered by trip finish position ascending

        int start = trips[0][1];
        int currCapacity = capacity;

        int i = 0;
        while(i < len) {
            while(!pq.isEmpty() && pq.peek()[2] <= trips[i][1]) {
                currCapacity += pq.poll()[0];
            }

            start = trips[i][1];
            while(i < len && trips[i][1] == start) {
                if(currCapacity < trips[i][0]) return false;
                currCapacity -= trips[i][0];
                pq.offer(trips[i]);
                ++i;
            }
        }

        return true;
    }
}