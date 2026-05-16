class Solution {
    public int trap(int[] heights) {
        if(heights == null) return 0;

        final int len = heights.length;
        if(len == 0) return 0;

        int volume = 0;

        final int[] greaterToRight = new int[len];
        int runningMax = heights[len - 1];
        
        for(int i = len - 1; i >= 0; --i) {
            greaterToRight[i] = Math.max(heights[i], runningMax);
            runningMax = Math.max(runningMax, greaterToRight[i]);
        }

        runningMax = heights[0];
        for(int i = 0; i < len; ++i) {
            runningMax = Math.max(runningMax, heights[i]);
            volume += Math.min(runningMax, greaterToRight[i]) - heights[i];
        }

        return volume;
    }
}
