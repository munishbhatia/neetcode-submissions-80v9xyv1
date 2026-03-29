class Solution {
    public int[] dailyTemperatures(int[] temperatures) {
        if(null == temperatures || temperatures.length == 0) {
            return new int[0];
        }

        final Stack<Integer> stack = new Stack<>();
        final int len = temperatures.length;
        final int[] result = new int[len];

        for(int i=len-1; i>=0; --i) {
            while(!stack.isEmpty() && temperatures[stack.peek()] <= temperatures[i]) {
                stack.pop();
            }
            if(stack.isEmpty()) {
                result[i] = 0;
                stack.push(i); //We are storing index of the temp, not the actual temp
                continue;
            }
            result[i] = stack.peek() - i;
            stack.push(i);
        }
        return result;
    }
}

// Brute force approach - two loops - O(N^2) time complexity, O(1) space


// 1. We want to stack the temperatures such that lower temperatures occur on top of
// higher temperatures
// 2. We stores index of that temperature in the array as the stack member
// 3. When traversing the array (end to start), we keep popping all the stack elements
// as long as they're smaller than or equal to the current temperature
// 4. We store the index difference in the result array and push the element's index to
// the stack
// 5. If the stack goes empty while popping in step 3, we store the result for that
// temperature index as zero
// 6. Base condition - result[size-1] will always be 0

// Time complexity - O(N), (N) space