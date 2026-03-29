class Solution {
    public int findDuplicate(int[] nums) {
        final int len = nums.length;

        //Cyclical sort
        for(int i=0; i<len; ++i) {
            while(i != nums[i]) {
                int temp = nums[nums[i]];
                if(temp == nums[i]) {
                    return temp;
                }
                nums[nums[i]] = nums[i];
                nums[i] = temp;
            }
        }

        return 0;
    }
}


//O(N) time, O(N) space - using hashset to keep track of observed numbers
//Also can do using an index array to map seen numbers to indices
//O(NlgN) using sorting then O(N) traversal
//O(N^2) using multiple traversals trying to find the next expected number from 1 to n