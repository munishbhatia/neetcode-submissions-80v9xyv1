class Solution {
    public int climbStairs(int n) {
        if(n < 2) {
            return 1;
        }
        
        int numWaysToClimb1 = 1; //Solution for n-1 steps
        int numWaysToClimb2 = 1; //Solution for n-2 steps
        
        int numWaysToClimb = 0;
        for(int i=2; i<=n; ++i) {
            numWaysToClimb = numWaysToClimb1 + numWaysToClimb2;
            numWaysToClimb2 = numWaysToClimb1;
            numWaysToClimb1 = numWaysToClimb;
        }

        return numWaysToClimb;
    }
}

//So this is a recursion problem where we can use the results of the previous subproblem to
//calculate the solution to the next problem. We can store the results of the previous problem to optimize the time complexity

//Intuition:
//Base Cases:
//N = 0; Output = 1;
//N = 1; Output: 1
