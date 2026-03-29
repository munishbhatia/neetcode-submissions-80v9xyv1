class Solution {
    public List<Integer> findClosestElements(int[] arr, int k, int x) {
        //Sliding window on the deltas
        //Sum of the deltas in the sliding window will be minimum at the expected outcome

        if(arr == null) return null;
        if(k >= arr.length) return Arrays.stream(arr).boxed().collect(Collectors.toList());

        final int[] deltas = new int[arr.length];
        for(int i=0; i<arr.length; ++i) {
            deltas[i] = Math.abs(arr[i] - x);
        }

        //Create k-sized window from start
        long windowSum = 0;
        for(int i=0; i<k; ++i) {
            windowSum += deltas[i];
        }

        //Now move window 1 element at a time and adjust window [start,end] if we see reduced sum of deltas in the window
        int left = 0;
        int right = k;
        for(; right < deltas.length; ++right) {
            long currSum = windowSum + deltas[right] - deltas[left];
            if(currSum < windowSum) {
                windowSum = currSum;
                left = right-k+1;
            }
        }

        final List<Integer> result = new ArrayList<>();
        for(int i=0; i<k; ++i) {
            result.add(arr[left+i]);
        }

        return result;
    }
}