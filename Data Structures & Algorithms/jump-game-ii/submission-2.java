class Solution {
    public int jump(int[] nums) {
        final int len = nums.length;
        final PriorityQueue<int[]> minSteps = new PriorityQueue<>((a,b) -> a[1] - b[1]);
        final PriorityQueue<int[]> temp = new PriorityQueue<>((a,b) -> a[1] - b[1]);
        minSteps.offer(new int[]{len-1, 0});

        int lastInsert = 0;

        int goal = len-1;

        for(int i=len-2; i>=0; --i) {
            int minJumps = 500000;
            if(nums[i] + i >= goal) {
                while(!minSteps.isEmpty() && nums[i] + i < minSteps.peek()[0]) {
                    temp.offer(minSteps.poll());
                }

                // if(!minSteps.isEmpty()) {} //This will ALWAYS be the case because of this if condition check: if(nums[i] + i >= goal)
                lastInsert = minSteps.peek()[1] + 1;
                minSteps.offer(new int[]{i, minSteps.peek()[1] + 1});

                if(!temp.isEmpty()) {
                    minSteps.addAll(temp);
                }

                goal = i;
            }
        }

        return lastInsert;
    }
}
