class Solution {
    public int[][] kClosest(int[][] points, int k) {
        if(null == points || points.length == 0) {
            return new int[1][1];
        }

        final PriorityQueue<int[]> maxHeap = new PriorityQueue<>((a,b) -> Double.compare(
            getEuclideanDistanceFromOrigin(b),
            getEuclideanDistanceFromOrigin(a)
        ));
        
        for(int[] point : points) {
            maxHeap.offer(point);
            if(maxHeap.size() > k) {
                maxHeap.poll();
            }
        }

        final int[][] result = new int[k][2];
        int i = 0;
        while(!maxHeap.isEmpty()) {
            result[i++] = maxHeap.poll();
        }
        return result;
    }

    private double getEuclideanDistanceFromOrigin(int[] point) {
        return Math.sqrt(point[0]*point[0] + point[1]*point[1]);
    }
}