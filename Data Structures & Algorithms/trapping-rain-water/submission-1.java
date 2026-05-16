class Solution {
    // public int trap(int[] height) {
    //     if(height == null) return 0;

    //     final int len = height.length;
    //     if(len == 0) return 0;

    //     int left = 0;
    //     int right = left + 1;
    //     int volume = 0;

    //     while (right < len) {
    //         if(height[right] >= height[left]) {
    //             left = right;
    //             right++;
    //             continue;
    //         }

    //         System.out.println(String.format("left: %d; right: %d; adding volume: %d", left, right, height[left] - height[right]));
    //         volume += height[left] - height[right++];
    //     }

    //     --right;
    //     while(height[right] < height[left] && right > left) {
    //         System.out.println(String.format("left: %d; right: %d; subtracting volume: %d", left, right, height[left] - height[right]));
    //         volume -= height[left] - height[right--];
    //     }

    //     return volume;
    // }

    public int trap(int[] heights) {
        if(heights == null) return 0;

        final int len = heights.length;
        if(len == 0) return 0;

        int volume = 0;

        final int[] greaterToLeft = new int[len];
        final int[] greaterToRight = new int[len];

        int runningMax = heights[0];
        for(int i = 0; i < len; ++i) {
            greaterToLeft[i] = Math.max(heights[i], runningMax);
            runningMax = Math.max(runningMax, greaterToLeft[i]);
        }

        runningMax = heights[len - 1];
        for(int i = len - 1; i >= 0; --i) {
            greaterToRight[i] = Math.max(heights[i], runningMax);
            runningMax = Math.max(runningMax, greaterToRight[i]);
        }

        for(int i = 0; i < len; ++i) {
            volume += Math.min(greaterToLeft[i], greaterToRight[i]) - heights[i];
        }

        return volume;
    }
}
