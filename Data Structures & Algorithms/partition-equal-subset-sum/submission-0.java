class Solution {
    public boolean canPartition(int[] nums) {
        if(nums == null) return false;

        final int len = nums.length;
        if(len == 1) return false;

        final int sum = Arrays.stream(nums).sum();

        if(sum%2 != 0) return false;

        Arrays.sort(nums);

        // int runningSum = 0;

        // for(int i=0; i<len; ++i) {
        //     runningSum += nums[i];
        //     if (runningSum == sum/2) return true;
        //     if (runningSum > sum/2) return false;
        // }

        // final boolean[][] dp = new int[len+1][sum/2+1];

        // Arrays.fill(dp[0], true);
        // for(int i=0; i < dp.length; ++i) {
        //     dp[i][0] = true;
        // }

        // for(int i=0; i<=sum/2; ++i) {

        // }

        return partitionHelper(nums, 0, sum/2);

        
        // return false;
    }

    private boolean partitionHelper(int[] arr, int index, int reqSum) {
        // System.out.println("Checking for " + reqSum + " at index " + index);
        if(index >= arr.length) return false;

        if(arr[index] > reqSum) return false; //All numbers after this are larger than this so all arr[j] where j > index will be larger than reqSum

        if(arr[index] == reqSum) return true;

                //arr[index] is part of the solution                    arr[index] is not part of the solution
        return partitionHelper(arr, index+1, reqSum-arr[index]) || partitionHelper(arr, index+1, reqSum);
    }
}
