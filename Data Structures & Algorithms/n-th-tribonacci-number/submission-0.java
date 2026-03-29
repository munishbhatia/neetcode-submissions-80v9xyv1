class Solution {
    public int tribonacci(int n) {
        if(n == 0) return 0;
        if(n <= 2) return 1;

        final int[] previousValues = new int[]{0, 1, 1};
        final int len = previousValues.length;

        for(int i=3; i<=n; ++i) {
            int curr = IntStream.of(previousValues).sum();
            for(int j=0; j<len-1; ++j) {
                previousValues[j] = previousValues[j+1];
            }
            previousValues[len-1] = curr;
        }

        return previousValues[len-1];
    }
}