class Solution {
    public int maxProduct(int[] nums) {
        if(null == nums) return 0;

        final int len = nums.length;
        if(len == 1) return nums[0];

        //Left to right
        int runningProd = nums[0];
        int maxLeftToRight = nums[0];

        for(int i=1; i<len; ++i) {
            runningProd *= nums[i];
            maxLeftToRight = Math.max(maxLeftToRight, runningProd);
            if(runningProd == 0) runningProd = 1;
        }

        //Right to Left
        runningProd = nums[len-1];
        int maxRightToLeft = nums[len-1];

        for(int i=len-2; i>=0; --i) {
            runningProd *= nums[i];
            maxRightToLeft = Math.max(maxRightToLeft, runningProd);
            if(runningProd == 0) runningProd = 1;
        }

        return Math.max(maxLeftToRight, maxRightToLeft);
    }
}


//Brute-Force:
//Take every subarray: O(N^2)
//Calculate product of that subarray
//Find max over all products and return

//Insights:
//We can keep calculating incremental product by multiplying previous result with the current index value
//Likewise during shrinking of the window, we can use division operator

// [1, 2, -3, 4] --> [1, 2, 2, 4] || RP: [1, 2, 0, 4]
// [1, 2, -3, -4] --> [1, 2, 2, 24] || RP: [1, 2, 0, 24]
// [-2, -1] --> [-2, 2] || RP: [-2, 2]
// [-2, 1, 2, -8] --> [-2, 1, 2, 32] || RP: [-2, -2, -4, 32]
// [-4, 1, 2, -1, 4, -8] --> [-4, 1, 2, 8, 32, 64] || RP: [0, 1, 2, 8, 32, -256] || MP: [-4, -4, -8, 0, 4, ]

//First negative is probably easy to handle - just copy the running product from the previous index
//How do we handle a subsequent negative? And a third negative?
//For a third negative, excluding the lesser of the previous negatives will maximize the outcome

//For an array ending at index i; i.e. arr[0..i] --> either arr[i] is part of the solution or not
//If it is not, how do we mark that? May be with a 0 so future products go to zero
//If it is part of the solution, we mark with the running product
//When we see a zero, we can't include that solution in the next subproblem because that will break the continuity of the subarray in the solution
//How do we handle a subsequent negative then?

//Single -ve num will never be a part of the solution

//Item itself; item * prev running product; 

// [1, 2, -3, 4] --> LR: [1, 2, -6, -24] || RL: [-24, -24, -12, 4]
// [1, 2, -3, -4] --> LR: [1, 2, -6, 24] || RL: [24, 24, 12, -4]
// [-2, -1] --> LR: [-2, 2] || RL: [2, -1]
// [-2, 1, 2, -8] --> LR: [-2, -2, -4, 32] || RL: [32, -16, -16, -8]
// [-4, 1, 2, -1, 4, -8] --> LR: [-4, -4, -8, 8, 32, -256] || RL: [-256, 64, 64, 32, -32, -8]