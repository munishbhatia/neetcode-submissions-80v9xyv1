class Solution {
    public int trap(int[] heights) {
        if(heights == null) return 0;

        final int len = heights.length;
        if(len == 0) return 0;

        int volume = 0;

        final int[] greaterToLeft = new int[len];
        final int[] greaterToRight = new int[len];

        int runningMaxLeft = heights[0];
        int runningMaxRight = heights[len - 1];
        for(int i = 0; i < len; ++i) {
            greaterToLeft[i] = Math.max(heights[i], runningMaxLeft);
            runningMaxLeft = Math.max(runningMaxLeft, greaterToLeft[i]);

            greaterToRight[len - 1 - i] = Math.max(heights[len - 1 - i], runningMaxRight);
            runningMaxRight = Math.max(runningMaxRight, greaterToRight[len - 1 - i]);
        }

        for(int i = 0; i < len; ++i) {
            volume += Math.min(greaterToLeft[i], greaterToRight[i]) - heights[i];
        }

        return volume;
    }
}
