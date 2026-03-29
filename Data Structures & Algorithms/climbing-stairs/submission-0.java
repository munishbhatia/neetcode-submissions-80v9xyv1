class Solution {
    public int climbStairs(int n) {
        final int[] numWaysToClimb = new int[n+1]; //Indexed 0 -> n
        numWaysToClimb[0] = 1;
        numWaysToClimb[1] = 1;

        for(int i=2; i<=n; ++i) {
            numWaysToClimb[i] = numWaysToClimb[i-1] + numWaysToClimb[i-2];
        }

        return numWaysToClimb[n];
    }
}

//So this is a recursion problem where we can use the results of the previous subproblem to
//calculate the solution to the next problem. We can store the results of the previous problem to optimize the time complexity

//Intuition:
//Base Cases:
//N = 0; Output = 1;
//N = 1; Output: 1
