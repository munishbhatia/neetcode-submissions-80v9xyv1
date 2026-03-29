class Solution {
    public int maxArea(int[] heights) {
        int left = 0;
        int right = heights.length - 1;

        int max = 0;
        while(left < right) {
            int currArea = Math.min(heights[left], heights[right]) * (right - left);
            max = Math.max(max, currArea);
            if(heights[left] < heights[right]) {
                ++left;
            } else {
                --right;
            }
        }
        return max;
    }
}
